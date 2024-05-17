package dominio;

import interfaz.TipoViajero;

import java.util.regex.Pattern;
public class Viajero implements Comparable<Viajero> {
    private String cedula;
    private int edad;
    private String nombre;
    private TipoViajero tipoViajero;
    public Viajero (String cedula){
        this.cedula=cedula;
        this.edad = 0;
        this.nombre="";
        this.tipoViajero = null;
    }

    public Viajero(String cedula, String nombre, int edad, TipoViajero tipoViajero) {
        this.cedula = cedula;
        this.edad = edad;
        this.nombre=nombre;
        this.tipoViajero = tipoViajero;
    }


    public boolean isCedulaOK(String cedula) {
       if(cedula==null || cedula.isEmpty()){return false;}
        String regex="^([1-9][.])?[0-9]{3}[.][0-9]{3}-[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(cedula).matches();
    }



    public int parseoIntCI(String cedula){
        String nuevo=cedula.replace("-","");
        nuevo=nuevo.replace(".","");
       // nuevo = nuevo.substring(0, 7);
        return Integer.parseInt(nuevo);
    }
    public boolean esVacio() {
        return this.cedula == null || this.cedula.isEmpty()  || this.nombre == null  || this.nombre.isEmpty()  || this.edad == 0 || this.tipoViajero == null;
    }

    @Override
    public String toString() {
        return  cedula + ";"+ nombre +";"  +edad +";" + tipoViajero.getTexto().toUpperCase();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoViajero getTipoViajero() {
        return tipoViajero;
    }

    public void setTipoViajero(TipoViajero tipoViajero) {
        this.tipoViajero = tipoViajero;
    }

    @Override
    public int compareTo(Viajero o) {
        return  parseoIntCI(this.cedula) - parseoIntCI(o.getCedula());
    }
}
