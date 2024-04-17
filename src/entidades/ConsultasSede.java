package entidades;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class ConsultasSede {

    //Constante para facilitar la consulta por procedimientos almacenados    
    public static String SP_CALL = "call SP_ConsultasSede";

    /**
     * Método para facilitar la consulta del procedimiento almacenado
     * controlador de esta clase.
     *
     * @param pFlag Número del flag asignado al método.
     * @return Instrucción en string del proceso almacenado que completa la
     * consulta.
     */
    public static String getCall(int pFlag) {
        return SP_CALL + "(" + pFlag + ",'','" + Conexion.ANIO + "')";
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
            return SP_CALL + "(" + pFlag + ",'" + pParams.toString() + "','" + Conexion.ANIO + "')";
        } else {
            return SP_CALL + "(" + pFlag + ",'','" + Conexion.ANIO + ")";
        }
    }

//    /**
//     * Método sobrecargado de getCall.
//     *
//     * @param pFlag Número del flag asignado al método.
//     * @param pParams Parámetros para que van para el prodecimiento almacenado.
//     * @return Instrucción en string del proceso almacenado que completa la
//     * consulta.
//     */
//    public static String getCall(int pFlag, StringBuilder pParams) {
//        if (pParams != null) {
//            return new StringBuilder(SP_CALL).append("(").append(pFlag).append(",'").append(pParams.toString()).append("')").toString();
//        } else {
//            return new StringBuilder(SP_CALL).append("(").append(pFlag).append(",'')").toString();
//        }
//    }
//
//    /**
//     * Método para facilitar la consulta del procedimiento almacenado
//     * controlador de esta clase.
//     *
//     * @param pFlag Número del flag asignado al método.
//     * @return Instrucción en string del proceso almacenado que completa la
//     * consulta.
//     */
//    public static String getCall(int pFlag) {
//        return new StringBuilder(SP_CALL).append("(").append(pFlag).append(",'')").toString();
//    }
    //
    public static Object[][] darSede() {
        String[] buscar = {"codigo","nombre","direccion", "telefono", "correo", "fax"};
        return Conexion.getConsultas().getTabla(buscar, getCall(1));
    }
    
    
    // da la sede por codigo realizado por DSCG 02/02/2024
      public static Object[][] darSedePorCodigo(String pCodigo) {
        String[] buscar = {"codigo","nombre"};
        return Conexion.getConsultas().getTabla(buscar, getCall(7, new StringBuilder(pCodigo)));
    }
      
       public static Object[][] darInfoSedePorCodigo(String pCodigo) {
        String[] buscar = {"codigo", "nombre","direccion","telefono","correo", "fax"};
        return Conexion.getConsultas().getTabla(buscar, getCall(9, new StringBuilder(pCodigo)));
    }
      
       public static Object[][] darCodigoPorSede(String pNombre) {
        String[] buscar = {"nombre", "codigo"};
        return Conexion.getConsultas().getTabla(buscar, getCall(8, new StringBuilder(pNombre)));
    } 
       //VERIFICA SI LA SEDE ESTA REPETIDA EN EL REGISTRO REALIZADO POR DCG Y ALVARO 22/02/2024*/
       public static boolean existeSedePorCodigo(String codigoSede) {
    String[] buscar = {"codigo"};
   
    //  busca en la base de datos basado en el código de la sede.
    Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(90, new StringBuilder(codigoSede)));
    
    // Si resultado no es vacío, significa que se encontró al menos una sede con ese código.
    return resultado.length > 0 && resultado[0].length > 0;
}
       
     
  
       public static Object[][] darSedesPorCodigo(String pCodigo) {
        String[] buscar = {"codigo","nombre","direccion", "telefono", "correo", "fax"};
        return Conexion.getConsultas().getTabla(buscar, getCall(77, new StringBuilder(pCodigo)));
    }

       public static void insertarSede(Object[] pData) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params);
        Conexion.getConsultas().ejecutarInstruccion(getCall(12, sb));
    }


    //2
    public static void subirDatos(Object[] pData) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params);
        Conexion.getConsultas().ejecutarInstruccion(getCall(2, sb));
    }
      public static void ActualizarDatos(Object[] pData, String pCodigo) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params);
        sb.append(Conexion.getDelimitador());
        sb.append(pCodigo);
        Conexion.getConsultas().ejecutarInstruccion(getCall(88, sb));
    }
      public static void actualizarConfiguracionCorreoInformes(String[] pData, String pCodigo) {
        StringBuilder sb = Conexion.getParamsFromArray(pData);
        sb.append(Conexion.getDelimitador());
        sb.append(pCodigo);
        Conexion.getConsultas().ejecutarInstruccion(getCall(3, sb));
    }


    //3

    //5
    public static void actualizarImagenSede(byte[] pImagen, String pCodigo) {
        Conexion.getConsultas().subirImagen(pImagen, pCodigo, 1);
    }

    //4
    public static Object[] darConfiguracionCorreoInformes() {
        String[] buscar = {"servidorCorreo", "puerto", "correoInformes", "contraseña"};
        return Conexion.getConsultas().getTabla(buscar, getCall(4))[0];
    }

   
    
    public static void updateFotoSede(byte[] pImagen, String pCodigo) {
       
     StringBuilder sb = new StringBuilder(pCodigo);
     Conexion.getConsultas().subirImagen(pImagen, 1, sb);
        
    }
    
    public static void eliminarMaterial(String pCodigo) {
        StringBuilder sb = new StringBuilder(pCodigo);
        Conexion.getConsultas().ejecutarInstruccion(getCall(14,sb));
    }
    

    //6 
    public static byte[] bajarImagenSede(String pCodigo) {
        return Conexion.getConsultas().bajarImagen(getCall(6, new StringBuilder(pCodigo)));
    }
    public static byte[] bajarImagenSedePorCodigo(String pCodigo) {
        StringBuilder sb = new StringBuilder(pCodigo);
        return Conexion.getConsultas().bajarImagen(getCall(11,sb));
    }
    
     public static void subirDatosPorCodigo(Object[] pData, String pCodigo) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params).append(Conexion.getDelimitador()).append(pCodigo);
        Conexion.getConsultas().ejecutarInstruccion(getCall(10, sb));
    }
     
}
