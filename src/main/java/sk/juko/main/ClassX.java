package sk.juko.main;

/**
 * Created by px on 10/18/16.
 */
public class ClassX{

    private String str;
    private String str2;

    public ClassX(String str, String str2) {
        this.str = str;
        this.str2 = str2;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    @Override
    public String toString() {
        return "ClassX{" +
                "str='" + str + '\'' +
                ", str2='" + str2 + '\'' +
                '}';
    }
}
