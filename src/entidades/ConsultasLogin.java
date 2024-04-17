package entidades;

import static entidades.ConsultasUsuarios.getCall;

public class ConsultasLogin {

    //Constante para facilitar la consulta por procedimientos almacenados    
    public static String SP_CALL = "call SP_ConsultasLogin";
    private static String sede;
    


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
            return new StringBuilder(SP_CALL).append("(").append(pFlag).append(",'").append(pParams.toString()).append("')").toString();
        } else {
            return new StringBuilder(SP_CALL).append("(").append(pFlag).append(",'')").toString();
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
        return new StringBuilder(SP_CALL).append("(").append(pFlag).append(",'')").toString();
    }

    //1
    public static String validarEstadisticas(String pEquipo) {
        String[] buscar = {"temporal"};
        StringBuilder sb = new StringBuilder(pEquipo);
        try {
            return Conexion.getConsultas().getTabla(buscar, getCall(1, sb))[0][0].toString();
        } catch (Exception e) {
            return "LICENCIA ALTERADA";
        }
    }

    //2
    public static String getLastVersion() {
        try {
            String[] buscar = {"ultimaVersion"};
            return Conexion.getConsultas().getTabla(buscar, getCall(2))[0][0].toString();
        } catch (Exception e) {
            return "---";
        }
    }

    //3
    public static String getNombreSede() {
        try {
            String[] buscar = {"nombre"};
            return Conexion.getConsultas().getTabla(buscar, getCall(3))[0][0].toString();
        } catch (Exception e) {
            return "---";
        }
    }
    
     public static Object[][] darSedes() {
        String[] buscar = {"codigo", "sede"};
        return Conexion.getConsultas().getTabla(buscar, getCall(6));
    }
    
   
   

    //3
  
    //4
    public static String getDateFromDatabase() {
        String[] buscar = {"fecha"};
        return Conexion.getConsultas().getTabla(buscar, getCall(4))[0][0].toString();
    }

    public static String getSede() {
        return sede;
    }

    public static void setSede(String pSede) {
        sede = pSede;
    }
}
