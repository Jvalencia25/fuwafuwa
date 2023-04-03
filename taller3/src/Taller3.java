import java.util.Collections;
import java.util.List;
import edu.princeton.cs.algs4.*;

/**
 * Taller 3
 * 
 * @author: Natalia Arboleda y Jeronimo Valencia
 * 
 */

public class Taller3 {

    public static void main(String[] args) {
        
        int n=256000;
        GeneradorADTs a=new GeneradorADTs();
        List<Person> personas = a.generar(n);

        /* 
        System.out.println("N generados:");
        for (Person persona : personas) {
            System.out.println(persona.getApellidos() + ", " + persona.getNombres());
        }

        System.out.println("Lista ordenada:\n");
        */

        Stopwatch sw=new Stopwatch();
        Collections.sort(personas, new StringComparator()); //usar StringComparator.java

        /* 
        for (Person persona : personas) {
            System.out.println(persona.getApellidos() + ", " + persona.getNombres());
        }
        */

        double tiempo=sw.elapsedTime();
        System.out.println("\nTiempo: "+tiempo+" s");
    }
}



