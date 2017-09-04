package main.java.Bunnies.Bunny2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alina on 9/4/2017.
 */
public class Bunny2 {


    public static final int SIZE_OF_CHESS_BOARD = 8;
    private static final BfsAlgorithm bfs = new BfsAlgorithm();
    private static final ChessBoard chessBoard = new ChessBoard(SIZE_OF_CHESS_BOARD, 1, 2);


    public  static void main(String[] args){

        System.out.println(answer(19,36));
        System.out.println(answer(0,1));
    }

    public static int answer(int from, int to){
        ArrayList<Vertex> list = createChessboardGraph();
        Vertex fromVertex = new Vertex();
        Vertex toVertex = new Vertex();
        for(Vertex v :list){
            if(v.getName() == from) fromVertex = v;
            else if(v.getName() == to) toVertex = v;
        }
        return  solveProblem(fromVertex,toVertex);
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

    private static void createAdjVertexIfInBounds(Vertex v, int x,int y, ArrayList<Vertex> vertexes){
        if(chessBoard.isPositionOnChessBoard(x,y)){
            final int indexOf = vertexes.indexOf(new Vertex(x,y));
            final Vertex reusedVertex = vertexes.get(indexOf);
            v.addAdjVertex(reusedVertex);
        }
    }

    private static int solveProblem(final Vertex from, final Vertex to){

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
                if(vertex.equals(to)) return true;
                else return false;
            }
        });
        path.add(to);
        Vertex par = parentToChild.get(to);
        while(par != null){
            path.add(par);
            par = parentToChild.get(par);
        }
        return path.size()-1;
    }

}
