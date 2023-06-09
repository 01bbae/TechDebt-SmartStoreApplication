package src.com.cpsc370.store.test;

public class TestDriver {
    public static void main(String args[]) {
        try {
            if (args.length == 0){
                throw new Exception("You did not provide a file to parse, Usage: TestDriver <filename>");
            }
            CommandProcessor comprocessor = new CommandProcessor();
            comprocessor.processCommandFile(args[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
