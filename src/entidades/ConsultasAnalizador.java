package entidades;

public class ConsultasAnalizador {

    public static String SP_CALL = "call SP_ConsultasAnalizador";

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

    /**
     * Método sobrecargado de getCall que incluye el parámetro para cambiar el
     * año de consulta.
     *
     * @param pFlag Número del flag asignado al método.
     * @param pParams Parámetros para que van para el prodecimiento almacenado.
     * @param pAnio Año en el que se va a realizar la consulta.
     * @return Instrucción en string del proceso almacenado que completa la
     * consulta.
     */
    public static String getCall(int pFlag, StringBuilder pParams, int pAnio) {

        String tempAnio = pAnio + "";
        if (pAnio == 2020) {
            tempAnio = "";
        }

        if (pParams != null) {
            return SP_CALL + "(" + pFlag + ",'" + pParams.toString() + "','" + tempAnio + "')";
        } else {
            return SP_CALL + "(" + pFlag + ",'','" + tempAnio + "')";
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
//            return "call SP_ConsultasAnalizador(" + pFlag + ",'" + pParams.toString() + "')";
//        } else {
//            return "call SP_ConsultasAnalizador(" + pFlag + ",'')";
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
//        return "call SP_ConsultasAnalizador(" + pFlag + ",'')";
//    }
    //1
    public static Object[][] darExamenesRelacionados() {
        String[] buscar = {"codigoAnalizador", "detalle", "codigoExamen", "abreviatura"};
        return Conexion.getConsultas().getTabla(buscar, getCall(1));
    }

    //2
    public static Object[][] darExamenesAnalizadorDispnonibles() {
        String[] buscar = {"consecutivo", "codigoAnalizador", "detalle"};
        return Conexion.getConsultas().getTabla(buscar, getCall(2));
    }

    //3
    public static Object[][] darExamenesAnalizador() {
        String[] buscar = {"consecutivo", "codigoAnalizador", "detalle"};
        return Conexion.getConsultas().getTabla(buscar, getCall(3));
    }

    //4
    public static Object[][] darExamenesSistema() {
        String[] buscar = {"codigo", "abreviatura", "nombre"};
        return Conexion.getConsultas().getTabla(buscar, getCall(4));
    }

    //5
    public static void crearRelacionExamenes(String pCodigoAnalizador, String pCodigoSistema) {
        String d = Conexion.getDelimitador();
        StringBuilder sb = new StringBuilder("");
        sb.append(pCodigoAnalizador).append(d).append(pCodigoSistema);
        Conexion.getConsultas().ejecutarInstruccion(getCall(5,sb));
    }

    //6
    public static void eliminarRelacionExamenes(String pCodigoAnalizador, String pCodigoSistema) {
        String d = Conexion.getDelimitador();
        StringBuilder sb = new StringBuilder("");
        sb.append(pCodigoAnalizador).append(d).append(pCodigoSistema);
        Conexion.getConsultas().ejecutarInstruccion(getCall(6,sb));
    }

    //7
    public static void eliminarRelacionExamenes(String pCodigoAnalizador) {
        StringBuilder sb = new StringBuilder(pCodigoAnalizador);
        Conexion.getConsultas().ejecutarInstruccion(getCall(7,sb));
    }

    //8
    public static boolean existeExamenAnalizador(String pCodigo) {
        String[] buscar = {"codigoAnalizador"};
        StringBuilder sb = new StringBuilder(pCodigo);
        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(8, sb));

        try {
            String temp = resultado[0][0].toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //9
    public static void insertarExamenAnalizador(String[] pData) {
        Conexion.getConsultas().ejecutarInstruccion(getCall(9, Conexion.getParamsFromArray(pData)));
        //System.out.println(getCall(9, Conexion.getParamsFromArray(pData)));
    }

    //10
    public static void actualizarExamenAnalizador(String pConsecutivo, String[] pData) {
        StringBuilder temp = Conexion.getParamsFromArray(pData).append(Conexion.getDelimitador()).append(pConsecutivo);
        //System.out.println(temp.toString());
        Conexion.getConsultas().ejecutarInstruccion(getCall(10, temp));
        //System.out.println(getCall(10, temp));
    }

    //11
    public static Object[] darExamenAnalizadorPorConsecutivo(String pConsecutivo) {
        String[] buscar = {"codigoAnalizador", "detalle"};
        StringBuilder sb = new StringBuilder(pConsecutivo);
        return Conexion.getConsultas().getTabla(buscar, getCall(11, sb))[0];
    }

    //12
    public static void eliminarExamenAnalizador(String pConsecutivo) {
        Conexion.getConsultas().ejecutarInstruccion(getCall(12, new StringBuilder(pConsecutivo)));
    }
}
