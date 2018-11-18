package log4jdemo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MyClass {
    private static Logger LOGGER = LogManager.getLogger(MyClass.class);
    public static void logToDefault() {
        LOGGER.debug("debug log to default");
        LOGGER.info("info log to default");
        LOGGER.warn("warn log to default");
        LOGGER.error("error log to default");
    }
}
