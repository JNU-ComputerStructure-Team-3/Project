package com.company;

public class test {
    public static Short hexToBin(String hex) {
        int bit4 = hex.length()-1;
        short tempHex;
        short temp = 1;
        short bin = 0;

        while (bit4>=0) { // Hex -> 4bit
            tempHex = (short)hex.charAt(bit4); // char -> int

            // 1. ASCII -> Hex
            if (tempHex <58 && tempHex>47) tempHex -= 48;
            else if (tempHex > 64 && tempHex < 71) tempHex -= 55;
            else break;

            bin += temp*tempHex;

            temp*=16;

            bit4--;
        }
        return bin;
    }
    public static int rotateLeft(short i) {
        return (i << 1) | (i >>> -1);
    }
    public static int rotateRight(short i, short E) {
        return (i >>> 1) | (i << -1);
    }
    public static void main(String[] args){
        short AC = 0x706a;
//        AC = (short) (AC/0x1000);
        AC = (short) (AC%2);
        System.out.println(AC);
        System.out.println("----------------------");
//        AC = (short) Integer.rotateLeft(AC, 1);
//        System.out.println(AC);
        AC = (short)rotateLeft(AC);
        System.out.println(AC);
//        Short bin_data = hexToBin(AC);
//        System.out.println(AC);
    }
}
