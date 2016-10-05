import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Front_End extends JFrame {

	private JPanel contentPane;
	public TextField textField_1;
	public TextField textField_2;
	public TextField textField_0;
	public TextField textField_plain;
	public TextArea textArea_key;
	public TextArea textArea;
	public TextArea textArea_cipher;
	public TextArea textArea_decipher;
	public TextArea textArea_randec;
	public String finaltext;

	Encryption er1 = new Encryption();
	keygen k = new keygen();
	Decryption dr = new Decryption();
	private JTextField textField;
	private JTextField textField_ranplain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Front_End frame = new Front_End();
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
	public Front_End() {
		setTitle("For CryptAnalysis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label label = new Label("Enter Random No. 1:");
		label.setBounds(10, 10, 113, 22);
		contentPane.add(label);

		/*
		 * TextField textField_0 = new TextField(); textField_0.setBounds(189,
		 * 10, 179, 22); contentPane.add(textField_0);
		 */

		textField_0 = new TextField();
		textField_0.setBounds(189, 10, 179, 22);
		contentPane.add(textField_0);

		Label label_1 = new Label("Enter Random No. 2:");
		label_1.setBounds(10, 38, 113, 22);
		contentPane.add(label_1);

		textField_1 = new TextField();
		textField_1.setBounds(189, 38, 179, 22);
		contentPane.add(textField_1);

		Label label_2 = new Label("Enter Plain Text:");
		label_2.setBounds(10, 66, 113, 22);
		contentPane.add(label_2);

		textField_2 = new TextField();
		textField_2.setBounds(189, 66, 179, 22);
		contentPane.add(textField_2);

		Button button = new Button("Encrypt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_1.getText() == null
						|| textField_2.getText() == null) {
				} else {
					int a = Integer.parseInt(textField_0.getText());
					int b = Integer.parseInt(textField_1.getText());
					String plaintext = textField_2.getText();
					AES_system.ran1 = a;
					AES_system.ran2 = b;
					AES_system.plain_text = plaintext;

					int len = plaintext.length();
					if (len < 16) {
						plaintext = plaintext + '#';
					}
					AES_system.plain_text = plaintext;
					AES_system.main(null);

					String keyfinal = "";
					String cipher_send = "";
					// String cipher_rec="";

					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 4; j++) {
							keyfinal = keyfinal + keygen.key_array[i][j] + " ";
							cipher_send += keygen.cipher[i][j] + " ";
							// cipher_rec+=Decryption.ciphcopy[i][j]+" ";

						}
						keyfinal += "\n";
						cipher_send += "\n";
						// cipher_rec+="\n";

					}

					textArea_key.setText(keyfinal);
					textArea_cipher.setText(cipher_send);
					// textArea_rev.setText(cipher_rec);

				}
			}
		});
		button.setBounds(10, 105, 70, 22);
		contentPane.add(button);

		Label label_3 = new Label("Key");
		label_3.setBounds(544, 38, 62, 22);
		contentPane.add(label_3);

		textArea_key = new TextArea();
		textArea_key.setEditable(false);
		textArea_key.setBounds(712, 0, 138, 104);
		contentPane.add(textArea_key);

		textArea_cipher = new TextArea();
		textArea_cipher.setEditable(false);
		textArea_cipher.setBounds(189, 154, 138, 79);
		contentPane.add(textArea_cipher);

		Label label_4 = new Label("Cipher");
		label_4.setBounds(10, 192, 62, 22);
		contentPane.add(label_4);

		Label label_6 = new Label("Deciphered text");
		label_6.setBounds(10, 373, 113, 22);
		contentPane.add(label_6);

		textArea_decipher = new TextArea();
		textArea_decipher.setEditable(false);
		textArea_decipher.setBounds(189, 333, 138, 104);
		contentPane.add(textArea_decipher);

		Button button_1 = new Button("Decrypt");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[][] ciph = new String[4][4];
				String[][] plaintext = new String[4][4];
				ciph = Decryption.ciph;
				Decryption.ciph = dr.inverse_multiply(ciph);

				Decryption.ciph = dr.step_rearrange(Decryption.ciph);
				Decryption.ciph = dr.shiftrowinv(Decryption.ciph);
				Decryption.plainkey_after_decipher = dr.xorinv(Decryption.ciph,
						keygen.key_array);
				plaintext = dr.boxtodecrypt(Decryption.plainkey_after_decipher);
				String decipher = "";
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {

						decipher += plaintext[i][j] + " ";
					}

					decipher += "\n";
				}

				textArea_decipher.setText(decipher);
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

				//textField_ranplain.setText(strf);
				textField_plain.setText(strf);
				finaltext=strf;

			}
		});
		button_1.setBounds(10, 303, 70, 22);
		contentPane.add(button_1);

		JLabel lblNewLabel = new JLabel("Plain Text");
		lblNewLabel.setBounds(8, 489, 72, 14);
		contentPane.add(lblNewLabel);

		textField_plain = new TextField();
		textField_plain.setEditable(false);
		textField_plain.setBounds(134, 483, 198, 20);
		contentPane.add(textField_plain);

		JLabel lblRandomKeyGenerated = new JLabel("Random Key Generated");
		lblRandomKeyGenerated.setBounds(506, 226, 124, 43);
		contentPane.add(lblRandomKeyGenerated);

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(702, 192, 148, 109);
		contentPane.add(textArea);

		JButton btnGenerateRandomKey = new JButton("Stop");
		btnGenerateRandomKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnGenerateRandomKey.setBounds(858, 586, 70, 23);
		contentPane.add(btnGenerateRandomKey);

		JButton btnDecryptUsingRandom = new JButton("Decrypt using Random Key");
		btnDecryptUsingRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				while(true)
				{
					String rankey = k.random_key();
					keygen.key_array_random = k.convbytes(rankey);
					String keyfinal = "";

					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 4; j++) {
							keyfinal = keyfinal + keygen.key_array_random[i][j]
									+ " ";

						}
						keyfinal += "\n";

					}

					textArea.setText(keyfinal);
				
				String[][] ciph = new String[4][4];
				String[][] plaintext = new String[4][4];
				ciph = Decryption.ciph;
				Decryption.ciph = dr.inverse_multiply(ciph);

				Decryption.ciph = dr.step_rearrange(Decryption.ciph);
				Decryption.ciph = dr.shiftrowinv(Decryption.ciph);
				Decryption.plainkey_after_decipher = dr.xorinv(Decryption.ciph,
						keygen.key_array_random);
				plaintext = dr.boxtodecrypt(Decryption.plainkey_after_decipher);
				Decryption.ciph=ciph;
				String decipher = "";
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {

						decipher += plaintext[i][j] + " ";
					}

					decipher += "\n";
				}

				textArea_randec.setText(decipher);
				textField_ranplain.setText(Decryption.str);
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

				
if(strf==finaltext)
{
	break;
}
textField_ranplain.setText(strf);
			}
				}
		});
		
		btnDecryptUsingRandom.setBounds(623, 351, 226, 23);
		contentPane.add(btnDecryptUsingRandom);

		textArea_randec = new TextArea();
		textArea_randec.setEditable(false);
		textArea_randec.setBounds(702, 413, 148, 104);
		contentPane.add(textArea_randec);

		JLabel lblDecrypt = new JLabel("Decrypt");
		lblDecrypt.setBounds(544, 447, 46, 14);
		contentPane.add(lblDecrypt);

		JLabel lblPlainText = new JLabel("Plain Text");
		lblPlainText.setBounds(536, 563, 70, 14);
		contentPane.add(lblPlainText);

		textField_ranplain = new JTextField();
		textField_ranplain.setEditable(false);
		textField_ranplain.setBounds(684, 560, 148, 20);
		contentPane.add(textField_ranplain);
		textField_ranplain.setColumns(10);

	}
	
}
