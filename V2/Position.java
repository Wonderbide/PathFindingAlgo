package V2;
class Position {
    int x = 0;
    int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ";" + this.y + ")";
    }

    @Override
    public boolean equals(Object point) {
        if (!(point instanceof Position))
            return false;

        Position p = (Position) point;

        return this.x == p.x && this.y == p.y ? true : false;
    }

    public Position direction(Position finalPos) {
        return new Position(finalPos.x - this.x, finalPos.y - this.y);
    }
}