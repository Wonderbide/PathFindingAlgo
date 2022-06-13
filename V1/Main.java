package V1;
class  Main{
    public static void main(String[] args) throws Exception {
        Engine engine = new Engine(100);
        engine.mazeBuilder();
        Printer.print(engine.maze);
    }
}