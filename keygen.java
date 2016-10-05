public class keygen {
	int[][] su = new int[9][9];
	int[] key = new int[9];
	int i, j, k;
	String[][] plaintxt = new String[4][4];
	public static String[][] key_array = new String[4][4];
	public static String[][] cipher = new String[4][4];
	public static String ran1, ran2;

	public String[][] receive_key() {
		String[][] keyy = new String[4][4];
		keyy = key_array;

		return keyy;

	}

	keygen() {
		su[0][0] = 8;
		su[1][0] = 6;
		su[2][0] = 5;
		su[3][0] = 3;
		su[4][0] = 2;
		su[5][0] = 7;
		su[6][0] = 9;
		su[7][0] = 4;
		su[8][0] = 1;
		su[0][1] = 4;
		su[1][1] = 7;
		su[2][1] = 1;
		su[3][1] = 6;
		su[4][1] = 9;
		su[5][1] = 5;
		su[6][1] = 2;
		su[7][1] = 3;
		su[8][1] = 8;
		su[0][2] = 2;
		su[1][2] = 9;
		su[2][2] = 3;
		su[3][2] = 8;
		su[4][2] = 1;
		su[5][2] = 4;
		su[6][2] = 6;
		su[7][2] = 7;
		su[8][2] = 5;
		su[0][3] = 5;
		su[1][3] = 1;
		su[2][3] = 8;
		su[3][3] = 9;
		su[4][3] = 3;
		su[5][3] = 2;
		su[6][3] = 7;
		su[7][3] = 6;
		su[8][3] = 4;
		su[0][4] = 3;
		su[1][4] = 2;
		su[2][4] = 7;
		su[3][4] = 1;
		su[4][4] = 4;
		su[5][4] = 6;
		su[6][4] = 8;
		su[7][4] = 5;
		su[8][4] = 9;
		su[0][5] = 9;
		su[1][5] = 4;
		su[2][5] = 6;
		su[3][5] = 7;
		su[4][5] = 5;
		su[5][5] = 8;
		su[6][5] = 3;
		su[7][5] = 1;
		su[8][5] = 2;
		su[0][6] = 7;
		su[1][6] = 8;
		su[2][6] = 4;
		su[3][6] = 2;
		su[4][6] = 6;
		su[5][6] = 1;
		su[6][6] = 5;
		su[7][6] = 9;
		su[8][6] = 3;
		su[0][7] = 6;
		su[1][7] = 5;
		su[2][7] = 9;
		su[3][7] = 4;
		su[4][7] = 8;
		su[5][7] = 3;
		su[6][7] = 1;
		su[7][7] = 2;
		su[8][7] = 7;
		su[0][8] = 1;
		su[1][8] = 3;
		su[2][8] = 2;
		su[3][8] = 5;
		su[4][8] = 7;
		su[5][8] = 9;
		su[6][8] = 4;
		su[7][8] = 8;
		su[8][8] = 6;
		i = 0;
		j = 0;
		k = 0;

	}

	public int select_key(int a, int b) {
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				System.out.print("" + su[i][j]);
			}
			System.out.println("");
		}

		if (a == 0) {
			for (i = 0; i < 9; i++)
				key[i] = su[b][i];
		}
		if (a == 1) {
			for (i = 0; i < 9; i++) {
				key[i] = su[i][b];
			}
		}
		if (a == 2) {
			if (b == 0) {
				for (i = 0; i < 3; i++) {
					for (j = 0; j < 3; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 1) {
				for (i = 0; i < 3; i++) {
					for (j = 3; j < 6; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 2) {
				for (i = 0; i < 3; i++) {
					for (j = 6; j < 9; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 3) {
				for (i = 3; i < 6; i++) {
					for (j = 0; j < 3; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 4) {
				for (i = 3; i < 6; i++) {
					for (j = 3; j < 6; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 5) {
				for (i = 3; i < 6; i++) {
					for (j = 6; j < 9; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 6) {
				for (i = 6; i < 9; i++) {
					for (j = 0; j < 3; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 7) {
				for (i = 6; i < 9; i++) {
					for (j = 3; j < 6; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}
			if (b == 8) {
				for (i = 6; i < 9; i++) {
					for (j = 6; j < 9; j++) {
						key[k] = su[i][j];
						k++;
					}
				}
			}

		}
		/*
		 * for(i=0;i<9;i++){ System.out.print(""+key[i]+" ");}
		 */

		int result = 0;
		int digit = 0;
		for (int i = 0; i < 9; i++) {
			digit = key[i];
			for (int j = 0; j < 8 - i; j++) {
				digit *= 10;
			}
			result += digit;
		}
		// System.out.println("digit:"+result);

		// String keys=Arrays.toString(key);
		k = 0;
		j = 0;
		i = 0;
		return result;
	}

	public String finalkey(int len, String b) {
		String res_redu = "";
		int b_len = b.length();
		if (b_len >= 100) {
			for (i = 0; i < 28; i++) {
				int ins_add = (int) b.charAt(i) ^ (int) b.charAt(b_len - i - 1);
				String ins_adds = Integer.toString(ins_add);
				b = b.concat(ins_adds);
			}
			return b;
		}
		if (b_len < 30) {
			for (i = 0; i < 30 - b_len; i++) {
				int ins_add = (int) b.charAt(i) ^ (int) b.charAt(b_len - i - 1);
				String ins_adds = Integer.toString(ins_add);

				b = ins_adds.concat(b);
			}
		}
		b_len = b.length();
		if (b_len <= len) {

			for (i = 0; i < b_len / 2; i++) {

				int insi = (int) b.charAt(i) ^ (int) b.charAt(b_len - i - 1);

				b = b.concat(Integer.toString(insi));
			}
			return b;
		} else {
			for (i = 0; i < len; i++) {

				int insi = (int) b.charAt(i) ^ (int) b.charAt(b_len - i - 1);
				res_redu = res_redu.concat(Integer.toString(insi));
			}
			return res_redu;
		}

	}

	public void print_key() {

		for (j = 0; j < 4; j++) {

			System.out.println("");

			for (k = 0; k < 4; k++)

			{

				System.out.print(key_array[j][k] + " ");

			}

		}

	}

	public void convbytes(String final_key) {
		// TODO Auto-generated method stub
		// int subi=0;
		int j = 0;
		int k = 0;

		for (i = 0; i <= 120; i += 8) {
			String subs = final_key.substring(i, i + 8);
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
				key_array[j][k] = bin;
				k++;
				if (i / 8 == 3) {
					j++;
					k = 0;
				}
			}
			if (i / 8 > 3 && i / 8 < 8) {
				key_array[j][k] = bin;
				k++;
				if (i / 8 == 7) {
					j++;
					k = 0;
				}
			}
			if (i / 8 > 7 && i / 8 < 12) {
				key_array[j][k] = bin;
				k++;
				if (i / 8 == 11) {
					j++;
					k = 0;

				}
			}
			if (i / 8 > 11 && i / 8 < 16) {
				key_array[j][k] = bin;
				k++;
				if (i / 8 == 15) {
					j++;
					k = 0;
				}

			}

		}

	}

}