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

public class Application extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private final static Logger logger = LoggerFactory.getLogger("app");

	
//	/**
//	 * Launch the application.
//	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
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
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 614);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnApplication = new JMenu("Application");
		menuBar.add(mnApplication);

		JMenuBar menuBar_1 = new JMenuBar();
		mnApplication.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																911,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																panel_1,
																GroupLayout.PREFERRED_SIZE,
																909,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(19, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap(40, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 267,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 245,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));

		JButton btnTest = new JButton("Nombre");
		btnTest.addMouseListener(new MouseAdapter() {

			// public void mouseClicked(MouseEvent e) {
			// JOptionPane.showMessageDialog(null, "¡Hola, " +
			// "!");
			// }
		});

		txtName = new JTextField();
		txtName.setColumns(10);
		btnTest.addActionListener(new EventoSaludo(txtName));

		JButton btnCrearCa = new JButton("Crear CA");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGap(350)
																		.addComponent(
																				txtName,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(5)
																		.addComponent(
																				btnTest))
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnCrearCa,
																				GroupLayout.PREFERRED_SIZE,
																				147,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(351, Short.MAX_VALUE)));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGap(8)
																		.addComponent(
																				txtName,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addGap(5)
																		.addComponent(
																				btnTest)))
										.addGap(7)
										.addComponent(btnCrearCa,
												GroupLayout.PREFERRED_SIZE,
												115, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(115, Short.MAX_VALUE)));
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
			// String dirIP = JOptionPane.showInputDialog(contentPane,
			// "Introduce la IP Servidor: ", "Introduce los datos", 1);
			// String puerto = JOptionPane.showInputDialog(contentPane,
			// "Introduce el puerto del Servidor: ",
			// "Introduce los datos", 1);

			// InputStreamReader isr=new InputStreamReader(System.in);
			// BufferedReader br=new BufferedReader(isr);
			// System.out.println("Enter the class name");
			// String s = null;
			// try {
			// s = br.readLine();
			// } catch (IOException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }

			// String str="XYZ";
			new EchoClient2();
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
