package estructuras.Lista;

import dominio.Viajero;

public interface ILista<T> extends Iterable<T> {

    public void insertar(T dato);

    public int largo();

    public boolean esVacia();
}
