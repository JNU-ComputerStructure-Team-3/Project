public abstract class outputScreenConsole { 
	public void output(String output) {
		System.out.println("|    "+output+"    |");  //칸 수 보고 띄어쓰기 조정
	}
	public void outputScreen() {
		System.out.println("----------------------------------------");
		output();  //여기에 출력문을 적어주세요
		output();  //여기에 출력문을 적어주세요
		output();  //여기에 출력문을 적어주세요
		output();  //여기에 출력문을 적어주세요
		output();  //여기에 출력문을 적어주세요
		output();  //여기에 출력문을 적어주세요
		System.out.println("----------------------------------------");
	}
	
	public void outputLetter(String output) {
		System.out.print(output);
	}
	//만약 단어 한개씩 출력이 된다면
	public void outputScreenLetter(int Tn) {
		//Tn는 T0~Tn까지 일 떄 n
		System.out.println("----------------------------------------");
		for(int i = 0;i<Tn;i++) {
			System.out.print("|   T"+i+" : ");     //띄어쓰기 보정 가능
			outputLetter();       //()안에 출력대상(리턴값은 String형)	
			System.out.print("   |");     //띄어쓰기 보정 가능
			System.out.println();
		}
		
		System.out.println();
		System.out.println("결과 값 : ");
		
		outputLetter();       //()안에 출력대상(리턴값은 String형)	
		System.out.println();
		outputLetter();       //()안에 출력대상(리턴값은 String형)	
		System.out.println();
		outputLetter();       //()안에 출력대상(리턴값은 String형)	
		System.out.println();
		
		System.out.println("----------------------------------------");
	}
