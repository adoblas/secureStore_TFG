package adoblas.ssl;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;


public class Step1 extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Step1 frame = new Step1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Step1() {
		setBounds(100, 100, 730, 521);
		
		JButton btnOk = new JButton("OK");
		getContentPane().add(btnOk, BorderLayout.SOUTH);

	}

}
