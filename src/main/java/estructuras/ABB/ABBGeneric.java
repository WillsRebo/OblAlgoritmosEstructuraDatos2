package estructuras.ABB;

import estructuras.Lista.*;

public class ABBGeneric<T extends Comparable<T>> {

    private NodoAbb<T> raiz;

    public ABBGeneric() {
        this.raiz = null;
    }

    public void insertar(T nodo) {

        if (this.raiz == null) {
            this.raiz = new NodoAbb<T>(nodo);
        } else {
            this.insertarRec(this.raiz, nodo);
        }
    }

    private void insertarRec(NodoAbb<T> raiz, T nodo) {
        if (nodo.compareTo(raiz.getElement()) < 0) {
            if (raiz.getIzq() == null) {
                raiz.setIzq(new NodoAbb<T>(nodo));
            } else {
                insertarRec(raiz.getIzq(), nodo);
            }
        } else {
            if (raiz.getDer() == null) {
                raiz.setDer(new NodoAbb<T>(nodo));
            } else {
                insertarRec(raiz.getDer(), nodo);
            }
        }
    }

    public boolean exist(T obj) {
        return existRec(this.raiz, obj);
    }

    private boolean existRec(NodoAbb<T> nodo, T obj) {

        if (nodo == null) {
            return false;
        } else if (nodo.getElement().compareTo(obj) == 0) {
            return true;
        } else {
            if (nodo.getElement().compareTo(obj) > 0) {
                return existRec(nodo.getIzq(), obj);
            } else {
                return existRec(nodo.getDer(), obj);
            }
        }
    }
    public T getElemento(T dato) {
        return getElementoRec(this.raiz, dato);
    }

    private T getElementoRec(NodoAbb<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        } else if (nodo.getElement().compareTo(dato) == 0) {
            return nodo.getElement();
        } else {
            if (nodo.getElement().compareTo(dato) > 0) {
                return getElementoRec(nodo.getIzq(), dato);
            } else {
                return getElementoRec(nodo.getDer(), dato);
            }
        }
    }

    public int cantidadRecorridas(T dato) {
        if (dato != null) {
            return cantidadRecorridasRec(this.raiz, dato, 0);
        }
        return -1;
    }

    private int cantidadRecorridasRec(NodoAbb<T> nodo, T dato, int cant) {
        if (nodo == null) {
            return 0;
        } else if (nodo.getElement().compareTo(dato) == 0) {
            return cant;
        } else {
            if (nodo.getElement().compareTo(dato) > 0) {
                return cantidadRecorridasRec(nodo.getIzq(), dato, cant + 1);
            } else {
                return cantidadRecorridasRec(nodo.getDer(), dato, cant + 1);
            }
        }
    }

    public String listarAsc() {
        String retorno = listarAsc(raiz);
        return retorno;
    }
    private String listarAsc(NodoAbb<T> nodo) {
        if (nodo != null) {
            String abbizq =listarAsc(nodo.getIzq());
            String abbder = listarAsc(nodo.getDer());

            String nodoString = nodo.getElement().toString();
            if(!abbder.isEmpty() && !abbizq.isEmpty()){
                return abbizq + "|" + nodoString + "|" + abbder;
            }else if(!abbizq.isEmpty()){
                return  abbizq + "|" + nodoString;
            }else if(!abbder.isEmpty()){
                return nodoString + "|" +abbder ;
            }else {
                return nodoString;
            }
        }else {
            return "";
        }
    }

    public String listarDes() {
        ListaImp<T> list = new ListaImp<T>();
        listarDes(raiz, list);
        String retorno = "";
        for (T dato : list) {
            retorno += dato.toString() + "|";
        }
        if (!retorno.isEmpty()) {
            retorno = retorno.substring(0, retorno.length() - 1);
        }
        return retorno;

    }

    private void listarDes(NodoAbb<T> nodo, ListaImp<T> list) {
        if (nodo != null) {
            listarDes(nodo.getIzq(), list);
            list.insertar(nodo.getElement());
            listarDes(nodo.getDer(), list);
        }
    }

    private class NodoAbb<T> {
        private T elemento;
        private NodoAbb<T> der;
        private NodoAbb<T> izq;

        public NodoAbb(T obj) {
            this.elemento = obj;
        }

        public NodoAbb(T obj, NodoAbb<T> der, NodoAbb<T> izq) {
            this.elemento = obj;
            this.der = der;
            this.izq = izq;
        }

        public T getElement() {
            return elemento;
        }

        public void setElement(T obj) {
            this.elemento = obj;
        }

        public NodoAbb<T> getDer() {
            return der;
        }

        public void setDer(NodoAbb<T> der) {
            this.der = der;
        }

        public NodoAbb<T> getIzq() {
            return izq;
        }

        public void setIzq(NodoAbb<T> izq) {
            this.izq = izq;
        }
    }


}
