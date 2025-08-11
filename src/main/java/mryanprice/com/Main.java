package mryanprice.com;

import java.math.BigInteger;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            sort(0,1,1,1);
        } catch (RuntimeException e) {
            System.out.println("0 dimension throws as expected");
        }
        try {
            sort(-1,1,1,1);
        } catch (RuntimeException e) {
            System.out.println("negative dimension throws as expected");
        }
        System.out.println("(1,1,1,1) size package should be STANDARD: " + sort(1,1,1,1));
        System.out.println("(150,1,1,1) size package should be SPECIAL: " + sort(150,1,1,1));
        System.out.println("(10,10,10,20) size package should be SPECIAL: " + sort(10,10,10,20));
        System.out.println("(100,100,100,1) size package should be SPECIAL: " + sort(100,100,100,1));
        System.out.println("(150,1,1,20) size package should be REJECTED: " + sort(150,1,1,20));
        System.out.println("(MAXINT,MAXINT,MAXINT,MAXINT) size package should be REJECTED: " + sort(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE));
    }

    public static final String STANDARD = "STANDARD";
    public static final String SPECIAL = "SPECIAL";
    public static final String REJECTED = "REJECTED";

    public static String sort(int width, int height, int length, int mass) {
        // require positive int arguments because that's the way other systems do it (e.g. USPS postage calculator)
        if (width <= 0 || height <= 0 || length <= 0 || mass <= 0) {
            throw new RuntimeException("dimensions must be positive, round up");
        }
        boolean bulky;
        if (width >= 150 || height >= 150 || length >= 150) {
            bulky = true;
        } else {
            // only do the volume calculation if necessary
            // use BigInt for calculations so we dont have to worry about overflow
            final BigInteger MAX_VOLUME = BigInteger.valueOf(1000000);
            BigInteger volume = BigInteger.valueOf(width).multiply(BigInteger.valueOf(height)).multiply(BigInteger.valueOf(length));
            bulky = volume.compareTo(MAX_VOLUME) > -1;
        }
        boolean heavy = mass >= 20;
        if (bulky && heavy) {
            return REJECTED;
        }
        if (bulky || heavy) {
            return SPECIAL;
        }
        return STANDARD;
    }
}