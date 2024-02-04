package name.ex_snowball;

import name.ex_snowball.client.render.entity.ModEntityRenders;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class ExSnowballClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityRenders.RegEntityRender();
    }
}
