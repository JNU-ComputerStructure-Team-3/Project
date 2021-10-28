
//최상위 클래스
//단 한개의 프로세서 레지스터를 갖는 컴퓨터
public abstract class CPU {
    protected static short[] MEMORY; // 메모리
    protected static short DR; // 데이터 - 메모리에서 읽어온 피연산자를 저장 (16)
    protected static short AR; // 메모리 주소 - 메모리의 주소를 나타냄 (4096범위이므로 12비트로 제한)
    protected static short AC; // 누산기 - 범용 처리 레지스터 (16)
    protected static short IR; // 명령어 - 메모리에서 읽어온 명령어 저장 (16)
    protected static short PC; // 프로그램 카운터 - 메모리의 주소를 나타냄 (12)
    protected static short TR; // 임시 - 계산 도중의 임시 데이터 (16)
    protected static byte INPR; // 입력 - 입출력 장치로부터 8비트 문자 정보 송수신 (8)
    protected static byte OUTR; // 출력 - 입출력. (8)
    protected static byte SC; // 순차 카운터
    protected static byte I; // 간접주소 enable code
    protected static byte R; // interrupt code
    protected static byte IEN; // enable code

    public static void CPU() {
        // 메모리 생성
        MEMORY = new short[4096];
        // 레지스터 생성
        DR = 0;
        AR = 0;
        TR = 0;
        AC = 0;
    }
}
