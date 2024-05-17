package estructuras.Grafo;


import dominio.Ciudad;
import dominio.Conexion;
import estructuras.ABB.ABBGeneric;
import estructuras.Cola.ColaGeneric;
import estructuras.Lista.ListaImp;
import interfaz.TipoConexion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrafCC {

    private int cantidad;
    private int tope;
    private Ciudad[] ciudades;
    private Arista[][] matAdy;


    public GrafCC(int unTope, boolean esDirigido) {
        this.cantidad = 0;
        this.tope = unTope;
        this.ciudades = new Ciudad[tope];
        this.matAdy = new Arista[tope][tope];
        if (esDirigido) {
            for (int i = 0; i < tope; i++) {
                for (int j = 0; j < tope; j++) {
                    matAdy[i][j] = new Arista();
                }
            }
        } else {
            for (int i = 0; i < tope; i++) {
                for (int j = i; j < tope; j++) {
                    Arista conAux = new Arista();
                    matAdy[i][j] = conAux;
                    matAdy[j][i] = conAux;
                }
            }
        }
    }

    public boolean esLleno() {
        return cantidad == tope;
    }

    public boolean esVacio() {
        return cantidad == 0;
    }

    // PRE: !esLleno()
    private int obtenerPosLibre() {
        for (int i = 0; i < tope; i++) {
            if (ciudades[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(Ciudad ciudad) {
        for (int i = 0; i < tope; i++) {
            if (ciudad.equals(ciudades[i])) {
                return i;
            }
        }
        return -1;
    }

    //auxiliar necesaria para registrar Conexión
    public int obtenerPosPorCodCiudad(String codCiudad) {
        if (codCiudad != null) {
            for (int i = 0; i < tope; i++) {
                if (ciudades[i] != null) {
                    if (codCiudad.equals(ciudades[i].getCodigo())) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public boolean existConexion(String idOr, String idDes, int idConexion) {
        int posOr = obtenerPosPorCodCiudad(idOr);
        int posDes = obtenerPosPorCodCiudad(idDes);
        return matAdy[posOr][posDes].existeConexion(idConexion) && existeArista(idOr, idDes);
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existeArista(String origen, String destino) {
        int posOr = obtenerPosPorCodCiudad(origen);
        int posDes = obtenerPosPorCodCiudad(destino);
        if (posDes == -1 || posOr == -1) {
            return false;
        }
        return matAdy[posOr][posDes].isExiste();
    }

    // PRE: !esLleno && !existeVertice
    public void agregarVertice(Ciudad ciudad) {
        int pos = obtenerPosLibre();
        ciudades[pos] = ciudad;
        cantidad++;
    }

    // PRE: existeVertice
    public void borrarCiudad(Ciudad ciudad) {
        int pos = obtenerPos(ciudad);
        ciudades[pos] = null;
        for (int k = 0; k < tope; k++) {
            matAdy[pos][k].setExiste(false);
            matAdy[k][pos].setExiste(false);
        }
        cantidad--;
    }

    public Ciudad getCity(String codCiudad) {
        //if(codCiudad!=null) {
        int pos = obtenerPosPorCodCiudad(codCiudad);
        if (pos != -1) {
            return ciudades[pos];
        }//else{
        //      return null;
        //   }
        // }
        return null;
    }

    public boolean existeCiudad(Ciudad ciudad) {
        int pos = obtenerPos(ciudad);
        return pos != -1;
    }

    public boolean existCodCity(String codigo) {
        int pos = obtenerPosPorCodCiudad(codigo);
        return pos != -1;
    }


    //Funcionalidad 8 Registrar Conexión
    // existeVertice(origen) && existeVertice(destino) && !existeArista
    public void registrarConexion(String codigoCOri, String codigoCDes, int idConexion, double costo, double tiempo, TipoConexion tipo) {
        int posOrigen = obtenerPosPorCodCiudad(codigoCOri);
        int posDestino = obtenerPosPorCodCiudad(codigoCDes);
        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].getConexiones().insertar(new Conexion(codigoCOri, codigoCDes, idConexion, costo, tiempo, tipo));

    }

    public void actualizarConexion(String codigoCiudadOrigen, String codigoCiudadDestino, int identificadorConexion, double costo, double tiempo, TipoConexion tipo) {
        int posOrigen = obtenerPosPorCodCiudad(codigoCiudadOrigen);
        int posDestino = obtenerPosPorCodCiudad(codigoCiudadDestino);
        matAdy[posOrigen][posDestino].setExiste(true);
        if (matAdy[posOrigen][posDestino].existeConexion(identificadorConexion)) {
            Conexion c = matAdy[posOrigen][posDestino].getElemento(identificadorConexion);
            c.setCodigoCiudadDestino(codigoCiudadDestino);
            c.setCodigoCiudadOrigen(codigoCiudadOrigen);
            c.setCosto(costo);
            c.setTiempo(tiempo);
            c.setTipo(tipo);
        }
    }

    public boolean ValidarCodigoDeCiudad(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return false;
        }
        String patron = "^(?=.*[A-Z0-9])[A-Z0-9]{5,}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(codigo);
        return matcher.matches();
    }

    public String listadoCiudadesCantTransbordos(String codigoCiudad, int cantidad, ABBGeneric<Ciudad> abbCiudad) {
        ColaGeneric<TuplaCity> colaCiudad = new ColaGeneric<>();
        int init = obtenerPosPorCodCiudad(codigoCiudad);
        Ciudad c = ciudades[init];
        boolean[] visitados = new boolean[tope];
        colaCiudad.encolar(new TuplaCity(c, 0));
        visitados[init] = true;
        int cont = 0;
        while (!colaCiudad.esVacia() && cont <= cantidad) {
            TuplaCity tupla = colaCiudad.desencolar();
            Ciudad cLoop = tupla.getCiudad();
            if (!abbCiudad.exist(cLoop)) {
                abbCiudad.insertar(cLoop);
            }
            int pos = obtenerPosPorCodCiudad(cLoop.getCodigo());
            int nivel = tupla.getNivel();
            for (int j = 0; j < tope; j++) {
                if (matAdy[pos][j].isExiste() && !visitados[j]) {
                    Ciudad jCity = ciudades[j];
                    cont = nivel + 1;
                    visitados[j] = true;
                    colaCiudad.encolar(new TuplaCity(jCity, nivel + 1));
                    if (!abbCiudad.exist(jCity) && cont <= cantidad) {
                        abbCiudad.insertar(jCity);
                    }
                }
            }
        }
        return abbCiudad.listarAsc();
    }


    public double dijkstra2(String or, String des) {
        int posOr = obtenerPosPorCodCiudad(or);
        int posDes = obtenerPosPorCodCiudad(des);

        boolean[] visitados = new boolean[tope];
        Ciudad[] anteriores = new Ciudad[tope];
        double[] costos = new double[tope];

        for (int i = 0; i < tope; i++) {
            costos[i] = Double.MAX_VALUE;
            anteriores[i] = null;
        }
        costos[posOr] = 0;

        for (int i = 0; i < cantidad; i++) {
            int pos = getNextNoVisitadoLowCost(costos, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < tope; j++) {
                    if (matAdy[pos][j].isExiste() && !visitados[j]) {
                        Conexion c = matAdy[pos][j].obtenerConexionDeMenorTiempo();
                        double costonuevo = costos[pos] + c.getTiempo();
                        if (costonuevo < costos[j]) {
                            costos[j] = costonuevo;
                            anteriores[j] = ciudades[pos];
                        }
                    }
                }
            }
        }
        return costos[posDes];
    }


    public String mejorCaminoRecorrido(String or, String des) {
        int posOr = obtenerPosPorCodCiudad(or);
        boolean[] visitados = new boolean[tope];
        Ciudad[] anteriores = new Ciudad[tope];
        double[] costos = new double[tope];
        TipoConexion[] tiposConexion = new TipoConexion[tope];

        for (int i = 0; i < tope; i++) {
            costos[i] = Double.MAX_VALUE;
            anteriores[i] = null;
        }
        costos[posOr] = 0;

        for (int i = 0; i < cantidad; i++) {
            int pos = getNextNoVisitadoLowCost(costos, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < tope; j++) {
                    if (matAdy[pos][j].isExiste() && !visitados[j]) {
                        Conexion c = matAdy[pos][j].obtenerConexionDeMenorTiempo();
                        double costonuevo = costos[pos] + c.getTiempo();
                        if (costonuevo < costos[j]) {
                            if(!existe(anteriores, ciudades[pos]) && !des.equals(ciudades[pos].getCodigo())) {
                                costos[j] = costonuevo;
                                tiposConexion[j] = c.getTipo();
                                anteriores[j] = ciudades[pos];
                            }
                        }
                    }
                }
            }
        }
        int i = 0;
        String x = "";
        for (Ciudad c : anteriores) {
            if (c != null) {
                if (!des.equals(c.getCodigo())) {
                    x += c + "|" + tiposConexion[i] + "|";
                } else {
                    x += c.toString();
                    break;
                }
            }
            i++;
        }
        return x;
    }
        public boolean existe(Ciudad[] ciudades, Ciudad ci){
        if(ci ==null){return false;}
        for(Ciudad c: ciudades) {
            if(c!=null){
                if(c.getCodigo().equals(ci.getCodigo())){
                    return true;
                }
            }
        }
        return  false;
    }

    public int getNextNoVisitadoLowCost(double[] costos, boolean[] visitados) {
        int posMin = -1;
        double minimo = Double.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if (costos[i] < minimo && !visitados[i]) {
                minimo = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }

}
