package sk.juko.dao;

import sk.juko.Type;
import sk.juko.dao.impl.SQLiteDao;

/**
 * book
 * 13.10.2016
 */
public class DaoFactory {

    public static Dao getDao(Type type){
        switch (type){
            case SQLITE: return new SQLiteDao();
            default: return new Dao();
        }
    }
}
