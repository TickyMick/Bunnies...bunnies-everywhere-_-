package main.java.Bunnies.Bunny2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Alina on 1/12/2017.
 */
public class BfsAlgorithm {

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
