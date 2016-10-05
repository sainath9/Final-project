public class Encryption {
	String[][] plaintxt = new String[4][4];
	int i = 0, j = 0, k = 0;

	public void printtext() {
		// TODO Auto-generated method stub

		for (j = 0; j < 4; j++) {

			System.out.println("");

			for (k = 0; k < 4; k++)

			{

				System.out.print(plaintxt[j][k] + " ");

			}

		}

	}

	public void convbytes_plain(String result_plain) {
		// TODO Auto-generated method stub
		int j = 0;
		int k = 0;

		for (i = 0; i <= 120; i += 8) {
			String subs = result_plain.substring(i, i + 8);
			// subi=Integer.parseInt(subs);
			String bin = Integer.toHexString(Integer.parseInt(subs, 2));
			/*
			 * System.out.println(subs); System.out.println(bin);
			 */
			bin = bin.toUpperCase();
			if (bin.length() == 1) {
				bin = "0" + bin;
			}
			if (i / 8 < 4) {
				plaintxt[j][k] = bin;
				k++;
				if (i / 8 == 3) {
					j++;
					k = 0;
				}
			}
			if (i / 8 > 3 && i / 8 < 8) {
				plaintxt[j][k] = bin;
				k++;
				if (i / 8 == 7) {
					j++;
					k = 0;
				}
			}
			if (i / 8 > 7 && i / 8 < 12) {
				plaintxt[j][k] = bin;
				k++;
				if (i / 8 == 11) {
					j++;
					k = 0;

				}
			}
			if (i / 8 > 11 && i / 8 < 16) {
				plaintxt[j][k] = bin;
				k++;
				if (i / 8 == 15) {
					j++;
					k = 0;
				}

			}

		}

	}

	public String tobinarytext(String plain_text) {
		// TODO Auto-generated method stub

		String result = "";
		char[] messChar = plain_text.toCharArray();

		for (int i = 0; i < messChar.length; i++) {
			String s = Integer.toBinaryString(messChar[i]);
			s = s + "0";
			if (s.length() < 8) {

				s = "0" + s;

			}
			System.out.println(s);
			result += s;
		}
		if (result.length() < 128) {
			int a = 128 - result.length();
			for (i = 0; i < a; i++) {
				result += Math.round(Math.random() * 1);
			}

		}

		return result;
	}

	/* Encryption starts here */

	public void step_multiply() {
		// TODO Auto-generated method stub
		System.out.println("In step multiply:");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				keygen.cipher[i][j] = this.matrix_conv(keygen.cipher[i][j]);
				System.out.print(keygen.cipher[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public String matrix_conv(String s) {
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

		int[][] mul = { { 1, 0, 0, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 0, 1, 1, 1 },
				{ 1, 1, 1, 0, 0, 0, 1, 1 }, { 1, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 0, 0 },
				{ 0, 0, 1, 1, 1, 1, 1, 0 }, { 0, 0, 0, 1, 1, 1, 1, 1 } };
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

	public void step_colummix() {
		// TODO Auto-generated method stub
		int[][] res = new int[4][4];
		int[] a = new int[4];
		int[] b = new int[4];
		int[] c = new int[4];
		int[] d = new int[4];
		int[] e = new int[4];

		System.out.println("In Step-3:");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res[i][j] = Integer.parseInt(keygen.cipher[i][j], 16);

			}
		}
		for (int i = 0; i < 4; i++) {
			a[i] = res[i][0];
			b[i] = res[i][1];
			c[i] = res[i][2];
			d[i] = res[i][3];
			e[i] = res[i][0];

		}
		a = this.column_mix(a, b);
		b = this.column_mix(a, c);
		c = this.column_mix(b, d);
		d = this.column_mix(c, e);

		for (int i = 0; i < 4; i++) {
			res[i][0] = a[i];
			res[i][1] = b[i];
			res[i][2] = c[i];
			res[i][3] = d[i];
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				keygen.cipher[i][j] = Integer.toHexString(res[i][j]);
				keygen.cipher[i][j] = keygen.cipher[i][j].toUpperCase();
				System.out.print(keygen.cipher[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public int[] column_mix(int a[], int b[]) {
		int[] res = new int[4];
		for (int i = 0; i < a.length; i++) {

			res[i] = a[i] ^ b[i];

		}
		return res;
	}

	public void step_shift() {
		// TODO Auto-generated method stub

		int i = 1;
		for (i = 1; i < 4; i++)
			for (int j = 1; j <= i; j++) {
				shift(i);

			}
		System.out.println("\n\ncipher after step 2:");
		for (j = 0; j < 4; j++) {

			for (i = 0; i < 4; i++) {

				System.out.print(keygen.cipher[j][i] + " ");
			}
			System.out.println("");
		}
	}

	public void shift(int i2) {
		String temp = "";

		for (j = 0; j < 4; j++) {

			if (j == 0) {
				temp = keygen.cipher[i2][j];
				keygen.cipher[i2][j] = keygen.cipher[i2][j + 1];
			} else if (j == 3) {
				keygen.cipher[i2][j] = temp;
			} else {
				keygen.cipher[i2][j] = keygen.cipher[i2][j + 1];
			}

		}

	}

	public void step_xor(String final_key, String result_plain) {
		// TODO Auto-generated method stub
		String binary_xor = "";
		for (i = 0; i < 128; i++) {
			int xor = (int) final_key.charAt(i) ^ (int) result_plain.charAt(i);
			binary_xor += Integer.toString(xor);
		}

		forcipher(binary_xor);
		System.out.println("\n\ncipher after step 1:");
		for (j = 0; j < 4; j++) {
			System.out.println(" ");
			for (i = 0; i < 4; i++) {

				System.out.print(keygen.cipher[j][i] + " ");
			}
		}

	}

	public void forcipher(String binary_xor) {
		// TODO Auto-generated method stub
		int j = 0;
		int k = 0;

		for (i = 0; i <= 120; i += 8) {
			String subs = binary_xor.substring(i, i + 8);

			String bin = Integer.toHexString(Integer.parseInt(subs, 2));

			bin = bin.toUpperCase();
			if (bin.length() == 1) {
				bin = "0" + bin;
			}
			if (i / 8 < 4) {
				keygen.cipher[j][k] = bin;
				k++;
				if (i / 8 == 3) {
					j++;
					k = 0;
				}
			}
			if (i / 8 > 3 && i / 8 < 8) {
				keygen.cipher[j][k] = bin;
				k++;
				if (i / 8 == 7) {
					j++;
					k = 0;
				}
			}
			if (i / 8 > 7 && i / 8 < 12) {
				keygen.cipher[j][k] = bin;
				k++;
				if (i / 8 == 11) {
					j++;
					k = 0;

				}
			}
			if (i / 8 > 11 && i / 8 < 16) {
				keygen.cipher[j][k] = bin;
				k++;
				if (i / 8 == 15) {
					j++;
					k = 0;
				}

			}

		}

	}

}
