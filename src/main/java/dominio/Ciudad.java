package dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ciudad implements Comparable<Ciudad> {

    private String codigo;

    private String nombre;

    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Ciudad() {

    }

    public boolean Validar() {
        if (codigo == null || codigo.isEmpty()) return false;
        String patron = "^(?=.*[A-Z0-9])[A-Z0-9]{5,}$";
        Pattern pattern = Pattern.compile(patron);
        return pattern.matcher(codigo).matches();
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(codigo, ciudad.codigo) && Objects.equals(nombre, ciudad.nombre);
    }


    @Override
    public int compareTo(Ciudad o) {
        return this.codigo.compareTo(o.getCodigo());
    }
}
