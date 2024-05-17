package estructuras.Lista;

import dominio.Viajero;

public class NodoListaViajero {

    private Viajero viajero;
    private NodoListaViajero sig;

    public NodoListaViajero() {}
    public NodoListaViajero(Viajero viajero) {
        this.viajero = viajero;
    }

    public Viajero getViajero() {
        return viajero;
    }
    public NodoListaViajero getSig() {
        return sig;
    }
    public void setSig(NodoListaViajero sig) {
        this.sig = sig;
    }


}
