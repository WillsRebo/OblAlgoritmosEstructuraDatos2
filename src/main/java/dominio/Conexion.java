package dominio;

import interfaz.TipoConexion;

public class Conexion{

    private String codigoCiudadOrigen;
    private String codigoCiudadDestino;
    private int identificadorConexion;
    private double costo;
    private double tiempo;
    private TipoConexion tipo;


    public Conexion(String codigoCiudadOrigen, String codigoCiudadDestino, int identificadorConexion, double costo, double tiempo, TipoConexion tipo) {
        this.codigoCiudadOrigen = codigoCiudadOrigen;
        this.codigoCiudadDestino = codigoCiudadDestino;
        this.identificadorConexion = identificadorConexion;
        this.costo = costo;
        this.tiempo = tiempo;
        this.tipo = tipo;
    }

    public String getCodigoCiudadOrigen() {
        return codigoCiudadOrigen;
    }

    public void setCodigoCiudadOrigen(String codigoCiudadOrigen) {
        this.codigoCiudadOrigen = codigoCiudadOrigen;
    }

    public String getCodigoCiudadDestino() {
        return codigoCiudadDestino;
    }

    public void setCodigoCiudadDestino(String codigoCiudadDestino) {
        this.codigoCiudadDestino = codigoCiudadDestino;
    }

    public int getIdentificadorConexion() {
        return identificadorConexion;
    }

    public void setIdentificadorConexion(int identificadorConexion) {
        this.identificadorConexion = identificadorConexion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public TipoConexion getTipo() {
        return tipo;
    }

    public void setTipo(TipoConexion tipo) {
        this.tipo = tipo;
    }

}
