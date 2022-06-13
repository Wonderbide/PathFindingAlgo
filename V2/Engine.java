package V2;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Engine {

    Grid maze;
    int mazeSize;
    Deque<Cell> cellDeque;
    static final Position NORTH = new Position(-1, 0);
    static final Position SOUTH = new Position(1, 0);
    static final Position WEST = new Position(0, -1);
    static final Position EST = new Position(0, 1);

    public Engine(int size) {
        this.maze = new Grid(size);
        this.mazeSize = size;
        this.cellDeque = new ArrayDeque<>();
    }

    public void builder() {
        onePathBuilder(0,0);
        while (cellDeque.size() > 0){
            Cell c = cellDeque.pop();
            maze.lastVisited = c.position;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(this.maze);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (getUnvisitedNeighbor(c.position.x, c.position.y).size() > 0){
                onePathBuilder(c.position.x, c.position.y);
            }
        }
    }

    public void onePathBuilder(int x, int y){

//        System.out.println(this.maze.toString());
        Cell initialCell = maze.getCell(x,y);
        cellDeque.push(initialCell);

        initialCell.isVisited = true;
        List<Cell> unvisitedNeighbour = getUnvisitedNeighbor(x, y);

        while (unvisitedNeighbour.size() != 0) {
            // selectionne un voisin au hasard
            Cell newCell = unvisitedNeighbour.get(randomPicker(unvisitedNeighbour.size()));
            Position direction = initialCell.position.direction(newCell.position);
            if (direction.equals(NORTH))
                newCell.wallBot = false;
            if (direction.equals(SOUTH))
                initialCell.wallBot = false;
            if (direction.equals(WEST))
                newCell.wallRight = false;
            if (direction.equals(EST))
                initialCell.wallRight = false;

            initialCell = newCell;
            cellDeque.push(initialCell);
            initialCell.isVisited = true;
            unvisitedNeighbour = getUnvisitedNeighbor(initialCell.position.x, initialCell.position.y);

            System.out.print("\033[H\033[2J");
            System.out.flush();

            maze.lastVisited = new Position(initialCell.position.x,initialCell.position.y);

            System.out.println(this.maze.toString());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public List<Cell> getUnvisitedNeighbor(int x, int y) {
        List<Cell> unvisitedNeighbour = new ArrayList<>();

        if (x + 1 < mazeSize && !maze.isVisited(x + 1, y))
            unvisitedNeighbour.add(maze.getCell(x + 1, y));
        if (x > 0 && !maze.isVisited(x - 1, y))
            unvisitedNeighbour.add(maze.getCell(x - 1, y));
        if (y + 1 < mazeSize && !maze.isVisited(x, y + 1))
            unvisitedNeighbour.add(maze.getCell(x, y + 1));
        if (y > 0 && !maze.isVisited(x, y - 1))
            unvisitedNeighbour.add(maze.getCell(x, y - 1));

        return unvisitedNeighbour;
    }

    public static int randomPicker(int range) {
        return (int) (Math.random() * range);
    }
}