package sk.juko.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * book
 * 11.10.2016
 */
public class DbUtils {

    /**
     * Close any auto closeable object to avoid memory leaking
     * @param closeables closeable objects
     * @throws Exception error
     */
    public static void close(AutoCloseable... closeables) throws Exception {
        for (AutoCloseable closeable : closeables) {
            if (null != closeable) {
                closeable.close();
            }
        }
    }
}
