package HextoDec;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HextoDec {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String hexInput = br.readLine();
		
		System.out.println("input : " + hexInput);
		
		int decimal = Integer.parseInt(hexInput,16);
		System.out.println("Hex -> Decimal : " + decimal);

	}
}