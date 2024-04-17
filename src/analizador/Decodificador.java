/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;
import analizador.Puerto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.util.Date;
import analizador.Examen;
import javax.swing.JOptionPane;

/**
 *
 * @author DESARROLLO
 */
public class Decodificador {


    /**
     * @param args the command line arguments
     */
    
    
 
       public static void main(String[] args) {
        

        
    }
       
       public static void datos(){
           String mensaje = Puerto.getReceivedData().toString();
        
        List<String> grupos = dividirMensaje(mensaje);
        
        
       List<Examen> examenes = new ArrayList<>();
        int contador = 0;

         
        for (int i = 0; i < grupos.size(); i += 22) { 
            String codigo = grupos.get(i);
            String fecha = grupos.get(i + 1);
            String wbc = grupos.get(i + 2);
            String lymph = grupos.get(i + 3);
            String mid = grupos.get(i + 4);
            String gran = grupos.get(i + 5);
            String lymph1 = grupos.get(i + 6);
            String mid1 = grupos.get(i + 7);
            String gran1 = grupos.get(i + 8);
            String rbc = grupos.get(i + 9);
            String hgb = grupos.get(i + 10);
            String mchc = grupos.get(i + 11);
            String mcv = grupos.get(i + 12);
            String mch = grupos.get(i + 13);
            String rdwcv = grupos.get(i + 14);
            String hct = grupos.get(i + 15);
            String plt = grupos.get(i + 16);
            String mpv = grupos.get(i + 17);
            String pdw = grupos.get(i + 18);
            String pct = grupos.get(i + 19);
            String rdwsd = grupos.get(i + 20);
             String hola = grupos.get(i + 21);
          

            Examen examen = new Examen(codigo + "-1", fecha, wbc, lymph, mid, gran, lymph1, mid1, gran1, rbc, hgb, mchc, mcv, mch, rdwcv, hct, plt, mpv, pdw, pct, rdwsd,"hola");
            examenes.add(examen);

            // Imprimir los resultados de cada examen
            System.out.println("Examen #" + (contador + 1) + ":");
            System.out.println(examen.toString());
            contador++;
        }
         
       CrearArchivo(examenes);
       }
    
       
    public static List<String> dividirMensaje(String mensaje) {
        List<String> grupos = new ArrayList<>();
        int[] divisiones = {10, 13,4, 4, 4, 4, 3, 3, 3, 3, 3, 4, 4, 4, 3, 3, 4, 3, 3, 4, 3,2365};
        int index = 0;
        
                
 
        while (index < mensaje.length()) {
        for (int i = 0; i < divisiones.length; i++) {
          
            int longitud = divisiones[i];
            
             
            if (index + longitud <= mensaje.length()) {
                String grupo = mensaje.substring(index, index + longitud);
               
                
                 if( i == 0){
                    String grupo1 = grupo.substring(8);
                   
       
                    grupo = grupo1;
                     
                }
                 else if(i == 1){
                      String grupo1 = grupo.substring(1);
                      SimpleDateFormat formato = new SimpleDateFormat("MMddyyyyHHmm");

        try {
            // Convertir el string a objeto de tipo Date
            Date fecha = formato.parse(grupo1);
            grupo = fecha.toString();
            
            

        } catch (ParseException e) {
            e.printStackTrace();             
        }
                 }
               
                else if (i == 16 ) {
                    
                   int sum = Integer.parseInt(grupo);
                   
                   grupo = String.valueOf(sum);
                }
                
                else if(i == 9){
                    
                    DecimalFormat formato = new DecimalFormat("#,##0,00");
                    
                    double numero = Double.parseDouble(grupo); // Dividir después de convertir a double
                     // Reemplazar la coma con un punto
                    
                    String numeroConDecimales = formato.format(numero);
                    grupo = numeroConDecimales;
                    
                
                }else if(i == 19){
                    
                    double numero = Double.parseDouble(grupo) /10000; // Dividir después de convertir a double
                     
                    grupo = String.valueOf(numero);
                } else if (i == 21){
                    String grupo1 = grupo.substring(2365);

                    grupo = grupo1 + "///////////";
                }
                   
                else{
                     if (grupo.startsWith("00")) {
                         // Si la parte comienza con dos ceros seguidos de uno o más dígitos
                         double numero = Double.parseDouble(grupo.substring(2)) / 10;
                         String mensajeModificado = String.valueOf(numero);
                         grupo = mensajeModificado;

                     }else if(grupo.startsWith("0")){
                          double numero = Double.parseDouble(grupo.substring(1)) / 10;
                         String mensajeModificado = String.valueOf(numero);
                         grupo = mensajeModificado;
                     }else{
                          double numero = Double.parseDouble(grupo) / 10;
                         String mensajeModificado = String.valueOf(numero);
                         grupo = mensajeModificado;
                     }
                }
                grupos.add(grupo);
                index += longitud;
               
                
            } else {
                // Manejo si la longitud especificada excede la longitud restante del mensaje
                grupos.add(mensaje.substring(index));
                break;
            }
          
           
        }
        
         
        
         }
        return grupos;

    }
    
    public static void CrearArchivo(List<Examen> examenes){
        
       try {
           
           for (Examen examen : examenes) {
        
            String nombreArchivo = examen.getCodigo() + ".txt"; // Generar nombre de archivo único basado en el código del examen
            BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo));
            escritor.write("Id: " + examen.getCodigo() + "\n" + "Fecha: " + examen.getFecha() + "\n" + "WBC: " + examen.getWbc() + "\n" + "LYMPH#: " + examen.getLymph()+
                    "\n" + "MID#: " + examen.getMid()+ "\n" + "GRAN#: " + examen.getGran()+ "\n" + "LYMPH%: " + examen.getLymph1()+ "\n" + "MID%: " + examen.getMid1() +
                    "\n" + "GRAN%: " + examen.getGran1()+ "\n" + "RBC: " + examen.getRbc()+ "\n" + "HGB: " + examen.getHgb()+ "\n" + "MCHC: " + examen.getMchc() +
                    "\n" + "MCV: " + examen.getMcv()+ "\n" + "MCH: " + examen.getMch() + "\n" + "RDWCV: " + examen.getRdwcv()+ "\n" + "HCT: " + examen.getHct() + 
                    "\n" + "PLT: " + examen.getPlt()+ "\n" + "MPV: " + examen.getMpv() + "\n" + "PDW: " + examen.getPdw()+ "\n" + "PCT: " + examen.getPct() + "\n" + "RDWSD: " + examen.getRdwsd());
            escritor.close();
               JOptionPane.showMessageDialog(null, "Archivo '" + nombreArchivo + "' creado exitosamente.");
        }
    } catch (IOException e) {
        System.err.println("Error al escribir en el archivo: " + e.getMessage());
    }
    }
    
}
