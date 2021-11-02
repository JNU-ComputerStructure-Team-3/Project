import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pass {
    public static void main(String[] args) throws IOException {
        short[] memory = new short[4096];
        HashMap<String, Integer> sat = new HashMap<String, Integer>();
        int LC = 0;
        firstPass(memory, sat, LC);
        for (Map.Entry<String, Integer> element : sat.entrySet()) {
            System.out.println(element.getKey() + " : " + element.getValue());
        }
    }

    public static void firstPass(short[] memory, HashMap<String, Integer> sat, int LC) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input_example.txt"));
        String commandLine;

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
                sat.put(commandLine.substring(0,targetSymbolicIdx), LC++);
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


    public static void secondPass(){

    }
}
