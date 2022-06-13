import java.util.ArrayList;
import java.util.List;

public class Engine{

    List<Position> unvisitedPositions = new ArrayList<>();
    
    List<Position> visitedPositions = new ArrayList<>();

    int mazeSize;

    boolean[][] maze;       

    public Engine(int size){
        for( int x = 0 ; x < size ; x ++){
            for( int y = 0 ; y < size ; y++){
                unvisitedPositions.add(new Position(x, y));
            } 
        }
        this.mazeSize = size;
        this.maze = new boolean[size][size];       
    }

    public void mazeBuilder() throws Exception{
        int listIndex = randomPicker(unvisitedPositions.size()-1);
        Position pos = unvisitedPositions.get(0);
        List<Position> nonBolockedPosition = getNonBlockedPosition(pos);


        while (nonBolockedPosition.size() != 0){
            
            int indexNonBlockedList = randomPicker(nonBolockedPosition.size());
            setToVisited(pos.x, pos.y);
            pos = nonBolockedPosition.get(indexNonBlockedList);
            nonBolockedPosition.remove(indexNonBlockedList);
            for (Position position : nonBolockedPosition) {
               maze[position.x][position.y] = true; 
            }
            nonBolockedPosition = getNonBlockedPosition(pos);
        }
        
    }

    public void setToVisited(int x, int y) throws Exception{
        boolean found = false;
        for ( Position p : unvisitedPositions) {
            if (p.x == x && p.y == y) {
                found = true;
                visitedPositions.add(p);
                unvisitedPositions.remove(p);
                break;
            }
        }
    }

    public List<Position> getNonBlockedPosition(Position pos){
        List<Position> nonBlockedPositions = new ArrayList<>();
        if (pos.x+1 < mazeSize  && !maze[pos.x+1][pos.y] && !visitedPositions.contains(new Position(pos.x+1, pos.y)))        nonBlockedPositions.add(new Position(pos.x+1, pos.y));
        if (pos.x   > 0         && !maze[pos.x-1][pos.y] && !visitedPositions.contains(new Position(pos.x-1, pos.y)))        nonBlockedPositions.add(new Position(pos.x-1, pos.y));
        if (pos.y+1 < mazeSize  && !maze[pos.x  ][pos.y+1] && !visitedPositions.contains(new Position(pos.x, pos.y+1)))      nonBlockedPositions.add(new Position(pos.x, pos.y+1));
        if (pos.y   > 0         && !maze[pos.x  ][pos.y-1] && !visitedPositions.contains(new Position(pos.x, pos.y-1)))      nonBlockedPositions.add(new Position(pos.x, pos.y-1));
        return nonBlockedPositions;
    } 

    public static int randomPicker (int range){
        return (int)(Math.random()*range);
    }

    public static boolean[][] generator(int size){
        boolean[][] maze = new boolean[size][size];

        return maze;
    }
    
}

class Position{
    int x = 0;
    int y = 0;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "("+this.x+";"+this.y+")";
    }

    @Override
    public boolean equals(Object point){
        if (! (point instanceof Position)) return false;
        
        Position p = (Position)point;
        
        return this.x==p.x && this.y==p.y ? true : false;
    }
}