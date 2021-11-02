
public class basicComputer extends CPU {

    public static void main(String[] args) {

        basicComputer bsc = new basicComputer(); // basicComputer 클래스를 생성한다.
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
        instructionCycle();

    }

    public basicComputer() {
        super.CPU();
    }

    public static void instructionCycle() {

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

        IR = MEMORY[AR];
        PC = (byte) (PC + 1);
        incre_SC();
        // T2 : decode 과정
        // AR <- IR 과정이 들어가야함
        // IR 을 Decode하는 함수 넣기 -> d_Seven_flag 의 값을 설정하는 과정이 있어야한다.
        System.out.println("Decode");
        System.out.printf("T%d : AR <- IR(0-11), decode IR(12-14), I <- IR(15)%n", SC);
        I = 1; // test code 임.
        incre_SC();

        // T3: 명령어 종류의 결정 -> 책 111p
        // 임의로 flag 를 만들어서 T4에서 명령어의 종류를 판별할 수 있게 알려 주어야 한다.
        if (!d_Seven_flag && I == 1) {
            AR = MEMORY[AR];
            System.out.printf("T%d : AR <-[AR]%n", SC);
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