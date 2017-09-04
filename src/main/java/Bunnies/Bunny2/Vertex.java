package main.java.Bunnies.Bunny2;

import java.util.LinkedList;

/**
 * Created by Alina on 1/12/2017.
 */
public class Vertex {

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

    public Vertex(int x, int y){
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
}
