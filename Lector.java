import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Lector implements serializable {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("/Users/Dazuam.ZubietAmbriz/Google Drive/Cowmatic/Version 2 JList"));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            v.nombre.(scanner.next());
        }
        scanner.close();
    }

}