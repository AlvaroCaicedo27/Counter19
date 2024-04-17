package interfaces;

import analizador.Puerto;
import clases.EncDec;
import clases.EvaluarEstadisticas;
import clases.RoundJPass;
import clases.RoundJTextField;
import static com.sun.tools.javac.tree.TreeInfo.args;
import entidades.Conexion;

import entidades.ConsultasLogin;
import entidades.ConsultasSede;
import entidades.ConsultasUsuarios;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;



/**
 * Clase que genera la vista de 'JFLogin'. Esta vista realiza verificación
 * conexión con el servidor, licencias del software y última versión del
 * software. Además es la encargada de realizar la conexión de un usuario para
 * poder ingresar al sistema.
 */
public class JFLogin extends javax.swing.JFrame {

    //Atributos
    public static Color COLOR_BOTON_ENCIMA = new Color(0,51,153);
    public static Color COLOR_BOTON_FUERA = new Color(38,101,181);

    private String estadisticas;
    private ConsultasLogin consultas;
    private static String nombreUsuario;
   

    //Constructor
    public JFLogin() {
        loadVersion();
    }
     
    public static void establecerNombreUsuario(String nombre) {
        nombreUsuario = nombre;
    }

    // Método para obtener el nombre de usuario
    public static String obtenerNombreUsuario() {
        return nombreUsuario;
    }
    
  
    /**
     * Constructor sobrecargado que incluye un mensaje. Este mensaje es una
     * aviso con la razón por la que el software fue cerrado y enviado al Login.
     *
     * @param pMensaje información a enseñar al usuario.
     */
    public JFLogin(String pMensaje) {
        JOptionPane.showMessageDialog(null, pMensaje, "Sesión cerrada", -1);
        loadVersion();
    }
 
    /**
     * Método encargado de llamar la verificación de tiempo, obtener el ID del
     * equipo y mandarlo a verficar. Si la licencia es inválida, cierra el
     * programa.
     */
    public void loadKey() {
        //Verificar tiempos del servidor y del cliente.
        if (verificarTiempos()) {

            EvaluarEstadisticas temp = new EvaluarEstadisticas();
            //Se Obtiene la identificación del equipo por medio de la clase EvaluarEstadisticas.
            estadisticas = temp.mediana();

            //Se valida la licencia del equipo.
            //Si retorna false, significa que la licencia no es válida.
            

        } //Si los tiempos del servidor no retornan true
        else {
            //Avisa al usuario y cierra el programa.
            JOptionPane.showMessageDialog(null, "La hora del equipo no corresponde a la del servidor.\nAsegúrese de configurar correctamente la fecha y hora de su equipo.");
            System.exit(0);
        }
    }

    /**
     * Método encargado de validar la licencia del equipo que entre por
     * parámetro. La respuesta de la verificación se da en String, y dependiendo
     * de ella se crean avisos para el usuario.
     *
     * @param pEquipo ID del equipo a verificar su licencia.
     * @return true si el equipo cuenta con licencia válida, false si no.
     */
    

    /**
     * Método encargado de cargar la versión del software (registrada en la base
     * de datos) y cargar el nombre de la sede sobre el login.
     *
     * El método carga 2 versiones que previamente pasa a revisar: versionNube
     * -> Se refiere a la versión que se encuentra registrada en la base de
     * datos. Conexion.VERSION -> Se refiere a la versión que se registró
     * directamente sobre el software.
     *
     * Si ambas versiones son iguales, significa que el software está
     * actualizado.
     */
    public void loadVersion() {
        try {
            //Crea una conexión por ping.
            Conexion.ping("PING", EncDec.comprobarEscritura("123", "PING"));
            //Obtiene la última versión según la base de datos.
            String versionNube = ConsultasLogin.getLastVersion();
            //Inicia los componentes de la vista
            initComponents();
            //Ubica el frame en el centro de la pantalla.
            this.setLocationRelativeTo(null);

            //Carga la versión del software según lo que haya registrado el desarrollador.
            labVersion.setText(Conexion.VERSION);
            

            //Cierra la conexión de ping previamente creada.
            Conexion.forcedCloseConexion();

            //Verifica si la versión de la base de datos y la del programa son iguales.
            if (!versionNube.equals(Conexion.VERSION)) {
                //Si no son iguales, avisa al usuario de que existe una nueva versión disponible.
                //Muestra un aviso de confirmación que permite al usuario dirigirse a la página de descarga del software.
                if (JOptionPane.showConfirmDialog(null, "Nueva versión del software disponible.\n¿Desea descargarla?\n\n(Se abrirá una ventana en el navegador.)", "Nueva versión de FENIXLAB", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                    //Si acepta, se abrirá la página con el navegador por defecto del sistema.
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                        try {
                            Desktop.getDesktop().browse(new URI("https://www.notion.so/Actualizaciones-FenixLab-3fad741642f2427ca62d655f38425236"));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Ocurrió un error abriendo la página web. Por favor, notificarlo con el equipo de desarrollo.\n comercial@controlcaem.com");
                        }
                    }
                }
            }

            loadKey();

        } //Si hubo un error intentando obtener la versión del software
        catch (Exception ex) {

            ex.printStackTrace();
            String mensaje = ex.getMessage();
            //Si el mensaje de la excepción contiene 'No se ha indicado una dirección'.
            //Significa que no se a configurado el apuntador hacia la base de datos del software.
            if (mensaje.contains("No se ha indicado una dirección")) {
                //Elimina y escode los componentes de la vista del login.
                this.removeAll();
                this.dispose();
                this.setUndecorated(true);

                //Se avisa al usuario de que debe configurar la dirección del servidor. 
                JOptionPane.showMessageDialog(null, "No se ha encontrado la dirección del servidor.\nPor favor, regístrelo dentro de la siguiente ventana.", "ERROR", JOptionPane.ERROR_MESSAGE);
                //Se abre al ventana para realizar al configuración de la dirección del servidor.
                PopUpAgregarDireccion temp = new PopUpAgregarDireccion();
                temp.setLocationRelativeTo(null);
                temp.setVisible(true);
                temp.setFocusable(true);
                //Se agrega un evento que, al cerrar la ventana de configuración, reinicia el programa.
                temp.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        //Método que 'reinicia' el programa.
                        reOpen();
                    }
                });
            }

            //Si el mensaje de la excepción contiene 'Any packets from the server'.
            //Significa que el apuntar hacia la base de datos está configurado, pero no corresponde a la dirección
            //de la base de datos o no se encuentra en línea.
             if (mensaje.contains("any packets from the server")) {
                //Elimina y escode los componentes de la vista del login.
                this.removeAll();
                this.dispose();
                this.setUndecorated(true);

                //Se avisa al usuario de que debe volver a configurar la dirección del servidor.
                JOptionPane.showMessageDialog(null, "La dirección proporcionada en la configuración no corresponde a la del servidor.\nPor favor, regístrelo nuevamente.", "ERROR", JOptionPane.ERROR_MESSAGE);
                //Se abre al ventana para realizar al configuración de la dirección del servidor.
                PopUpAgregarDireccion temp = new PopUpAgregarDireccion();
                temp.setLocationRelativeTo(null);
                temp.setVisible(true);
                temp.setFocusable(true);
                //Se agrega un evento que, al cerrar la ventana de configuración, reinicia el programa.
                temp.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        //System.out.println("reOpen");
                        reOpen();
                    }
                });
            }
        }
    }

    /**
     * Método encargado de comparar el tiempo del equipo local con el de la base
     * de datos. Esto se realiza para evitar inconsistencias en el ingreso de
     * datos y para hacer la verificación de licencias más segura.
     *
     * @return true si la diferencia de tiempo entre el equipo y el servidor no
     * supera los 2 minutos absolutos.
     */
    public boolean verificarTiempos() {
        //Obtiene la fecha y hora del servidor.
        Date fechaServidor = tiempoServidor();
        //Verifica que no esté null
        if (fechaServidor != null) {

            //Se obtiene la fecha del equipo.
            GregorianCalendar time = new GregorianCalendar();
            Date fechaEquipo = time.getTime();

            //Se guarda la diferencia de las fechas en un long.
            long diff = fechaServidor.getTime() - fechaEquipo.getTime();
            //Se convierte la diferencia a minutos.
            long diffMinutes = diff / (60 * 1000);
            int minutes = (int) diffMinutes;

            //Se realiza la verificación.
            return minutes > -2 && minutes < 2;
        } //Si es null, significa que hubo un problema de conexión y no se logró obtener la fecha y hora del servidor.
        //Debido a que no se puede comprobar, se cierra el programa.
        else {
            JOptionPane.showMessageDialog(null, "No se logró determinar la fecha del servidor.");
            System.exit(0);
            return false;
        }
    }

    
   
    
   
    /**
     * Método que obtiene la fecha del servidor, la convierte a date y la
     * retorna.
     *
     * @return fecha y hora del servidor en Date.
     */
    public Date tiempoServidor() {
        try {
            //Crea la conexión por ping.
            Conexion.ping("PING", EncDec.comprobarEscritura("123", "PING"));
            //Obtiene la fecha del servidor.
            String strFechaSistema = ConsultasLogin.getDateFromDatabase();
            //Cierra la conexión por ping.
            Conexion.forcedCloseConexion();
            //Retorna la fecha del servidor.
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strFechaSistema);
        } //Si entra, significa que hubo un error intentando obtener la fecha y hora del servidor.
        catch (Exception e) {

            e.printStackTrace();
            //Se avisa al usuario y se cierra el programa. Debido a que la hora del servidor es importante
            //para validar las licencias de los equipos.
            JOptionPane.showMessageDialog(null, "Ocurrió un error mientras se intentaba acceder a la fecha del servidor.\nEl programa se cerrará.");
            Conexion.forcedCloseConexion();
            return null;
        }
    }

    /**
     * Método que genera una nueva instancia de la vista de login para reiniciar
     * el proceso de verificación de estadisticas, etc. También, cierra la vista
     * de login actual.
     */
    public void reOpen() {
        JFLogin temp = new JFLogin();
        temp.setLocationRelativeTo(null);
        temp.setVisible(true);
        this.dispose();
    }

    /**
     * Método sobrecargado de 'login' que realiza la verificación de la
     * información de nombre de usuario y contraseña directamente con la base de
     * datos. Desde aquí se determina si el nombre de usuario y contraseña son
     * correctos, si el usuario ya cuenta con una sesión iniciada o si se
     * presentó cualquier otro error intentando loggearse.
     *
     * @param pUsername Nombre de usuario a loggearse.
     * @param pPass Contraseña del usuario.
     */
    public void login(String pUsername, String pPass) {
        try {
            //Se intenta crea la instancia de conexión, esta es la encargada de realizar el proceso de 'inicio de sesión'
            //En caso de algun error, genera una excepción que posteriormente es atrapada.
            Conexion.getConexion(pUsername, EncDec.comprobarEscritura(pPass, pUsername));
            //En caso de que no generara ninguna excepción, significa que el loggeo fue limpio.
            //Se procede a crear el menú principal del software.
            
            if(Conexion.getUserActiveStatus()){
                GUIPrincipal vista = new GUIPrincipal();
                vista.setTitle("FenixLAB(Counter19) V"+Conexion.VERSION+" | "+ Conexion.getUsername() );
                //Se muestra.
                
                vista.setVisible(true);
                //Se cierra la ventana de login.
                this.dispose();
                 
                
            }
            else
            {
                Conexion.forcedCloseConexion();
                JOptionPane.showMessageDialog(null, "Este usuario se encuentra inactivo.\nPor favor, comuníquese con su coordinador con el equipo de soporte.\n\nContacto: comercial@controlcaem.com", "USUARIO INACTIVO", JOptionPane.WARNING_MESSAGE);
                txtUsuario.requestFocus();
            }
            
        } catch (Exception e) {
            //Se obtiene el mensaje de la excepción para entender la razón por la que el inicio de sesión falló.
            String mensaje = e.getMessage();

            //Si el mensaje contiene 'Access denied for user' fue un error en los datos ingresados.
            if (mensaje.contains("Access denied for user")) {
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Contraseña o nombre de usuario incorrecto.", "Error de inicio de sesión", JOptionPane.WARNING_MESSAGE);
            } //Si el mensaje contiene 'max_user_connections' significa que el usuario ya ingreso en otro sistema y su sesión sigue activa.
            else if (mensaje.contains("max_user_connections")) {
                JOptionPane.showMessageDialog(null, "El usuario ha iniciado sesión previamente en otro dispositivo.\nDebe cerrar sesión para poder continuar.\nSi el error persiste intente reicionar el dispositivo\no contactar el equipo de soporte. (comercial@controlcaem.com) ", "Sesión iniciada en otro dispositivo.", JOptionPane.WARNING_MESSAGE);
            } //Si no fue ninguno de los anteriores, se notifica al usuario y se da la información para contactar al soporte del software.
            else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al iniciar sesión.\n\n" + e.getMessage(), "CONTACTAR SOPORTE", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Método encargado de verificar los campos de nombre de usuario y
     * contraseña para realizar el inicio de sesión del usuario.
     */
  public void login() {
        //Si el campo del nombre no está vacio. Obtiene la contraseña ingresada e intenta realizar el inicio de sesión.
        if (!txtUsuario.getText().equals("")) {
            //Obtiene la contraseña ingresada por el usario.
            String tempContraseña = String.copyValueOf(txtContrasenia.getPassword()).trim();
            //Limpia el color de fondo del campo de nombre de usuario.
            //En caso de que se encuentre en color rosado.
            txtUsuario.setBackground(Color.white);
            
            
        
        
            //Si la contraseña ingresada no fue vacío, se realiza el intento de inicio de sesión con el método sobrecargado 'login'
            if (!tempContraseña.isEmpty()) {
                
                txtContrasenia.setBackground(Color.white);
                login(txtUsuario.getText().trim(), String.copyValueOf(txtContrasenia.getPassword()).trim());
                 
            
                 }
          
            
            else{
                txtContrasenia.requestFocus(true);
                txtContrasenia.setBackground(Color.pink);
            }
      
            //Si está vacío, dirige al usuario al campo para que ingrese la información
        //y lo resalta de color rosado.
        } else {
            txtUsuario.requestFocus(true);
            txtUsuario.setBackground(Color.pink);
        }
    }
  
  
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelLogin = new javax.swing.JPanel();
        labVersion = new javax.swing.JLabel();
        txtContrasenia = new RoundJPass(0);
        labConstrasenia = new javax.swing.JLabel();
        txtUsuario = new RoundJTextField(0);
        labUsuario = new javax.swing.JLabel();
        labLogin = new javax.swing.JLabel();
        LOGEORAPIDOBORRAR = new javax.swing.JLabel();
        labBotonCerrarVentana = new javax.swing.JLabel();
        labBackground = new javax.swing.JLabel();
        labNombreSede = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo_24.png")));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Source Sans Pro", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(1187, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 60));

        panelLogin.setBackground(new java.awt.Color(255, 255, 255));

        labVersion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labVersion.setText("1.0.0");
        labVersion.setToolTipText("Versión del software");
        labVersion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labVersionMousePressed(evt);
            }
        });

        txtContrasenia.setToolTipText("Contraseña...");
        txtContrasenia.setName("txtContrasenia"); // NOI18N
        txtContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseniaActionPerformed(evt);
            }
        });
        txtContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyTyped(evt);
            }
        });

        labConstrasenia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labConstrasenia.setText("Contraseña");
        labConstrasenia.setToolTipText("");

        txtUsuario.setToolTipText("Nombre de usuario...");
        txtUsuario.setName("txtUsuario"); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        labUsuario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labUsuario.setText("Usuario");

        labLogin.setBackground(new java.awt.Color(38, 101, 181));
        labLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labLogin.setForeground(new java.awt.Color(255, 255, 255));
        labLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labLogin.setText("Iniciar Sesión");
        labLogin.setToolTipText("");
        labLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        labLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labLogin.setOpaque(true);
        labLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labLoginMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(labVersion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labConstrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labConstrasenia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(labLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGap(83, 83, 83)
                .addComponent(labVersion)
                .addContainerGap())
        );

        getContentPane().add(panelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 320, 380));

        LOGEORAPIDOBORRAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LOGEORAPIDOBORRARMousePressed(evt);
            }
        });
        getContentPane().add(LOGEORAPIDOBORRAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 630, 160, 70));

        labBotonCerrarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_cerrarBorde_40.png"))); // NOI18N
        labBotonCerrarVentana.setToolTipText("Cerrar programa");
        labBotonCerrarVentana.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        labBotonCerrarVentana.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        labBotonCerrarVentana.setIconTextGap(0);
        labBotonCerrarVentana.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        labBotonCerrarVentana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labBotonCerrarVentanaMousePressed(evt);
            }
        });
        getContentPane().add(labBotonCerrarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, 40, 40));

        labBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Fenix Lab (Analizador).png"))); // NOI18N
        getContentPane().add(labBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        labNombreSede.setFont(new java.awt.Font("Source Sans Pro", 1, 13)); // NOI18N
        labNombreSede.setText("---");
        labNombreSede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labNombreSedeMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labNombreSedeMousePressed(evt);
            }
        });
        getContentPane().add(labNombreSede, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, 171, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseniaActionPerformed
    }//GEN-LAST:event_txtContraseniaActionPerformed

    private void txtContraseniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyPressed

        //Al presionar 'Enter' en el campo de la contraseña, se intenta hacer un inicio de sesión.
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             SwingUtilities.invokeLater(() -> {
        login();
        
        });
        
        new Thread(() -> {
         Puerto.conectar();
         Puerto.getReceivedData();
        }).start();
        }

    }//GEN-LAST:event_txtContraseniaKeyPressed

    private void txtContraseniaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyTyped

        char c = evt.getKeyChar();
        //Cualquier caracter escrito se pasa a mayúsculas.
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

    }//GEN-LAST:event_txtContraseniaKeyTyped

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed

        //Al dar 'Enter' en el campo del nombre de usuario.
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el campo de la contraseña no está vacío, realiza el loggueo.
            if (!String.valueOf(txtContrasenia.getPassword()).equals("")) {
                login(txtUsuario.getText().trim(), String.copyValueOf(txtContrasenia.getPassword()).trim());
            } //Si no, ubica al usuario en el campo de la contraseña, para que la ingrese.
            else {
                txtContrasenia.requestFocus();
            }
        }

    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped

        char c = evt.getKeyChar();
        //Cualquier caracter escrito se pasa a mayúsculas.
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }

    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void labBotonCerrarVentanaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labBotonCerrarVentanaMousePressed
        System.exit(0);
    }//GEN-LAST:event_labBotonCerrarVentanaMousePressed

    private void labVersionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labVersionMousePressed
    }//GEN-LAST:event_labVersionMousePressed

    private void LOGEORAPIDOBORRARMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LOGEORAPIDOBORRARMousePressed
    }//GEN-LAST:event_LOGEORAPIDOBORRARMousePressed

    private void labLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labLoginMouseEntered
        labLogin.setBackground(COLOR_BOTON_ENCIMA);
    }//GEN-LAST:event_labLoginMouseEntered

    private void labLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labLoginMouseExited
        labLogin.setBackground(COLOR_BOTON_FUERA);
    }//GEN-LAST:event_labLoginMouseExited

    private void labLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labLoginMousePressed
        SwingUtilities.invokeLater(() -> {
        login();
        
        });
        
        new Thread(() -> {
         Puerto.conectar();
         Puerto.getReceivedData();
        }).start();
       

        
    }//GEN-LAST:event_labLoginMousePressed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void labNombreSedeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labNombreSedeMouseEntered
        // TODO add your handling code here:
  
    }//GEN-LAST:event_labNombreSedeMouseEntered

    private void labNombreSedeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labNombreSedeMousePressed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_labNombreSedeMousePressed

    /**
     * Primer método que se ejecuta del software. Encargado de iniciar todo.
     *
     * @param args argumentos de ejecución.
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new JFLogin().setVisible(true);
                 
            }
        });
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LOGEORAPIDOBORRAR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labBackground;
    private javax.swing.JLabel labBotonCerrarVentana;
    private javax.swing.JLabel labConstrasenia;
    private javax.swing.JLabel labLogin;
    private javax.swing.JLabel labNombreSede;
    private javax.swing.JLabel labUsuario;
    private javax.swing.JLabel labVersion;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPasswordField txtContrasenia;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

                                                
}
