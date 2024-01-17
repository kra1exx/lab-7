import java.io.Serializable;
public class Avto implements Serializable {
    String model;
    int nomer;
    int god;
    int probeg;
    int stoimost;
    @Override
    public String toString() {
        return "Avto{" +
                "model='" + model + '\'' +
                ", reg_nomer='" + nomer + '\'' +
                ", god_vipuska=" + god +
                ", probeg=" + probeg +
                ", stoimost=" + stoimost +
                '}';
    }
}