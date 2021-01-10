package io.github.ytg1234.template;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Template implements ModInitializer {
    public static Logger logger = LogManager.getLogger("Template");

    @Override
    public void onInitialize() {
        logger.info("Template");
    }
}

