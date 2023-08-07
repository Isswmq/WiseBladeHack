package com.example.examplemod;

import com.example.examplemod.module.impl.packet.ElytraSwapPacket;
import com.example.examplemod.module.impl.packet.UpdateInventoryPacket;
import com.example.examplemod.module.key.Key;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static org.apache.http.params.CoreProtocolPNames.PROTOCOL_VERSION;

@Mod("examplemod")
public class ExampleMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Key());
        CHANNEL.registerMessage(
                1921, // ID пакета
                ElytraSwapPacket.class,
                ElytraSwapPacket::encode,
                ElytraSwapPacket::decode,
                ElytraSwapPacket::handle
        );
    }

    private void setup(final FMLCommonSetupEvent event) {
        Client.startup();
    }

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("examplemod", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
}
