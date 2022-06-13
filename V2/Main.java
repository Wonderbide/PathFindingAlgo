package V2;
public class Main{
    public static void main(String[] args) {
        Engine engine = new Engine(Integer.parseInt(args[0]));
        engine.builder();
        System.out.println(engine.maze.toString());

    }
}
