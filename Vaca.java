import java.io.Serializable;

public class Vaca implements Serializable{

    public String madre, padre, fierro, nombre;
    public int partos, siniga, arete;
    public double  peso, promedio;
    public boolean parto, palpo, lista;

    public String toString() {
        return nombre+ ": en su última pesada obtuvo: " + peso + "kg, con un promedio de: "+promedio;
    }

}
