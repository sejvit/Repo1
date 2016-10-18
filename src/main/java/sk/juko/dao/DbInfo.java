package sk.juko.dao;

import sk.juko.Type;

import java.util.Arrays;

/**
 * book
 * 11.10.2016
 */
public class DbInfo {
    private String url;
    private String user;
    private String password;
    private String[] tableNames;
    private Type type;

    public DbInfo(String url, String... tableNames){
        this(url, null, null, tableNames, Type.SQLITE);
    }

    public DbInfo(String url, String user, String password, String[] tableNames, String type) {
        this(url, user, password, tableNames, Type.getType(type));
    }

    public DbInfo(String url, String user, String password, String[] tableNames, Type type) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.tableNames = tableNames;
        this.type = type;
    }

    public DbInfo(String url, String user, String password, String tableName, Type type) {
        this(url, user, password, new String[]{tableName}, type);
    }

    public DbInfo(String url, String user, String password, String tableName, String type) {
        this(url, user, password, new String[]{tableName}, Type.getType(type));
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String dbUrl) {
        url = dbUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String dbUser) {
        user = dbUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String dbPassword) {
        password = dbPassword;
    }

    public String getTableName() {
        return tableNames[0];
    }

    public void setTableName(String dbTableName) {
        tableNames[0] = dbTableName;
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] aDbTableNames) {
        this.tableNames = aDbTableNames;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type dbType) {
        this.type = dbType;
    }

    @Override
    public String toString() {
        return "DbInfo{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", tableNames=" + Arrays.toString(tableNames) +
                ", type=" + type +
                '}';
    }
}
