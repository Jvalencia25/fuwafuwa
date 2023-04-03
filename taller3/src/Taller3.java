public class Taller3 {

    public static void main(String[] args) {
        
        int n=10;
        GeneradorADTs a=new GeneradorADTs();
        Lista<Person> personas = a.generar(n);

        personas.imprimir();

        personas.mergeSort(personas);
    }

    
}
