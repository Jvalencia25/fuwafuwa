import java.util.Iterator;

import javax.sound.sampled.SourceDataLine;

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
            if (a.get(i).compareTo(b.get(j)) <= 0) { //si a.item es menor, agrega primero a b.item
                resultado.add(a.get(i));
                i++;
            } else {
                resultado.add(b.get(j));
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

        resultado.invert();
        return resultado;
    }

    public Lista[] split(){

        Lista[] result = new Lista[2];
        int longitud = this.size();
  
        int mitad = longitud / 2;
        if (longitud % 2 != 0) {
            mitad++; 
        }

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

        return result;
    }

    int xd=0;
    public Lista<Comparable> mergeSort(Lista<Comparable> a){
        if(a.size()<2){
            return a;
        }
        Lista<Comparable>[] tmp = a.split();
        Lista<Comparable> left = tmp[0];
        Lista<Comparable> right = tmp[1];

        left=mergeSort(left);
        right=mergeSort(right);

        return fusionar(left, right);
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
        System.out.println("lista:");
        for(Nodo x=this.first;x!=null;x=x.sig){
            System.out.println(x.item);
        }
        System.out.println("");
    }
    
    public static void main(String[] args) throws Exception {
        
        /* 
        //prueba unitaria fusion
        Lista a = new Lista<>();
        a.add(50);
        a.add(9);
        //a.add(3);
        //a.add(1);
        a.imprimir();


        System.out.println("");

        Lista b = new Lista<>();
        b.add(14);
        b.add(2);
        //b.add(4);
        //b.add(2);


        b.imprimir();

        System.out.println("\nResultado");

        Lista resultado = fusionar(a, b);

        //assert(resultado.get(0).equals(1));
        //assert(resultado.get(5).equals(6));
        //assert(resultado.size()==6);
        resultado.imprimir();

        
        //prueba unitaria split
        System.out.println("Split");
        Lista [] arregloListas= resultado.split();

        System.out.println(" ");
        arregloListas[0].imprimir();
        System.out.println(" ");
        arregloListas[1].imprimir(); 

        */

         
        //prueba unitaria mergeSort
        Lista probando = new Lista<>();
        probando.add(9);
        probando.add(50);
        probando.add(14);
        probando.add(2);
        probando.add(100);
        probando.add(16);
        probando.add(1);
        probando.add(21);
        probando.add(4);
        probando.add(7);
        probando.add(400);
        probando=probando.mergeSort(probando);
        
        probando.imprimir();
        
    }
}