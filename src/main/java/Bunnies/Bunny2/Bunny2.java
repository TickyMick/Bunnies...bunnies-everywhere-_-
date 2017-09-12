package main.java.Bunnies.Bunny2;

import java.util.*;

/**
 * Created by Alina on 9/4/2017.
 */
public class Bunny2 {

    public static final int SIZE_OF_CHESS_BOARD = 8;
    private static final BfsAlgorithm bfs = new BfsAlgorithm();
    private static final ChessBoard chessBoard = new ChessBoard(SIZE_OF_CHESS_BOARD, 1, 2);

    public  static void main(String[] args){

        System.out.println(answer(15,15));
        System.out.println(answer(19,36));
        System.out.println(answer(0,1));
    }
    public static int answer(int src, int dest) {
        ArrayList<Vertex> list = createChessboardGraph();
        Vertex fromVertex = new Vertex();
        Vertex toVertex = new Vertex();
        if(src == dest) return 0;
        for(Vertex v :list){
            if(v.getName() == src) fromVertex = v;
            else if(v.getName() == dest) toVertex = v;
        }
        return  solveProblem(fromVertex,toVertex);
    }

    private static int solveProblem(Vertex from, Vertex to){

        final Vertex destination = to;
        final List<Vertex> path = new ArrayList<>();
        final HashMap<Vertex, Vertex> parentToChild = new HashMap<>();
        bfs.run(from, new BfsAlgorithm.BfsAction() {
            private Vertex currParent;

            @Override
            public boolean onVisitParent(Vertex parent) {
                currParent = parent;
                return false;
            }

            @Override
            public boolean onVisitChild(Vertex vertex) {
                parentToChild.put(vertex, currParent);
                if(vertex.equals(destination)) return true;
                else return false;
            }
        });
        path.add(destination);
        Vertex par = parentToChild.get(destination);
        while(par != null){
            path.add(par);
            par = parentToChild.get(par);
        }
        System.out.println(path.toString());
        return path.size()-1;
    }

    private static void createAdjVertexIfInBounds(Vertex v, int x,int y, ArrayList<Vertex> vertexes){
        if(chessBoard.isPositionOnChessBoard(x,y)){
            final int indexOf = vertexes.indexOf(new Vertex(x,y));
            final Vertex reusedVertex = vertexes.get(indexOf);
            v.addAdjVertex(reusedVertex);
        }
    }

    private static void createAdjListForVertex(Vertex v, ArrayList<Vertex> vertexes){

        int xRightFull = v.getX() + chessBoard.getFarthestMove();
        int xRightHalf = v.getX() + chessBoard.getNearestMove();
        int xLeftFull = v.getX() + -chessBoard.getFarthestMove();
        int xLeftHalf = v.getX() + -chessBoard.getNearestMove();

        int yUpFull = v.getY() + chessBoard.getFarthestMove();
        int yUpHalf = v.getY() + chessBoard.getNearestMove();
        int yDownFull = v.getY() + -chessBoard.getFarthestMove();
        int yDownHalf = v.getY() + -chessBoard.getNearestMove();

        createAdjVertexIfInBounds(v, xRightFull, yUpHalf, vertexes);
        createAdjVertexIfInBounds(v, xRightHalf, yUpFull, vertexes);
        createAdjVertexIfInBounds(v, xLeftHalf, yUpFull, vertexes);
        createAdjVertexIfInBounds(v, xLeftFull, yUpHalf, vertexes);
        createAdjVertexIfInBounds(v, xLeftFull, yDownHalf, vertexes);
        createAdjVertexIfInBounds(v, xLeftHalf, yDownFull, vertexes);
        createAdjVertexIfInBounds(v, xRightHalf, yDownFull, vertexes);
        createAdjVertexIfInBounds(v, xRightFull, yDownHalf, vertexes);
    }

    private static ArrayList<Vertex> createChessboardGraph(){

        final ArrayList<Vertex> vertexes = new ArrayList<>();
        for(int i = 0; i< chessBoard.getChessBordSize(); i++){
            for(int j = 0; j< chessBoard.getChessBordSize(); j++){
                vertexes.add(new Vertex(i,j));
            }
        }

        for (Vertex v: vertexes) {
            if(v.getmAdjVertices().isEmpty())
                createAdjListForVertex(v,vertexes);
        }

        return vertexes;
    }

}

class BfsAlgorithm {

    public void run(Vertex vert, BfsAction action){
        List<Vertex> visitedList = new ArrayList<>();
        Queue q = new LinkedList();
        q.add(vert);
        vert.setVisited(true);
        visitedList.add(vert);

        while(!q.isEmpty()){
            Vertex n = (Vertex) q.poll();

            if(action.onVisitParent(n))
                break;

            for(Vertex adj: n.getmAdjVertices()){

                if(!adj.isVisited()){
                    adj.setVisited(true);
                    visitedList.add(adj);
                    q.add(adj);

                    if(action.onVisitChild(adj)){
                        q = new LinkedList();
                        break;
                    }
                }
            }
        }

        for (Vertex v: visitedList) {
            v.setVisited(false);
        }
    }


    public interface BfsAction {

        boolean onVisitParent(Vertex vertex);

        boolean onVisitChild(Vertex vertex);
    }
}

class ChessBoard {

    private  final  int mSizeOfBoardNxN;
    private final int mNearestMove;
    private final  int mFarthestMove;

    public ChessBoard(int sizeOfBoardNxN, int nearestMove,int farthestMove){

        mSizeOfBoardNxN = sizeOfBoardNxN;
        mNearestMove = nearestMove;
        mFarthestMove = farthestMove;
    }

    public boolean isPositionOnChessBoard(int x,int y){
        return (x < mSizeOfBoardNxN && y < mSizeOfBoardNxN && x>= 0 && y>= 0);
    }


    public int getFarthestMove(){
        return mFarthestMove;
    }

    public int getNearestMove() {
        return mNearestMove;
    }

    public int getChessBordSize(){
        return mSizeOfBoardNxN;
    }
}

class Vertex {

    private int mX;
    private int mY;
    private int mName;
    private boolean visited;
    private LinkedList<Vertex> mAdjVertices = new LinkedList<>();

    public Vertex(){
        mName = 0;
        mX = 0;
        mY = 0;
    }

    public Vertex(int x,int y){
        mName = x+8*y;
        mX = x;
        mY = y;
    }

    public int getX() {
        return mX;
    }

    public int getY() { return mY; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return mName == vertex.mName;
    }

    @Override
    public int hashCode() {
        int result = mX;
        result = 31 * result + mY;
        return result;
    }

    public LinkedList<Vertex> getmAdjVertices(){
        return mAdjVertices;
    }

    public int getName(){
        return mName;
    }

    public boolean isVisited(){
        return visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public void addAdjVertex(Vertex v){
        mAdjVertices.add(v);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "mX=" + mX +
                ", mY=" + mY +
                ", mName=" + mName +
                '}';
    }
}
