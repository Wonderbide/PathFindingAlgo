import java.util.ArrayList;
import java.util.List;
public class Grid {
    Cell[][] grid;

    public Grid(int size) {
        this.grid = new Cell[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public boolean isVisited(int x, int y) {
        return grid[x][y].isVisited;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("+");

        for (Cell[] cells : grid) {
            output.append("---+");
        }

        output.append("\n");
        for (Cell[] cells : grid) {
            output.append("|");
            for (Cell cell : cells) {
                if (cell.wallRight)
                    output.append("   |");
                else
                    output.append("   ");
            }
            output.append("\n");
            output.append("+");
            for (Cell cell : cells) {
                if (cell.wallBot)
                    output.append("---+");
                else
                    output.append("   +");
            }
            output.append("\n");
        }
        return output.toString();
    }
}