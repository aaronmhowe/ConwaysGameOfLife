
public class Main {
    /**
     * Main method to run the program
     * @param args
     */
    public static void main(String[] args) {
        LifeApp life = new LifeApp();
        // loop to run through seven generations, resets after four.
        for (int i = 0; i <= 7; i++) {
            System.out.println(life);
            life.nextGen();
        }
    }
}



