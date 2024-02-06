package name.ex_snowball.entity;

import name.ex_snowball.registry.ModEntities;
import name.ex_snowball.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HEATSnowballlEntity extends ThrownItemEntity {
    public HEATSnowballlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public HEATSnowballlEntity(double d, double e, double f, World world) {
        super(ModEntities.HEAT_SNOWBALL_ENTITY_TYPE, d, e, f, world);
    }

    public HEATSnowballlEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.HEAT_SNOWBALL_ENTITY_TYPE, livingEntity, world);
    }
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? ParticleTypes.FLAME : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    private Double random_XYZ(){
        double x = getWorld().random.nextDouble();
        if (getWorld().random.nextBoolean()){
            x = -x;
        }
        return x*0.25;
    }

    @Override
    public void handleStatus(byte status) {
        super.handleStatus(status);
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();
            for (int i = 0; i < 25; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), random_XYZ(), random_XYZ(), random_XYZ());
            }
        }
    }
    Vec3d vec;

    @Override
    protected Item getDefaultItem() {
        return ModItems.HEAT_SNOWBALL;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 9);
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
        vec = this.getVelocity();
        double x = this.getX()+vec.x*3;
        double y = this.getY()+vec.y*3;
        double z = this.getZ()+vec.z*3;
        getWorld().createExplosion(this,x,y,z,10f, World.ExplosionSourceType.TNT);
    }
}
