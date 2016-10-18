package sk.juko.managers;

import sk.juko.dao.DbInfo;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * book
 * 11.10.2016
 */
public interface Driver {
    Connection getConnection(int loginTime, DbInfo dbInfo) throws SQLException, ClassNotFoundException;

    Connection getConnection(DbInfo dbInfo) throws SQLException, ClassNotFoundException;
}
