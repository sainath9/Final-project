import java.awt.Button;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Front_End_A extends JFrame {

	public static int ran1, ran2;
	public static String plain_text;
	public static String final_key;
	public static int len;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private TextArea textArea;
	int[] x = new int[50];
	int xi = 0;
	private JLabel lblNewLabel_1;
	private JLabel label;
	private JButton btnGenerateKey;
	Encryption er1 = new Encryption();
	keygen k = new keygen();
	private JTextField textField_7;
	keygen pr = new keygen();
	Encryption er = new Encryption();

	private JLabel lblNewLabel_2;
	private TextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Front_End_A frame = new Front_End_A();
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
	public Front_End_A() {
		setTitle("User A(Sender)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRandomNumbers = new JLabel("Random Numbers");
		lblRandomNumbers.setBounds(10, 47, 95, 50);
		contentPane.add(lblRandomNumbers);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(115, 62, 43, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(168, 62, 43, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(221, 62, 43, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(274, 62, 43, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("Generate Indices");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = getRandomNumberInRange(0, 9999);
				int b = getRandomNumberInRange(0, 9999);
				int c = getRandomNumberInRange(0, 9999);
				int d = getRandomNumberInRange(0, 9999);
				System.out.println(a + " " + b + " " + c + " " + d);
				textField.setText("" + a);
				textField_1.setText("" + b);
				textField_2.setText("" + c);
				textField_3.setText("" + d);

				x[0] = d;
				for (int i = 1; i <= 2; i++) {

					int k = (x[xi] * a + b) % c;
					xi++;
					x[xi] = k;
					if (i == 1) {
						textField_4.setText("" + x[xi]);
						ran1 = Integer.parseInt(textField_4.getText());
					} else {
						textField_5.setText("" + x[xi]);
						ran2 = Integer.parseInt(textField_5.getText());
					}

				}
				for (int i = 0; i <= 2; i++) {
					System.out.println(x[i]);
				}
				/*
				 * AES_system.ran1 = Integer.parseInt(textField_4.getText());
				 * AES_system.ran2 = Integer.parseInt(textField_5.getText());
				 */
			}
		});
		btnNewButton.setBounds(10, 26, 161, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Numbers");
		lblNewLabel.setBounds(348, 58, 53, 29);
		contentPane.add(lblNewLabel);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(431, 62, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(546, 62, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		lblNewLabel_1 = new JLabel("Generated Key");
		lblNewLabel_1.setBounds(10, 153, 74, 50);
		contentPane.add(lblNewLabel_1);

		label = new JLabel("Plain Text");
		label.setBounds(10, 239, 74, 50);
		contentPane.add(label);

		btnGenerateKey = new JButton("Generate Key");
		btnGenerateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ran1 = ran1 % 3;
				ran2 = ran2 % 9;
				System.out.println(ran1 + "" + ran2);
				int key = pr.select_key(ran1, ran2);
				System.out.println("Key generated is:" + key);
				String binary = Integer.toBinaryString(key);
				System.out.println(binary);

				System.out.println("Number of bits for the key is 128");
				// int key_len=s.nextInt();
				int key_len = 128;
				final_key = pr.finalkey(key_len, binary);
				final_key = pr.finalkey(key_len, final_key);
				final_key = pr.finalkey(key_len, final_key);
				final_key = pr.finalkey(key_len, final_key);
				int g = final_key.length();

				System.out.println("Final key is:" + final_key + "  "
						+ "length:" + g);
				pr.convbytes(final_key);
				pr.print_key();

				System.out.println();

				// AES_system.main(null);

				String keyfinal = "";
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						keyfinal = keyfinal + keygen.key_array[i][j] + " ";

					}
					keyfinal += "\n";

				}

				textArea.setText(keyfinal);
			}
		});
		btnGenerateKey.setBounds(10, 108, 120, 23);
		contentPane.add(btnGenerateKey);

		textField_7 = new JTextField();
		textField_7.setBounds(131, 254, 167, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(168, 123, 120, 100);
		contentPane.add(textArea);

		Button button = new Button("Encrypt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("plain text:");
				plain_text = textField_7.getText();
				len = plain_text.length();
				if (len > 16) {
					// JOptionPane.showMessageDialog(, “Message goes here”);
					JOptionPane.showMessageDialog(rootPane,
							"Encryption for 16 chars only");

				}
				String ptxt = textField_7.getText();

				if (len < 16) {
					ptxt = ptxt + '#';
				}

				plain_text = ptxt;

				String result_plain = er.tobinarytext(plain_text);
				System.out.println(result_plain + "" + " length:"
						+ result_plain.length());
				er.convbytes_plain(result_plain);
				er.printtext();
				er.step_xor(final_key, result_plain);
				er.step_shift();
				er.step_colummix();
				er.step_multiply();
				System.out.println("In main:");

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (keygen.cipher[i][j].length() != 2) {
							keygen.cipher[i][j] = "0" + keygen.cipher[i][j];
						}
						System.out.print(keygen.cipher[i][j] + " ");

					}
					System.out.println("");
				}

				String cipher_send = "";

				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {

						cipher_send += keygen.cipher[i][j] + " ";

					}

					cipher_send += "\n";

				}

				textArea_1.setText(cipher_send);

			}
		});
		button.setBounds(338, 252, 70, 22);
		contentPane.add(button);

		lblNewLabel_2 = new JLabel("Encrypted Text");
		lblNewLabel_2.setBounds(10, 359, 86, 50);
		contentPane.add(lblNewLabel_2);

		textArea_1 = new TextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(148, 323, 120, 100);
		contentPane.add(textArea_1);

		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Socket socketClient = new Socket("localhost", 8082);
					System.out.println("Client: " + "Connection Established");

					ObjectOutputStream out = new ObjectOutputStream(
							socketClient.getOutputStream());
					out.writeObject(keygen.cipher);
					/*
					 * writer.write(ran1); writer.write(ran2);
					 */
					out.writeObject("" + ran1);
					out.writeObject("" + ran2);
					JOptionPane.showMessageDialog(rootPane,
							"Cipher Sent Successfully!");
					// socketClient.close();

				} catch (Exception e1) {

					JOptionPane.showMessageDialog(rootPane,
							"connection refused");
				}

			}
		});
		btnNewButton_1.setBounds(348, 386, 89, 23);
		contentPane.add(btnNewButton_1);

	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
