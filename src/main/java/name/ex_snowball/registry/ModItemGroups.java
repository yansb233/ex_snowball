package name.ex_snowball.registry;

import name.ex_snowball.ExSnowball;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final RegistryKey<ItemGroup> EX_SNOWBALL_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(ExSnowball.MOD_ID, "ex_snowballs"));

    public static void regModItemGroups(){
        ExSnowball.LOGGER.debug("Reg"+ExSnowball.MOD_ID);
        Registry.register(Registries.ITEM_GROUP, EX_SNOWBALL_KEY, FabricItemGroup.builder()
                .displayName(Text.translatable("itemgroup.ex_snowball.snowballs"))
                .icon(() -> new ItemStack(ModItems.HEAT_SNOWBALL)).build());

    }
}
