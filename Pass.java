import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pass {
    public static void main(String[] args) throws IOException {
        short[] memory = new short[4096];
        HashMap<String, String> sat = new HashMap<String, String>();
        BufferedReader br = new BufferedReader(new FileReader("input_example.txt"));
        BufferedReader sbr = new BufferedReader(new FileReader("input_example.txt"));

        int LC = 0;
        firstPass(memory, sat, LC, br);
        secondPass(memory, sat, LC, sbr);

        for (Map.Entry<String, String> element : sat.entrySet()) {
            System.out.println(element.getKey() + " : " + element.getValue());
        }
        for(int i=0; i<200; i++){
            System.out.println("Memory Idx:" + i + " Value:" + Integer.toHexString(memory[i]));
        }

    }

    public static void firstPass(short[] memory, HashMap<String, String> sat, int LC, BufferedReader br) throws IOException {
        String commandLine;
        LC = 0;
        while(true){
            commandLine = br.readLine();
            // 프로그램 읽기가 끝날 때
            if (commandLine.equals("END") || commandLine == null)
                break;

            String comma = ",";
            String space = " ";
            String command = "";

            int targetSymbolicIdx = commandLine.indexOf(comma);
            int targetSpaceIdx = commandLine.indexOf(space);

            // line of code have Labels
            if(targetSymbolicIdx != -1){
                String LCString = "" + Integer.toString(LC);
                while(LCString.length() != 3){
                    LCString = "0" + LCString;
                }
                sat.put(commandLine.substring(0,targetSymbolicIdx), LCString);
                if(commandLine.substring(targetSymbolicIdx + 1, targetSpaceIdx).equals("DEC")){
                    int temp = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1));
                    memory[LC++] = (short)temp;
                }
                else if(commandLine.substring(targetSymbolicIdx + 1, targetSpaceIdx).equals("HEX")){
                    int temp = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1), 16);
                    memory[LC++] = (short)temp;
                }

            }
            // it's doesn't have labels
            else {
                if (commandLine.substring(0, 3).equals("ORG")) {
                    LC = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1));
                    continue;
                } else if (commandLine.substring(0, 3).equals("END")) {
                    // go to second pass
                    break;
                } else {
                    LC++;
                }
            }
        }

        return;
    }

    public static void secondPass(short[] memory, HashMap<String, String> sat, int LC, BufferedReader sbr) throws IOException{
        String commandLine = "";
        LC = 0;
        while(true){
            commandLine = sbr.readLine();
            // 프로그램 읽기가 끝날 때
            if (commandLine == null)
                break;

            String comma = ",";
            String space = " ";
            String secondPassCommand = "";
            String commandOperand = "";
            boolean MRI_flag = false;
            boolean non_MRI_flag = false;

            int targetSymbolicIdx = commandLine.indexOf(comma);
            int targetSpaceIdx = commandLine.indexOf(space);
            int targetSecondSpaceIdx = commandLine.lastIndexOf(space);

            if(targetSecondSpaceIdx == -1) targetSecondSpaceIdx = commandLine.length();

            // already have decode processing in first pass. (DEC, HEX)
            if(targetSymbolicIdx != -1){
                LC++;
                continue;
            }
            String command = commandLine.substring(0,3);
            // pseudo - instruction
            if(command.equals("ORG")){
                LC = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1));
                continue;
            } else if(command.equals("END")){
                return ;
            }

            // MRI
            // 간접 주소에 따라 처리해주는 과정
            boolean indirectSignal = false;
            if(commandLine.substring(commandLine.length()-1).equals("I")) {
                indirectSignal = true;
                commandOperand = commandLine.substring(targetSpaceIdx + 1, targetSecondSpaceIdx);
            }
            switch(command){
                case "AND":
                    secondPassCommand = secondPassCommand + (indirectSignal ? "8" : "0");
                    MRI_flag = true;
                    break;
                case "ADD":
                    secondPassCommand = secondPassCommand + (indirectSignal ? "9" : "1");
                    MRI_flag = true;
                    break;
                case "LDA":
                    secondPassCommand = secondPassCommand + (indirectSignal ? "A" : "2");
                    MRI_flag = true;
                    break;
                case "STA":
                    secondPassCommand = secondPassCommand + (indirectSignal ? "B" : "3");
                    MRI_flag = true;
                    break;
                case "BUN":
                    secondPassCommand = secondPassCommand + (indirectSignal ? "C" : "4");
                    MRI_flag = true;
                    break;
                case "BSA":
                    secondPassCommand = secondPassCommand + (indirectSignal ? "D" : "5");
                    MRI_flag = true;
                    break;
                case "ISZ":
                    secondPassCommand = secondPassCommand + (indirectSignal ? "E" : "6");
                    MRI_flag = true;
                    break;
            }
            commandOperand = commandLine.substring(targetSpaceIdx + 1);
            String symbolAddress = sat.get(commandOperand);
            // if it's not symbolic
            if(symbolAddress == null && MRI_flag){
                secondPassCommand = secondPassCommand + commandOperand;
                System.out.println(commandLine + " " + secondPassCommand);
                int temp = Integer.parseInt(secondPassCommand, 16);
                memory[LC++] = (short) temp;
                continue;
            } else if (symbolAddress != null && MRI_flag){
                    secondPassCommand = secondPassCommand + symbolAddress;
                    int temp = Integer.parseInt(secondPassCommand, 16);
                    memory[LC++] = (short)temp;
                    continue;
            }

            // non - MRI instruction
            int temp;
            switch (command){
                case "CLA":
                    temp = Integer.parseInt("7800", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "CLE":
                    temp = Integer.parseInt("7400", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "CMA":
                    temp = Integer.parseInt("7200", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "CME":
                    temp = Integer.parseInt("7100", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "CIR":
                    temp = Integer.parseInt("7080", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "CIL":
                    temp = Integer.parseInt("7040", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "INC":
                    temp = Integer.parseInt("7020", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "SPA":
                    temp = Integer.parseInt("7010", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "SNA":
                    temp = Integer.parseInt("7008", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "SZA":
                    temp = Integer.parseInt("7004", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "SZE":
                    temp = Integer.parseInt("7002", 16);
                    memory[LC++] = (short) temp;
                    break;
                case "HLT":
                    temp = Integer.parseInt("7001", 16);
                    memory[LC++] = (short) temp;
                    break;
            }
        }

    }
}
