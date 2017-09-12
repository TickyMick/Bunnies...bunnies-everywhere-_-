package main.java.Bunnies.Bunny8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alina on 9/12/2017.
 */
public class Bunny8 {


    public static void main(String [] args){

        System.out.println(Arrays.deepToString(answer(2,1)));
        System.out.println(Arrays.deepToString(answer(5,3)));
        System.out.println(Arrays.deepToString(answer(4,4)));
    }


    public static int[][] answer(int num_buns, int num_required) {

        ArrayList<MyArrList> r = new ArrayList<>();

        ArrayList<MyArrList> func;
        func = combine(num_buns,num_required);
        int total = func.size()*num_required;
        int repeatTimes = num_buns-num_required+1;
        ArrayList<MyArrList> func1;
        func1 = combine(num_buns,repeatTimes);

        int x = total/repeatTimes;
        for(int z = 0; z<num_buns; z++){
            r.add(new MyArrList());
        }

        for(int i = 0; i<x; i++){
            for (int j=0;j<func1.get(i).getArrayList().size();j++){
                int n = func1.get(i).getArrayList().get(j);
                r.get(n).getArrayList().add(i);
            }
        }
        int[][] result = new int[r.size()][];
        for(int a=0; a<r.size();a++){
            int[] arr= convert(r.get(a).getArrayList());
            result[a]= arr;
        }

        return result;

    }

    public static int[] convert(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    public static ArrayList<MyArrList> combine(int n, int k) {

        ArrayList<MyArrList> result = new ArrayList<>();

        if (n <= 0 || n < k)
            return result;

        MyArrList item = new MyArrList();
        dfs(n, k, 0, item,  result);

        return result;
    }

    public static void dfs(int n, int k, int start, MyArrList item,  ArrayList<MyArrList> res) {

        if (item.getArrayList().size() == k) {
            res.add(new MyArrList(item));
            return;
        }

        for (int i = start; i < n; i++) {
            item.getArrayList().add(i);
            dfs(n, k, i + 1,item, res);
            item.getArrayList().remove(item.getArrayList().size() - 1);
        }
    }
}

class MyArrList {
    private ArrayList<Integer> arrayList;

    public MyArrList(MyArrList myArrList){
        this.arrayList = new ArrayList<>();
        for(Integer i: myArrList.getArrayList()){
            this.arrayList.add(i);
        }
    }


    public MyArrList(){
        this.arrayList = new ArrayList<>();
    }

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }
}
