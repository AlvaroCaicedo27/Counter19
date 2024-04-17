package interfaces;


import entidades.ConsultasLogin;

import entidades.ConsultasSede;
import entidades.ConsultasUsuarios;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Método que genera la vista de 'PopUpTextosCodificados'. Este popup es el más
 * usado en el sistema. Es una ventana genérica que facilita la carga de campos
 * codificados para los usuarios. Se usa en caso de que el usuario desconosca
 * los atajos de los campos codificados. La vista los carga y facilita la
 * selección del que necesite.
 */
public class PopUpTextosCodificados extends javax.swing.JFrame {

    //Constantes para la vista de INRegistroOrdenes.
    public final static int REGISTRODEMOGRAFICO_MEDICOS = 1;
    public final static int REGISTRODEMOGRAFICO_SERVICIOS = 2;
    public final static int REGISTRODEMOGRAFICO_TIPOSDEORDEN = 3;

    //Constantes para la vista INConfigurarExamenes.
    public final static int CONFIGURAREXAMENES_MUESTRAS = 4;
    public final static int CONFIGURAREXAMENES_SECCIONES = 5;
    public final static int CONFIGURAREXAMENES_TECNICAS = 6;
    public final static int CONFIGURAREXAMENES_EXAMENES = 7;

    //Constantes para la vista de INConfigurarProductos.
    public final static int CONFIGURARPRODUCTOS_PRESENTACIONES = 8;
    public final static int CONFIGURARPRODUCTOS_MARCAS = 9;
    public final static int CONFIGURARPRODUCTOS_CATEGORIAS = 10;
    
    //Constantes para la vista de INIngresoMercancia.
    public final static int INGRESOMERCANCIA_PRODUCTOS = 11;
    public final static int INGRESOMERCANCIA_PROVEEDORES = 12;
     public final static int CONFIGURARUSUARIOS_ROLES = 13;
    public final static int CONFIGURARUSUARIOS_SEDES = 14;
    public final static int REGISTRAR_SEDES = 15; // va arriba en las constantes
     public final static int CONFIGURAR_ENTIDAD = 16;

    //Constantes para la vista de INEstadisticasInventario.
    
    //Referencias de las clases que llaman este popup.
   
    private JFLogin jfLogin;
   
    
    //Atributos
    private int refConstante;
    private String campo;
    private TableRowSorter trs;
    private JTextField campoDestino;
    private JLabel campoDestino1;
    /**
     * Constructor sobrecargado para ser usado por la vista INRegistroOrdenes.
     * Dependiendo de la constante que se envía por parámetro, se cargan
     * diferentes campos codificados sobre esta ventana.
     *
     * @param pInternal referencia de la clase INRegistroOrdenes.
     * @param pConstante constante que indica que valores de campos codificados
     * se necesitan cargar.
     */
   

    /**
     * Constructor sobrecargado para ser usado por la vista
     * INConfigurarExamenes. Dependiendo de la constante que se envía por
     * parámetro, se cargan diferentes campos codificados sobre esta ventana.
     *
     * @param pVista referencia de la clase INConfigurarExamenes.
     * @param pConstante constante que indica que valores de campos codificados
     * se necesitan cargar.
     */
 

public PopUpTextosCodificados(JFLogin pVista, int pApuntador, JLabel campoDestino) {

        initComponents();
        //Ajusta la vista.
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.campoDestino1 = campoDestino; 
        //Guarda la referencia de la vista.
        jfLogin = pVista;
        refConstante = pApuntador;
   

        //Obtiene el modelo de la tabla de la vista.
        DefaultTableModel model = (DefaultTableModel) tablaBase.getModel();
        
        tablaBase.getSelectionModel().addListSelectionListener(event -> {
    if (!event.getValueIsAdjusting() && tablaBase.getSelectedRow() != -1) {
        // Suponiendo que deseas concatenar el código y el nombre de la sede como valor del campoDestino
        int selectedRow = tablaBase.getSelectedRow();
       // String codigo = tablaBase.getModel().getValueAt(selectedRow,0 ).toString();
        String codigo = tablaBase.getModel().getValueAt(selectedRow,0).toString();
        String sede = tablaBase.getModel().getValueAt(selectedRow,1).toString();
        String valor = sede;
        
        campoDestino1.setText(valor); // Actualiza el campoDestino con la selección
        
        // Cierra el PopUp después de la selección
    }
});
        
       
        switch (pApuntador) {

                   
            case CONFIGURARUSUARIOS_SEDES:
               Object[] titulosSedes = {"codigo","sedes"};

                for (Object titulo : titulosSedes) {
                    model.addColumn(titulo);
                }

                Object[][] sedes = ConsultasSede.darSede();
                for (Object[] dato : sedes) {
                    if (dato[0] != null) {
                        model.addRow(dato);
                    }
                }
        
  
            
                break;
         
        }
    }


    



    /**
     * Constructor sobrecargado para ser usado por la vista INInformes.
     * Dependiendo de la constante que se envía por parámetro, se cargan
     * diferentes campos codificados sobre esta ventana.
     *
     * @param pInternal referencia de la clase INInformes.
     * @param pConstante constante que indica que valores de campos codificados
     * se necesitan cargar.
     */
   
     
   
    /**
     * Constructor sobrecargado para ser usado por la vista INEstadisticas.
     * Dependiendo de la constante que se envía por parámetro, se cargan
     * diferentes campos codificados sobre esta ventana.
     *
     * @param pVista referencia de la clase INEstadisticas.
     * @param pAgrupacion primer nivel de agrupacion de las constantes.
     * @param pCampo segundo nivel de agrupacion de las constantes.
     *
     * Las constante por esta vista viene agrupadas en dos niveles, para
     * faciliar las consultas.
     */
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBase = new javax.swing.JTable();
        butAceptar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labTitle = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo_24.png")));

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));

        tablaBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ){
            Class[] types = new Class [] {
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tablaBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaBaseKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaBase);

        butAceptar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        butAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icono_seleccionar_24.png"))); // NOI18N
        butAceptar.setText("Seleccionar");
        butAceptar.setIconTextGap(12);
        butAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAceptarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labTitle.setText("Buscar por nombre:");

        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labTitle)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(butAceptar)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(34, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(butAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAceptarActionPerformed

   
    }//GEN-LAST:event_butAceptarActionPerformed


    private void txtFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyTyped

        //Obtiene el modelo de la tabla.
        DefaultTableModel dtm = (DefaultTableModel) tablaBase.getModel();
        //Añade el evento keyListener al campo.
        txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                switch(refConstante){
                    case CONFIGURAREXAMENES_EXAMENES:
                        trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText(), 2));
                break;
                    default:
                if( dtm.getColumnCount() > 1){
                    //Crea el nuevo modelo de filtrado.
                    trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText(), 1));
                  
                }
                else{
                    //Crea el nuevo modelo de filtrado.
                    trs.setRowFilter(RowFilter.regexFilter("(?i)" + txtFiltro.getText(), 0));
                }
                break;
               }
                
            }
        });

        //crea y establece el modelo de filtrado a la tabla.
        trs = new TableRowSorter(dtm);
        tablaBase.setRowSorter(trs);

    }//GEN-LAST:event_txtFiltroKeyTyped

    private void tablaBaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaBaseKeyPressed

        int codigo = evt.getKeyCode();
        if (codigo == KeyEvent.VK_ENTER) {
            butAceptarActionPerformed(null);
        }

    }//GEN-LAST:event_tablaBaseKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAceptar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labTitle;
    private javax.swing.JTable tablaBase;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}