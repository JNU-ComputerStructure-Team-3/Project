	public static void execute_Register_Command(short command, int SC){
	//  Register-Reference
	    switch (command) {
	        case 0x7800:
//	                CLA
	            System.out.println("rB11 : AC <- 0, SC <- 0");
	            AC = 0;
	            SC = 0;
	            break;
	        case 0x7400:
//	                CLE
	            System.out.println("rB10 : E <- 0, SC <- 0");
	            E = 0;
	            SC = 0;
	            break;
	        case 0x7200:
//	                CMA
	            System.out.println("rB9 : AC <- AC', SC <-0");
	            AC = (short)~AC;
	            SC = 0;
	            break;
	        case 0x7100:
//	                CME
	            System.out.println("rB8 : E <- E', SC <- 0");
	            E = (short)~E;
	            SC = 0;
	            break;
	        case 0x7080:
//	                CIR
	            System.out.println("rB7 : AC <- shr AC, AC(15) <- E, E <- AC(0), SC <- 0");
	             AC = (short) Integer.rotateRight(AC, 1);
	             SC = 0;
	            break;
	        case 0x7040:
//	                CIL
	            System.out.println("rB6 : AC <- shl AC, AC(0) <- E, E <- AC(15), SC <- 0");
	            AC = (short) Integer.rotateLeft(AC, 1);
	            SC = 0;
	            break;
	            
	            
	            
	        case 0x7020:
//	                INC
	        	System.out.println("rB5 : AC <- AC + 1");
	        	AC = AC + 1;
	        	SC = 0;
	            break;
	        case 0x7010:
//	                SPA
	        	System.out.println("rB4 : If (AC(15) = 0) then (PC <- PC + 1)");
	        	if(AC(15) = 0) {
	        		PC = PC + 1;
	        	}
	        	SC = 0;
	            break;
	        case 0x7008:
//	                SNA
	        	System.out.println("rB3 : If (AC(15) = 1) then (PC <- PC + 1)");
	        	if(AC(15) = 1) {
	        		PC = PC + 1;
	        	}
	        	SC = 0;
	            break;
	        case 0x7004:
//	                SZA
	        	System.out.println("rB2 : If (AC = 0) then (PC <- PC + 1)");
	        	if (AC = 0) {
	        		PC = PC + 1;
	        	}
	        	SC = 0;
	            break;
	        case 0x7002:
//	                SZE
	        	System.out.println("rB1 : If (E = 0) then (PC <- PC + 1)");
	        	if (E = 0) {
	        		PC = PC + 1;
	        	}
	        	SC = 0;
	            break;
	        case 0x7001:
//	                HLT
	        	System.out.println("rB0 : S <- 0");
	            return -1;                              
	    }
	    }
}
