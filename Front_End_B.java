import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Front_End_B extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserBreceiver;
	private TextArea textArea;
	private TextArea textArea_1;
	Decryption dr = new Decryption();
	keygen kr = new keygen();
	public static String[][] cipher_receive = new String[4][4];
	String[][] key_gen = new String[4][4];
	static int ran1;
	static int ran2;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Front_End_B frame = new Front_End_B();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
		soc();
	}

	public static void soc() {

		ServerSocket mysocket;
		try {
			mysocket = new ServerSocket(8082);

			Socket connectionSocket = mysocket.accept();

			ObjectInputStream inObjectStream = new ObjectInputStream(
					connectionSocket.getInputStream());
			/*
			 * BufferedReader reader = new BufferedReader(new
			 * InputStreamReader(connectionSocket.getInputStream()));
			 */

			cipher_receive = (String[][]) inObjectStream.readObject();
			String ran1_s = (String) inObjectStream.readObject();
			String ran2_s = (String) inObjectStream.readObject();

			System.out.println("in object:"+ inObjectStream.toString());
			
			// System.out.println("Random numbers:" + ran1_s + " " + ran2_s);
			ran1 = Integer.parseInt(ran1_s);
			ran2 = Integer.parseInt(ran2_s);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Front_End_B() {
		setTitle("User B(Receiver)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cipher Text Received");
		lblNewLabel.setBounds(10, 10, 112, 50);
		contentPane.add(lblNewLabel);

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(143, 10, 159, 104);
		contentPane.add(textArea);

		JButton btnRecieve = new JButton("Receive");
		btnRecieve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cipher = "";
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {

						cipher += cipher_receive[i][j] + " ";
					}

					cipher += "\n";
				}

				textArea.setText(cipher);
			}
		});
		btnRecieve.setBounds(361, 37, 89, 23);
		contentPane.add(btnRecieve);

		JButton btnDecipher = new JButton("Decipher");
		btnDecipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String final_key;
				ran1 = ran1 % 3;
				ran2 = ran2 % 9;
				System.out.println(ran1 + "" + ran2);
				int key = kr.select_key(ran1, ran2);
				System.out.println("Key generated is:" + key);
				String binary = Integer.toBinaryString(key);
				System.out.println(binary);

				System.out.println("Number of bits for the key is 128");
				// int key_len=s.nextInt();
				int key_len = 128;
				final_key = kr.finalkey(key_len, binary);
				final_key = kr.finalkey(key_len, final_key);
				final_key = kr.finalkey(key_len, final_key);
				final_key = kr.finalkey(key_len, final_key);
				int g = final_key.length();

				System.out.println("Final key is:" + final_key + "  "
						+ "length:" + g);
				kr.convbytes(final_key);
				kr.print_key();

				System.out.println();

				// AES_system.main(null);

				String keyfinal = "";
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						keyfinal = keyfinal + keygen.key_array[i][j] + " ";

					}
					keyfinal += "\n";

				}
				System.out.println("test test");
				System.out.println("key received is" + "\n" + keyfinal);

				dr.receive_cipher(cipher_receive);

				dr.inverse_multiply();
				dr.step_rearrange();
				dr.shiftrowinv();
				dr.xorinv();
				dr.boxtodecrypt();
				String decipher = "";
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {

						decipher += Decryption.plainkey_after_decipher[i][j]
								+ " ";
					}

					decipher += "\n";
					System.out.println("deciphered plain text is:" + decipher);

					textArea_1.setText(decipher);
					String strf = Decryption.str;
					int k = 0;
					for (int w = 0; w < strf.length(); w++) {
						if (strf.charAt(w) == '#') {
							k = w;
						}

					}
					// k=strf.indexOf("#");
					System.out.println("index:" + k);
					if (k != 0)
						strf = strf.substring(0, k);
					txtUserBreceiver.setText(strf);

				}
			}

		});
		btnDecipher.setBounds(10, 174, 89, 23);
		contentPane.add(btnDecipher);

		JLabel lblPlainText = new JLabel("Plain Text");
		lblPlainText.setBounds(10, 266, 72, 50);
		contentPane.add(lblPlainText);

		txtUserBreceiver = new JTextField();
		txtUserBreceiver.setEditable(false);
		txtUserBreceiver.setBounds(141, 266, 178, 20);
		contentPane.add(txtUserBreceiver);
		txtUserBreceiver.setColumns(10);

		textArea_1 = new TextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(143, 120, 159, 104);
		contentPane.add(textArea_1);

		btnNewButton = new JButton("refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			

				setVisible(false);
				dispose();
			Front_End_B b= new Front_End_B();
				 b.setVisible(true);

				//dispose();
				//soc();
				//Front_End_B.main(null);
				
				

			}
		});
		btnNewButton.setBounds(10, 362, 89, 23);
		contentPane.add(btnNewButton);

	}

	public void revalidate() {
		// TODO Auto-generated method stub
		super.revalidate();
		cipher_receive = null;
	}

	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
		cipher_receive = null;
	}
}
