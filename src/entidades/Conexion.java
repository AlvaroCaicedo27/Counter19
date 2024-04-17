package entidades;

import clases.Consultas;
import clases.Permisos;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase encargada iniciar el proceso de conexión y la gestiona. Contiene un
 * patrón singleton. También trabaja los atributos usualmente requeridos de la
 * conexión.
 */
public class Conexion {

    //Atributos
    private static Conexion conx = null;
    private static Consultas sqlConsultas = null;
    private static String username;
    private static String userId;
    private static Permisos permisos;
    private static final String delimitador = "&";
    

    //Constante para etiquetar la versión actual del software.
    public static final String VERSION = "2.3.0.0";

    //Constante para facilitar la consulta por procedimientos almacenados
    public static String SP_CALL = "call SP_Conexion";
    
    //Constante para facilitar el año de las consultas
    public static String ANIO = new SimpleDateFormat("yyyy").format(new Date()).equals("2020")? "" : new SimpleDateFormat("yyyy").format(new Date());

    /**
     * Método constructor de la clase que se encarga de crear la instancia de
     * conexión por medio de sus parámetros. El método también inicializa
     * variables como el username y userId de la clase.
     *
     * @param pUsername - Username para realizar la conexión.
     * @param pPass - Password para realizar la conexión.
     * @throws Exception En caso de que las credenciales sean incorrectas o no
     * se logre conexión con el servidor.
     */
    private Conexion(String pUsername, String pPass) throws Exception {
        sqlConsultas = new Consultas(pUsername, pPass);
        username = pUsername;
        String[] buscar = {"consecutivo"};
        StringBuilder sb = new StringBuilder(username);
        Object[][] datos = sqlConsultas.getTabla(buscar, getCall(1, sb));
        userId = datos[0][0] + "";
    }

    //Método set
    public void setUsername(String pUsername) {
        this.username = pUsername;
    }

    //Métodos get
    public static Consultas getConsultas() {
        return sqlConsultas;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getDelimitador() {
        return delimitador;
    }

    public static String getUsername() {
        return username;
    }

    public static Conexion getConexion() {
        return conx;
    }
    
    /**
     * Método get del tipo singleton para mantener una única instancia de la
     * conexión. En caso de que no haya conexión, se tomarán los parametros para
     * intentar crear una.
     *
     * @param pUsername Nombre de usuario que desea conectarse.
     * @param pPass Contraseña del usuario que desea conectarse.
     * @return La conexión existente o recien creada.
     * @throws Exception En caso de que ocurre una error de autenticación del
     * usuario.
     */
    public static Conexion getConexion(String pUsername, String pPass) throws Exception {
        if (conx == null) {
            conx = new Conexion(pUsername, pPass);
        }
        return conx;
    }

    /**
     * Método sobrecargado de getCall.
     *
     * @param pFlag Número del flag asignado al método.
     * @param pParams Parámetros para que van para el prodecimiento almacenado.
     * @return Instrucción en string del proceso almacenado que completa la
     * consulta.
     */
    public static String getCall(int pFlag, StringBuilder pParams) {
        if (pParams != null) {
            return SP_CALL + "(" + pFlag + "," + '"' + pParams.toString() + '"' + ")";
        } else {
            return SP_CALL + "(" + pFlag + ",'')";
        }
    }

    /**
     * Método para facilitar la consulta del procedimiento almacenado
     * controlador de esta clase.
     *
     * @param pFlag Número del flag asignado al método.
     * @return Instrucción en string del proceso almacenado que completa la
     * consulta.
     */
    public static String getCall(int pFlag) {
        return SP_CALL + "(" + pFlag + ",'')";
    }

    /**
     * Método encargado de cargar los permisos del usuario que se conectó por la
     * sesión actual. Los permisos se cargan sobre la variable global de esta
     * clase.
     *
     * @return Entidad que almacena los permisos del usuario actual
     */
    public static Permisos getPermisos() {
        //Si ya se encuentra una conexión, realizamos una consulta de los permisos según el usuario que se conectó.
        if (sqlConsultas != null) {
            String[] buscarPermisos = {"registrarDemograficos", "registrarOrdenes", "registrarResultados", "modificarDemograficos", "modificarOrdenes", "modificarResultados", "consultar", "imprimir", "validar", "desvalidar", "validarSegundaVez", "desvalidarSegundaVez", "crearUsuarios", "modificarUsuarios", "configuracion", "verConfidencial", "soporte","crearSede","administrarSede","croles","cmedico","cservicios","canalizador","centidades","ctarifa"};
            StringBuilder sb = new StringBuilder(userId);
            System.out.println(getCall(2, sb));
            Object[][] per = sqlConsultas.getTabla(buscarPermisos, getCall(2, sb));
            permisos = new Permisos(per);
        }
        return permisos;
    }

    /**
     * Determina el estado de actividad del usuario.
     *
     * @return true si el usuario puede acceder al sistema, false si el usuario
     * está desactivado.
     */
    public static boolean getUserActiveStatus() {
        if (sqlConsultas != null) {
            String[] buscar = {"estado"};
            StringBuilder sb = new StringBuilder(userId);
            Object[][] per = sqlConsultas.getTabla(buscar, getCall(3, sb));
            return per[0][0].equals("1");
        } else {
            return false;
        }
    }
   

    /**
     * Crea una instancia temporal de 'sqlConsultas' con datos ingresados por
     * parámetro para realizar consultas rápidas de verificación de datos en la
     * bd.
     *
     * Se usa comumente para validar licencias y cargar la versión del software
     * registrada en la base de datos.
     *
     * @param pUsername - Username para realizar la conexión.
     * @param pPass - Password para realizar la conexión.
     * @throws Exception En caso de que las credenciales sean incorrectas o no
     * se logre conexión con el servidor.
     */
    public static void ping(String pUsername, String pPass) throws Exception {
        sqlConsultas = new Consultas(pUsername, pPass);
    }

    /**
     * Método creado para asegurar que la conexión previa quede cerrada. Se
     * ejecuta al momento de que el usuario cierre sesión en la aplicación.
     */
    public static void forcedCloseConexion() {
        try {
            sqlConsultas.getCon().getConexion().close();
            conx.finalize();
            conx = null;
            sqlConsultas = null;
        } catch (SQLException ex) {
        } catch (Throwable ex) {
        }
    }

    /**
     * Método que facilita la creación de un StringBuilder para ser usado como
     * parámetro en las consultas por procedimientos almacenados. El método
     * recibe un String[] por parámetro, cambiar los valores null por un texto
     * específico y retorna un StrinbBuilder en el que se encuentra toda la
     * información. Esta información se encuentra en el mismo orden del arreglo
     * y se separa por el 'delimitador' definido en esta clase.
     *
     * @param pArray String[] con la información que requiere convertirse en un
     * StringBuilder de parametros.
     * @return StringBuilder con la información del arreglo que entra por
     * parámetro, en el mismo orden, formateado y separado por el deliminator.
     */
    public static StringBuilder getParamsFromArray(String[] pArray) {
        StringBuilder sb = new StringBuilder("");
        for (String string : pArray) {
            if (string == null) {
                sb.append("¬€£~");
            } else {
                sb.append(string);
            }
            sb.append(delimitador);
        }
        sb.setLength(sb.length() - 1);
        return sb;
    }

}
