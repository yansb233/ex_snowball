package name.ex_snowball.client.render.entity;

import name.ex_snowball.ExSnowball;
import name.ex_snowball.registry.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

@Environment(EnvType.CLIENT)
public class ModEntityRenders {
    public static void RegEntityRender(){
        ExSnowball.LOGGER.debug("Reg.Entity");
        EntityRendererRegistry.register(ModEntities.HEAVY_SNOWBALL_ENTITY_TYPE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.HEAT_SNOWBALL_ENTITY_TYPE,FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.APCR_SNOWBALL_ENTITY_TYPE,FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LIGHTNING_SNOWBALL_ENTITY_TYPE,FlyingItemEntityRenderer::new);
    }
}
