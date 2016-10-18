package sk.juko.managers;

import sk.juko.Type;
import sk.juko.managers.impl.SQLiteDriver;

/**
 * book
 * 13.10.2016
 */
public class DriverFactory {

    public static Driver getDriver(Type type){
        switch (type){
            case SQLITE: return SQLiteDriver.getInstance();
            default: return null;
        }
    }
}
