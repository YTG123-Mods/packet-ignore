package io.github.ytg1234.packetignore;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PacketIgnore implements ModInitializer {
    public static Logger logger = LogManager.getLogger("PacketIgnore");

    @Override
    public void onInitialize() {
        logger.info("PacketIgnore initializing");
    }
}

