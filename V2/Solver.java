package V2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solver {
    public static final int size = 50;
    public static Engine engine = new Engine(size);
    public static Position goal = new Position(size-1,size-1);
    public static Position pointer = new Position(0,0);
    public static Deque<Cell> cellDeque = new ArrayDeque<>();
    public static Grid maze = new Grid(size);

    public static boolean[][] mapHistory = new boolean[size][size];

    static {
        engine.builder();
    }

    public Engine resolveMaze(){
        cellDeque.push(engine.maze.getCell(pointer.x, pointer.y));
        List<Position> positionList;
        Position tempPosition;
        mapHistory[pointer.x][pointer.y] = true;
        BOUCLE:while (!pointer.equals(goal)){
            positionList = getWaysFrom(pointer);
            if (positionList.size() == 0 && !cellDeque.isEmpty()){
                recursion();
                continue BOUCLE;
            }
            tempPosition = closestWay(positionList);
            cellDeque.push(engine.maze.getCell(pointer.x, pointer.y));
            pointer = tempPosition;
            mapHistory[pointer.x][pointer.y] = true;
        }
        while (!cellDeque.isEmpty()){
            Cell cell = cellDeque.pop();
            maze.getCell(cell.position.x, cell.position.y).setVisited();
            engine.maze.getCell(cell.position.x, cell.position.y).picture = "\033[1;41;31m"+"o"+"\033[0m";

            System.out.print("\033[H\033[2J");
            System.out.flush();

            engine.printMaze();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        engine.printMaze();

        return engine;
    }

    public static void recursion(){
        Position p = cellDeque.pop().position;
        while (getWaysFrom(p).size() == 0 && !cellDeque.isEmpty()){
            p = cellDeque.pop().position;
        }
        pointer = p;
    }

    public static Position closestWay(List<Position> positionList){
        int shortestDisntance = size;
        int shortestDID = 0;

        for (int i = 0; i < positionList.size() ; i++)
            if (shortestDisntance > pointer.getDistance(positionList.get(i))){
                shortestDisntance = pointer.getDistance(positionList.get(i));
                shortestDID = i;
            }else if (shortestDisntance == pointer.getDistance(positionList.get(i))) {
                if (Math.random() >= 0.5){
                    shortestDID = i;
                }
            }
        return positionList.get(shortestDID);
    }

    public static List<Position> getWaysFrom(Position position){
        List<Position> positionList = new ArrayList<>();
        if (position.x + 1 < engine.mazeSize && !engine.maze.getCell(position.x, position.y).wallBot && !mapHistory[position.x+1][position.y])
            positionList.add(engine.maze.getCell(position.x+1, position.y).position);
        if (position.x  > 0 && !engine.maze.getCell(position.x - 1, position.y).wallBot && !mapHistory[position.x-1][position.y])
            positionList.add(engine.maze.getCell(position.x - 1, position.y).position);
        if (position.y + 1 < engine.mazeSize && !engine.maze.getCell(position.x, position.y).wallRight && !mapHistory[position.x][position.y+1])
            positionList.add(engine.maze.getCell(position.x, position.y+1).position);
        if (position.y  > 0 && !engine.maze.getCell(position.x, position.y - 1).wallRight && !mapHistory[position.x][position.y-1])
            positionList.add(engine.maze.getCell(position.x, position.y - 1).position);

        return positionList;
    }


}
