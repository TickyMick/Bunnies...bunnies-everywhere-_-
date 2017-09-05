package main.java.Bunnies.Bunny5;

import java.math.BigInteger;

/**
 * Created by Alina on 9/4/2017.
 */
public class Bunny5 {

    public static void main(String [] args){

        System.out.println(answer("2","1"));
        System.out.println(answer("4","7"));
        System.out.println(answer("4","2"));
    }

    public static String answer(String M, String F) {

        BigInteger n = BigInteger.ZERO;
        BigInteger m = new BigInteger(M);
        BigInteger f = new BigInteger(F);

        while(!(m.equals(BigInteger.ONE)&&(f.equals(BigInteger.ONE)))) {
            if ((m.equals(BigInteger.ZERO)) || (f.equals(BigInteger.ZERO)))
                return "impossible";
            else if (m.equals(BigInteger.ONE)) {
                n = n.add(f).subtract(BigInteger.ONE);
                return "" + n;
            } else if (f.equals(BigInteger.ONE)) {
                n = n.add(m).subtract(BigInteger.ONE);
                return "" + n;
            } else if (m.compareTo(f) == 1) {
                n = n.add(m.divide(f));
                m = m.remainder(f);
            } else {
                n = n.add(f.divide(m));
                f = f.remainder(m);
            }
        }
        return ""+n;
    }
}
