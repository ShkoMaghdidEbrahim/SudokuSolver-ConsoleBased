import java.util.Scanner;

public class Solver {
    public Solver() {
        
        int[][] sudoku;
        Scanner s = new Scanner(System.in);
        boolean outerRepeat = true;
        System.out.println("Enter your values and choose a row and a column as follows: r(n)c(n) : r1c1=2 \n*Type done to solve it*");
        while (outerRepeat) {
            sudoku = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}};
            boolean innerRepeat = true;
            
            while (innerRepeat) {
                printer(sudoku);
                System.out.print("Input: ");
                String input = s.next();
                if (input.equalsIgnoreCase("done")) {
                    //if it is possible to solve:
                    if (checker(sudoku)) {
                        solve(sudoku, 0, 0); //solve it
                        printer(sudoku);
                        System.out.println("------------------------------------------------------");
                       
                        break;
                    }
                    //if not:
                    else {
                        innerRepeat = false;
                        System.out.println("Theres no possible solution, \ninputs are intersecting");
                        System.out.println("------------------------------------------------------");
                    }
                }
                else {
                    String[] inputSeparated = input.replaceAll(" ", "").split("=");
                    int row;
                    int col;
                    try {
                        row = Integer.parseInt(String.valueOf(inputSeparated[0].charAt(1))) - 1;
                        col = Integer.parseInt(String.valueOf(inputSeparated[0].charAt(3))) - 1;
                        sudoku[row][col] = Integer.parseInt(inputSeparated[1]);
                    }
                    catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please enter a valid value");
                        System.out.println("------------------------------------------------------");
                    }
                }
            }
            System.out.println("Do you want to replay?    Y/N");
            String answer = s.next();
            if (answer.trim().equalsIgnoreCase("N")) {
                outerRepeat = false;
            }
        }
    }
    
    static boolean checker(int[][] sudoku) {
        //Check the rows for any duplicates
        for (int l = 0; l < sudoku[0].length; l++) {
            for (int i = 0; i < sudoku[l].length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    if (sudoku[l][i] == sudoku[l][j] && i != j && sudoku[l][i] != 0) {
                        return false;
                    }
                }
            }
        }
        //Check the columns for any duplicates
        for (int l = 0; l < sudoku[0].length; l++) {
            for (int i = 0; i < sudoku[l].length; i++) {
                for (int j = 0; j < sudoku.length; j++) {
                    if (sudoku[i][l] == sudoku[j][l] && i != j && sudoku[i][l] != 0) {
                        return false;
                    }
                }
            }
        }
        //Check the blocks for any duplicates
        for (int l = 0; l < 3; l++) {
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //Check the left side blocks
                        if (sudoku[l][k] == sudoku[i][j] && l != i && k != j && sudoku[l][k] != 0) {
                            return false;
                        } //Block 0,0
                        if (sudoku[l + 3][k] == sudoku[i + 3][j] && l != i && k != j && sudoku[l + 3][k] != 0) {
                            return false;
                        }//Block 1,0
                        if (sudoku[l + 6][k] == sudoku[i + 6][j] && l != i && k != j && sudoku[l + 6][k] != 0) {
                            return false;
                        }//Block 2,0
                        //Middle row of blocks
                        if (sudoku[l][k + 3] == sudoku[i][j + 3] && l != i && k != j && sudoku[l][k + 3] != 0) {
                            return false;
                        }//Block 0,1
                        if (sudoku[l + 3][k + 3] == sudoku[i + 3][j + 3] && l != i && k != j && sudoku[l + 3][k + 3] != 0) {
                            return false;
                        }//Block 1,1
                        if (sudoku[l + 6][k + 3] == sudoku[i + 6][j + 3] && l != i && k != j && sudoku[l + 6][k + 3] != 0) {
                            return false;
                        }//Block 2,1
                        //Right Side Blocks
                        if (sudoku[l][k + 6] == sudoku[i][j + 6] && l != i && k != j && sudoku[l][k + 6] != 0) {
                            return false;
                        }//Block 0,2
                        if (sudoku[l + 3][k + 6] == sudoku[i + 3][j + 6] && l != i && k != j && sudoku[l + 3][k + 6] != 0) {
                            return false;
                        }//Block 1,2
                        if (sudoku[l + 6][k + 6] == sudoku[i + 6][j + 6] && l != i && k != j && sudoku[l + 6][k + 6] != 0) {
                            return false;
                        }//Block 2,2
                    }
                }
            }
        }
        
        return true;
    }
    static void printer(int[][] sudoku) {
        System.out.println(Main.CYAN + "       1     2     3     4     5     6     7     8     9" + Main.RESET);
        System.out.println(Main.RED + "       ↓     ↓     ↓     ↓     ↓     ↓     ↓     ↓     ↓" + Main.RESET);
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (i + j == 0) {
                    System.out.println("    ╔═════╤═════╤═════╦═════╤═════╤═════╦═════╤═════╤═════╗");
                }
                if (j == 0) {
                    System.out.print(Main.CYAN + (i + 1) + Main.RED + " → " + Main.RESET);
                }
                if ((j) % 3 == 0) {
                    System.out.print("║  ");
                }
                if (j % 3 != 0 && sudoku[i][j] != 0) {
                    System.out.print("│  " + Main.GREEN + sudoku[i][j] + Main.RESET + "  ");
                }
                else if (j % 3 != 0 && sudoku[i][j] == 0) {
                    System.out.print("│  " + " " + "  ");
                }
                else if (sudoku[i][j] == 0) {
                    System.out.print(" " + "  ");
                }
                else {
                    System.out.print(Main.GREEN + sudoku[i][j] + Main.RESET + "  ");
                }
                if (j == 8) {
                    System.out.print("║");
                }
            }
            if ((i + 1) % 3 == 0 && i != 8) {
                System.out.println("\n    ╠═════╪═════╪═════╬═════╪═════╪═════╬═════╪═════╪═════╣");
            }
            else if (i != 8) {
                System.out.println("\n    ╟─────┼─────┼─────╫─────┼─────┼─────╫─────┼─────┼─────╢");
            }
        }
        System.out.println("\n    ╚═════╧═════╧═════╩═════╧═════╧═════╩═════╧═════╧═════╝");
    }
    
    static boolean isThisNumberSafe(int[][] sudoku, int col, int row, int num) {
        
        for (int x = 0; x <= 8; x++) {
            if (sudoku[row][x] == num) {
                return false;
            }
        }
        for (int x = 0; x <= 8; x++) {
            if (sudoku[x][col] == num) {
                return false;
            }
        }
        
        int startRow = row - (row % 3), startColumn = col - (col % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[i + startRow][j + startColumn] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    static boolean solve(int[][] sudoku, int row, int col) {
        
        if (row == 9 - 1 && col == 9) {
            return true;
        }
        if (col == 9) {
            row++;
            col = 0;
        }
        if (sudoku[row][col] != 0) {
            return solve(sudoku, row, col + 1);
        }
        for (int num = 1; num < 10; num++) {
            if (isThisNumberSafe(sudoku, col, row, num)) {
                sudoku[row][col] = num;
                if (solve(sudoku, row, col + 1)) {
                    return true;
                }
            }
            sudoku[row][col] = 0;
        }
        return false;
    }
    
}