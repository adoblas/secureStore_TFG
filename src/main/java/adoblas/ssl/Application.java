package adoblas.ssl;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

public class Application extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final static Logger logger = LoggerFactory.getLogger("app");

	// /**
	// * Launch the application.
	// */
	public static void main(String[] args) {
		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		logger.info("1 {} 2", System.getProperty("bootServer"));
		try {
			if ("true".equals(System.getProperty("bootServer", "false"))) {
				logger.info("booting SSL server");
				EchoServer myServer = new EchoServer();
				Thread myThread = new Thread(myServer);
				myThread.start();
			}
			Application frame = new Application();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		// });
	}

	/**
	 * Create the frame.
	 */
	public Application() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 582);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnApplication = new JMenu("Application");
		menuBar.add(mnApplication);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnApplication.add(mntmSalir);

		JMenuBar menuBar_1 = new JMenuBar();
		mnApplication.add(menuBar_1);
		
		JMenu mnConfiguracin = new JMenu("Configuración");
		menuBar.add(mnConfiguracin);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de ...");
		mnConfiguracin.add(mntmAcercaDe);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmDocumentacin = new JMenuItem("Documentación");
		mnAyuda.add(mntmDocumentacin);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 255));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(204, 255, 255));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(204, 255, 255));
		
		JLabel label = new JLabel("");
		
		JLabel lblAliciaDoblas = new JLabel("© Alicia Doblas");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(579))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAliciaDoblas)
					.addContainerGap(509, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAliciaDoblas)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(9))
		);
		
		JButton button = new JButton("Almacen");
		button.setFont(new Font("Ubuntu", Font.BOLD, 15));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(0, 204, 255));
		
		JButton button_1 = new JButton("Almacen 2");
		button_1.setFont(new Font("Ubuntu", Font.BOLD, 15));
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(0, 204, 255));
		
		JButton button_4 = new JButton("Instrucciones");
		button_4.setFont(new Font("Ubuntu", Font.BOLD, 15));
		button_4.setForeground(Color.WHITE);
		button_4.setBackground(new Color(0, 204, 255));
		
		JButton button_5 = new JButton("Ayuda");
		button_5.setFont(new Font("Ubuntu", Font.BOLD, 15));
		button_5.setForeground(Color.WHITE);
		button_5.setBackground(new Color(0, 204, 255));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(37)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addGap(33))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(48)
					.addComponent(button_4)
					.addGap(12)
					.addComponent(button_5))
		);
		panel_2.setLayout(gl_panel_2);
		
		JButton btnAlmacen = new JButton("Almacen");
		btnAlmacen.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnAlmacen.setForeground(Color.WHITE);
		btnAlmacen.setBackground(new Color(0, 204, 255));
		
		JButton btnAlmacen_1 = new JButton("Almacen 2");
		btnAlmacen_1.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnAlmacen_1.setForeground(Color.WHITE);
		btnAlmacen_1.setBackground(new Color(0, 204, 255));
		
		JButton button_2 = new JButton("Instrucciones");
		button_2.setFont(new Font("Ubuntu", Font.BOLD, 15));
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(0, 204, 255));
		
		JButton button_3 = new JButton("Ayuda");
		button_3.setFont(new Font("Ubuntu", Font.BOLD, 15));
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(new Color(0, 204, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addComponent(btnAlmacen, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(btnAlmacen_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAlmacen, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAlmacen_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(43)
					.addComponent(button_2)
					.addGap(12)
					.addComponent(button_3))
		);
		panel.setLayout(gl_panel);

		JButton btnCrearCa = new JButton("Crear CA");
		btnCrearCa.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnCrearCa.setForeground(Color.WHITE);
		btnCrearCa.setBackground(new Color(0, 204, 255));
		
		JButton btnImportarCa = new JButton("Importar CA");
		btnImportarCa.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnImportarCa.setForeground(Color.WHITE);
		btnImportarCa.setBackground(new Color(0, 204, 255));
		
		JButton btnInstrucciones = new JButton("Instrucciones");
		btnInstrucciones.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnInstrucciones.setForeground(Color.WHITE);
		btnInstrucciones.setBackground(new Color(0, 204, 255));
		
		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.setFont(new Font("Ubuntu", Font.BOLD, 15));
		btnAyuda.setForeground(Color.WHITE);
		btnAyuda.setBackground(new Color(0, 204, 255));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addComponent(btnCrearCa, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(btnImportarCa, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAyuda, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInstrucciones))
					.addGap(36))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(25)
							.addComponent(btnInstrucciones)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAyuda))
						.addComponent(btnImportarCa, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(btnCrearCa, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
					.addGap(36))
		);
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		btnCrearCa.addActionListener(new EventoCrearCa());

	}

	public class EventoCrearCa implements ActionListener {
		// private JTextField txtName;
		public EventoCrearCa() {
			// this.txtName = txtName;
		}

		public void actionPerformed(ActionEvent e) {
			int confirmado = JOptionPane.showConfirmDialog(rootPane,
					"¿Está seguro de querer crear un CA?", "Confirmación", 1);
			if (JOptionPane.OK_OPTION == confirmado)
				JOptionPane.showMessageDialog(rootPane, "Haciendo petición al servidor de CA...", "Creando CA", 1);
				new EchoClient();
//			else {
//			}

		}
	}

	public class EventoSaludo implements ActionListener {
		private JTextField txtName;

		public EventoSaludo(JTextField txtName) {
			this.txtName = txtName;
		}

		public void actionPerformed(ActionEvent e) {
			// JOptionPane.showInternalInputDialog(txtName, "Te llamas " +
			// txtName.setText(txtName.getText); + "?");
			JOptionPane.showMessageDialog(rootPane, "Confirmas tu nombre como "
					+ txtName.getText() + "!", "CONFIRMACION", 1);
			// JOptionPane.showConfirmDialog(txtName,
			// "Confirmas entonces tu nombre como " + txtName.getName() + "?");
			// showMessageDialog(null, "¡Hola, " +
			// txtName.getText() + "!");
		}
	}
}
