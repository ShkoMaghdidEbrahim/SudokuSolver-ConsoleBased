import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome to sudoku game\nChoose an option from the following: \n1.Solver   ,You give it a board and it will try to solve it\n2.YouSoveIt    ,This time it will give you a question to solve :)");
        boolean repeat = true;
        while (repeat) {
            System.out.print("Input: ");
            String input = s.next();
            if (input.equalsIgnoreCase("Solver") || input.equals("1")) {
                new Solver();
                repeat = false;
            }
            else if (input.equalsIgnoreCase("YouSolveIt") || input.equals("2")) {
                new YouSolveIt();
                repeat = false;
            }
            else {
                System.out.println("Please enter a valid input from the options!");
                System.out.println("------------------------------------------------------");
            }
        }
    }
    
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
}