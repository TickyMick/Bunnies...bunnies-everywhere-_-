package main.java.Bunnies.Bunny9;

import static java.lang.Math.pow;

/**
 * Created by Alina on 9/12/2017.
 */
public class Bunny9 {


    public static void main(String[] args){

        boolean[][] g1 = {
                {true, false, true},
                {false, true,false},
                {true, false, true}
        };
        boolean[][] g2 = {
                {true, false, true, false, false, true, true, true},
                {true, false, true, false, false, false, true, false},
                {true, true, true, false, false, false, true, false},
                {true, false, true, false, false, false, true, false},
                {true, false, true, false, false, true, true, true}
        };
        boolean[][] g3 = {
                {true, true, false, true, false, true, false, true, true, false},
                {true, true, false, false, false, false, true, true, true, false},
                {true, true, false, false, false, false, false, false, false, true},
                {false, true, false, false, false, false, true, true, false, false}
        };

        System.out.println(answer(g1));
        System.out.println(answer(g2));
        System.out.println(answer(g3));

    }

    public static int answer(boolean[][] g){
        byte[][] byteG = new byte[g.length][g[0].length];
        for (int i=0;i <g.length; i++){
            for (int j=0; j<g[0].length; j++){
                if(g[i][j]) byteG[i][j] = 1;
                else byteG[i][j] = 0;
            }
        }

        return doNebula(byteG);
    }

    public static int doNebula(byte[][] g){
        int i,j,k,l,m,n,l1,l2,s;
        boolean b;
        long[] cn, cn2;
        int[] cn1;
        byte[] cl;
        byte[][] a;

        n = g[0].length-1;
        m = g.length+1;
        a = new byte[(int) pow(2,m)][m];
        m--;

        for (i = 0; i<=m; i++){
            l = (int) pow(2, m-i);
            l2 = 0;
            for(j = 1; j<=(int) pow(2,i);j++){
                l1 = l2;
                l2 = l1+l;
                for (k = l1; k<=l2-1; k++){
                    a[k][i] = 0;
                }
                l1 = l2;
                l2 = l1+l;
                for(k = l1; k<=l2-1;k++){
                    a[k][i] = 1;
                }
            }
        }

        cl = new byte[a.length];
        for(byte clEl: cl){
            clEl  = 0;
        }
        cn = new long[a.length];
        m--;

        for (j = 0;j<a.length; j++){
            for (k = 0; k<=m; k++){
                l= a[j][k] + a[j][k+1];
                if((g[m-k][0] == 1)&&(l>1)) {
                    cl[j] = 1;
                    break;
                }
            }
            if(cl[j] == 0) cn[j] = 1;
        }

        for (i =0; i<=n; i++) {
            s = 0;
            cn1 = new int[a.length];
            for (int cn1El : cn1) {
                cn1El = 0;
            }
            cn2 = new long[a.length];
            for (long cn2El : cn2) {
                cn2El = 0;
            }

            for (j = 0; j < a.length; j++) {
                if (cl[j] == 0) {
                    s++;
                    for (k = 0; k < a.length; k++) {
                        b = true;
                        l2 = a[j][0] + a[k][0];
                        for (l = 0; l <= m; l++) {
                            l1 = l2;
                            l2 = a[j][l + 1] + a[k][l + 1];
                            if (((g[m - l][i] == 1) && (l1 + l2 != 1)) || ((l1 + l2 == 1) && (g[m - l][i] != 1))) {
                                b = false;
                                cn1[k]++;
                                break;
                            }
                        }
                        if (b) cn2[k] = cn2[k] + cn[j];
                    }
                }
            }

            for (j = 0; j < a.length; j++) {
                if (cn1[j] == s)
                    cl[j] = 1;
                else {
                    cl[j] = 0;
                    cn[j] = cn2[j];
                }
            }

        }

        int result = 0;

        for (i = 0; i<cl.length; i++){
            if(cl[i]==0) result = result + (int) cn[i];
        }

        return result;
    }
}



