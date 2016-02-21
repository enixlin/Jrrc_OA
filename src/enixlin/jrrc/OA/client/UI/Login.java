package enixlin.jrrc.OA.client.UI;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.management.RuntimeOperationsException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Login {

	private JFrame frmoa;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					Login window = new Login();

					window.frmoa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmoa = new JFrame();
		frmoa.setBounds(new Rectangle(500, 400, 0, 0));
		frmoa.setLocation(new Point(500, 300));
		frmoa.setResizable(false);
		// frmoa.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\projects\\java\\Jrrc_OA\\res\\rhlogos.png"));
		frmoa.setTitle("\u6B22\u8FCE\u4F7F\u7528\u878D\u548C\u519C\u5546\u94F6\u884COA\u5BA2\u6237\u7AEF");
		frmoa.setBounds(500, 300, 450, 228);
		// frmoa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmoa.getContentPane().setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u540D\u79F0");
		label.setBounds(34, 38, 54, 15);
		frmoa.getContentPane().add(label);

		textField = new JTextField();
		textField.setBounds(98, 35, 202, 21);
		frmoa.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u7528\u6237\u5BC6\u7801");
		label_1.setBounds(34, 78, 54, 15);
		frmoa.getContentPane().add(label_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(98, 75, 202, 21);
		frmoa.getContentPane().add(textField_1);

		JLabel label_2 = new JLabel("\u7248\u6743\u6240\u6709@\u6797\u632F\u7115-2016\u5E74");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(98, 164, 202, 15);
		frmoa.getContentPane().add(label_2);

		JLabel lblVersion = new JLabel("Version 1.0.0");
		lblVersion.setBounds(314, 164, 93, 15);
		frmoa.getContentPane().add(lblVersion);

		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			web_login();
				
				

			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(314, 34, 93, 59);
		frmoa.getContentPane().add(btnNewButton);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String skin = "com.jtattoo.plaf.graphite.GraphiteLookAndFee";
				if (comboBox.getSelectedItem().toString() == "LUNA") {
					skin = "com.jtattoo.plaf.luna.LunaLookAndFeel";
				}
				if (comboBox.getSelectedItem().toString() == "McWin") {
					skin = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
				}
				if (comboBox.getSelectedItem().toString() == "Graphite") {
					skin = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
				}
				if (comboBox.getSelectedItem().toString() == "aluminium") {
					skin = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
				}
		

				try {
					UIManager.setLookAndFeel(skin);
					SwingUtilities.updateComponentTreeUI(frmoa);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		comboBox.setBounds(98, 117, 202, 21);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "LUNA", "McWin", "aluminium", "Graphite","nimbus" }));

		frmoa.getContentPane().add(comboBox);

		JLabel label_3 = new JLabel("\u754C\u9762\u98CE\u683C");
		label_3.setBounds(34, 120, 54, 15);
		frmoa.getContentPane().add(label_3);
	}
	
	
	public void web_login(){
		new SwingWorker<StringBuilder, String>() {

			@Override
			protected StringBuilder doInBackground() throws Exception {
				StringBuilder result=new StringBuilder();
				
				try {
					URI uri=new URIBuilder()
							.setScheme("http")
							.setHost("linzhenhuan.net")
							.setPath("/Jrrc_web/index.php")
							.setParameter("s", "/Home/Login/index")
							.build(); 
					
					CloseableHttpClient client = HttpClients.createDefault();

					HttpPost httpPost = new HttpPost(uri.toString());
					
					System.out.println(uri.toString());
					try {
						CloseableHttpResponse response = client.execute(httpPost);
						HttpEntity entity = response.getEntity();
						if (entity != null) {
							InputStream inputStream = entity.getContent();
							
							BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
							String line="";
						
							while ((line = bufferedReader.readLine()) != null) {
								result.append(line);
								result.append("/n");
							}
						
						bufferedReader.close();
						inputStream.close();
						}
						
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				} catch (URISyntaxException  e) {
					e.printStackTrace();
				}
				
				
				return result;
			}

			@Override
			protected void done() {
				// TODO Auto-generated method stub
				try {
					textField.setText(get().toString());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.done();
			}

			@Override
			protected void process(List<String> chunks) {
				// TODO Auto-generated method stub
				super.process(chunks);
			}
			
			
			
		}.execute();
	}
}
