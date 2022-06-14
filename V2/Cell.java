package V2;
class Cell {
    boolean isVisited;
    boolean wallBot = true;
    boolean wallRight = true;
    Position position;
    boolean goal;
    String picture = "▒" ;

    public Cell(int x, int y) {
        this.position = new Position(x, y);
    }
    public Cell(int x, int y, boolean goal) {
        this.position = new Position(x, y);
        this.goal = goal;
    }

    public void setVisited(){
        this.isVisited = true;
        this.picture = " ";
    }

    @Override
    public String toString(){
        return this.picture;
    }

}
