/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizador;
import com.fazecast.jSerialComm.*;
import analizador.Decodificador;
import interfaces.GUIPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author DESARROLLO
 */
public class Puerto {
    
    
    private static StringBuilder receivedData = new StringBuilder();
    private static GUIPrincipal gui;
    
 
    
    
    
  

    public static void main(String[] args) {

        gui = new GUIPrincipal();
              

    }

    public static StringBuilder getReceivedData() {
        return receivedData;
    }

    public static void conectar() {
        SerialPort[] ports = SerialPort.getCommPorts();

        // Imprimir los nombres de los puertos serie disponibles
        System.out.println("Puertos Serie Disponibles:");
        for (SerialPort port : ports) {
            System.out.println(port.getSystemPortName());
        }

        // Seleccionar el puerto serie deseado (cambia el índice según sea necesario)
        try{
            SerialPort selectedPort = ports[2];

            // Configurar los parámetros de comunicación del puerto serie (ajusta según las especificaciones del dispositivo)
            selectedPort.setComPortParameters(9600, 7, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);

            // Abrir el puerto serie seleccionado
          
            while (true) {
                if (selectedPort.openPort()) {

                    // Variable para almacenar los datos recibidos
                    receivedData = new StringBuilder();

                    // Crear un hilo para escuchar los datos entrantes desde el puerto serie
                    selectedPort.addDataListener(new SerialPortDataListener() {
                        @Override
                        public int getListeningEvents() {
                            return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                        }

                        @Override
                        public void serialEvent(SerialPortEvent event) {
                            if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                                byte[] newData = new byte[selectedPort.bytesAvailable()];
                                selectedPort.readBytes(newData, newData.length);
                                // Procesar los datos recibidos y almacenarlos en la variable
                                String dataString = new String(newData);
                                receivedData.append(dataString);

                            }
                        }
                    });

                } else {
                    
                    return;
                }
            
              
            // Esperar un poco antes de terminar el programa
            try {
                Thread.sleep(16000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Mostrar los datos recibidos
            System.out.println("Datos recibidos: " + receivedData.toString());

            Decodificador.datos();
            

        }
    

    }catch (Exception e) {
            // Captura cualquier excepción y muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexion");
            e.printStackTrace();
            return; // Sale del método en caso de error
        
    }
    }
    
}
