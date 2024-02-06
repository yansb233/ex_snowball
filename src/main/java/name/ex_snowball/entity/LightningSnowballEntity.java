package name.ex_snowball.entity;

import name.ex_snowball.registry.ModEntities;
import name.ex_snowball.registry.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LightningSnowballEntity extends ThrownItemEntity {
    public LightningSnowballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightningSnowballEntity(double d, double e, double f, World world) {
        super(ModEntities.LIGHTNING_SNOWBALL_ENTITY_TYPE, d, e, f, world);
    }

    public LightningSnowballEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.LIGHTNING_SNOWBALL_ENTITY_TYPE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LIGHTNING_SNOWBALL;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        LightningEntity lightningEntity;
        BlockPos blockPos = entity.getBlockPos();
        Entity entity2 = this.getOwner();
        entity.damage(this.getDamageSources().magic(),9);
        if(this.getWorld() instanceof ServerWorld && (lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld())) != null){
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity2 : null);
            this.getWorld().spawnEntity(lightningEntity);
        }

        // && this.world.isSkyVisible(blockPos = entity.getBlockPos())
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockPos blockPos = blockHitResult.getBlockPos();
        LightningEntity lightningEntity;
        Entity entity2 = this.getOwner();
        if(this.getWorld() instanceof ServerWorld && (lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld())) != null){
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            lightningEntity.setChanneler(entity2 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity2 : null);
            this.getWorld().spawnEntity(lightningEntity);
        }
    }
}
