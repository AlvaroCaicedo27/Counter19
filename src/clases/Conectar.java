package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase encargada de realizar la conexión a través de jdbc con la base de
 * datos.
 */
public class Conectar {

    //Instancia que representa la sesión de conexión con la base de datos.
    Connection conect = null;
    private String apuntador = null;

    //Método que crea la conexión
    @SuppressWarnings("unchecked")
    public Conectar(String username, String password) throws ConnectException, SQLException, IOException, ClassNotFoundException {
        if (!netIsAvailable()) {
            throw new ConnectException("El dispositivo no cuenta con ningùn tipo de conexiòn.");
        } else {
            try {
                String ip;
                String puerto;

                //Carga la información suministrada en el archivo properties dentro  en la ruta 'user.home'.
                String dataFolder = System.getProperty("user.home") + "\\";
                File archivo = new File(dataFolder + "a55a348c74ba99d7681f8a3498656894.properties");
                FileReader lector = new FileReader(archivo);
                BufferedReader reader = new BufferedReader(lector);

                //Lee la ip y la dirección allí suministrada.
                ip = reader.readLine();
                ip = ip.trim().replace("/", "");
                puerto = reader.readLine();
                puerto = puerto.trim().replace("/", "");
                apuntador = reader.readLine();
                if (apuntador != null) {
                    apuntador = apuntador.trim().replace("/", "");

                } else {
                    apuntador = "";
                }
                
                System.out.println(ip);
                System.out.println(puerto);
                System.out.println(apuntador);
                
                //Cierra los lectores del archivo.
                lector.close();
                reader.close();

                //Cargamos el Driver MySQL
                Class.forName("com.mysql.jdbc.Driver");

                //Creamos la propiedades de la conexión.
                Properties p = new Properties();
                p.setProperty("user", username);                //user
                p.setProperty("password", password);  //password          
                p.setProperty("MaxPooledStatements", "200");    //Establece el tamaño de la pool de statements para esta sesión en la base de datos.

                conect = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/analizador" + apuntador, p); // CONEXION LOCAL CON MARIA DB
            } catch (SQLException e) {
                throw e;
            } catch (IOException ex) {
                //Al no lograrse una conexión, tira una excepción IO.
                throw new IOException("No se ha indicado una dirección del servidor. Por favor, reinicie el programa.");
            } catch (ClassNotFoundException ex) {
                //Al no cargarse el driver para la conexión, tira una excepción ClassNotFound.
                throw new ClassNotFoundException("El controlador para la conexión con la base de datos no es el correcto.");
            }
        }
    }

    /**
     * Determina si el dispositivo actual está conectado a alguna red. Para más
     * detalles, revisar al clase 'EvaluarEstadisticas'.
     *
     * @return true si está conectado, false si no.
     */
    private static boolean netIsAvailable() {
        EvaluarEstadisticas prueba = new EvaluarEstadisticas();
        String mac = "";
        try {
            mac = prueba.varianzaMedia();
        } catch (Exception e) {
        }
        return !mac.equals("");
    }

    /**
     * @return instancia de conexión.
     */
    public Connection getConexion() {
        return conect;
    }

    /**
     * Método que retorna el apuntador junto con el nombre de la base de datos
     * al que se está conectado.
     *
     * @return String con el nombre de la base de datos seguido del apuntador.
     */
    public String getDatabase() {
        String result = "analizador";
        if (apuntador != null) {
            result += apuntador;
        }
        return result;
    }

    /**
     *
     * @return String del apuntador de la base de datos. (Este puede ser dev,qa
     * y certificaciones).
     */
    public String getApuntador() {
        return apuntador;
    }
}
