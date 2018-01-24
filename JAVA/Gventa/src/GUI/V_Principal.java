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

    /**
     * Creates new form V_Principal
     */
    public V_Principal() {
        initComponents();

        // --------------- PROCESO UTILIZANDO BD ------------------------------- 
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        Query query = session.createQuery("from Pedido");

        List<Pedido> lista = query.list();
        pedidos = lista.toArray(pedidos);

//        session.close();
//        HibernateUtil.getSessionFactory().close();
        // --------------- PROCESO UTILIZANDO BD ------------------------------- 
        DefaultMutableTreeNode pedidos_root = new DefaultMutableTreeNode("Pedidos");

        // BUCLE AÑADIR PEDIDOS
        for (int i = 0; i < pedidos.length; i++) {

            DefaultMutableTreeNode pedidoNode = new DefaultMutableTreeNode("Pedido " + pedidos[i].getId());

            // Rellenar un array con las lineas del pedido actual del bucle
            Pedidolinea[] lineasAux = new Pedidolinea[1];
            Hibernate.initialize(pedidos[i].getPedidolineas());
            lineasAux = pedidos[i].getPedidolineas().toArray(lineasAux);

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

        tree_pedidos.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
//                System.out.println(tse.getPath().toString());

                String[] ruta = tse.getPath().toString().substring(1).replace("]", "").split(",");
                if (ruta.length > 1) {
                    String node = ruta[1].split(" ")[2];
                    seleccionado = (Pedido) session.load(Pedido.class, Long.parseLong(node));
                    Hibernate.initialize(seleccionado);
                }

                //-- DATOS PEDIDO  
                edit_id_ped.setText(String.valueOf(seleccionado.getId()));

                DateFormat formatterDia = new SimpleDateFormat("dd/MM/yyyy");
                edit_fecha_ped.setText(formatterDia.format(seleccionado.getFecha()));

                DateFormat formatterHora = new SimpleDateFormat("hh:mm");
                edit_hora_ped.setText(formatterHora.format(seleccionado.getFecha()));

                edit_importe_pd.setText(String.valueOf(seleccionado.getImporte()));
                //FIN DATOS PEDIDO --

                edit_id_cliente.setText(String.valueOf(seleccionado.getCliente().getId()));
                edit_nombre_cli.setText(String.valueOf(seleccionado.getCliente().getNombre()));

                Pedidolinea[] lineasTabla = new Pedidolinea[1];
                Hibernate.initialize(seleccionado.getPedidolineas());
                lineasTabla = seleccionado.getPedidolineas().toArray(lineasTabla);

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

//        DefaultTreeModel model = (DefaultTreeModel) tree_pedidos.getModel();
//        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//        root.setUserObject("My label");
//        model.nodeChanged(root);
//        tree_pedidos = new JTree(pedidos_root);
//        DefaultTreeModel defaultModel = (DefaultTreeModel) tree_pedidos.getModel();
//        defaultModel.
//        defaultModel.reload();
        DefaultTreeModel defaultModel = new DefaultTreeModel(pedidos_root);
        tree_pedidos.setModel(defaultModel);

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
        edit_id_ped.setText("23");
        edit_id_ped.setToolTipText("");

        edit_fecha_ped.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edit_fecha_ped.setText("10/10/2000");

        jLabel3.setText("Importe Total");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Hora");

        edit_hora_ped.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edit_hora_ped.setText("10:10");

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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ref.", "Artículo", "Precio", "Unidades", "Total"
            }
        ));
        jScrollPane2.setViewportView(tabla_linea_ped);

        btn_ver_articulo.setText("Ver Artículo");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ver_articulo))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
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
