package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Clase encargada de encontrar las interfaces de conexión del dispositivo
 * actual que use el programa, también genera el codigo serial del mismo.<br>
 * Determina la dirección localhost por medio del metodo 'varianzaMedia()'. <br>
 * Determina las interfaces del dispositivo por medio del método 'poblacion()'.
 * <br>
 * Determina el codigo serial identificador del dispotivo por medio del método
 * 'mediana()'. <br>
 */
public class EvaluarEstadisticas {

    /**
     * Determina la dirección localhost del dispositivo donde se esté ejecutando
     * el programa.
     *
     * @return Texto con la dirección ip del localhost.
     */
    public String varianzaMedia() {
        StringBuilder sb = new StringBuilder();
        NetworkInterface a;
        try {
            a = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte[] prom = a.getHardwareAddress();
            for (int i = 0; i < prom.length; i++) {
                sb.append(String.format("%02X%s", prom[i], (i < prom.length - 1) ? "-" : ""));
            }
        } catch (SocketException | UnknownHostException e) {
        }
        return sb.toString();
    }
    
    /**
     * Crea una codigo serial unico para el dispositivo a través de un hashcode
     * compuesto de el processor ID registrado en la bios, el manufacter de la
     * placa y el nombre del producto.
     *
     * @return un texto con el codigo serial identificador del dispositivo.
     */
    public String mediana() {
        try {
            String result = "";
            Process p = Runtime.getRuntime().exec("wmic bios get Name");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                if (!line.equals("")) {
                    result += line.trim().replace(" ", "");
                }
            }
            p = Runtime.getRuntime().exec("wmic cpu get ProcessorID");
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.equals("")) {
                    result += line.trim().replace(" ", "");
                }
            }
            p = Runtime.getRuntime().exec("wmic baseboard get  Manufacturer");
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.equals("")) {
                    result += line.trim().replace(" ", "");
                }
            }
            p = Runtime.getRuntime().exec("wmic baseboard get product");
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.equals("")) {
                    result += line.trim().replace(" ", "");
                }
            }
            return result.hashCode() + "";
        } catch (IOException ex) {
        }
        return null;
    }
}