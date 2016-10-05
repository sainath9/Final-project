public class Decryption {
	public static String[][] ciph = null;
	public static String[][] ciphcopy = null;
	public static String[][] key = null;
	int i = 0, j = 0, k = 0;
	public static String[][] plainkey_after_decipher = new String[4][4];
	public static String str;

	public void receive_cipher(String[][] ciphr) {
		ciph = ciphr;
		ciphcopy = ciph;

	}

	public void inverse_multiply() {

		System.out.println("In Decipher Step-1:");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ciph[i][j] = this.matrix_inv(ciph[i][j]);
				System.out.print(ciph[i][j] + " ");
			}
			System.out.println("");
		}
	}

	private String matrix_inv(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);
		int[][] mul = { { 0, 0, 1, 0, 0, 1, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1, 0 },
				{ 0, 1, 0, 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 0, 1, 0, 0 },
				{ 0, 1, 0, 1, 0, 0, 1, 0 }, { 0, 0, 1, 0, 1, 0, 0, 1 },
				{ 1, 0, 0, 1, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0, 1, 0 } };
		int i = Integer.parseInt(s, 16);
		String res = "";
		String bin = Integer.toBinaryString(i);

		int[][] a = new int[8][1];
		int j = 0, l = 0;
		int ln = bin.length();

		if (ln < 8) {
			int dif = 8 - ln;

			while (j < dif) {
				a[j][0] = 0;
				j++;
			}
		}
		int y = j;
		for (int x = j; x < y + bin.length(); x++) {
			a[j][0] = Integer.parseInt(bin.charAt(l) + "");

			l++;
			j++;

		}

		int[][] re = new int[8][1];
		for (int m = 0; m < 8; m++) {
			for (int n = 0; n < 1; n++) {
				for (int o = 0; o < 8; o++) {
					re[m][n] = re[m][n] + mul[m][o] * a[o][n];
					if (re[m][n] % 2 == 0)
						re[m][n] = 0;
					else
						re[m][n] = 1;

				}
			}

		}

		for (int m = 0; m < 8; m++) {
			res = res + re[m][0];
		}

		res = Integer.toHexString(Integer.parseInt(res, 2));
		res = res.toUpperCase();
		return res;

	}

	public void step_rearrange() {
		// TODO Auto-generated method stub
		int[][] res = new int[4][4];
		int[] e = new int[4];
		int[] f = new int[4];
		int[] g = new int[4];
		int[] h = new int[4];
		int[] a = new int[4];
		int[] b = new int[4];
		int[] c = new int[4];
		int[] d = new int[4];
		System.out.println("In Step-2:");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res[i][j] = Integer.parseInt(ciph[i][j], 16);

			}
		}
		for (int i = 0; i < 4; i++) {
			e[i] = res[i][0];
			f[i] = res[i][1];
			g[i] = res[i][2];
			h[i] = res[i][3];
		}
		a = this.column_remix(g, h);
		d = this.column_remix(g, f);
		c = this.column_remix(e, f);
		b = this.column_remix(a, e);

		for (int i = 0; i < 4; i++) {
			res[i][0] = a[i];
			res[i][1] = b[i];
			res[i][2] = c[i];
			res[i][3] = d[i];
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ciph[i][j] = Integer.toHexString(res[i][j]);
				ciph[i][j] = ciph[i][j].toUpperCase();
				System.out.print(ciph[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public int[] column_remix(int a[], int b[]) {
		int[] res = new int[4];
		for (int i = 0; i < a.length; i++) {

			res[i] = a[i] ^ b[i];

		}
		return res;
	}

	public void shiftrowinv() {
		// TODO Auto-generated method stub

		int i = 1;
		for (i = 1; i < 4; i++)
			for (int j = 1; j <= i; j++) {
				shift_right(i);

			}
		System.out.println("\n\n In Step-3:");
		for (j = 0; j < 4; j++) {

			for (i = 0; i < 4; i++) {
				ciph[i][j] = ciph[i][j].toUpperCase();
				if (ciph[j][i].length() == 1) {
					ciph[j][i] = "0" + ciph[j][i];
				}
				System.out.print(ciph[j][i] + " ");
			}
			System.out.println("");
		}
	}

	public void shift_right(int i2) {
		String temp = "";

		for (j = 3; j >= 0; j--) {

			if (j == 3) {
				temp = ciph[i2][j];
				ciph[i2][j] = ciph[i2][j - 1];
			} else if (j == 0) {
				ciph[i2][j] = temp;
			} else {
				ciph[i2][j] = ciph[i2][j - 1];
			}

		}

	}

	public void xorinv() {
		// TODO Auto-generated method stub
		int[][] res_cipher = new int[4][4];
		int[][] res_key = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.println(ciph[i][j]);
				res_cipher[i][j] = Integer.parseInt(ciph[i][j], 16);
				System.out.println(res_cipher[i][j]);
				res_key[i][j] = Integer.parseInt(keygen.key_array[i][j], 16);

				int temp = res_cipher[i][j] ^ res_key[i][j];
				plainkey_after_decipher[i][j] = Integer.toHexString(temp);
				// plainkey_after_decipher[i][j]=temp+"";

			}

		}
		System.out.println("\n\nPlaintext After decipher:");
		for (int i = 0; i < 4; i++) {
			System.out.println("");
			for (int j = 0; j < 4; j++) {
				if (plainkey_after_decipher[i][j].length() == 1) {
					plainkey_after_decipher[i][j] = plainkey_after_decipher[i][j]
							+ "0";
				}
				plainkey_after_decipher[i][j] = plainkey_after_decipher[i][j]
						.toUpperCase();

				System.out.print(plainkey_after_decipher[i][j] + " ");

			}
		}

	}

	public void boxtodecrypt() {
		// TODO Auto-generated method stub
		String st_temp = "";

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int temp = Integer.parseInt(plainkey_after_decipher[i][j], 16);
				String tem = Integer.toBinaryString(temp);
				if (tem.length() < 8) {
					int l = 8 - tem.length();
					for (k = 0; k < l; k++) {
						tem = "0" + tem;
					}
				}
				st_temp += tem;

			}

		}
		System.out.println("\nplaintext:" + st_temp + "   length:"
				+ st_temp.length());

		str = "";
		for (i = 0; i < 128; i++) {
			if (i % 8 == 7) {

			}
		}
		for (i = 0; i < st_temp.length() / 8; i++) {

			String as = st_temp.substring(8 * i, (i + 1) * 8);
			as = as.substring(0, as.length() - 1);
			int asi = Integer.parseInt(as, 2);
			str += (char) (asi);
			System.out.println("in loop");
		}
		/*
		 * if(AES_system.len >= 16) { str=str.substring(0,15); } else
		 * str=str.substring(0, AES_system.len);
		 * System.out.println("Deciphered text is:"+ AES_system.len+" "
		 * +str+" after str");
		 */
		System.out.println("Deciphered text is:" + " " + str
				+ " after str");

	}

}
