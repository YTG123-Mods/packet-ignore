package io.github.ytg1234.template.testmod;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestMod implements ModInitializer {
    public static Logger logger = LogManager.getLogger("TestMod");

    @Override
    public void onInitialize() {
        logger.info("Test mod onInitialize");
    }
}

