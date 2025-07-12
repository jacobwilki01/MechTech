package io.github.jacobwilki01;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = MechTech.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MechTech.MODID, value = Dist.CLIENT)
public class MechTechClient {
    public MechTechClient(ModContainer container) {

    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        MechTech.LOGGER.info("HELLO FROM CLIENT SETUP");
    }
}
