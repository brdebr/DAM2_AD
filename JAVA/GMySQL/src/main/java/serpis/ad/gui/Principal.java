package serpis.ad.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		JMenuItem menu_añadir = new JMenuItem("Añadir");
		mnNewMenu.add(menu_añadir);
		menu_añadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Datos_articulo datos_articulo = new Datos_articulo();
				datos_articulo.setVisible(true);
			}
		});
		
		JMenuItem menu_editar = new JMenuItem("Editar");
		mnNewMenu.add(menu_editar);
		
		JMenuItem menu_eliminar = new JMenuItem("Eliminar");
		mnNewMenu.add(menu_eliminar);
		
		JMenuItem menu_consultar = new JMenuItem("Consultar");
		mnNewMenu.add(menu_consultar);
		
		JMenuItem menu_listar = new JMenuItem("Listar");
		mnNewMenu.add(menu_listar);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem menu_salir = new JMenuItem("Salir");
		mnNewMenu.add(menu_salir);
		menu_salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});
		
		

	}

}
