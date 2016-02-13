package enixlin.jrrc.OA.client.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */


	public void setvisable(){
		frame.setVisible(true);
	}
	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(198, 113, 93, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
