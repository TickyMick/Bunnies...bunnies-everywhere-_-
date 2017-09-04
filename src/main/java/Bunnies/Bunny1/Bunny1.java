package main.java.Bunnies.Bunny1;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Alina on 1/8/2017.
 */

    public class Bunny1 {

        public static void main(String[] args) {

            int[] example1x = {13, 5, 6, 2, 5};
            int[] example1y = {5, 2, 5, 13};
            int[] example2x = { 14, 27, 1, 4, 2, 50, 3, 1 };
            int[] example2y = { 2, 4, -4, 3, 1, 1, 14, 27, 50 };

            int additionalExample1 = answer(example1x, example1y);
            int additionalExample2 = answer(example2x, example2y);

            System.out.println("Additional id " + additionalExample1);
            System.out.println("Additional id " + additionalExample2);
        }

        public static int answer(int[] x, int[] y) {
            List<String> yList = new ArrayList<>();

            if (x.length > y.length) {

                for (int id : y) {
                    yList.add(id + "");
                }

                for (int i = 0; i < x.length; i++) {
                    if (yList.contains(x[i] + "")) {
                        yList.remove(x[i] + "");
                    } else {
                        return x[i];
                    }
                }
            } else {
                for (int id : x) {
                    yList.add(id + "");
                }

                for (int i = 0; i < y.length; i++) {
                    if (yList.contains(y[i] + "")) {
                        yList.remove(y[i] + "");
                    } else {
                        return y[i];
                    }
                }
            }

             return 0;
        }

    }

