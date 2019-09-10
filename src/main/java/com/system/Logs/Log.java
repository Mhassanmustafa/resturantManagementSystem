package com.system.Logs;

import com.system.config.Config;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public Logger logger;
    FileHandler fh;

    public Log() throws Exception{
        File file = new File(Config.logPath);

        if(!Files.exists(Config.logsFile)){
            Files.createDirectories(Config.logsFile);
        }else{
            if(!file.exists()){
                file.createNewFile();
            }else{
                fh = new FileHandler(Config.logPath,true);
                logger = Logger.getLogger("test");
                logger.addHandler(fh);
                SimpleFormatter simpleFormatter = new SimpleFormatter();
                fh.setFormatter(simpleFormatter);
            }
        }
    }


}
