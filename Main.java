public class Main{
    public static void main(String[] args) {
        Grid grid = new Grid(10);
        Engine engine = new Engine(10);
        engine.builder();
        System.out.println(engine.maze.toString());

    }
}