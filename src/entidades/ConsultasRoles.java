package entidades;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsultasRoles {

    //Constante para facilitar la consulta por procedimientos almacenados    
    public static String SP_CALL = "call SP_ConsultasRoles";

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
    //1 /*Modificado para dar roles a la sede establecida DCG 20/02/2024*/
    public static Object[][] darRoles() {
        String[] buscar = {"codigo","rol","sede"};
        return Conexion.getConsultas().getTabla(buscar, getCall(1));
    }

    //2
    public static Object[] darRolPorCodigo(String pCodigo) {
        String[] buscar = {"codigo", "rol", "estadoInfoExtra","sede"};
        StringBuilder sb = new StringBuilder(pCodigo);
        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(2, sb));
        return resultado[0];
    }

    //3
    public static void insertarRol(Object[] pData) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params);
        Conexion.getConsultas().ejecutarInstruccion(getCall(3, sb));
    }

    //4
    public static void actualizarRol(Object[] pData) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params);
        Conexion.getConsultas().ejecutarInstruccion(getCall(4, sb));
    }

    /**
     *
     * @param pCodigo
     * @return true si existe el rol, false si no.
     */
    //5
    public static boolean existeRol(String pCodigo) {
        String[] buscar = {"codigo"};
        StringBuilder sb = new StringBuilder(pCodigo);
        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(5, sb));

        try {
            String temp = resultado[0][0].toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //6
    public static Object[][] darPermisosPorRol(String pRol) {
        String[] buscar = {"registrarDemograficos", "registrarOrdenes", "registrarResultados", "modificarDemograficos", "modificarOrdenes", "modificarResultados", "consultar", "imprimir", "validar", "desvalidar", "validarSegundaVez", "desvalidarSegundaVez", "crearUsuarios", "modificarUsuarios", "configuracion", "verConfidencial","crearSede","administrarSede","croles","cmedico","cservicios","canalizador"};
        StringBuilder sb = new StringBuilder(pRol);
        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(6, sb));
        return resultado;
    }

    //7
    public static void insertarPermisos(ArrayList pPermisos, String pCodigo) {
        String[] subir = {pCodigo, (boolean) pPermisos.get(0) ? "1" : "0", (boolean) pPermisos.get(1) ? "1" : "0", (boolean) pPermisos.get(2) ? "1" : "0", (boolean) pPermisos.get(3) ? "1" : "0",
            (boolean) pPermisos.get(4) ? "1" : "0", (boolean) pPermisos.get(5) ? "1" : "0", (boolean) pPermisos.get(6) ? "1" : "0", (boolean) pPermisos.get(7) ? "1" : "0",
            (boolean) pPermisos.get(8) ? "1" : "0", (boolean) pPermisos.get(9) ? "1" : "0", (boolean) pPermisos.get(10) ? "1" : "0", (boolean) pPermisos.get(11) ? "1" : "0",
            (boolean) pPermisos.get(12) ? "1" : "0", (boolean) pPermisos.get(13) ? "1" : "0", (boolean) pPermisos.get(14) ? "1" : "0", (boolean) pPermisos.get(15) ? "1" : "0",
              (boolean) pPermisos.get(16) ? "1" : "0", (boolean) pPermisos.get(17) ? "1" : "0" ,(boolean) pPermisos.get(18) ? "1" : "0", (boolean) pPermisos.get(19) ? "1" : "0" ,(boolean) pPermisos.get(20) ? "1" : "0", (boolean) pPermisos.get(21) ? "1" : "0"  };
        StringBuilder sb = Conexion.getParamsFromArray(subir);
        Conexion.getConsultas().ejecutarInstruccion(getCall(7, sb));
    }

    //8
    public static void actualizarPermisos(ArrayList pPermisos, String pCodigo) {
        String[] subir = {(boolean) pPermisos.get(0) ? "1" : "0", (boolean) pPermisos.get(1) ? "1" : "0", (boolean) pPermisos.get(2) ? "1" : "0", (boolean) pPermisos.get(3) ? "1" : "0",
            (boolean) pPermisos.get(4) ? "1" : "0", (boolean) pPermisos.get(5) ? "1" : "0", (boolean) pPermisos.get(6) ? "1" : "0", (boolean) pPermisos.get(7) ? "1" : "0",
            (boolean) pPermisos.get(8) ? "1" : "0", (boolean) pPermisos.get(9) ? "1" : "0", (boolean) pPermisos.get(10) ? "1" : "0", (boolean) pPermisos.get(11) ? "1" : "0",
            (boolean) pPermisos.get(12) ? "1" : "0", (boolean) pPermisos.get(13) ? "1" : "0", (boolean) pPermisos.get(14) ? "1" : "0", (boolean) pPermisos.get(15) ? "1" : "0",
            (boolean) pPermisos.get(16) ? "1" : "0", (boolean) pPermisos.get(17) ? "1" : "0" ,(boolean) pPermisos.get(18) ? "1" : "0", (boolean) pPermisos.get(19) ? "1" : "0" ,(boolean) pPermisos.get(20) ? "1" : "0", (boolean) pPermisos.get(21) ? "1" : "0"  };
        StringBuilder sb = Conexion.getParamsFromArray(subir).append(Conexion.getDelimitador()).append(pCodigo);

        Conexion.getConsultas().ejecutarInstruccion(getCall(8, sb));
    }

    /**
     *
     * @param pRol
     * @return true si el rol requiere información extra. False si no.
     */
    //9
    public static boolean rolConInformacionExtra(String pRol) {
        String[] buscar = {"estadoInfoExtra"};
        StringBuilder sb = new StringBuilder(pRol);
        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(9, sb));

        try {
            String temp = resultado[0][0].toString();
            return temp.equals("1");
        } catch (Exception e) {
            return false;
        }
    }
}
