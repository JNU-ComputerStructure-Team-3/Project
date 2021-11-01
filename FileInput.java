import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInput {

	public static void main(String[] args) throws IOException {

		short[] MEMORY = new short[4096];
		input_File(MEMORY);

	}

	public static void input_File(short[] M) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input_example.txt"));
		int LC = 0;
		String result, line;

		while (true) {
			line = br.readLine();
			if (line == "END" || line == null)
				break;

			String comma = ",";
			String space = " ";

			int target_comma = line.indexOf(comma);
			int target_space = line.indexOf(space);
			int target_last_space = line.lastIndexOf(space);

			String symbol = "";
			String command = "";
			String operand = "";
			String indirect_signal = "";

			// 콤마가 있을 때 -> symbol 이 있음.
			if (target_comma != -1) {
				symbol = line.substring(0, target_comma);
				System.out.print("symbol:" + symbol);
				command = line.substring(target_comma + 1, target_comma + 4);
				System.out.print("command:" + command);
				operand = line.substring(target_space + 1, line.length());
				System.out.println("operand:" + operand);

			} else if (target_comma == -1 && target_space == -1) {
				command = line;
				System.out.println("command:" + command);
			}

			else {
				command = line.substring(0, 4);
				System.out.print("command:" + command);
				operand = line.substring(4, line.length());
				System.out.println("operand:" + operand);
			}

			// 명령어 저장 과정
		}
	}

	public String commandTable(String command, String indirect_Signal) {
		switch (command) {
		case "AND":
			if (indirect_Signal == "I")
				return "8";
			else
				return "0";
		case "ADD":
			if (indirect_Signal == "I")
				return "9";
			else
				return "1";
		case "LDA":
			if (indirect_Signal == "I")
				return "A";
			else
				return "2";
		case "STA":
			if (indirect_Signal == "I")
				return "B";
			else
				return "3";
		case "BUN":
			if (indirect_Signal == "I")
				return "C";
			else
				return "4";
		case "BSA":
			if (indirect_Signal == "I")
				return "D";
			else
				return "5";
		case "ISZ":
			if (indirect_Signal == "I")
				return "E";
			else
				return "0";
		}
		return "";
	}
}