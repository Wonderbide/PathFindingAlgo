import java.util.ArrayList;
import java.util.List;
public class Grid {
    Cell[][] grid;
    Position lastVisited = new Position(0,0);

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
        StringBuilder output = new StringBuilder("");

        output.append("\033[1;37m"+"┼───".repeat(grid.length)+"┼"+"\033[37m"+"\n");

        for (Cell[] cells : grid) {
            output.append("\033[1;37m"+"│"+"\033[0m");
            for (Cell cell : cells) {
                if (cell.wallRight)
                    if (!cell.position.equals(lastVisited))
                        output.append("\033[1;37m"+"   │"+"\033[0m");
                    else
                        output.append("\033[1;43m"+" "+"▒"+" "+"\033[0m"+"\033[1;33m"+"│"+"\033[0m");

                else
                    if (!cell.position.equals(lastVisited))
                        output.append("    ");
                    else
                        output.append("\033[;43m"+" "+"▒"+" "+"\033[0m"+" ");
            }
            output.append("\n");
            output.append("\033[1;37m"+"┼"+"\033[0m");
            for (Cell cell : cells) {
                if (cell.wallBot)
                    output.append("\033[1;37m"+"───┼"+"\033[0m");

                else
                    output.append("\033[1;37m"+"   ┼"+"\033[0m");


            }
            output.append("\n");
        }
        return output.toString();
    }
}