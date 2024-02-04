package name.ex_snowball.registry;

import name.ex_snowball.ExSnowball;
import name.ex_snowball.entity.HEATSnowballlEntity;
import name.ex_snowball.entity.HeavySnowballEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<ThrownItemEntity> HEAVY_SNOWBALL_ENTITY_TYPE =
            registerModEntities("heavy_snowball",HeavySnowballEntity::new,0.25f,20);
    public static final  EntityType<ThrownItemEntity> HEAT_SNOWBALL_ENTITY_TYPE =
            registerModEntities("heat_snowball", HEATSnowballlEntity::new,0.25f,30);
    public static EntityType<ThrownItemEntity> registerModEntities(String path, EntityType.EntityFactory<ThrownItemEntity> factory,Float size,Integer range){
        EntityType<ThrownItemEntity> type = Registry.register(Registries.ENTITY_TYPE,new Identifier(ExSnowball.MOD_ID, path),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC,factory)
                        .dimensions(EntityDimensions.fixed(size, size))
                        .trackRangeBlocks(range).trackedUpdateRate(10)
                        .build());
        return type;
    }
    public static void regModEntity(){
        ExSnowball.LOGGER.debug("Reg.Entity"+ExSnowball.MOD_ID);
    }
}
