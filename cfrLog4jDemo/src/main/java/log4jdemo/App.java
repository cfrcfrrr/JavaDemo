package log4jdemo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Hello world!
 */
public class App {
    private static Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        while (true) {
            LOGGER.debug("debug log to custom file");
            LOGGER.info("info log to custom file");
            LOGGER.warn("warn log to custom file");
            LOGGER.error("error log to custom file");
            MyClass.logToDefault();
        }
    }
}
