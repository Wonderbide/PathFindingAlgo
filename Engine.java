import java.util.ArrayList;
import java.util.List;

public class Engine {

    Grid maze;
    int mazeSize;

    static final Position NORTH = new Position(-1, 0);
    static final Position SOUTH = new Position(1, 0);
    static final Position WEST = new Position(0, -1);
    static final Position EST = new Position(0, 1);

    public Engine(int size) {
        this.maze = new Grid(size);
        this.mazeSize = size;
    }

    public void builder() {
        Cell co = maze.getCell(0, 0);
        List<Cell> unvisitedNeighbour = getUnvisitedNeighbor(0, 0);

        while (unvisitedNeighbour.size() != 0) {
            // selectionne un voisin au hasard
            Cell cn = unvisitedNeighbour.get(randomPicker(unvisitedNeighbour.size()));
            Position direction = co.position.direction(cn.position);
            if (direction.equals(NORTH))
                cn.wallBot = false;
            if (direction.equals(SOUTH))
                co.wallBot = false;
            if (direction.equals(WEST))
                co.wallRight = false;
            if (direction.equals(EST))
                cn.wallRight = false;

            co = cn;

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