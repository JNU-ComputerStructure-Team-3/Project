public class hexToBIn {
	final static String HEX = "FFE9012";

	public static void main(String[] args) {
		
		//2. String -> StirngArray 출력
		for (int i= 0; i<HEX.length();i++) {
			System.out.print(hexToBin_StringArray(HEX)[i]+" ");
		}
		System.out.println();
		
		//3. String -> IntArray 출력
		for (int i = 0; i<HEX.length()*4;i++) {
			System.out.print(hexToBin_IntArray(HEX)[i] + " ");
		}
		System.out.println();
		
		System.out.println(hexToBin(HEX));
		}
	
	
	
	// 1. HexString -> BinString
	public static String hexToBin(String hex) {
		int hexLen = hex.length();
		int bit4 = 0;
		int bit1, count, tempHex;
		int[] temp = new int[4];
		String binString = "";
		
		// 4비트씩 나눠서 2진수로 변환
		while (bit4<hexLen) { // Hex -> 4bit
			bit1 = count = 0;
			tempHex = (int)hex.charAt(bit4); // char -> int
			
			// 1. ASCII -> Hex
			if (tempHex <58 && tempHex>47) tempHex -= 48;
			else if (tempHex > 64 && tempHex < 71) tempHex -= 55;
			else break;
			
			// 2. Hex -> BinArray
			while (bit1<4) { // 4bit -> 1bit
				temp[3-bit1] = tempHex%2;
				tempHex /= 2;
				bit1 ++;
			}
			
			// 3. BinIntArray -> BinString
			while (count<4) {
				binString += Integer.toString(temp[count]);// 변환된 2진수를 String으로 변환
				temp[count] = 0;
				count ++;
			}
			bit4 ++;
		}
		return binString;
	}
	
	// 2. HexString -> BinStringArray
	public static String[] hexToBin_StringArray(String hex) {
		int hexLen = hex.length();
		int bit4 = 0;
		int bit1, count, tempHex;
		int[] temp = new int[4];
		String[] binStringArray = new String[hexLen];
		String binString;
		
		// 4비트씩 나눠서 2진수로 변환
		while (bit4<hexLen) { // Hex -> 4bit
			bit1 = count = 0;
			tempHex = (int)hex.charAt(bit4); // char -> int
			binString = "";
			
			// 1. ASCII -> Hex
			if (tempHex <58 && tempHex>47) tempHex -= 48;
			else if (tempHex > 64 && tempHex < 71) tempHex -= 55;
			else break;
			
			// 2. Hex -> BinArray
			while (bit1<4) { // 4bit -> 1bit
				temp[3-bit1] = tempHex%2;
				tempHex /= 2;
				bit1 ++;
			}
			
			// 3. BinIntArray -> BinString
			while (count<4) {
				binString += Integer.toString(temp[count]);// 변환된 2진수를 String으로 변환
				temp[count] = 0;
				count ++;
			}
			
			// 4. BinString -> BinStringArray
			binStringArray[bit4] = binString;
			bit4 ++;
		}
		return binStringArray;
	}
	
	// 3. Stirng -> Int Array
	public static int[] hexToBin_IntArray(String hex) {
		int hexLen = hex.length();
		int bit4 = 0;
		int bit1, count, tempHex;
		int[] temp = new int[4*hexLen];
		String binString = "";
		
		// 4비트씩 나눠서 2진수로 변환
		while (bit4<hexLen) { // Hex -> 4bit
			bit1 = count = 0;
			tempHex = (int)hex.charAt(bit4); // char -> int
			
			// 1. ASCII -> Hex
			if (tempHex <58 && tempHex>47) tempHex -= 48;
			else if (tempHex > 64 && tempHex < 71) tempHex -= 55;
			else break;
			
			// 2. Hex -> BinArray
			while (bit1<4) { // 4bit -> 1bit
				temp[4*bit4 + 3-bit1] = tempHex%2;
				tempHex /= 2;
				bit1 ++;
			}
			bit4 ++;
		}
		return temp;
	}
	

	
}
