import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileInput {

	public static void main(String[] args) throws IOException {

		short[] MEMORY = new short[4096];
		HashMap<String, Integer> symbolAddressTable = new HashMap<String, Integer>();
		command_Decoding(MEMORY, symbolAddressTable);

	}

	public static void command_Decoding(short[] M, HashMap<String, Integer> sat) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input_example.txt"));
		int LC = 0;
		String line;
		while (true) {
			line = br.readLine();
			// 프로그램 읽기가 끝날 때
			if (line.equals("END") || line == null)
				break;

			String comma = ",";
			String space = " ";

			int target_comma = line.indexOf(comma);
			int target_space = line.indexOf(space);
			int check_indirect_code = line.indexOf(line.length()-1);

			String symbol = "";
			String command = "";
			String operand = "";
			String command_code;
			boolean  indirect_signal = false;
	
			// 콤마가 있을 때 -> symbol 이 있음.
			if (target_comma != -1) {
				symbol = line.substring(0, target_comma);
				command = line.substring(target_comma + 1, target_comma + 4);
				operand = line.substring(target_space + 1);
				sat.put(symbol, LC++);
				System.out.println(sat.get(symbol));
				System.out.println(commandTable(command, operand, indirect_signal));

			// operand와 symbol 이 없는 명령어 reference - instruction,
			} else if (target_comma == -1 && target_space == -1) {
				command = line;
				System.out.println(commandTable(command, operand, indirect_signal));
				LC++;
			}
			// 전형적인 command 명령어 + 수도 코드(end 제외)
			// I를 처리하는 과정 구현 해야함.
			else {
				command = line.substring(0, 3);
				operand = line.substring(4);

				LC++;
			}


		}
	}

	
	public static String commandTable(String command, String operand, boolean indirect_Signal) {
		String ret = "" ;
		switch (command) {
		case "AND":
			ret = indirect_Signal ? "0x8" : "0x0" ;
			// hashmap 매핑을 통해 구현해야함.
			break;
		case "ADD":
			ret = indirect_Signal ? "0x9" : "0x1" ;
			// hashmap 매핑을 통해 구현해야함.
			break;
		case "LDA":
			ret = indirect_Signal ? "0xA" : "0x2" ;
			// hashmap 매핑을 통해 구현해야함.
			break;
		case "STA":
			ret = indirect_Signal ? "0xB" : "0x3" ;
			// hashmap 매핑을 통해 구현해야함.
			break;
		case "BUN":
			ret = indirect_Signal ? "0xC" : "0x4" ;
			// hashmap 매핑을 통해 구현해야함.
			break;
		case "BSA":
			ret = indirect_Signal ? "0xD" : "0x5" ;
			// hashmap 매핑을 통해 구현해야함.
			break;
		case "ISZ":
			ret = indirect_Signal ? "0xE" : "0x6" ;
			// hashmap 매핑을 통해 구현해야함.
			break;
		case "CLA":
			ret = "0x7800";
			break;
		case "CLE":
			ret = "0x7400";
			break;
		case "CMA":
			ret = "0x7200";
			break;
		case "CME":
			ret = "0x7100";
			break;
		case "CIR":
			ret = "0x7080";
			break;
		case "CIL":
			ret = "0x7040";
			break;
		case "INC":
			ret = "0x7020";
			break;
		case "SPA":
			ret = "0x7010";
			break;
		case "SNA":
			ret = "0x7008";
			break;
		case "SZE":
			ret = "0x7002";
			break;
		case "HLT":
			ret = "0x7001";
			break;
		case "DEC":
			// 10진수 -> 16진수
			break;
		case "HEX":
			// 16진수 대입
			break;
		}
		return ret;
	}
}

	