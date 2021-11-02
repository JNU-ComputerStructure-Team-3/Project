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
        // T1 : fetch ����
        System.out.printf("T%d : IR <- M[AR], PC <- PC + 1 %n", SC);

        //I �� ������ +8


        IR = MEMORY[AR];
        System.out.println(IR);
        PC = (byte) (PC + 1);
        incre_SC();
        // T2 : decode ����



        // AR <- IR ������ ������
        AR = IR;
        // IR �� Decode�ϴ� �Լ� �ֱ� -> d_Seven_flag �� ���� �����ϴ� ������ �־���Ѵ�.
        System.out.println("Decode");
        System.out.printf("T%d : AR <- IR(0-11), decode IR(12-14), I <- IR(15)%n", SC);
        I = true; // test code ��.
        incre_SC();

        boolean B_Flag = (!d_Seven_flag && I);
        // T3: ��ɾ� ������ ���� -> å 111p
        // ���Ƿ� flag �� ���� T4���� ��ɾ��� ������ �Ǻ��� �� �ְ� �˷� �־�� �Ѵ�.
        if (!d_Seven_flag && I == true) {
            AR = MEMORY[AR];
            System.out.printf("T%d : AR <-[AR]%n", SC);
            incre_SC();/* MRI ��ɾ��� SC==4�� �ʿ��ϱ� ������ �߰� [����]*/
        } else if (d_Seven_flag && I == true) {
            System.out.printf("T%d : I/O command%n", SC);

        } else if (d_Seven_flag && I == false) {
            System.out.printf("T%d : Register Reference Instruction%n", SC);

        } else {
            System.out.printf("T%d : nothing %n", SC);

        }
        
        // T4 ��ɾ� �ܰ� ����

        if ((!d_Seven_flag && I == true) | (!d_Seven_flag && I == false)) {
            if (execute_Memory_Command(command, SC) == -1) {
//                HLT
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
/* TEST�� ���ؼ� ��� void���� �޼ҵ带 int�������� ��ȯ��. [����]*/
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
            	PC = AR;
        		System.out.printf("D4T%d : PC <- AR, SC <- 0%n", SC);
        		print();
            	SC = 0;
                break;
            case (5):
            	//<BSA>
            	//T4
            	MEMORY[AR] = PC;
            	AR ++;
            	System.out.printf("D5T%d : M[AR] <- PC, AR <- AR + 1%n", SC);
            	print();
            	SC++;
            	
            	//T5
            	PC = AR;
            	System.out.printf("D5T%d : PC<-AR, SC <- 0%n", SC);
            	print();
            	SC = 0;
                break;
            case (6):
            	//<ISZ>
            	//T4
            	DR = MEMORY[AR];
            	System.out.printf("D6T%d : DR <- M[AR]%n", SC);
            	print();
            	SC++;
            	//T5
            	DR++;
            	System.out.printf("D6T%d : DR <- DR + 1%n", SC);
            	print();
            	SC++;
            	//T6
            	MEMORY[AR] = DR;
            	if (DR == 0) PC++;
            	System.out.printf("D6T%d : M[AR] <- DR, if (DR = 0) then (PC <- PC + 1), SC<-0%n", SC);
            	print();
            	SC = 0;
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
    // ���� ī������ ���� �ϳ� ���� ��Ű�� ����
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
