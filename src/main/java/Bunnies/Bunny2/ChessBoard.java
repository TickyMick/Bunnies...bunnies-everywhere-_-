package main.java.Bunnies.Bunny2;

/**
 * Created by Alina on 1/12/2017.
 */
public class ChessBoard {

    private  final  int mSizeOfBoardNxN;
    private final int mNearestMove;
    private final  int mFarthestMove;

    public ChessBoard(int sizeOfBoardNxN, int nearestMove, int farthestMove){

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

