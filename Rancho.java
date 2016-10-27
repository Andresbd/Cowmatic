import java.io.Serializable;

public class Rancho implements Serializable {

    public String nombre;
    public int vacas;

    public String toString() {
        return "El rancho: "+ nombre +" tiene: "+ vacas + " vacas.";
    }

}