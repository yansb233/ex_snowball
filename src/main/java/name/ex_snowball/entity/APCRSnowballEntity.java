package name.ex_snowball.entity;

import name.ex_snowball.registry.ModEntities;
import name.ex_snowball.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class APCRSnowballEntity extends ThrownItemEntity {
    public APCRSnowballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public APCRSnowballEntity(double d, double e, double f, World world) {
        super(ModEntities.APCR_SNOWBALL_ENTITY_TYPE, d, e, f, world);
    }

    public APCRSnowballEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.APCR_SNOWBALL_ENTITY_TYPE, livingEntity, world);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.APCR_SNOWBALL;
    }
    private int canHit = 2;
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this,this.getOwner()),15);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient){
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState state = this.getWorld().getBlockState(pos);
            Block block = state.getBlock();
            if (block != Blocks.OBSIDIAN && block != Blocks.BEDROCK){
                if (block == Blocks.IRON_BLOCK || block == Blocks.GOLD_BLOCK || block == Blocks.DIAMOND_BLOCK){
                    this.getWorld().breakBlock(pos, false);
                    getWorld().syncWorldEvent(WorldEvents.ANVIL_LANDS ,pos, 0);
                    this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
                    this.discard();
                }else {
                    this.getWorld().breakBlock(pos, false);
                }

            } else {
                this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
                this.discard();
                getWorld().syncWorldEvent(WorldEvents.ANVIL_LANDS ,pos, 0);
            }
        }



    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);;
        if (!this.getWorld().isClient) {
            if (canHit > 0) {
                this.canHit -= 1;
            } else {
                this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
                this.discard();
            }


        }

    }
}
