package sistema;

import estructuras.ABB.ABBGeneric;
import estructuras.Grafo.GrafCC;
import interfaz.*;
import dominio.*;

public class ImplementacionSistema implements Sistema {

    GrafCC grafCiudades;
    ABBGeneric<Viajero> viajeros;
    ABBGeneric<Viajero> viajerosTipoPremium;
    ABBGeneric<Viajero> viajerosTipoEstandar;
    ABBGeneric<Viajero> viajerosTipoCasual;

    ABBGeneric<Ciudad> ciudadesBSF;

    @Override
    public Retorno inicializarSistema(int maxCiudades) {

        if (maxCiudades > 5) {
            grafCiudades = new GrafCC(maxCiudades, true);
            viajeros = new ABBGeneric<>();
            viajerosTipoPremium = new ABBGeneric<>();
            viajerosTipoEstandar = new ABBGeneric<>();
            viajerosTipoCasual = new ABBGeneric<>();
            ciudadesBSF = new ABBGeneric<>();
            return Retorno.ok();
        } else {
            return Retorno.error1("");
        }
    }

    @Override
    public Retorno registrarViajero(String cedula, String nombre, int edad, TipoViajero tipo) {
        if (cedula == null || cedula.isEmpty() || nombre == null || nombre.isEmpty() || edad == 0 || tipo == null) {
            return Retorno.error1("Revise que los datos ingresados no sean vacíos ni nulos");
        } else {
            Viajero v = new Viajero(cedula, nombre, edad, tipo);
            if (!v.isCedulaOK(cedula)) {
                return Retorno.error2("La cédula no tiene un formato válido");
            } else if (viajeros.exist(v)) {
                return Retorno.error3("Ya existe un viajero registrado con esta cédula");
            }
            viajeros.insertar(v);
            if (tipo.equals(TipoViajero.PREMIUM)) {
                viajerosTipoPremium.insertar(v);
            } else if (tipo.equals(TipoViajero.ESTANDAR)) {
                viajerosTipoEstandar.insertar(v);
            } else if (tipo.equals(TipoViajero.CASUAL)) {
                viajerosTipoCasual.insertar(v);
            }
            return Retorno.ok();
        }
    }

    @Override
    public Retorno buscarViajero(String cedula) {
        Viajero v = new Viajero(cedula);
        if (cedula == null || !v.isCedulaOK(cedula)) {
            return Retorno.error1("Formato no válido");
        } else if (!viajeros.exist(v)) {
            return Retorno.error2("No existen registros");
        } else {
            Viajero v2 = viajeros.getElemento(v);
            return Retorno.ok(viajeros.cantidadRecorridas(v2), v2.toString());
        }
    }

    @Override
    public Retorno listarViajerosAscendente() {
        String ret = viajeros.listarAsc();
        return Retorno.ok(ret);
    }

    @Override
    public Retorno listarViajerosDescendente() {
        String ret = viajeros.listarDes();
        return Retorno.ok(ret);
    }

    @Override
    public Retorno listarViajerosPorTipo(TipoViajero tipo) {
        if (tipo == null) {
            return Retorno.error1("el tipo es null");
        }
        String respuesta = "";
        if (tipo.equals(TipoViajero.PREMIUM)) {
            respuesta = viajerosTipoPremium.listarAsc();
        } else if (tipo.equals(TipoViajero.ESTANDAR)) {
            respuesta = viajerosTipoEstandar.listarAsc();
        } else if (tipo.equals(TipoViajero.CASUAL)) {
            respuesta = viajerosTipoCasual.listarAsc();
        }
        return Retorno.ok(respuesta);
    }

    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        Ciudad c = new Ciudad(codigo, nombre);
        if (grafCiudades.esLleno()) {
            return Retorno.error1("max ciudades completo");
        } else if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return Retorno.error2("Inconsistencia de datos");
        } else if (!c.Validar()) {
            return Retorno.error3("Formato de código incorrecto");
        } else if (grafCiudades.existeCiudad(c)) {
            return Retorno.error4("Existe este codigo");
        } else {
            grafCiudades.agregarVertice(c);
            return Retorno.ok();
        }
    }
    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino, int identificadorConexion, double costo, double tiempo, TipoConexion tipo) {
        if (costo <= 0 || tiempo <= 0) {
            return Retorno.error1("Verifique que el costo y/o el tiempo no sean menor o igual a cero");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty() || tipo == null || tipo.equals("")) {
            return Retorno.error2("Verifique que los códigos de ciudad y el tipo de conexión no sean vacío o null");
        } else if (!grafCiudades.ValidarCodigoDeCiudad(codigoCiudadOrigen) || !grafCiudades.ValidarCodigoDeCiudad(codigoCiudadDestino)) {
            return Retorno.error3("Alguno de los códigos de ciudad no es válido");
        } else if (!grafCiudades.existCodCity(codigoCiudadOrigen)) {
            return Retorno.error4("No existe la ciudad de origen");
        } else if (!grafCiudades.existCodCity(codigoCiudadDestino)) {
            return Retorno.error5("No existe la ciudad de destino");
        }  else if (grafCiudades.existConexion(codigoCiudadOrigen, codigoCiudadDestino, identificadorConexion)) {
            return Retorno.error6("Ya hay una conexión entre ciudades de origen y destino con ese identificador");
        } else {
            grafCiudades.registrarConexion(codigoCiudadOrigen, codigoCiudadDestino, identificadorConexion, costo, tiempo, tipo);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno actualizarConexion(String codigoCiudadOrigen, String codigoCiudadDestino, int identificadorConexion, double costo, double tiempo, TipoConexion tipo) {

        if (costo <= 0 || tiempo <= 0) {
            return Retorno.error1("Verifique que el costo y/o el tiempo no sean menor o igual a cero");
        } else if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty() || tipo == null || tipo.equals("")) {
            return Retorno.error2("Verifique que los códigos de ciudad y el tipo de conexión no sean vacío o null");
        } else if (!grafCiudades.ValidarCodigoDeCiudad(codigoCiudadOrigen) || !grafCiudades.ValidarCodigoDeCiudad(codigoCiudadDestino)) {
            return Retorno.error3("Alguno de los códigos de ciudad no es válido");
        }  else if (!grafCiudades.existCodCity(codigoCiudadOrigen)) {
            return Retorno.error4("No existe la ciudad de origen");
        } else if (!grafCiudades.existCodCity(codigoCiudadDestino)) {
            return Retorno.error5("No existe la ciudad de destino");
        }else if (!grafCiudades.existConexion(codigoCiudadOrigen, codigoCiudadDestino, identificadorConexion)) {
            return Retorno.error6("no existen conexiones");
        } else {
            grafCiudades.actualizarConexion(codigoCiudadOrigen, codigoCiudadDestino, identificadorConexion, costo, tiempo, tipo);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno listadoCiudadesCantTrasbordos(String codigo, int cantidad) {
        if (cantidad < 0) {
            return Retorno.error1("cantidad debe ser mayor a 0");
        } else if (codigo == null || codigo.isEmpty()) {
            return Retorno.error2("el codigo no puede ser nulo");
        } else if (!grafCiudades.ValidarCodigoDeCiudad(codigo)) {
            return Retorno.error3("Codigo no válido");
        }
        Ciudad c = grafCiudades.getCity(codigo);
        if (c == null) {
            return Retorno.error4("La ciudad no se encuentra registrada en el sistema");
        } else {
            String ret = grafCiudades.listadoCiudadesCantTransbordos(codigo, cantidad, ciudadesBSF);
            return Retorno.ok(0, ret);
            //RETORNA 1 ERROR EN EL TEST
        }
    }

    @Override
    public Retorno viajeCostoMinimo(String codigoCiudadOrigen, String codigoCiudadDestino) {
        if (codigoCiudadOrigen == null || codigoCiudadOrigen.isEmpty() || codigoCiudadDestino == null || codigoCiudadDestino.isEmpty()) {
            return Retorno.error1("inconcistencia de datos");
        } else if (!grafCiudades.ValidarCodigoDeCiudad(codigoCiudadOrigen) || !grafCiudades.ValidarCodigoDeCiudad(codigoCiudadDestino)) {
            return Retorno.error2("formato no válido");
        } else if (!grafCiudades.existCodCity(codigoCiudadOrigen)) {
            return Retorno.error4("no existe origen");
        } else if (!grafCiudades.existCodCity(codigoCiudadDestino)) {
            return Retorno.error5("no existe destino");
        }
        double ret = grafCiudades.dijkstra2(codigoCiudadOrigen, codigoCiudadDestino);
        String s = grafCiudades.mejorCaminoRecorrido(codigoCiudadOrigen, codigoCiudadDestino);
        if (ret == Double.MAX_VALUE) {
            return Retorno.error3("no existe un camino");
        }

        return Retorno.ok((int) ret , s);
        //RETORNA 3 ERRORES EN EL TEST
    }
}
