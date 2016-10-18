package sk.juko.managers.impl;

import sk.juko.dao.DbInfo;
import sk.juko.managers.Driver;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * book
 * 11.10.2016
 */
public class SQLiteDriver implements Driver {
    public static String SQLITE_DB_CONNECTION_PREFIX = "jdbc:sqlite:";
    public static String SQLITE_JDBC = "org.sqlite.JDBC";
    private static SQLiteDriver instance;

    public static SQLiteDriver getInstance(){
        if (null == instance){
            instance = new SQLiteDriver();
        }
        return instance;
    }

    @Override
    public Connection getConnection(int loginTime, DbInfo info) throws SQLException, ClassNotFoundException {
        return getConnection(info);
    }

    @Override
    public Connection getConnection(DbInfo info) throws SQLException, ClassNotFoundException {
        Class.forName(SQLITE_JDBC);
//        Connection connection = java.sql.DriverManager.getConnection(SQLITE_DB_CONNECTION_PREFIX +info.getDbUrl());
//        connection.setAutoCommit(false);
//        return connection;
        return java.sql.DriverManager.getConnection(SQLITE_DB_CONNECTION_PREFIX +info.getUrl());
    }
}
