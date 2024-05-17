package estructuras.Grafo;

import dominio.Conexion;
import estructuras.Lista.ListaImp;

public class Arista {

    private boolean existe;
    private ListaImp<Conexion> conexiones;

    public Arista() {
        this.existe = false;
        this.conexiones = new ListaImp<>();
    }

    public boolean isExiste() {
        return existe;
    }

    public Conexion getConexionPorCodigo(String or, String des){
        for (Conexion c:conexiones){
            if(c.getCodigoCiudadOrigen().equals(or) && c.getCodigoCiudadDestino().equals(des)){
                return  c;
            }
        }
        return null;
    }
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public ListaImp<Conexion> getConexiones() {
        return conexiones;
    }

    public void agregarConexion(Conexion conexion) {
        conexiones.insertar(conexion);
    }

    public boolean existeConexion(int conexionId){
        for(Conexion c:conexiones){
            if(conexionId==c.getIdentificadorConexion()){
                return true;
            }
        }
        return false;
    }
    public Conexion getElemento(int conexionId){
        for(Conexion c:conexiones){
            if(c.getIdentificadorConexion()==conexionId){
                return c;
            }
        }
        return null;
    }

    public Conexion obtenerConexionDeMenorTiempo(){
        double menor= Double.MAX_VALUE;
        Conexion ret = null;
        for(Conexion c:conexiones){
            if(c.getTiempo()<menor){
                menor=c.getTiempo();
                ret = c;
            }
        }

        return ret;
    }

}
