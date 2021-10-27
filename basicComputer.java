
public class basicComputer extends CPU {

    public static void main(String[] args) {

        // basicComputer 클래스를 생성한다.
        basicComputer bsc = new basicComputer();

        short origin_Address = 0; // 첫 프로그램이 시작하는 번지를 저장하는 변수
        bsc.PC = origin_Address; // PC에 값 저장. 여기 까지가 ORG 0

        /* 기본 프로그램 시작 */
        while () {
            /* start SC <- 0 , IEN <- 1 , R <- 0 */
            SC = 0;
            R = 0;
            IEN = 1;
            instructionCycle();
        }

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
        System.out.printf("T%d : AR <- PC", SC);
        AR = PC;
        incre_SC();
        // T1 : fetch 과정
        System.out.printf("IR <- M[AR]");
        System.out.printf("PC <- PC + 1");
        IR = MEMORY[AR];
        PC = (byte) ((int) PC + 1);
        incre_SC();
        // T2 : decode 과정
        // AR <- IR 과정이 들어가야함
        // I <- IR 과정 들어가야함
        // IR 을 Decode하는 함수 넣기 -> d_Seven_flag 의 값을 설정하는 과정이 있어야한다.

        // T3 : 레지스터 명령 or I/O 명령
        if (d_Seven_flag == true) {
            /* I = 1 이면 */
        }
        // T3 : 메모리 참조 명령
        else {

        }

    }

    // 순차 카운터의 값을 하나 증가 시키는 과정
    public static void incre_SC() {
        SC = (byte) ((int) SC + 1);
    }

    @Override
    public void inputData(int position, short data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void decodeInstruction(short instruction) {
    }

    @Override
    public void decodeOpcode() {
        // TODO Auto-generated method stub
    }

    @Override
    public void readInstruction() {
        // TODO Auto-generated method stub
    }

}
