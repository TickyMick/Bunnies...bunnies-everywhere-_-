package main.java.Bunnies.Bunny7;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Alina on 9/12/2017.
 */
public class Bunny7 {

    public static void main(String[] args){

        int [] banana_list1 = {1,1};
        int [] banana_list2 = {1, 7, 3, 21, 13, 19};
        int [] a = {1,1,1,1,4,4,4,4,3,3};
        int [] b = {1,5};
        int [] c = {11,11,11,6,6,14,14,42,42,42,11,11,11,11};
        System.out.println(answer(banana_list1));
        System.out.println(answer(banana_list2));
        System.out.println(answer(a));
        System.out.println(answer(b));
        System.out.println(answer(c));
    }

    public static int answer(int[] banana_list) {

        HashMap<Integer, Node> graph = createGraph(banana_list);
        populateGraph(graph);
        int result = 0;
        int[] matchR = new int[banana_list.length];
        for(int j=0; j<matchR.length; j++){
            matchR[j] = -1;
        }

        for(int i = 0;i<banana_list.length; i++ ){
            boolean[] visited = new boolean[banana_list.length];
            for(int i1=0; i1<visited.length;i1++) visited[i1] = false;
            if (Bunny7.match(i, matchR, visited, graph)) result+=1;
        }

//        for(Node node: graph.values()) {
//            if (node.getAdjacencyList().size() != 0) result+=1;
//        }
        System.out.println("***");
        return  banana_list.length-2*(result/2);
    }

    public static boolean match(int u, int[] matchR, boolean[] visited, HashMap<Integer, Node> graph){
        for(int v = 0; v<matchR.length; v++){
            if((graph.get(u).getAdjacencyList().contains(v))&& (visited[v] == false)) {
                visited[v] = true;

                if ((matchR[v] == -1) || (match(matchR[v], matchR, visited, graph))) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static int gcd(int a, int b)
    {
        if (b==0) return a;
        else
            return gcd(b,a%b);
    }

    public static boolean isPowerOfTwo(int x){
        return (x & (x - 1)) == 0;

    }
    public static boolean hasExit(int a,int b){

        return isPowerOfTwo((a+b)/gcd(a,b));
    }

    public static HashMap<Integer,Node> createGraph(int [] banana_list){
        HashMap<Integer, Node> graph = new HashMap<Integer, Node>();
        for(int i = 0;i<banana_list.length;i++){
            Node node = new Node(i, banana_list[i]);
            graph.put(node.label, node);
        }

        return graph;
    }

    public static void populateGraph(HashMap<Integer, Node> graph){
        for(Node node: graph.values()){
            for(Node node1: graph.values()){
                if(!hasExit(node.value,node1.value))  {
                    node.addAdjNode(node1);
                }
            }
        }
    }
}

class  Node{

    Integer label;
    Integer value;
    ArrayList<Integer> adjacencyList;

    public Node(Integer label) {
        this.label = label;
        this.value = 0;
        this.adjacencyList = new ArrayList<>();
    }

    public Node(Integer label, Integer value) {
        this.label = label;
        this.value = value;
        this.adjacencyList = new ArrayList<>();
    }

    public Integer getLabel() {
        return label;
    }

    public ArrayList<Integer> getAdjacencyList() {
        return adjacencyList;
    }

    public Integer getValue() {
        return value;
    }

    public void addAdjNode(Node node){
        this.adjacencyList.add(node.getLabel());
    }

    @Override
    public String toString() {
        return "Node{" +
                "label=" + label +
                ", value=" + value +
                ", adjacencyList=" + adjacencyList +
                '}';
    }
}