package estructuras.Lista;

import dominio.Viajero;

import java.util.Iterator;

public class ListaImp<T> implements ILista<T> {

    protected NodoLista<T> inicio;
    protected int largo;

    public ListaImp() {
        this.inicio = null;
        this.largo = 0;
    }


    @Override
    public void insertar(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }

    @Override
    public String toString() {
        NodoLista<T> aux = inicio;
        String ret= "";
        while (aux!=null){
            ret += toString();
            aux=aux.getSig();
        }
        return ret;
    }


    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean esVacia() {
        return largo == 0;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.getDato();
                aux = aux.getSig();
                return dato;
            }

            @Override
            public void remove(){
            }

        };
    }

    public class NodoLista<T>{

        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }


    }


}


