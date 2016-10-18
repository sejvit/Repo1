package sk.juko.dao;

import sk.juko.common.ClazzUtils;
import sk.juko.common.DbUtils;
import sk.juko.managers.DriverFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * book
 * 11.10.2016
 */
public class Dao implements DaoConstants {

    public void createDatabase(DbInfo info) {
        try {
            File file = new File(info.getUrl());
            if(file.exists()){
                return;
            }
            Connection connection = getConnection(info);
            Statement statement = connection.createStatement();
            DbUtils.close(statement, connection);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while creating DB: " + info.getUrl());
        }
    }

    /**
     * Create table with column names of class member variables and type of TEXT
     * @param info about DB
     * @param tableName name of table to be created
     * @param clazz model class for table
     * @param <T> any
     * @return 0/1 table has been created
     */
    public <T> int createTable(final DbInfo info, final String tableName, final Class<T> clazz){
        return createTable(info, tableName, ClazzUtils.getVariables(clazz));
    }

    /**
     * Create table with column names of class member variables and type of TEXT
     * @param info info about DB
     * @param tableName name of table to be created
     * @param columnNames name of the columns for table
     * @param <T> any
     * @return 0/1 table has been created
     */
    public <T> int createTable(final DbInfo info, final String tableName, final String[] columnNames){
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" ");
        query.append("(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," );
        for(int i = 0; i < columnNames.length; i++) {
            query.append(columnNames[i]).append(" ").append("TEXT");
            if(i < columnNames.length -1){
                query.append(",");
            }else {
                query.append(")");
            }
        }
        return executeUpdate(info, query.toString())[0];
    }

    /**
     * Like createDbFile table, insert
     * @param info about DB
     * @param queries array of queries
     * @return 0 - no statement executed, otherwise number of rows or 1 execute was successfully
     */
    public int[] executeUpdate(final DbInfo info, final String... queries) {
        int[] result = new int[queries.length];
        try {
            Connection connection = getConnection(info);
            Statement statement = connection.createStatement();
            for (int i = 0; i < queries.length; i++) {
                result[i] = statement.executeUpdate(queries[i]);
            }
            DbUtils.close(statement, connection);
        } catch (Exception e) {
            System.err.println(String.format("Error while updating %s sqls %s", info.getUrl(), Arrays.asList(queries)));
            e.printStackTrace();
        }
        return result;
    }

    public void copyTableContent(final DbInfo info, final String srcTable, final String destinationTable) {
        String query = "INSERT INTO " + destinationTable + " SELECT * FROM " + srcTable + " ORDER BY ID ASC";
        executeUpdate(info, query);
    }

    public <T> void insertInBlocks(final DbInfo info, final String table, final List<String[]> values, final Class<T> clazz){
        insertInBlocks(info, table, values, ClazzUtils.getVariables(clazz), 72);
    }

    public void insertInBlocks(final DbInfo info, final String table, final List<String[]> values, final String[] columns){
        insertInBlocks(info, table, values, columns,72);
    }

    public void insertInBlocks(final DbInfo info, final String table, final List<String[]> values, final String[] columns, final int blockSize){
        int times = values.size()/blockSize;
        int divisionReminder = values.size() % blockSize;

        for(int i = 0; i < times; i++){
            insert(info, table, values.subList(i*blockSize, (i*blockSize) + blockSize), columns);
        }
        if(0 != divisionReminder) {
            insert(info, table, values.subList(blockSize * times, values.size()), columns);
        }
    }

    public <T> void insert(final DbInfo info, final String table, final Object obj) throws IllegalAccessException {
        this.insert(info, table, ClazzUtils.getValues(obj), obj.getClass());
    }


    public <T> void insert(final DbInfo info, final String table, final List<String[]> values, final Class<T> clazz){
        this.insert(info, table, values, ClazzUtils.getVariables(clazz));
    }

    public <T> void insert(final DbInfo info, final String table, final String[] values, final Class<T> clazz){
        List<String[]> listValues = new ArrayList<>();
        listValues.add(values);
        this.insert(info, table, listValues, ClazzUtils.getVariables(clazz));
    }

    public void insert(final DbInfo info, final String table, final List<String[]> values, final String[] columns){
        String query = "INSERT INTO "+table+" ("+ java.util.Arrays.toString(columns).replace("[","").replace("]","")+") VALUES ";
        StringBuilder valuesSB = new StringBuilder();
        String strChar = "\"";
        for(String[] value : values){
            valuesSB.append("(");
            valuesSB.append(strChar);
            valuesSB.append(java.util.Arrays.toString(value).replace("[","").replace("]","").replaceAll("\\s{0,},\\s{0,}","\",\"").replaceAll("/","/"));
            valuesSB.append(strChar);
            valuesSB.append(")");
            valuesSB.append(",");
        }
        executeUpdate(info, query + valuesSB.toString().replaceAll("\\s{0,},\\s{0,}$",""));
    }

    protected Connection getConnection(final DbInfo info) throws ClassNotFoundException, SQLException{
        return DriverFactory.getDriver(info.getType()).getConnection(info);
    }

    public <T> List<T> selectAll(final DbInfo info, final String tableName, String orderByColumnName, Class<T> clazz){
        String query = "SELECT * FROM " + tableName + " ORDER BY " + orderByColumnName + " ASC;";
        List<T> values = new ArrayList<>();
        try{
            Connection connection = getConnection(info);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String[] variables = ClazzUtils.getVariables(clazz);
            Object[] rsValues = new Object[variables.length];

            while (rs.next()){
                for(int i = 0; i < variables.length; i++){
                    rsValues[i] = rs.getObject(variables[i]);
                }
                values.add((T) ClazzUtils.newClass(clazz, rsValues));
            }
            DbUtils.close(rs, statement, connection);
        } catch (Exception e) {
            System.err.println(String.format("Error while executing query for %s sql: %s", info.getUrl(), Arrays.asList(query)));
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return values;
    }
}
