package com.system.Logs;

import com.system.config.Config;

import java.io.File;
import java.nio.file.Files;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public Logger logger;
    private static Log log;

    private FileHandler fh;

    private Log() {
        File file = new File(Config.logPath);

        try {
            if (!Files.exists(Config.logsFile)) {
                Files.createDirectories(Config.logsFile);
                if (!file.exists()) {
                    if (!file.createNewFile()) {
                        throw new Exception("File Not Created");
                    }
                }
            }
            fh = new FileHandler(Config.logPath, true);
            logger = Logger.getLogger("test");
            logger.addHandler(fh);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fh.setFormatter(simpleFormatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Log getInstance() {
        if (log == null) {
            log = new Log();
        }
        return log;
    }
}
