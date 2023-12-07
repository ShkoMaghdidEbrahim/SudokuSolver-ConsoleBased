import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class YouSolveIt {
    public YouSolveIt() {
        int[][] sudoku = {
                  {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                , {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        
        Scanner s = new Scanner(System.in);
        AtomicBoolean outerRepeat = new AtomicBoolean(true);
        while (outerRepeat.get()) {
            int[][] yourSolution = new int[][]{
                      {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
                    , {0, 0, 0, 0, 0, 0, 0, 0, 0}
            };
            //input some random numbers into the array
            for (int i = sudoku.length - 1; i > 0; i--) {
                sudoku[i][(int) (Math.random() * 9)] = i + 1;
            }
            //solve and shuffle a nearly empty board
            Random random = new Random();
            solver(sudoku, 0, 0);
            for (int[] area : sudoku) {
                for (int j = 0; j < area.length; j++) {
                    int blockLength = random.nextInt(0, 3);
                    int blockWidth = random.nextInt(0, 3);
                    int blockLength1 = random.nextInt(0, 3);
                    int blockWidth1 = random.nextInt(0, 3);
                    sudoku[blockLength][blockWidth] = sudoku[blockLength][blockWidth] ^ sudoku[blockLength1][blockWidth1] ^ (sudoku[blockLength1][blockWidth1] = sudoku[blockLength][blockWidth]);
                    sudoku[blockLength][blockWidth + 3] = sudoku[blockLength][blockWidth + 3] ^ sudoku[blockLength1][blockWidth1 + 3] ^ (sudoku[blockLength1][blockWidth1 + 3] = sudoku[blockLength][blockWidth + 3]);
                    sudoku[blockLength][blockWidth + 6] = sudoku[blockLength][blockWidth + 6] ^ sudoku[blockLength1][blockWidth1 + 6] ^ (sudoku[blockLength1][blockWidth1 + 6] = sudoku[blockLength][blockWidth + 6]);
                    
                    sudoku[blockLength + 3][blockWidth] = sudoku[blockLength + 3][blockWidth] ^ sudoku[blockLength1 + 3][blockWidth1] ^ (sudoku[blockLength1 + 3][blockWidth1] = sudoku[blockLength + 3][blockWidth]);
                    sudoku[blockLength + 3][blockWidth + 3] = sudoku[blockLength + 3][blockWidth + 3] ^ sudoku[blockLength1 + 3][blockWidth1 + 3] ^ (sudoku[blockLength1 + 3][blockWidth1 + 3] = sudoku[blockLength + 3][blockWidth + 3]);
                    sudoku[blockLength + 3][blockWidth + 6] = sudoku[blockLength + 3][blockWidth + 6] ^ sudoku[blockLength1 + 3][blockWidth1 + 6] ^ (sudoku[blockLength1 + 3][blockWidth1 + 6] = sudoku[blockLength + 3][blockWidth + 6]);
                    
                    sudoku[blockLength + 6][blockWidth] = sudoku[blockLength + 6][blockWidth] ^ sudoku[blockLength1 + 6][blockWidth1] ^ (sudoku[blockLength1 + 6][blockWidth1] = sudoku[blockLength + 6][blockWidth]);
                    sudoku[blockLength + 6][blockWidth + 3] = sudoku[blockLength + 6][blockWidth + 3] ^ sudoku[blockLength1 + 6][blockWidth1 + 3] ^ (sudoku[blockLength1 + 6][blockWidth1 + 3] = sudoku[blockLength + 6][blockWidth + 3]);
                    sudoku[blockLength + 6][blockWidth + 6] = sudoku[blockLength + 6][blockWidth + 6] ^ sudoku[blockLength1 + 6][blockWidth1 + 6] ^ (sudoku[blockLength1 + 6][blockWidth1 + 6] = sudoku[blockLength + 6][blockWidth + 6]);
                    if (!checker(sudoku)) {
                        j--;
                    }
                }
            }
            //Put some shuffled numbers into some random cells
            for (int i = 0; i < 35; i++) {
                int randomRow = random.nextInt(0, 9);
                int randomCol = random.nextInt(0, 9);
                yourSolution[randomRow][randomCol] = sudoku[randomRow][randomCol];
            }
            //Count the already solved cells
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku[i].length; j++) {
                    if (yourSolution[i][j] != 0) {
                        solvedCorrectly++;
                    }
                }
            }
            
            System.out.println("\nEnter your values and choose a row and a column as follows: r(n)c(n)=(n) : r1c1=2");
            
            while (true) {
                printer(yourSolution);
                System.out.println("chances left: " + chances + " | " + "Solved correctly: " + solvedCorrectly);
                System.out.print("Input: ");
                String input = s.next();
                String[] inputSeparated = input.replaceAll(" ", "").split("=");
                int row;
                int col;
                try {
                    row = Integer.parseInt(String.valueOf(inputSeparated[0].charAt(1))) - 1;
                    col = Integer.parseInt(String.valueOf(inputSeparated[0].charAt(3))) - 1;
                }
                catch (NumberFormatException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid value");
                    continue;
                }
                try {
                    yourSolution[row][col] = Integer.parseInt(inputSeparated[1]);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid position!");
                    System.out.println("------------------------------------------------------");
                    break;
                }
                if (sudoku[row][col] == yourSolution[row][col]) {
                    solvedCorrectly++;
                }
                else if (solvedCorrectly == 81 && chances > 0) {
                    System.out.println("Congratulations!!!\nYou won!!");
                    chances = 5;
                    solvedCorrectly = 0;
                    System.out.println("------------------------------------------------------");
                    break;
                }
                else if (chances == 1) {
                    System.out.println("You lost :(");
                    solvedCorrectly = 0;
                    chances = 5;
                    System.out.println("------------------------------------------------------");
                    break;
                }
                else {
                    chances--;
                }
            }
            System.out.println("Do you want to replay?    Y/N");
            String answer = s.next();
            if (answer.trim().equalsIgnoreCase("N")) {
                outerRepeat.set(false);
                break;
            }
            else if (answer.trim().equalsIgnoreCase("Y")) {
                chances = 5;
                solvedCorrectly = 0;
            }
            else {
                System.out.println("You've entered a wrong input\nThe program will be eliminated");
                outerRepeat.set(false);
                break;
            }
        }
    }
    
    static int chances = 5;
    static int solvedCorrectly = 0;
    
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
        System.out.println(Main.RED + "       \u2193     \u2193     \u2193     \u2193     \u2193     \u2193     \u2193     \u2193     \u2193" + Main.RESET);
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (i + j == 0) {
                    System.out.println("    ╔═════╤═════╤═════╦═════╤═════╤═════╦═════╤═════╤═════╗");
                }
                if (j == 0) {
                    System.out.print(Main.CYAN + (i + 1) + Main.RED + " \u2192 " + Main.RESET);
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
                    System.out.print("" + " " + "  ");
                }
                else {
                    System.out.print("" + Main.GREEN + sudoku[i][j] + Main.RESET + "  ");
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
    
    static boolean solver(int[][] sudoku, int row, int col) {
        
        if (row == 9 - 1 && col == 9) {
            return true;
        }
        if (col == 9) {
            row++;
            col = 0;
        }
        if (sudoku[row][col] != 0) {
            return solver(sudoku, row, col + 1);
        }
        for (int num = 1; num < 10; num++) {
            if (isThisNumberSafe(sudoku, col, row, num)) {
                sudoku[row][col] = num;
                if (solver(sudoku, row, col + 1)) {
                    return true;
                }
            }
            sudoku[row][col] = 0;
        }
        return false;
    }
}
    