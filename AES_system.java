
public class AES_system {
	public static int ran1, ran2;
	public static String plain_text;
	public static String final_key;
	public static int len;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		keygen pr = new keygen();
		Encryption er = new Encryption();
		Decryption d = new Decryption();

		/*
		 * Scanner s = new Scanner(System.in);
		 * System.out.println("Enter Any two random numbers");
		 */
		// ran1 = s.nextInt();
		// ran2 = s.nextInt();

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

		System.out.println("Final key is:" + final_key + "  " + "length:" + g);

		keygen.key_array = pr.convbytes(final_key);
		pr.print_key(keygen.key_array);

		System.out.println();
		System.out.print("enter plain text:");
		/*
		 * Scanner sc=new Scanner(System.in); plain_text = sc.nextLine();
		 */
		len = plain_text.length();

		/*
		 * String
		 * plain_binary=Integer.toBinaryString(Integer.parseInt(plain_text));
		 * System.out.println("plaintext binary is:"+plain_binary);
		 */
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
				System.out.print(Decryption.ciph[i][j] + " ");

			}
			System.out.println("");
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (keygen.cipher[i][j].length() != 2) {
					keygen.cipher[i][j] = "0" + keygen.cipher[i][j];
				}
				System.out.print(keygen.cipher[i][j] + " ");

			}
			System.out.println("");
		}

		// s.close();
		// sc.close();
	}

}
