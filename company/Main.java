package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends CPU {

    public static void main(String[] args) {

        Main bsc = new Main(); // basicComputer 클래스를 생성한다.
        short origin_Address = 0; // 첫 프로그램이 시작하는 번지를 저장하는 변수
        PC = origin_Address; // PC에 값 저장. 여기 까지가 ORG 0

        /*
         * first - pass 메모리에 명령어를 일단 저장한다. 명령어를 적고, 해당 명령어를 이진수로 바꾸는 메소드 필요. 바꾼 이진수는
         * memory 번지 0 부터 저장하는 메소드 필요.
         */

        // 기본 프로그램 시작
        // start SC <- 0 , IEN <- 1 , R <- 0
        SC = 0;
        R = 0;
        IEN = 1;
        short command = load_INP();
        instructionCycle();

    }

    public Main() {
        super.CPU();
    }

    public static void input_File(int CTR, short[] M) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("c:\\test\\input.txt"));
        byte ch1, ch2;
        String result,line;

        while(true) {
            line = br.readLine();
            if(line==null) break;

            int i=0;
            while(true) {
                //라인 끝 체크
                if(i==line.length()) {
                    ch1 = 13; //CR
                    ch2 = 32; //SP
                    result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
                    System.out.println(result);
                    //M[CTR] <- result ??
                    break;
                }
                //슬래시 체크
                else if(line.charAt(i)=='/'){
                    ch1 = 13;
                    ch2 = 32;
                    result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
                    System.out.println(result);
                    //M[CTR] <- result ??
                    break;
                }
                //ch1에 i번째 문자의 아스키코드 값 입력
                ch1 = (byte) line.charAt(i++);
                //라인 끝 체크
                if(i==line.length()) {
                    ch2 = 13;
                    result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
                    System.out.println(result);
                    //M[CTR] <- result ??
                    break;
                }
                //슬래시 체크
                else if(line.charAt(i)=='/'){
                    ch2 = 13;
                    result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
                    System.out.println(result);
                    //M[CTR] <- result ??
                    break;
                }
                //ch2에 i번째 문자의 아스키코드 값 입력
                ch2 = (byte) line.charAt(i++);
                //ch1,ch2 각 값을 이진수로 변환 후 이어붙임
                result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
                System.out.println(result);
                //M[CTR] <- result ??
            }
        }

    }

    public static void instructionCycle(short command) {

        boolean d_Seven_flag = false;

        /* interrupt cycle */
        if (R == 1) {
            /* interrupt cycle */
            /* 인터럽트가 끝나고 종료해야함. */
        }
        /* instruction cycle */
        // T0 : fetch 과정
        System.out.printf("T%d : AR <- PC %n", SC);
        AR = PC;
        incre_SC();
        // T1 : fetch 과정
        System.out.printf("T%d : IR <- M[AR], PC <- PC + 1 %n", SC);

//        test AR's initiation_value to 150
//        -----------------------------------------------------------
//        System.out.println(MEMORY[0]);

//---------------------------------------------------------------
            //I 가 있을때,
            +8


        IR = MEMORY[AR];
        System.out.println(IR);
        PC = (byte) (PC + 1);vt
        incre_SC();
        // T2 : decode 과정

        // AR <- IR 과정이 들어가야함
        AR=IR;
        // IR 을 Decode하는 함수 넣기 -> d_Seven_flag 의 값을 설정하는 과정이 있어야한다.
        System.out.println("Decode");
        System.out.printf("T%d : AR <- IR(0-11), decode IR(12-14), I <- IR(15)%n", SC);
        I = 1; // test code 임.
        incre_SC();

        boolean B_Flag = (!d_Seven_flag && I);
        // T3: 명령어 종류의 결정 -> 책 111p
        // 임의로 flag 를 만들어서 T4에서 명령어의 종류를 판별할 수 있게 알려 주어야 한다.
        if (!d_Seven_flag && I == 1) {
            AR = MEMORY[AR];
            System.out.printf("T%d : AR <-[AR]%n", SC);
//            memory_reference_instruction(command,SC);
        } else if (d_Seven_flag && I == 1) {
            System.out.printf("T%d : I/O command%n", SC);

        } else if (d_Seven_flag && I == 0) {
            System.out.printf("T%d : Register Reference Instruction%n", SC);

        } else {
            System.out.printf("T%d : nothing %n", SC);

        }

        // T4 명령어 단계 실행

    }

    // 순차 카운터의 값을 하나 증가 시키는 과정
    public static void incre_SC() {
        SC = (byte) (SC + 1);
    }
}
