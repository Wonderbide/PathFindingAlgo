package V2;
class Cell {
    boolean isVisited;
    boolean wallBot = true;
    boolean wallRight = true;
    Position position;

    public Cell(int x, int y) {
        this.position = new Position(x, y);
    }

}
