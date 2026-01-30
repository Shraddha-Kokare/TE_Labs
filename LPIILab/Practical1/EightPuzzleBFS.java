// import java.util.*;
// import java.util.stream.Collectors;

// class PuzzleState {
//     int[][] board;
//     int zeroRow, zeroCol;
//     PuzzleState parent;

//     PuzzleState(int[][] board, int zeroRow, int zeroCol, PuzzleState parent) {
//         this.board = board;
//         this.zeroRow = zeroRow;
//         this.zeroCol = zeroCol;
//         this.parent = parent;
//     }

//     // Convert board to string for hashing
//     String boardToString() {
//         StringBuilder sb = new StringBuilder();
//         for (int[] row : board) {
//             for (int val : row) {
//                 sb.append(val);
//             }
//         }
//         return sb.toString();
//     }
// }

// public class EightPuzzleBFS {
//     static int[][] GOAL; // Changed to be set at runtime if needed
//     static int[] dr = {-1, 1, 0, 0}; // Up, Down, Left, Right
//     static int[] dc = {0, 0, -1, 1};

//     static boolean isGoal(int[][] board) {
//         return Arrays.deepEquals(board, GOAL);
//     }

//     static int[][] copyBoard(int[][] board) {
//         int[][] newBoard = new int[3][3];
//         for (int i = 0; i < 3; i++)
//             System.arraycopy(board[i], 0, newBoard[i], 0, 3);
//         return newBoard;
//     }

//     static void printBoard(int[][] board) {
//         for (int[] row : board) {
//             for (int val : row) {
//                 System.out.print(val + " ");
//             }
//             System.out.println();
//         }
//         System.out.println();
//     }

//     static void printPath(PuzzleState goal) {
//         Stack<PuzzleState> stack = new Stack<>();
//         while (goal != null) {
//             stack.push(goal);
//             goal = goal.parent;
//         }
//         int step = 0;
//         while (!stack.isEmpty()) {
//             System.out.println("Step " + step++);
//             printBoard(stack.pop().board);
//         }
//     }

//     static void bfs(int[][] startBoard, int zeroRow, int zeroCol) {
//         Queue<PuzzleState> queue = new LinkedList<>();
//         Set<String> visited = new HashSet<>();
//         PuzzleState start = new PuzzleState(startBoard, zeroRow, zeroCol, null);
//         queue.offer(start);
//         visited.add(start.boardToString());

//         while (!queue.isEmpty()) {
//             PuzzleState current = queue.poll();

//             if (isGoal(current.board)) {
//                 System.out.println("Goal found!");
//                 printPath(current);
//                 return;
//             }

//             // Generate neighbors
//             for (int i = 0; i < 4; i++) {
//                 int newRow = current.zeroRow + dr[i];
//                 int newCol = current.zeroCol + dc[i];

//                 if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
//                     int[][] newBoard = copyBoard(current.board);
//                     // Swap zero with adjacent tile
//                     newBoard[current.zeroRow][current.zeroCol] = newBoard[newRow][newCol];
//                     newBoard[newRow][newCol] = 0;

//                     PuzzleState child = new PuzzleState(newBoard, newRow, newCol, current);
//                     String key = child.boardToString();
//                     if (!visited.contains(key)) {
//                         visited.add(key);
//                         queue.offer(child);
//                     }
//                 }
//             }
//         }
//         System.out.println("No solution exists.");
//     }

//     static int[][] getBoardFromUser(Scanner scanner, String boardType) {
//         int[][] board = new int[3][3];
//         System.out.println("Enter the " + boardType + " board (3 rows of 3 numbers 0-8, space separated):");
//         for (int i = 0; i < 3; i++) {
//             System.out.print("Row " + (i + 1) + ": ");
//             for (int j = 0; j < 3; j++) {
//                 // Ensure input is an integer
//                 while (!scanner.hasNextInt()) {
//                     System.out.println("Invalid input. Please enter a number.");
//                     scanner.next(); // clear the invalid input
//                 }
//                 board[i][j] = scanner.nextInt();
//             }
//         }
//         return board;
//     }

//     static int[] findZeroPosition(int[][] board) {
//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {
//                 if (board[i][j] == 0) {
//                     return new int[]{i, j};
//                 }
//             }
//         }
//         throw new IllegalArgumentException("Board must contain a 0 tile.");
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         // Get the start board from the user
//         int[][] startBoard = getBoardFromUser(scanner, "start");
//         int[] startZeroPos = findZeroPosition(startBoard);
//         int startZeroRow = startZeroPos[0];
//         int startZeroCol = startZeroPos[1];

//         // Get the end (goal) board from the user
//         GOAL = getBoardFromUser(scanner, "end (goal)");

//         scanner.close();

//         System.out.println("\nStarting BFS with the following boards:");
//         System.out.println("Start Board:");
//         printBoard(startBoard);
//         System.out.println("Goal Board:");
//         printBoard(GOAL);

//         bfs(startBoard, startZeroRow, startZeroCol);
//     }
// }

import java.util.*;
import java.util.stream.Collectors;

class PuzzleState {
    int[][] board;
    int zeroRow, zeroCol;
    PuzzleState parent;
    int level; // Add level tracking

    PuzzleState(int[][] board, int zeroRow, int zeroCol, PuzzleState parent, int level) {
        this.board = board;
        this.zeroRow = zeroRow;
        this.zeroCol = zeroCol;
        this.parent = parent;
        this.level = level; // Initialize level
    }

    // Convert board to string for hashing
    String boardToString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int val : row) {
                sb.append(val);
            }
        }
        return sb.toString();
    }
}

public class EightPuzzleBFS {
    static int[][] GOAL; // Changed to be set at runtime
    static int[] dr = {-1, 1, 0, 0}; // Up, Down, Left, Right
    static int[] dc = {0, 0, -1, 1};
    static int BOARD_SIZE = 3;

    static boolean isGoal(int[][] board) {
        return Arrays.deepEquals(board, GOAL);
    }

    static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, BOARD_SIZE);
        }
        return newBoard;
    }

    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    static void printPath(PuzzleState goal) {
        Stack<PuzzleState> stack = new Stack<>();
        while (goal != null) {
            stack.push(goal);
            goal = goal.parent;
        }
        int step = 0;
        while (!stack.isEmpty()) {
            System.out.println("Step " + step++);
            printBoard(stack.pop().board);
            System.out.println();
        }
    }

    // Updated bfs method to include level output and completion of logic
    static void bfs(int[][] startBoard, int zeroRow, int zeroCol) {
        Queue<PuzzleState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        // Start level is 0
        PuzzleState start = new PuzzleState(startBoard, zeroRow, zeroCol, null, 0); 

        queue.add(start);
        visited.add(start.boardToString());

        while (!queue.isEmpty()) {
            PuzzleState current = queue.poll();
            
            // Print the board generated at the current level
            System.out.println("Level " + current.level + ", exploring board:");
            printBoard(current.board);
            System.out.println(); // Extra line for formatting

            if (isGoal(current.board)) {
                System.out.println("Goal reached!");
                printPath(current);
                return;
            }

            // Generate neighbors
            for (int i = 0; i < 4; i++) {
                int newRow = current.zeroRow + dr[i];
                int newCol = current.zeroCol + dc[i];

                if (newRow >= 0 && newRow < BOARD_SIZE && newCol >= 0 && newCol < BOARD_SIZE) {
                    int[][] newBoard = copyBoard(current.board);
                    // Swap zero with the neighbor cell
                    newBoard[current.zeroRow][current.zeroCol] = newBoard[newRow][newCol];
                    newBoard[newRow][newCol] = 0;

                    if (!visited.contains(new PuzzleState(newBoard, newRow, newCol, current, current.level + 1).boardToString())) {
                        PuzzleState neighbor = new PuzzleState(newBoard, newRow, newCol, current, current.level + 1);
                        queue.add(neighbor);
                        visited.add(neighbor.boardToString());
                    }
                }
            }
        }
        System.out.println("No solution found.");
    }

    // Helper method to parse board input
    public static int[][] parseBoard(String input) {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        String[] parts = input.trim().split("\\s+");
        if (parts.length != BOARD_SIZE * BOARD_SIZE) {
            throw new IllegalArgumentException("Input must contain 9 numbers.");
        }
        for (int i = 0; i < parts.length; i++) {
            board[i / BOARD_SIZE][i % BOARD_SIZE] = Integer.parseInt(parts[i]);
        }
        return board;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get start board
        System.out.println("Enter start board (9 numbers separated by spaces, 0 for blank): e.g., 1 2 3 4 5 6 7 8 0");
        int[][] startBoard = parseBoard(scanner.nextLine());

        // Find zero's position in start board
        int startZeroRow = -1, startZeroCol = -1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (startBoard[i][j] == 0) {
                    startZeroRow = i;
                    startZeroCol = j;
                    break;
                }
            }
            if (startZeroRow != -1) break;
        }

        // Get end board (GOAL)
        System.out.println("Enter end board (9 numbers separated by spaces): e.g., 1 2 3 4 5 6 7 8 0");
        GOAL = parseBoard(scanner.nextLine());
        
        scanner.close();

        if (startZeroRow == -1) {
            System.out.println("Error: Start board must contain a 0.");
            return;
        }

        System.out.println("\nStarting BFS from the initial state to the goal state...");
        bfs(startBoard, startZeroRow, startZeroCol);
    }
}

