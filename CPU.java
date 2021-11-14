package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

//최상위 클래스
//단 한개의 프로세서 레지스터를 갖는 컴퓨터
public abstract class CPU {
    protected static short[] MEMORY = new short[4096]; // 메모리
    protected static short DR; // 데이터 - 메모리에서 읽어온 피연산자를 저장 (16)
    protected static short AR; // 메모리 주소 - 메모리의 주소를 나타냄 (4096범위이므로 12비트로 제한)
    protected static short AC; // 누산기 - 범용 처리 레지스터 (16)
    protected static short IR; // 명령어 - 메모리에서 읽어온 명령어 저장 (16)
    protected static short PC; // 프로그램 카운터 - 메모리의 주소를 나타냄 (12)
    protected static short TR; // 임시 - 계산 도중의 임시 데이터 (16)
    protected static byte INPR; // 입력 - 입출력 장치로부터 8비트 문자 정보 송수신 (8)
    protected static byte OUTR; // 출력 - 입출력. (8)
    protected static byte SC; // 순차 카운터
    protected static boolean I; // 간접주소 enable code
    protected static byte R; // interrupt code
    protected static byte E;
    protected static byte IEN; // enable code


    public static void CPU() {
        // 메모리 생성
        // 레지스터 생성
        DR = 0;
        AR = 0;
        TR = 0;
        AC = 0;
        E = 0;
        SC = 0;
    }

    public static int firstPass(HashMap<String, String> sat, int LC, BufferedReader br) throws IOException {
        String commandLine;
        int ORG_value = 0;
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
                String LCString = "" + Integer.toHexString(LC);
                while(LCString.length() != 3){
                    LCString = "0" + LCString;
                }
                sat.put(commandLine.substring(0,targetSymbolicIdx), LCString);

                if(commandLine.substring(targetSymbolicIdx + 1, targetSymbolicIdx + 4).equals("DEC")){
                    int temp = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1));
                    MEMORY[LC++] = (short)temp;
                }
                else if(commandLine.substring(targetSymbolicIdx + 1, targetSymbolicIdx + 4).equals("HEX")){
                    int temp = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1), 16);
                    MEMORY[LC++] = (short)temp;
                } else {
                    LC++;
                }

            }
            // it's doesn't have labels
            else {
                if (commandLine.substring(0, 3).equals("ORG")) {
                    LC = Integer.parseInt(commandLine.substring(targetSpaceIdx + 1));
                    ORG_value = LC;
                    continue;
                } else if (commandLine.substring(0, 3).equals("END")) {
                    // go to second pass
                    break;
                } else {
                    LC++;
                }
            }
        }

        return ORG_value;
    }

    public static void secondPass(HashMap<String, String> sat, int LC, BufferedReader sbr) throws IOException{
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
            String command = "";
            boolean MRI_flag = false;
            boolean non_MRI_flag = false;

            int targetSymbolicIdx = commandLine.indexOf(comma);
            int targetSpaceIdx = commandLine.indexOf(space);
            int targetSecondSpaceIdx = commandLine.lastIndexOf(space);

            if(targetSecondSpaceIdx == -1) targetSecondSpaceIdx = commandLine.length();

            // already have decode processing in first pass. (DEC, HEX)
            if(targetSymbolicIdx != -1 && (commandLine.substring(targetSymbolicIdx + 1, targetSymbolicIdx + 4).equals("DEC") || commandLine.substring(targetSymbolicIdx + 1, targetSymbolicIdx + 4).equals("HEX"))){
                LC++;
                continue;
            }
            else if(targetSymbolicIdx != -1){
                command = commandLine.substring(targetSymbolicIdx + 1, targetSymbolicIdx + 4);
            }
            else{
                command = commandLine.substring(0,3);
            }

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
                MEMORY[LC++] = (short) temp;
                continue;
            } else if (symbolAddress != null && MRI_flag){
                secondPassCommand = secondPassCommand + symbolAddress;
                int temp = Integer.parseInt(secondPassCommand, 16);
                MEMORY[LC++] = (short)temp;
                continue;
            }

            // non - MRI instruction
            int temp;
            switch (command){
                case "CLA":
                    temp = Integer.parseInt("7800", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "CLE":
                    temp = Integer.parseInt("7400", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "CMA":
                    temp = Integer.parseInt("7200", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "CME":
                    temp = Integer.parseInt("7100", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "CIR":
                    temp = Integer.parseInt("7080", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "CIL":
                    temp = Integer.parseInt("7040", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "INC":
                    temp = Integer.parseInt("7020", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "SPA":
                    temp = Integer.parseInt("7010", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "SNA":
                    temp = Integer.parseInt("7008", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "SZA":
                    temp = Integer.parseInt("7004", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "SZE":
                    temp = Integer.parseInt("7002", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "HLT":
                    temp = Integer.parseInt("7001", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "INP":
                    temp = Integer.parseInt("F800", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "OUT":
                    temp = Integer.parseInt("F400", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "SKI":
                    temp = Integer.parseInt("F200", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "SKO":
                    temp = Integer.parseInt("F100", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "ION":
                    temp = Integer.parseInt("F080", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
                case "IOF":
                    temp = Integer.parseInt("F040", 16);
                    MEMORY[LC++] = (short) temp;
                    break;
            }
        }

    }

}