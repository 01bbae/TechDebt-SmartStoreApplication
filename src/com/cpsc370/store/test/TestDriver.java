package src.com.cpsc370.store.test;

public class TestDriver {
    public static void main(String args[]) {
        try {
            CommandProcessor comprocessor = new CommandProcessor();
            comprocessor.processCommandFile(args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
