package main.java.Bunnies.Bunny4;

import java.math.BigInteger;

/**
 * Created by Alina on 9/4/2017.
 */
public class Bunny4 {

    public static void main(String [] args) {

        System.out.println(answer("4"));
        System.out.println(answer("15"));
    }

    public static int answer(String n) {
        BigInteger nr = new BigInteger(n);
        int count=0;
        while (nr.compareTo(BigInteger.ONE) == 1) {

            //n%2 == 0
            if (nr.remainder(BigInteger.valueOf(2)) == BigInteger.valueOf(0)) {
                nr = nr.divide(BigInteger.valueOf(2));
                count++;
            }
            //((n-1)/2)%2 == 0
            else if((((nr.add(BigInteger.ONE.negate())).divide(BigInteger.valueOf(2))).remainder(BigInteger.valueOf(2)) == BigInteger.valueOf(0))
                    || (nr.equals(BigInteger.valueOf(3)))) {
                nr = nr.add(BigInteger.ONE.negate());
                count++;
            }
            ///n+1
            else {
                nr = nr.add(BigInteger.ONE);
                count++;
            }
        }

        return count;
    }

}
