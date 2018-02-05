/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Hibernate.Classes.Articulo;
import Hibernate.Classes.Cliente;
import Hibernate.Classes.Pedido;
import Hibernate.Classes.Pedidolinea;
import Hibernate.HibernateUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author brybre
 */
public class V_Principal extends javax.swing.JFrame {

    Pedido[] pedidos = new Pedido[0];
    Cliente cliente;
    Pedidolinea[] linea;
    Pedido seleccionado;

    Session session;

    /**
     * Creates new form V_Principal
     */
    public V_Principal() {
        initComponents();

        fillTree();

        refreshTree();

        refreshSeleccionado();

        btn_crear_ped.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                V_Pedido v_Pedido = new V_Pedido();
                v_Pedido.setVisible(true);
                
            }
        });

        brn_borrar_ped.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                Query query = session.createQuery("delete Pedido where id = :ID");
                query.setParameter("ID", seleccionado.getId());

                int result = query.executeUpdate();
                session.flush();
                
                refreshTree();
                
            }
        });

    }

    public void refreshTree() {
        DefaultMutableTreeNode pedidos_root = new DefaultMutableTreeNode("Pedidos");

        // BUCLE AÑADIR PEDIDOS
        for (int i = 0; i < pedidos.length; i++) {

            DefaultMutableTreeNode pedidoNode = new DefaultMutableTreeNode("Pedido " + pedidos[i].getId());

            // Rellenar un array con las lineas del pedido actual del bucle
            Hibernate.initialize(pedidos[i].getPedidolineas());
            Pedidolinea[] lineasAux = pedidos[i].getPedidolineas().toArray(new Pedidolinea[0]);

            // BUCLE AÑADIR ARTICULOS (lineas) A LOS PEDIDOS
            for (int j = 0; j < lineasAux.length; j++) {
                Pedidolinea pedidolinea = lineasAux[j];

                if (pedidolinea.getArticulo() != null) {
                    Articulo articulo = pedidolinea.getArticulo();
                    DefaultMutableTreeNode lineaNode
                            = new DefaultMutableTreeNode(articulo.getNombre()
                                    + " <- " + lineasAux[j].getUnidades() + " Uds.");
                    pedidoNode.add(lineaNode);
                }
            }
            pedidos_root.add(pedidoNode);
        }

        DefaultTreeModel defaultModel = new DefaultTreeModel(pedidos_root);
        tree_pedidos.setModel(defaultModel);
    }

    public void refreshSeleccionado() {
        tree_pedidos.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {

                String[] ruta = tse.getPath().toString().substring(1).replace("]", "").split(",");
                if (ruta.length > 1) {
                    String node = ruta[1].split(" ")[2];
                    Hibernate.initialize(seleccionado);
                    seleccionado = (Pedido) session.load(Pedido.class, Long.parseLong(node));
                }

                //-- DATOS PEDIDO  
                edit_id_ped.setText(String.valueOf(seleccionado.getId()));

                DateFormat formatterDia = new SimpleDateFormat("dd/MM/yyyy");
                edit_fecha_ped.setText(formatterDia.format(seleccionado.getFecha()));

                DateFormat formatterHora = new SimpleDateFormat("hh:mm");
                edit_hora_ped.setText(formatterHora.format(seleccionado.getFecha()));

                edit_importe_pd.setText(String.valueOf(seleccionado.getImporte()));

                edit_id_cliente.setText(String.valueOf(seleccionado.getCliente().getId()));
                edit_nombre_cli.setText(String.valueOf(seleccionado.getCliente().getNombre()));

                //FIN DATOS PEDIDO --
                
                Hibernate.initialize(seleccionado.getPedidolineas());
                Pedidolinea[] lineasTabla = seleccionado.getPedidolineas().toArray(new Pedidolinea[0]);

                DefaultTableModel model = (DefaultTableModel) tabla_linea_ped.getModel();
                int rows = model.getRowCount();
                for (int i = rows - 1; i >= 0; i--) {
                    model.removeRow(i);
                }

                for (int i = 0; i < lineasTabla.length; i++) {
                    model.setNumRows(lineasTabla.length);
                    tabla_linea_ped.getModel().setValueAt(lineasTabla[i].getId(), i, 0);
                    tabla_linea_ped.getModel().setValueAt(lineasTabla[i].getArticulo().getNombre(), i, 1);
                    tabla_linea_ped.getModel().setValueAt(lineasTabla[i].getPrecio(), i, 2);
                    tabla_linea_ped.getModel().setValueAt(lineasTabla[i].getUnidades(), i, 3);
                    tabla_linea_ped.getModel().setValueAt(lineasTabla[i].getImporte(), i, 4);

                }
            }
        });
    }

    private void fillTree() {
        
        // --------------- PROCESO UTILIZANDO BD ------------------------------- 
        
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Pedido");

        List<Pedido> lista = query.list();
        pedidos = lista.toArray(pedidos);

        // --------------- PROCESO UTILIZANDO BD ------------------------------- 
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tree_pedidos = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edit_id_ped = new javax.swing.JTextField();
        edit_fecha_ped = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edit_importe_pd = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        edit_hora_ped = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        edit_id_cliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edit_nombre_cli = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_linea_ped = new javax.swing.JTable();
        btn_ver_articulo = new javax.swing.JButton();
        btn_crear_ped = new javax.swing.JButton();
        brn_borrar_ped = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(tree_pedidos);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Pedido"));

        jLabel1.setText(" ID");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha");

        edit_id_ped.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edit_id_ped.setToolTipText("");

        edit_fecha_ped.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Importe Total");

        edit_importe_pd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Hora");

        edit_hora_ped.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_id_ped, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_fecha_ped, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_hora_ped, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_importe_pd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edit_id_ped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(edit_fecha_ped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(edit_hora_ped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit_importe_pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel4.setText("ID");

        jLabel5.setText("Nombre y Apell.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edit_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(edit_nombre_cli))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(edit_id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(edit_nombre_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla_linea_ped.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ref.", "Artículo", "Precio", "Unidades", "Total"
            }
        ));
        jScrollPane2.setViewportView(tabla_linea_ped);

        btn_ver_articulo.setText("Ver Artículo");

        btn_crear_ped.setText("Crear pedido");

        brn_borrar_ped.setText("Borrar Pedido");

        jMenu1.setText("File");

        jMenuItem1.setText("Test1");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem2.setText("Test2");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(brn_borrar_ped)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_crear_ped)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_ver_articulo)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ver_articulo)
                    .addComponent(btn_crear_ped)
                    .addComponent(brn_borrar_ped))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new V_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brn_borrar_ped;
    private javax.swing.JButton btn_crear_ped;
    private javax.swing.JButton btn_ver_articulo;
    private javax.swing.JTextField edit_fecha_ped;
    private javax.swing.JTextField edit_hora_ped;
    private javax.swing.JTextField edit_id_cliente;
    private javax.swing.JTextField edit_id_ped;
    private javax.swing.JTextField edit_importe_pd;
    private javax.swing.JTextField edit_nombre_cli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_linea_ped;
    private javax.swing.JTree tree_pedidos;
    // End of variables declaration//GEN-END:variables

}
