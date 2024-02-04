package name.ex_snowball.registry;

import name.ex_snowball.ExSnowball;
import name.ex_snowball.item.BaseSnowballItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {

    //注册区域
    public static final Item HEAVY_SNOWBALL = registerItem("heavy_snowball", new BaseSnowballItem(new FabricItemSettings().maxCount(64),ModEntities.HEAVY_SNOWBALL_ENTITY_TYPE),ModItemGroups.EX_SNOWBALL_KEY,ItemGroups.COMBAT);
    public static final Item APCR_SNOWBALL = registerItem("apcr_snowball", new BaseSnowballItem(new FabricItemSettings().maxCount(64),ModEntities.HEAVY_SNOWBALL_ENTITY_TYPE),ModItemGroups.EX_SNOWBALL_KEY,ItemGroups.COMBAT);
    public static final Item HEAT_SNOWBALL = registerItem("heat_snowball", new BaseSnowballItem(new FabricItemSettings().maxCount(64),ModEntities.HEAT_SNOWBALL_ENTITY_TYPE),ModItemGroups.EX_SNOWBALL_KEY,ItemGroups.COMBAT);
    public static final Item LIGHTNING_SNOWBALL = registerItem("lightning_snowball",new BaseSnowballItem(new FabricItemSettings().maxCount(64),ModEntities.HEAVY_SNOWBALL_ENTITY_TYPE),ModItemGroups.EX_SNOWBALL_KEY,ItemGroups.COMBAT);
    //注册区域
    public static Item registerItem(String name, Item item, RegistryKey<ItemGroup>... keys){
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(ExSnowball.MOD_ID, name), item);
        for (RegistryKey<ItemGroup> key: keys){
            ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
                entries.add(item);
            });
        }
        return registeredItem;
    }

    public static void registerModItems(){
        ExSnowball.LOGGER.debug("Reg.Item"+ExSnowball.MOD_ID);
    }
}
