import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInput {

	public static void main(String[] args) throws IOException  {  
	
		 short[] MEMORY = new short[4096];
		 input_File(0,MEMORY);
	     
	}
	
	
	public static void input_File(int CTR, short[] M) throws IOException{
		 BufferedReader br = new BufferedReader(new FileReader("c:\\test\\input.txt"));
		 byte ch1, ch2;
		 String result,line;
		 
		 while(true) {
			 line = br.readLine();
			 if(line==null) break;
			 
	         int i=0;
	         while(true) {
	        	 //���� �� üũ
	        	 if(i==line.length()) {
	        		 ch1 = 13; //CR
	        		 ch2 = 32; //SP
	        		 result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
	        		 System.out.println(result);
	        		//M[CTR] <- result ??
	        		 break;
	        	 }
	        	 //������ üũ
	        	 else if(line.charAt(i)=='/'){
	        		 ch1 = 13; 
	        		 ch2 = 32;
	        		 result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
	        		 System.out.println(result);
	        		 //M[CTR] <- result ??
	        		 break;
	        	 }
	        	 //ch1�� i��° ������ �ƽ�Ű�ڵ� �� �Է�	
	        	 ch1 = (byte) line.charAt(i++);
	        	 //���� �� üũ
	        	 if(i==line.length()) {
	        		 ch2 = 13;
	        		 result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
	        		 System.out.println(result);
	        		 //M[CTR] <- result ??
	        		 break;
	        	 }
	        	 //������ üũ
	        	 else if(line.charAt(i)=='/'){
	        		 ch2 = 13;
	        		 result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
	        		 System.out.println(result);
	        		 //M[CTR] <- result ??
	        		 break;
	        	 }
	        	 //ch2�� i��° ������ �ƽ�Ű�ڵ� �� �Է�
	        	 ch2 = (byte) line.charAt(i++);
	        	 //ch1,ch2 �� ���� �������� ��ȯ �� �̾����
	        	 result = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch1)))+String.format("%08d", Integer.parseInt(Integer.toBinaryString(ch2)));
	        	 System.out.println(result);        		
		         //M[CTR] <- result ??
	         }
		 }
		 
	}

		
}
