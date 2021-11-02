import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public class Main extends CPU {

    public static void main(String[] args) {
    	
    	short command = 0x5200;

        Main bsc = new Main();
        short origin_Address = 0;
        PC = origin_Address;

        SC = 0;
        R = 0;
        IEN = 1;

        while(true) {
            if (instructionCycle(command) == -1) {
                break;
            }
        }
    }

	public Main() {
        super.CPU();
    }
    public static int instructionCycle(short command) {

        boolean d_Seven_flag = false;

        System.out.printf("T%d : AR <- PC %n", SC);
        AR = PC;
        incre_SC();
        // T1 : fetch 과정
        System.out.printf("T%d : IR <- M[AR], PC <- PC + 1 %n", SC);

        //I 가 있을때 +8


        IR = MEMORY[AR];
        System.out.println(IR);
        PC = (byte) (PC + 1);
        incre_SC();
        // T2 : decode 과정



        // AR <- IR 과정이 들어가야함
        AR = IR;
        // IR 을 Decode하는 함수 넣기 -> d_Seven_flag 의 값을 설정하는 과정이 있어야한다.
        System.out.println("Decode");
        System.out.printf("T%d : AR <- IR(0-11), decode IR(12-14), I <- IR(15)%n", SC);
        I = true; // test code 임.
        incre_SC();

        boolean B_Flag = (!d_Seven_flag && I);
        // T3: 명령어 종류의 결정 -> 책 111p
        // 임의로 flag 를 만들어서 T4에서 명령어의 종류를 판별할 수 있게 알려 주어야 한다.
        if (!d_Seven_flag && I == true) {
            AR = MEMORY[AR];
            System.out.printf("T%d : AR <-[AR]%n", SC);
            incre_SC();/* MRI 명령어중 SC==4가 필요하기 때문에 추가 [승현]*/
        } else if (d_Seven_flag && I == true) {
            System.out.printf("T%d : I/O command%n", SC);

        } else if (d_Seven_flag && I == false) {
            System.out.printf("T%d : Register Reference Instruction%n", SC);

        } else {
            System.out.printf("T%d : nothing %n", SC);

        }
        
        print();
        // T4 명령어 단계 실행

        if ((!d_Seven_flag && I == true) | (!d_Seven_flag && I == false)) {
            if (execute_Memory_Command(command, SC) == -1) {
//                HLT
            	SC = 0;
                return -1;
            }
        } else {
            int A = execute_Register_Command(command, SC);
            if (A == -1) {
//                HLT
                return -1;
            }
        }
		return -1;
    }
/* TEST를 위해서 모든 void형의 메소드를 int형식으로 전환함. [승현]*/
    public static int execute_Memory_Command(short command, int SC) {
        switch ((int) (command / Math.pow(16,3) % 8)) {
//          Memory-Reference
            case (0):
//                AND
                break;
            case (1):
//                ADD
                break;
            case (2):
//                LDA
                break;
            case (3):
//                STA
                break;
            case (4):
            	//<BUN>          	
            	//T4

            	if (CPU.SC == 4) {
                	PC = AR;
                	
            		System.out.printf("D4T%d : PC <- AR, SC <- 0%n", CPU.SC);
            		print();
            	}
            	else {
            		System.out.println("SC 값 에러");
            		break;
            	}

                break;
            case (5):
            	//<BSA>
            	//T4
            	if (CPU.SC == 4) {
                	MEMORY[AR] = PC;
                	AR ++;
                	System.out.printf("D5T%d : M[AR] <- PC, AR <- AR + 1%n", CPU.SC);
                	print();
                	CPU.SC++;
            	}
            	else {
            		System.out.println("SC 값 에러");
            		break;
            	}

            	
            	//T5
        		if (CPU.SC == 5) {
        			PC = AR;
                	System.out.printf("D5T%d : PC<-AR, SC <- 0%n", CPU.SC);
                	print();
        		}
            	else {
            		System.out.println("SC 값 에러");
            		break;
            	}
                break;
            case (6):
            	//<ISZ>
            	//T4
            	if (CPU.SC == 4) {
            		DR = MEMORY[AR];
                	System.out.printf("D6T%d : DR <- M[AR]%n", CPU.SC);
                	print();
                	CPU.SC++;
            	}
            	else {
            		System.out.println("SC 값 에러");
            		break;
            	}
            	
            	//T5
            	if (CPU.SC == 5) {
                	DR++;
                	System.out.printf("D6T%d : DR <- DR + 1%n", CPU.SC);
                	print();
                	CPU.SC++;
            	}
            	else {
            		System.out.println("SC 값 에러");
            		break;
            	}
            	
            	//T6
            	if (CPU.SC == 6) {
                	MEMORY[AR] = DR;
                	if (DR == 0) PC++;
                	System.out.printf("D6T%d : M[AR] <- DR, if (DR = 0) then (PC <- PC + 1), SC<-0%n", CPU.SC);
                	print();
            	}
            	else {
            		System.out.println("SC 값 에러");
            		break;
            	}
                break;
        }
        return -1;
    }
    public static int execute_Register_Command(short command, int SC){
//  Register-Reference
        switch (command) {
            case 0x7800:
//                CLA
                break;
            case 0x7400:
//                CLE
                break;
            case 0x7200:
//                CMA
                break;
            case 0x7100:
//                CME
                break;
            case 0x7080:
//                CIR
                break;
            case 0x7040:
//                CIL
                break;
            case 0x7020:
//                INC
                break;
            case 0x7010:
//                SPA
                break;
            case 0x7008:
//                SNA
                break;
            case 0x7004:
//                SZA
                break;
            case 0x7002:
//                SZE
                break;
            case 0x7001:
//                HLT
                return -1;
        }
        }
    // 순차 카운터의 값을 하나 증가 시키는 과정
    public static void incre_SC(){
        SC = (byte) (SC + 1);
    }
	public static void print() {
		System.out.println("-------------REG------------");
		System.out.println("DR "+DR);
		System.out.println("AR "+AR);
		System.out.println("AC "+AC);
		System.out.println("IR "+IR);
		System.out.println("PC "+PC);
		System.out.println("TR "+TR);
		System.out.println("INPR "+INPR);
		System.out.println("OUTR "+OUTR);
		System.out.println("SC "+SC);
		System.out.println("I "+I);
		System.out.println("R "+R);
		System.out.println("IEN "+IEN);
		System.out.println("-------------REG-----------");
	}
}
