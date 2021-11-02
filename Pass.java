import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pass {
    public static void main(String[] args) throws IOException {
        short[] memory = new short[4096];
        HashMap<String, Integer> sat = new HashMap<String, Integer>();
        BufferedReader br = new BufferedReader(new FileReader("input_example.txt"));
        BufferedReader sbr = new BufferedReader(new FileReader("input_example.txt"));

        int LC = 0;
        firstPass(memory, sat, LC, br);
        for (Map.Entry<String, Integer> element : sat.entrySet()) {
            System.out.println(element.getKey() + " : " + element.getValue());
        }
        for(int i=0; i<10; i++){
            System.out.println("Memory Idx:" + i + " Value:" + memory[i]);
        }
        secondPass(memory, sat, LC, sbr);

    }

    public static void firstPass(short[] memory, HashMap<String, Integer> sat, int LC, BufferedReader br) throws IOException {
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
                sat.put(commandLine.substring(0,targetSymbolicIdx), LC);
                if(commandLine.substring(targetSymbolicIdx + 1, targetSpaceIdx).equals("DEC")){
                    int temp = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1));
                    memory[LC++] = (short)temp;
                }
                else if(commandLine.substring(targetSymbolicIdx + 1, targetSpaceIdx).equals("HEX")){
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


    public static void secondPass(short[] memory, HashMap<String, Integer> sat, int LC, BufferedReader sbr) throws IOException{
        String commandLine = "";
        LC = 0;
        while(true){
            commandLine = sbr.readLine();
            // 프로그램 읽기가 끝날 때
            if (commandLine == null)
                break;

            String comma = ",";
            String space = " ";
            String commandOpcode = "0x";
            String commandOperand ;
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
            boolean indirectSignal = false;
            if(commandLine.substring(commandLine.length()-1 , commandLine.length()).equals("I")) indirectSignal = true;
            switch(command){
                default:
                    MRI_flag = true;
                case "AND":
                    commandOpcode = commandOpcode + (indirectSignal ? "8" : "0");
                    break;
                case "ADD":
                    commandOpcode = commandOpcode + (indirectSignal ? "9" : "1");
                    break;
                case "LDA":
                    commandOpcode =  commandOpcode + (indirectSignal ? "A" : "2");
                    break;
                case "STA":
                    commandOpcode = commandOpcode + (indirectSignal ? "B" : "3");
                    break;
                case "BUN":
                    commandOpcode = commandOpcode + (indirectSignal ? "C" : "4");
                    break;
                case "BSA":
                    commandOpcode = commandOpcode + (indirectSignal ? "D" : "5");
                    break;
                case "ISZ":
                    commandOpcode = commandOpcode + (indirectSignal ? "E" : "6");
                    break;
            }
            commandOperand = commandLine.substring(targetSpaceIdx+1, targetSecondSpaceIdx);
            Integer symbolIdx = sat.get(commandOperand);
            if(symbolIdx != null){
            }else{

            }

        }

    }
}
