# OblAlgoritmosEstructuraDatos2

Se desea implementar un software para la gestión y recomendación de viajeros, al gestionar una gran cantidad de ciudades, conexiones y viajeros debemos ser cuidadosos a la hora de diseñar las estructuras.

Luego de un breve análisis, se creó una interfaz para separar las reglas de negocio de la parte del manejo de servidor donde todas las operaciones deberán devolver una instancia de la clase Retorno. Dicha clase contiene: 
● Un resultado, que especifica si la operación se pudo realizar correctamente (OK), o si ocurrió algún error (según el número de error). 
● Un valorEntero, para las operaciones que retornen un número entero. 
● Un valorString, para las operaciones que retornen un String, o un valor más complejo, por ejemplo, un listado, el cual será formateado según lo indicado en los ejemplos. public class Retorno { public enum Resultado {OK, ERROR_1, ERROR_2, ERROR_3, ERROR_4, ERROR_5, ERROR_6, ERROR_7, NO_IMPLEMENTADA}; public Resultado resultado; }

Además, se provee: una interfaz llamada Sistema, la cual no podrá ser modificada en ningún sentido, y una clase ImplementacionSistema que la implementa, donde su equipo deberá completar la implementación de las operaciones solicitadas. Consideraciones: •La clase sistema NO PODRÁ SER UN SINGLETON, debe ser una clase instanciable. •Pueden definirse tipos de datos (clases) auxiliares, pero por ningún motivo puede modificarse las firmas definidas en la interfaz Sistema. •Se provee un proyecto base con la estructura base de las clases, utilizar este proyecto es obligatorio.

Funcionalidades:

1-Inicializar Sistema Retorno inicializarSistema (int maxCiudades); Descripción: Inicializa las estructuras necesarias para representar el sistema especificado, capaz de registrar como máximo la cantidad maxCiudades de ciudades.

2-Registrar viajero Retorno registrarViajero(String cedula, String nombre, int edad, TipoViajero tipo); Descripción: Registra el viajero con sus datos. Para -simplificar el problema se asume que todos los viajeros tienen una cédula de identidad única. Restricción de eficiencia: Esta operación deberá realizarse en orden O(log n) promedio.

3-Buscar Viajero Retorno buscarViajero(String cedula); Descripción: Retorna en valorString los datos del viajero según el formato indicado. Además, en el campo valorEntero de la clase Retorno, deberá devolver la cantidad de elementos que recorrió durante la búsqueda en la estructura utilizada. Restricción de eficiencia: Esta operación deberá realizarse en orden O(log n) promedio.

4-Listar viajeros por cédula descendente Retorno listarViajerosDescendente(); Descripción: Retorna en valorString los datos de todos los viajeros registrados, ordenados numéricamente por cédula en forma descendente. Restricción de eficiencia: Esta operación deberá realizarse en orden (n).

5-Listar viajeros por cédula ascendente Retorno listarViajerosAscendente(); Descripción: Retorna en valorString los datos de todos los viajeros registrados, ordenados numéricamente por cédula en forma ascendente. Restricción de eficiencia: Esta operación deberá realizarse en orden (n).

6-Listar viajeros por tipo Retorno listarViajerosPorTipo(TipoViajero tipo); Descripción: Retorna en valorString los datos de todos los viajeros registrados con ese tipo. Se requiere que estén ordenados de forma creciente. Restricción de eficiencia: Esta operación deberá realizarse en orden (k), siendo k la cantidad de viajeros con dicho tipo.

7-Registrar ciudad Retorno registrarCiudad(String codigo, String nombre); Descripción: Registra la cuidad en el sistema con el código y nombre indicado. El código es el identificador único alfanumérico formado por dígitos, letras en mayúscula y al menos debe tener largo 5. Restricción de eficiencia: no tiene.

8-Registrar Conexión Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino,int identificadorConexion, double costo, double tiempo, TipoConexion tipo); Descripción: Registra una conexión en el sistema desde la ciudad de origen al destino. Restricción de eficiencia: no tiene.

9-Actualizar conexión Retorno actualizarConexion(String codigoCiudadOrigen, String codigoCiudadDestino,int identificadorConexion, double costo, double tiempo, TipoConexion tipo); Descripción: Actualiza una conexión existente en el sistema, debe existir una conexión entre la ciudad de origen y destino. Restricción de eficiencia: no tiene.

10-Ciudades por cantidad de trasbordos Retorno listadoCiudadesCantTrasbordos(String codigo, int cantidad); Descripción: Dado una ciudad de origen se debe retornar en el valorString los datos de las ciudades (ordenados por código creciente) a los que se pueda llegar realizando hasta la cantidad de trasbordos indicada por parámetro. Restricción de eficiencia: no tiene.

11-Viaje de costo mínimo en tiempo Retorno viajeCostoMinimo(String codigoCiudadOrigen, String codigoCiudadDestino); Descripción: Retorna el camino más corto (de menor tiempo) que podría realizar un viajero para ir de la ciudad de origen a la de destino. Tener en cuenta que en este viaje se puede pasar por varias ciudades intermedias. Restricción de eficiencia: no tiene.

Información importante:

•Se deberán respetar los formatos de retorno dados para las operaciones que devuelven datos. 
•El objetivo de este obligatorio es la selección adecuada de las estructuras para modelar el problema y la eficiencia en cada una de las operaciones. Deberá aplicar la metodología vista en el curso. 
•Está terminantemente prohibido el uso de clases de Java tales como ArrayList. La utilización de estas clases implica la pérdida de todos los puntos del obligatorio. 
•Los objetos deben se deben comparar utilizando equals. 
•Se debe aplicar el uso de buenas prácticas vistas durante la carrera. •Ninguna de las operaciones implementadas debe imprimir en consola. •El sistema no debe requerir ningún tipo de interacción con el usuario por consola. 
•Es obligación del estudiante mantenerse al tanto de las aclaraciones que se realicen en clase o a través del foro de aulas. 
•El proyecto será implementado en lenguaje JAVA sobre una interfaz Sistema que se publicará en el sitio de la materia en aulas.ort.edu.uy (El uso de esta interfaz es obligatorio) 
•El proyecto entregado debe compilar y ejecutar correctamente en IntelliJ IDEA. 
•No se contestarán dudas sobre el obligatorio en las 48 horas previas a la entrega.
