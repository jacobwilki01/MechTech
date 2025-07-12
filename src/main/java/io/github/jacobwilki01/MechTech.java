package io.github.jacobwilki01;

import io.github.jacobwilki01.creative_tab.CreativeTabRegistry;
import io.github.jacobwilki01.material.MaterialRegistry;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.function.Supplier;

@Mod(MechTech.MODID)
public class MechTech {
    public static final String MODID = "mechtech";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MechTech(IEventBus modEventBus, ModContainer modContainer) {
        MaterialRegistry.ITEM_REGISTRY.register(modEventBus);
        MaterialRegistry.BLOCK_REGISTRY.register(modEventBus);
        CreativeTabRegistry.CREATIVE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
