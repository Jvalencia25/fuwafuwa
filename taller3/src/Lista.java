import java.util.ArrayList;
import java.util.Iterator;

/**
 * ListaSimple : Implementacion basica de la lista simplemente enlazada
 * Se proponen como ejercicios operaciones adicionales de la 
 * la lista simple.
 * 
 * @author: Jorge Londoño
 * 
 */
@SuppressWarnings("unchecked")
public class Lista<T> implements Iterable<T> {

    /**
     * Nodo representa un nodo de la lista simplemente enlazada
     */
    public class Nodo {
        T item;
        Nodo sig;
    }

    private Nodo first=null;
    private int n=0;

    /**
     * Agregar un item a la cabeza de la lista
     * @param item
     */
    public void add(T item) {
        Nodo x = new Nodo();
        x.item = item;
        x.sig = first;
        first = x;
        n++;
    }

    /**
     * Remover el primer nodo de la lista
     * @return item contenido en el nodo eliminado
     * @throws Exception
     */
    public T removeHead() throws Exception {
        if (first == null)
            throw new Exception("Lista vacia");
        T i = first.item;
        first = first.sig;
        n--;
        return i;
    }
    

    /**
     * True si la lista esta vacia
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * @return Longitud de la lista
     */
    public int size() {
        return n;
    }

    /**
     * Obtener un iterador para la lista
     */
    @Override
    public Iterator<T> iterator() {
        return new IteradorLista();
    }

    /**
     * Implementacion del iterador para la lista simple
     */
    private class IteradorLista implements Iterator<T> {

        private Nodo pos = first;

        @Override
        public boolean hasNext() {
            return pos!=null;
        }

        @Override
        public T next() {
            T i = pos.item;
            pos = pos.sig;
            return i;
        }

    }

    public static Lista<Comparable> fusionar(Lista<Comparable> a, Lista<Comparable> b ){
        Lista resultado = new Lista<>();

        int i = 0; // iterador a
        int j = 0; // iterador b

        while (i < a.size() && j < b.size()) {
            Comparable elementoA = a.get(i);
            Comparable elementoB = b.get(j);
    
            if (elementoA.compareTo(elementoB) > 0) { //si a.item es menor, agrega primero a b.item
                resultado.add(elementoA);
                i++;
            } else {
                resultado.add(elementoB);
                j++;
            }
        }

        //agrega elementos faltantes
        while (i < a.size()) {
            resultado.add(a.get(i));
            i++;
        }
        while (j < b.size()) {
            resultado.add(b.get(j));
            j++;
        }

        return resultado;
    }

    public Lista[] split(){

        Lista[] result = new Lista[2];
        int longitud = this.size();
  
        int mitad = longitud / 2;
        result[0] = new Lista();
        result[1] = new Lista();
  
        int i = 0;
  
        for(Nodo x=this.first;x!=null;x=x.sig){
            if (i < mitad) {
                result[0].add(x.item);
            } else {
                result[1].add(x.item);
            }
            i++;
        }

        
        /*
        while (nodoActual != null) {
            if (indiceActual < mitad) {
                result[0].add(nodoActual.item);
            } else {
                result[1].add(nodoActual.item);
            }
            nodoActual = nodoActual.sig;
            
        }
  
        
        */
        result[0].invert();
        result[1].invert();
        return result;
    }

   public static void mergeSort(ArrayList<Integer> list) {
    if (list.size() > 1) {
      int mid = list.size() / 2;
      ArrayList<Integer> left = new ArrayList<Integer>(list.subList(0, mid));
      ArrayList<Integer> right = new ArrayList<Integer>(list.subList(mid, list.size()));
      mergeSort(left);
      mergeSort(right);
      merge(list, left, right);
    }
  }

  private static void merge(ArrayList<Integer> list, ArrayList<Integer> left, ArrayList<Integer> right) {
    int i = 0, j = 0, k = 0;
    while (i < left.size() && j < right.size()) {
      if (left.get(i) < right.get(j)) {
        list.set(k++, left.get(i++));
      } else {
        list.set(k++, right.get(j++));
      }
    }
    while (i < left.size()) {
      list.set(k++, left.get(i++));
    }
    while (j < right.size()) {
      list.set(k++, right.get(j++));
    }
  }

  public static ArrayList<Integer> toIntArray(Lista a){
    ArrayList aux = new ArrayList<>();
        for(int i=0;i<a.size();i++){
            aux.add(a.get(i));
        }
    return aux;
  }


    /*
     *   Ejercicios: Implementar los siguientes métodos
     */

    /** Remueve el ultimo elemento de la lista */
    public T removeLast() { return null; }

    /** Agregar un elemento al final de la lista */
    public void addLast() {  }

    /** Obtener el item en la i-ésima posición de la lista */
    public T get(int indice) {
        Nodo nodoActual = first;
        int indiceActual = 0;
  
        while (nodoActual != null && indiceActual < indice) {
            nodoActual = nodoActual.sig;
            indiceActual++;
        }
        return nodoActual.item;
    }

    

    /** Insertar un item en la i-ésima posición de la lista */
    public void insert(int i, T dato) { }

    /** remueve el item de la i-ésima posición de la lista */
    public T remove(int i) { return null; }

    /** Obtener una nueva ListaSimple con todos los items en orden inverso */
    public Lista<T> invert() { 
        Nodo current = first;
        Nodo previous = null;
        Nodo next = null;
        
        while (current != null) {
            next = current.sig;
            current.sig=previous;
            previous = current;
            current = next;
        }
        
        first = previous;
        return this;
        
     }
    

    public void imprimir(){
        for(Nodo x=this.first;x!=null;x=x.sig){
            System.out.println(x.item);
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        /** remueve el item de la i-ésima posición de la lista
        //prueba unitaria fusion
        Lista a = new Lista<>();
        a.add(2);
        a.add(10);
        a.add(15);
        //a.add(8);


        Lista b = new Lista<>();
        b.add(3);
        b.add(11);
        b.add(14);
        //b.add(10);



        Lista resultado = fusionar(a, b);

        //assert(resultado.get(0).equals(1));
        //assert(resultado.get(5).equals(6));
        //assert(resultado.size()==6);
        resultado.imprimir();

        
        //prueba unitaria split

        Lista [] arregloListas= resultado.split();

        System.out.println(" ");
        arregloListas[0].imprimir();
        System.out.println(" ");
        arregloListas[1].imprimir(); 

        */

        //prueba unitaria mergeSort
        Lista actual = new Lista<>();
        ArrayList<Integer> expected = new ArrayList<Integer>();

        expected.add(2);
        expected.add(3);
        expected.add(8);
        expected.add(10);
        expected.add(10);
        expected.add(11);
        expected.add(14);
        expected.add(15);

        actual.add(2);
        actual.add(10);
        actual.add(15);
        actual.add(8);
        actual.add(3);
        actual.add(11);
        actual.add(14);
        actual.add(10);

        ArrayList actual_int = toIntArray(actual);
        
        System.out.println("\nAntes del mergesort");
        for(int i=0;i<actual_int.size();i++){
            System.out.print(" | " + actual_int.get(i));
        }

        mergeSort(actual_int);

        System.out.println("\n\nDespués del mergesort");
        for(int i=0;i<actual_int.size();i++){
            System.out.print(" | " + actual_int.get(i));
        }

        assert(actual_int.equals(expected));
    
    }
}