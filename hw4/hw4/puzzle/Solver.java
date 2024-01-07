package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


public class Solver {
    private int moves;
    private Deque<WorldState> solution;
    private class SearchNode implements Comparable<SearchNode>{
        WorldState worldState;
        int MoveTimes;
        SearchNode pre;
        boolean isUsed;
        private int estimatedDistanceToGoal;
        public SearchNode(WorldState w,int times,SearchNode pre){
            isUsed=false;
            this.worldState=w;
            this.MoveTimes=times;
            this.pre=pre;
            estimatedDistanceToGoal=w.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.MoveTimes+ this.estimatedDistanceToGoal
                    -o.MoveTimes-o.estimatedDistanceToGoal;
        }
    }
    public boolean islegal(SearchNode node,WorldState worldState){
        while (node!=null){
            if(node.worldState.equals(worldState))
                return false;
            node=node.pre;
        }
        return true;
    }
    private SearchNode SolverHelper(edu.princeton.cs.algs4.MinPQ<SearchNode> queue){
        if(queue==null){
            throw new RuntimeException("Solution Not Found");
        }else{
            SearchNode node=queue.delMin();
            node.isUsed=true;
            if(node.worldState.isGoal()){
                return node;
            }
            for(WorldState w:node.worldState.neighbors()){
                if(islegal(node,w)) {
                    queue.insert(new SearchNode(w, node.MoveTimes+1, node));
                }
            }
            return SolverHelper(queue);
        }
    }
    public Solver(WorldState initial){
        moves=0;
        solution=new ArrayDeque<>();
        edu.princeton.cs.algs4.MinPQ<SearchNode> queue=new MinPQ<>();
        queue.insert(new SearchNode(initial,moves,null));


        SearchNode finalnode=SolverHelper(queue);
        while(finalnode!=null){
            solution.addFirst(finalnode.worldState);
            finalnode=finalnode.pre;
        }
    }

    public int moves(){
        return solution.size()-1;
    }
    public Iterable<WorldState> solution(){
        return solution;
    }
}
