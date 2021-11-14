package com.company;

import com.company.CPU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


class basicComputer extends CPU {

    public static void main(String[] args) throws IOException {
        basicComputer bsc = new basicComputer(); // basicComputer 클래스를 생성한다.
        HashMap<String, String> sat = new HashMap<String, String>();
        short origin_Address = 0; // 첫 프로그램이 시작하는 번지를 저장하는 변수

        BufferedReader br = new BufferedReader(new FileReader("input_example.txt"));
        BufferedReader sbr = new BufferedReader(new FileReader("input_example.txt"));

        int LC = 0;
        origin_Address = (short)firstPass(sat, LC, br);
        secondPass(sat, LC, sbr);

        for (Map.Entry<String, String> element : sat.entrySet()) {
            System.out.println(element.getKey() + " : " + element.getValue());
        }
        for(int i=100; i<121; i++){
            System.out.println("Memory Idx:" + i + " Value:" + Integer.toHexString(MEMORY[i]));
        }


        // 기본 프로그램 시작
        // start SC <- 0 , IEN <- 1 , R <- 0
        SC = 0;
        R = 0;
        IEN = 1;
        PC = origin_Address ;
        while(true) {
            if (instructionCycle(MEMORY[PC]) == -1) {
                System.out.println("program exit.");
                break;
            }
        }
    }

    public static int instructionCycle(short operand) {

        int address_mask = 4095 ; // 0000 0000 0000 0000 0000 1111 1111 1111
        int opcode_mask = 61440; // 0000 0000 0000 0000 1111 0000 0000 0000
        int decode_opcode ;         // 4자리 opcode



        /* instruction cycle */
        // T0 : fetch 과정
        if(PC > 130) return -1; // 임시코드
        System.out.println("-----------------------------------------");
        System.out.printf("T%d : AR <- PC %n", SC++);
        AR = PC;
        System.out.println("AR :" + AR);
        // T1 : fetch 과정
        System.out.printf("T%d : IR <- M[AR], PC <- PC + 1 %n", SC++);
        IR = MEMORY[AR];
        PC = (short) (PC + 1);
        System.out.println("IR : 0x" + Integer.toHexString(IR));
        System.out.println("PC : " + PC);
        // T2 : decode 과정
        // AR <- IR 과정이 들어가야함
        AR = (short)(IR & address_mask);
        System.out.println("Decode");
        System.out.printf("T%d : AR <- IR(0-11), decode IR(12-14), I <- IR(15)%n", SC);
        System.out.println("AR :" + AR);
        decode_opcode = (short)((IR & opcode_mask)>>>12);
        System.out.println("opcode with Indirect code : " + Integer.toHexString(decode_opcode));
        incre_SC();

        // T3 단계.
        if(decode_opcode < 7){
            System.out.printf("T%d : noting %n", SC);
            SC++;
            execute_Memory_Command(decode_opcode);
        } else if(decode_opcode == 7){
            System.out.printf("T%d : Register Reference Instruction %n", SC);
            SC++;
            if(execute_Register_Command(IR) == -1) return -1;
        } else if(decode_opcode == 15){
            System.out.printf("T%d : I/O Instruction %n", SC);
            SC++;
        } else {
            AR = (short)(MEMORY[AR] & address_mask);
            I = true;
            System.out.printf("T%d : Enable Indirect Register %n", SC);
            System.out.printf("T%d : AR <- M[AR] %n", SC);
            System.out.println("AR :" + AR);
            SC++;
            decode_opcode -= 8;
            execute_Memory_Command(decode_opcode);
        }
        return 0;
    }

    public static int execute_Memory_Command(int opcode) {
        switch (opcode) {
//          Memory-Reference
            case 0:
//                AND
                System.out.println("AND Command");
                DR = MEMORY[AR];
                System.out.printf("T%d : DR <- M[AR]%n",SC++);
                System.out.println("DR : " + DR);
                System.out.printf("T%d : AC <- AC and DR, SC <- 0 %n", SC);
                System.out. println("AC : " + AC);
                AC = (short)(AC & DR);
                System.out.println("AC and DR :" + AC);
                SC = 0;
                break;
            case (1):
//                ADD
                System.out.println("ADD Command");
                DR = MEMORY[AR];
                System.out.printf("T%d : DR <- M[AR]%n",SC++);
                System.out.println("DR : " + DR);
                System.out.printf("T%d : AC <- AC + DR, SC <- 0%n",SC);
                System.out.println("AC :" + AC);
                AC = (short) (AC + DR);
                System.out.println("AC + DR :" + AC);
                SC = 0;
                break;
            case (2):
//                LDA
                System.out.println("LDA Command");
                DR = MEMORY[AR];
                System.out.printf("T%d : DR <- M[AR]%n",CPU.SC++);
                System.out.println("DR :" + DR);
                AC = DR;
                System.out.printf("T%d : AC <- DR, SC <- 0%n", SC);
                System.out.println("AC : " + AC);
                SC = 0;
                break;
            case (3):
//                STA
                System.out.println("STA Command");
                MEMORY[AR] = AC;
                System.out.printf("T%d : M[AR] <- AC, SC<- 0%n", SC);
                SC = 0;
                break;
            case (4):
                //<BUN>
                //T4
                PC = AR;
                System.out.println("BUN Command");
                System.out.printf("T%d : PC <- AR, SC <- 0%n", SC);
                System.out.println("PC :" + PC);
                SC = 0;
                break;
            case (5):
                //<BSA>
                //T4
                System.out.println("BSA Command");
                MEMORY[AR] = PC;
                AR ++;
                System.out.printf("T%d : M[AR] <- PC, AR <- AR + 1%n", SC++);
                PC = AR;
                System.out.printf("T%d : PC<-AR, SC <- 0%n", SC);
                SC = 0;
                break;
            case (6):
                //<ISZ>
                System.out.println("ISZ Command");
                DR = MEMORY[AR];
                System.out.printf("T%d : DR <- M[AR]%n", SC++);
                //T5
                DR++;
                System.out.printf("T%d : DR <- DR + 1%n", SC++);
                //T6
                MEMORY[AR] = DR;
                if (DR == 0) PC++;
                System.out.printf("D6T%d : M[AR] <- DR, if (DR = 0) then (PC <- PC + 1), SC<-0%n", SC);
                SC = 0;
                break;
        }
        return 0;
    }

    public static int execute_Register_Command(short command){
//  Register-Reference
        switch (command) {
            case 0x7800:
//                CLA
                System.out.println("CLA");
                System.out.printf("T%drB11 : AC <- 0, SC <- 0 %n", SC);
                AC = 0;
                SC = 0;
                break;
            case 0x7400:
//                CLE
                System.out.println("CLE");
                System.out.printf("T%drB10 : E <- 0, SC <- 0 %n", SC);
                E = 0;
                SC = 0;
                break;
            case 0x7200:
//                CMA
                System.out.println("CMA");
                System.out.printf("T%drB9 : AC <- AC', SC <-0 %n", SC);
                AC = (short) ~AC;
                SC = 0;
                break;
            case 0x7100:
//                CME
                System.out.println("CME");
                System.out.printf("T%drB8 : E <- E', SC <- 0", SC);
                E = (byte)(E*-1);
                SC = 0;
                break;
            case 0x7080:
//                CIR
                System.out.println("CIR Command");
                System.out.printf("T%drB7 : AC <- shr AC, AC(15) <- E, E <- AC(0), SC <- 0 %n", SC);
                short AC_2 = (short) ((AC >>> 1) | (AC << -1));
//                AC = (short) Integer.rotateRight(AC, 1);
                if((short) (AC%2) == 1){
                    E = 1;
                    AC = AC_2;
                }else{
                    E = 0;
                    AC = AC_2;
                }
                SC = 0;
                break;
            case 0x7040:
//                CIL
                System.out.println("CIL Command");
                System.out.printf("T%drB6 : AC <- shl AC, AC(0) <- E, E <- AC(15), SC <- 0 %n", SC);
                AC_2 = (short) ((AC << 1) | (AC >>> -1));
                if((short) (AC/0x1000)>=7){
                    E = 1;
                    AC = AC_2;
                }else{
                    E = 0;
                    AC = AC_2;
                }
                SC = 0;
                break;
            case 0x7020:
//                INC
                System.out.println("INC");
                System.out.printf("T%drB5 : AC <- AC + 1, SC <- 0 %n", SC);
                AC += 1;
                SC = 0;
                break;
            case 0x7010:
//                SPA
                System.out.println("SPA");
                System.out.printf("T%drB4 : If(AC(15)=0), PC <- PC + 1 %n", SC);
                if(AC>0){
                    PC = (short)(PC + 1);
                }
                SC = 0;
                break;
            case 0x7008:
//                SNA
                System.out.println("SNA");
                System.out.printf("T%drB4 : If(AC(15)=1), PC <- PC + 1 %n", SC);
                if(AC < 0){
                    PC = (short)(PC + 1);
                }
                SC = 0;
                break;
            case 0x7004:
//                SZA
                System.out.println("SZA");
                System.out.printf("T%drB2 : Skip ( PC <- PC +1 )  if AC is zero", SC);
                if(AC == 0){
                    PC = (short)(PC + 1);
                }
                SC = 0;
                break;
            case 0x7002:
//                SZE
                System.out.println("SZE");
                System.out.printf("T%drB1 : Skip ( PC <- PC +1 )  if E == zero %n", SC);
                if(E == 0)
                    PC = (short) (PC + 1);
                SC = 0;
                break;
            case 0x7001:
//                HLT
                System.out.println("HLT");
                System.out.printf("T%drB0 : HLT, S <- 0 %n", SC);
                SC = 0;
                return -1;
        }
        return 0;
    }

    // 순차 카운터의 값을 하나 증가 시키는 과정
    public static void incre_SC(){
        SC = (byte) (SC + 1);
    }
}