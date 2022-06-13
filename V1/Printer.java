package V1;
class Printer{
    public static void print(boolean[][] array) {
        for (boolean[] bs : array) {
           
            for ( boolean b : bs) {
                if (b)
                    System.out.print(" @ ");
                else
                    System.out.print(" . ");
            }

            System.out.println();
        }
    }
}