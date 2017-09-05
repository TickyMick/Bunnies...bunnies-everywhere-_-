package main.java.Bunnies.Bunny3;

/**
 * Created by Alina on 9/4/2017.
 */
public class Bunny3 {

    public static void main(String[] args){
        System.out.println(answer(">----<"));
        System.out.println(answer("<<>><"));
        System.out.println(answer("--->-><-><-->-"));
    }

    public static int answer(String s) {
        String[] substr = s.split("<");
        int count = 0,countx = 0, n;
        n = substr.length;

        for (int i=0;i<n-1;i++) {
            countx += substr[i].length() - substr[i].replace(">", "").length();
            count += 2 * countx;
        }
        if(s.substring(s.length()-1).equals("<")) {
            countx += substr[n - 1].length() - substr[n - 1].replace(">", "").length();
            count += 2 * countx;
        }

        return count;
    }
}
