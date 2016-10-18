package sk.juko;

/**
 * book
 * 11.10.2016
 */
public enum Type {
    NONE, SQLITE;

    public static Type getType(String type){
        if (null == type){
            return NONE;
        }else {
            for (Type t : Type.values()){
                if(t.name().equalsIgnoreCase(type)){
                    return t;
                }
            }
        }
        return NONE;
    }
}
