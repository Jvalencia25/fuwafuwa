import java.util.Comparator;

/**
 * String Comparator: Clase que permite comparar 2 atributos string de la clase Person
 * 
 * @author: Natalia Arboleda y Jeronimo Valencia
 * 
 */
public class StringComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {

        int res=p1.getApellidos().compareTo(p2.getApellidos());
        
        if (res==0){
            return p1.getNombres().compareTo(p2.getNombres());
        }
        return p1.getApellidos().compareTo(p2.getApellidos());
    }
}