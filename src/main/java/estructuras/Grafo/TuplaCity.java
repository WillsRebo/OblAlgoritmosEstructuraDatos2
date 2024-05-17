package estructuras.Grafo;

import dominio.Ciudad;

public class TuplaCity {

    private Ciudad ciudad;

    private int nivel;

    public TuplaCity(Ciudad ciudad, int nivel) {
        this.ciudad = ciudad;
        this.nivel = nivel;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
