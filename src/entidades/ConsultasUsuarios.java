package entidades;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class ConsultasUsuarios {

    //Constante para facilitar la consulta por procedimientos almacenados    
    public static String SP_CALL = "call SP_ConsultasUsuarios";

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
    public static void registrarUsuario(Object[] pData) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params);

        Conexion.getConsultas().ejecutarInstruccion(getCall(1, sb));
    }

    //2
    public static Object[][] darRolPorCodigo(String pCodigo) {
        String[] buscar = {"codigo", "rol"};
        StringBuilder sb = new StringBuilder(pCodigo);

        return Conexion.getConsultas().getTabla(buscar, getCall(2, sb));
    }

    //3
    public static Object[][] darRoles() {
        String[] buscar = {"codigo", "rol","sede"};
        return Conexion.getConsultas().getTabla(buscar, getCall(3));
    }
        
    public static Object[][] darSedePorCodigo(String pCodigo) {
        String[] buscar = {"codigo", "sede"};
        StringBuilder sb = new StringBuilder(pCodigo);

        return Conexion.getConsultas().getTabla(buscar, getCall(11, sb));
    }
    
     public static Object[][] darSedePorUsurname(String pUsername) {
        String[] buscar = {"username", "sede"};
        StringBuilder sb = new StringBuilder(pUsername);

        return Conexion.getConsultas().getTabla(buscar, getCall(13, sb));
    }

    //3
     /*Modificado para dar roles a la sede establecida DCG y ALVARO 20/02/2024*/
    public static Object[][] darSedes() {
        String[] buscar = {"codigo","sede"};
        return Conexion.getConsultas().getTabla(buscar, getCall(12));
    }

    /**
     * Usado para registrar nuevos usuarios.
     *
     * @param pUsername - username a verificar.
     * @return true si existe, false si no.
     */
    //4
    public static boolean usernameExiste(String pUsername) {
        String[] buscar = {"username"};
        StringBuilder sb = new StringBuilder(pUsername);
        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(4, sb));

        try {
            String temp = resultado[0][0].toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Usado para actualizar usuarios.
     *
     * @param pUsername
     * @param pConsecutivo
     * @return true si existe uno distinto al que se está actualizando, false de
     * lo contrario.
     */
    //5
    public static boolean usernameExiste(String pUsername, String pConsecutivo) {
        String[] buscar = {"username"};
        String[] params = {pUsername, pConsecutivo};
        StringBuilder sb = Conexion.getParamsFromArray(params);

        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(5, sb));
        try {
            String temp = resultado[0][0].toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //6
    public static Object[][] darUsuarios() {
        String[] buscar = {"consecutivo", "username", "nombres", "apellidos", "rol", "estado","sede"};
        return Conexion.getConsultas().getTabla(buscar, getCall(6));
    }

    /**
     * @param instruccion
     * @return - ejemplo: "SELECT foto FROM pacientes WHERE identificacion = '"
     * + pIdentificacion + "'"
     */
    public static byte[] bajarImagen(String instruccion) {
        try {
            PreparedStatement ps = Conexion.getConsultas().getCon().getConexion().prepareStatement(instruccion);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                Blob blob = res.getBlob(1);
                byte[] resultado = blob.getBytes(1L, (int) blob.length());
                res.close();
                return resultado;

            } else {
                res.close();
                return null;
            }
        } catch (Exception e) {
            //   e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Algo paso con la imagen: " + e);
            return null;
        }
    }

    /**
     * Da la información del usuario por el consecutivo. El usuario existe.
     *
     * @param pConsecutivo
     * @return
     */
    //7
    public static Object[] darUsuarioPorConsecutivo(String pConsecutivo) {
        String[] buscar = {"username", "estado", "contrasenia", "nombres", "apellidos", "rol", "correo","sede"};
        StringBuilder sb = new StringBuilder(pConsecutivo);
        Object[][] resultado = Conexion.getConsultas().getTabla(buscar, getCall(7, sb));
        return resultado[0];
    }

    //8
    public static void actualizarUsuario(Object[] pData, String pConsecutivo) {
        String[] params = Arrays.copyOf(pData, pData.length, String[].class);
        StringBuilder sb = Conexion.getParamsFromArray(params).append(Conexion.getDelimitador()).append(pConsecutivo);
        Conexion.getConsultas().ejecutarInstruccion(getCall(8, sb));
    }

    //9
    public static void desactivarUsuario(String pConsecutivo) {
        StringBuilder sb = new StringBuilder(pConsecutivo);
        Conexion.getConsultas().ejecutarInstruccion(getCall(9, sb));
    }

    //8 - subirImagen
    public static boolean updateFotoUsuario(byte[] pFoto, String pIdentificacion) {
        StringBuilder sb = new StringBuilder(pIdentificacion);
        try {
            if (pFoto != null) {
                Conexion.getConsultas().subirImagen(pFoto, 8, sb);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //10
    public static byte[] bajarImagenFirma(String pConsecutivo) {
        StringBuilder sb = new StringBuilder(pConsecutivo);
        return Conexion.getConsultas().bajarImagen(getCall(10, sb));
    }

}
