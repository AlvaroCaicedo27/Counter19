package clases;

import entidades.Conexion;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Consultas {

    //Constante
    public static String SP_CALL = "call SP_Consultas";
    public static String SP_CALL_IMAGEN = "call SP_SubirImagen";

    //Atribuos
    Conectar con;
    PreparedStatement ps;
    ResultSet res;

    //Constructor
    //También es el encargado de crear la conexión con la base de datos usando la clase Conectar.
    public Consultas(String username, String password) throws ConnectException, SQLException, IOException, ClassNotFoundException {
        con = new Conectar(username, password);
    }

    //Métodos get
    public Conectar getCon() {
        return con;
    }

    public Conectar getConectar() {
        return con;
    }

    public Consultas darConsultas() {
        return this;
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

    /**
     * Método sobrecargado de getCallImagen.
     *
     * @param pFlag Número del flag asignado al método.
     * @param pParams Parámetros para que van para el prodecimiento almacenado.
     * @return Instrucción en string del proceso almacenado que completa la
     * consulta.
     */
    public static String getCallImagen(int pFlag, StringBuilder pParams) {
        if (pParams != null) {
            return SP_CALL_IMAGEN + "(" + pFlag + "," + '"' + pParams.toString() + '"' + ",?,'" + Conexion.ANIO + "')";
        } else {
            return SP_CALL_IMAGEN + "(" + pFlag + ",'',?,'" + Conexion.ANIO + "')";
        }
    }

    /**
     * Método para facilitar la consulta del procedimiento almacenado
     * controlador de esta clase. En este caso, facilita la obteción de imagenes
     * almacenadas en la base de datos.
     *
     * @param pFlag Número del flag asignado al método.
     * @return Instrucción en string del proceso almacenado que completa la
     * consulta.
     */
    public static String getCallImagen(int pFlag) {
        return SP_CALL_IMAGEN + "(" + pFlag + ",'',?,'" + Conexion.ANIO + "')";
    }
    
    //TODO, HACER GET CALL CON AÑO DE PARAMETRO.

    /**
     * Método simplicado que permite ejecutar cualquier instrucción SQL que
     * venga por parámetro. El método no retorna ningun valor, por lo que suele
     * ser usado con método update o delete.
     *
     * En caso de algún error, se atrapa la excepción y se muestra por consola.
     *
     * @param pSqlInstruccion - Instrucción sql que se quiere ejecutar a través
     * de la conexión actual del cliente.
     */
    public void ejecutarInstruccion(String pSqlInstruccion) {
        try {
            ps = con.getConexion().prepareStatement(pSqlInstruccion);
            //System.out.println(pSqlInstruccion);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }
    }

    public void ejecutarInstruccion(int pNum, String[] pDatos, int pYear ) {
        StringBuilder sb = Conexion.getParamsFromArray(pDatos);
        this.ejecutarInstruccion(getCall(pNum, sb, pYear));
    }

    /**
     * Método que facilita la inserción de datos en una tabla de la base de
     * datos.
     *
     * @param datos - Areglo que corresponde a la información que se quiere
     * insertar.
     * @param insertar - Instrucción SQL INSERT que indica en qué campos va la
     * información.
     * @return true si la información se insertó correctamente, false de lo
     * contrario.
     */
    public boolean insertar(String[] datos, String insertar) {
        boolean inserto = false;
        try {
            ps = con.getConexion().prepareStatement(insertar);

            for (int i = 0; i < datos.length; i++) {
                ps.setString(i + 1, datos[i]);
            }

            ps.execute();
            ps.close();
            inserto = true;
        } catch (Exception e) {

            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, e.getMessage() + " (004)\n" /*+ insertar */);
        }

        return inserto;
    }

    /**
     * Método que facilita la obtención de información de la base de datos a
     * través de una consulta.
     *
     * @param colum - Arreglo de las columnas de una tabla a las cuales se les
     * desea sacar información.
     * @param tabla - Nombre de la tabla de donde se obtendrá la información.
     * @param sq1 - Instrucción SQL de tipo SELECT.
     * @return Object[][] el cual contiene los datos por filas de la información
     * solicitada en la instruccion. En caso de error, el método retorna un
     * aviso indicando las falencias de la consulta.
     */
    public Object[][] getTabla(String colum[], String tabla, String sq1) {

        String col[] = new String[colum.length];

        try {
            ps = con.getConexion().prepareStatement(sq1);
            res = ps.executeQuery();

            int entradas = 0;
            if (res.last()) {
                entradas = res.getRow();
                res.beforeFirst();
            }

            Object[][] data = new String[entradas][colum.length];

            int i = 0;
            while (res.next()) {
                for (int j = 0; j < colum.length; j++) {
                    col[j] = res.getString(colum[j]);
                    data[i][j] = col[j];
                }
                i++;
            }

            res.close();

            return data;
        } catch (Exception e) {
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }

            if (!e.getMessage().equals("0")) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " (001)");
            }
            return new Object[0][0];
        }
    }

    /**
     * Método sobrecargado de getTabla que no incluye el paráemtro del nombre de
     * la tabla.
     *
     * @param colum - Arreglo de las columnas de una tabla a las cuales se les
     * desea sacar información.
     * @param sq1 - Instrucción SQL de tipo SELECT.
     * @return Object[][] el cual contiene los datos por filas de la información
     * solicitada en la instruccion. En caso de error, el método retorna un
     * aviso indicando las falencias de la consulta.
     */
    public Object[][] getTabla(String colum[], String sq1) {

        String col[] = new String[colum.length];

        try {
            ps = con.getConexion().prepareStatement(sq1);
            res = ps.executeQuery();

            int entradas = 0;
            if (res.last()) {
                entradas = res.getRow();
                res.beforeFirst();
            }

            Object[][] data = new String[entradas][colum.length];

            int i = 0;
            while (res.next()) {
                for (int j = 0; j < colum.length; j++) {
                    col[j] = res.getString(colum[j]);
                    data[i][j] = col[j];
                }
                i++;
            }

            res.close();

            return data;
        } catch (Exception e) {
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }

            if (!e.getMessage().equals("0")) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " (001)");
            }
            return new Object[0][0];
        }
    }

    /**
     * Método que facilita la actualización de información de una tabla en la
     * base de datos.
     *
     * @param datos - Corresponde a los datos nuevos que se reemplazará en las
     * filas indicadas por la instrucción por parámetros.
     * @param insetar - Instrucción SQL tipo UPDATE.
     * @return true si se completó la actualización, false si hubo un error.
     *
     * En caso de error, el método retorna un aviso indicando las falencias de
     * la consulta.
     */
    public boolean modificar(String[] datos, String insetar) {
        boolean modifico = false;
        try {
            ps = con.getConexion().prepareStatement(insetar);
            for (int i = 0; i < datos.length; i++) {
                ps.setString(i + 1, datos[i]);
            }
            ps.execute();
            ps.close();
            modifico = true;
        } catch (Exception e) {
            e.printStackTrace();
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Algo paso: " + e.getMessage());
        }

        return modifico;
    }

    /**
     * Método que facilita la inserción de imágenes en la base de datos.
     *
     * @param pPhoto - Imagen en arreglo de bytes que se desea subir a una
     * columna espcifica.
     * @param instruccion - Instrucción SQL de tipo UPDATE que indica donde se
     * subirá la imagen. En caso de error, el método retorna un aviso indicando
     * las falencias de la consulta.
     */
     public void subirImagen(byte[] pPhoto, String pCodigo, int pFlag) {
        try {
            String instruccion = getCallImagen(pFlag, new StringBuilder(pCodigo));
            ps = con.getConexion().prepareStatement(instruccion);
            ps.setBinaryStream(1, new ByteArrayInputStream(pPhoto), pPhoto.length);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte.\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Algo paso: " + e.getMessage());
        }
    }

    /**
     * Método sobrecargado de subirImagen que facilita la inserción de imagenes
     * a través de procedimientos almancenados. La imagen a subir se recibe por
     * parametro como también el flag del procedimiento encargado de subir esa
     * imagen.
     *
     * @param pPhoto - Imagen en arreglo de bytes que se desea subir a una
     * columna espcifica.
     * @param pFlag Número del flag asignado al procedimiento almacenado que
     * sube la imagen.
     */
    public void subirImagen(byte[] pPhoto, int pFlag) {
        try {
            String instruccion = getCallImagen(pFlag);
            ps = con.getConexion().prepareStatement(instruccion);
            ps.setBinaryStream(1, new ByteArrayInputStream(pPhoto), pPhoto.length);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Algo paso: " + e.getMessage());
        }
    }

    /**
     * Método sobrecargado de subirImagen que facilita la inserción de imagenes
     * a través de procedimientos almancenados. La imagen a subir se recibe por
     * parametro como también el flag del procedimiento encargado de subir esa
     * imagen. Este metodo permite agregar parámetros para ser leidos por el
     * procedimiento almacenados.
     *
     * @param pPhoto - Imagen en arreglo de bytes que se desea subir a una
     * columna espcifica.
     * @param pFlag - Número del flag asignado al procedimiento almacenado que
     * sube la imagen.
     * @param pParams - Paramétros adicionales que quieren ser incluidos al
     * momento de ejecutar el procedimiento almanceado. Por lo general
     * proporcionan información de filtrado al procedimiento.
     */
    public void subirImagen(byte[] pPhoto, int pFlag, StringBuilder pParams) {
        try {
            String instruccion = getCallImagen(pFlag, pParams);
            ps = con.getConexion().prepareStatement(instruccion);
            ps.setBinaryStream(1, new ByteArrayInputStream(pPhoto), pPhoto.length);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Algo paso: " + e.getMessage());
        }
    }

    /**
     * Método que facilita la obtención de una imagen de la base de datos.
     *
     * @param instruccion - Instrucción de tipo SELECT que indica de donde se
     * bajará la imagen almacenada.
     * @return Arreglo de bytes que corresponde a la imagen obtenida.
     *
     * En caso de error, el método retorna un aviso indicando las falencias de
     * la consulta.
     */
    public byte[] bajarImagen(String instruccion) {
        try {
            ps = con.getConexion().prepareStatement(instruccion);
            res = ps.executeQuery();
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
            //Excepción que se atrapa en caso de que se haya pedido la conexión con el servidor. 
            //Esta excepción cierra el programa.
            if (e.getMessage() != null) {
                if (e.getMessage().contains("Communications link failure")) {
                    JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.\nEn caso de que el error persita:\n- Verificar la conexión del dispositivo.\n- Reiniciar el software.\n- Comunicarse con soporte (comerial@controlcaem.com).\nEl programa se cerrará.", "Conexión perdida", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
            }
            return null;
        }
    }
}
