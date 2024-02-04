package name.ex_snowball.item;

import name.ex_snowball.ExSnowball;
import name.ex_snowball.entity.HEATSnowballlEntity;
import name.ex_snowball.entity.HeavySnowballEntity;
import name.ex_snowball.registry.ModEntities;
import name.ex_snowball.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BaseSnowballItem extends Item {

    public EntityType<ThrownItemEntity> types;
    public BaseSnowballItem(Settings settings){super(settings);}
    public BaseSnowballItem(Settings settings, EntityType<ThrownItemEntity> type){
        super(settings);
        types=type;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
        if (!world.isClient) {
            ThrownItemEntity snowballEntity;
            if (types == ModEntities.HEAVY_SNOWBALL_ENTITY_TYPE){
                snowballEntity = new HeavySnowballEntity(user,world);
            }else if (types == ModEntities.HEAT_SNOWBALL_ENTITY_TYPE){
                snowballEntity = new HEATSnowballlEntity(user,world);
            }
            else {
                snowballEntity = new HeavySnowballEntity(user,world);
            }
            snowballEntity.setItem(itemStack);
            snowballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 1.0f);
            world.spawnEntity(snowballEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }

}
