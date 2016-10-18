package sk.juko.main;

/**
 * Created by px on 10/18/16.
 */
public class ClassY extends ClassX{
    private String stry;

    public ClassY(String str, String str2, String stry) {
        super(str, str2);
        this.stry = stry;
    }

    public String getStry() {
        return stry;
    }

    public void setStry(String stry) {
        this.stry = stry;
    }

    @Override
    public String toString() {
        return "ClassY{" +
                "stry='" + stry + '\'' +
                super.toString() +
                '}';
    }
}
