package sk.juko.dao.impl;

import sk.juko.dao.Dao;

/**
 * book
 * 13.10.2016
 */
public class SQLiteDao extends Dao {

    private static SQLiteDao INSTANCE;

    public SQLiteDao() {
        super();
    }

    public static SQLiteDao getInstance(){
        if (null == INSTANCE){
            INSTANCE = new SQLiteDao();
        }
        return INSTANCE;
    }
}
