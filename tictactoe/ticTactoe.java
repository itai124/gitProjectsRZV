import java.util.Scanner;
import java.io.*;

public class ticTactoe {
  static Scanner input = new Scanner(System.in);
  static final int TIC_TAC_TOE_LENGTH = 3;
  static final char X_PLAYER = 'X';
  static final char O_PLAYER = 'O';
  static boolean isSaved = false;

  public static void printInstructions() {
    System.out.println("----------------------------------------------");
    System.out.println("Welcome to the tic tac toe!");
    System.out.println("1. The game is played on a grid that's 3 squares by 3 squares.");
    System.out.println(
        "You are X, your friend is O. Players take turns putting their marks in empty squares.");
    System.out.println(
        "The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.");
    System.out.println(
        "When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.");
    System.out.println("----------------------------------------------");
  }

  public static void printMenu() {
    System.out.println("Please enter the action you wanna do ");
    System.out.println("1- to continue a game you have already started");
    System.out.println("2- to create a new game");
    System.out.println("3- to exit");
  }

  public static void printBoard(Cube[][] ticTacToe) {
    for (int rowIndex = 0; rowIndex < TIC_TAC_TOE_LENGTH; rowIndex++) {
      System.out.print("|");
      for (int colIndex = 0; colIndex < TIC_TAC_TOE_LENGTH; colIndex++) {
        System.out.print(ticTacToe[rowIndex][colIndex].getSign() + "|");
      }
      System.out.print("\n");
    }
  }

  public static Cube[][] createNewBoard(int boardSize) {
    Cube board[][] = new Cube[boardSize][boardSize];

    for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
      for (int colIndex = 0; colIndex < board.length; colIndex++) {
        board[rowIndex][colIndex] = new Cube(rowIndex, colIndex);
      }
    }

    return board;
  }

  public static String ticTacToe_New_Game() {
    printInstructions();
    char winner = ' ';
    Cube[][] newBoard = createNewBoard(TIC_TAC_TOE_LENGTH);
    printBoard(newBoard);
    while ((!checkWinner(newBoard) && !checkDraw(newBoard)) && !isSaved) {
      doTurn(newBoard, X_PLAYER);
      printBoard(newBoard);
      if (checkWinner(newBoard)) {
        winner = 'X';
      } else {
        doTurn(newBoard, O_PLAYER);
        printBoard(newBoard);
      }
      if (checkWinner(newBoard)) {
        winner = 'O';
      }
    }
    return checkGameOutput(winner);
  }

  public static String ticTacToe_Continue_Game(String FILE_PATH) {
    Cube[][] board = readGameFromFile(FILE_PATH);
    char signTurn = checkCurrentTurn(board);
    printBoard(board);
    char startingSign = 'X';
    char secondSign = 'O';
    char winner = ' ';
    if (signTurn == 'X') {
      startingSign = X_PLAYER;
      secondSign = O_PLAYER;
    } else if (signTurn == 'O') {
      startingSign = O_PLAYER;
      secondSign = X_PLAYER;
    } else return "something went wrong with the file";
    while ((!checkWinner(board) && !checkDraw(board)) && !isSaved) {
      doTurn(board, startingSign);
      printBoard(board);
      if (checkWinner(board)) {
        winner = startingSign;
      } else {
        doTurn(board, secondSign);
        printBoard(board);
      }
      if (checkWinner(board)) {
        winner = secondSign;
      }
    }
    return checkGameOutput(winner);
  }

  public static String checkGameOutput(char sign) {
    String gameOutput = "this game was a tie!";
    if (sign == 'O') {
      gameOutput = "the winner of this game is X";
    } else if (sign == 'X') {
      gameOutput = "the winner of this game is O";
    }
    return gameOutput;
  }

  public static void doTurn(Cube[][] ticTacToe, char sign) {
    System.out.println("please enter the row and the col you want to add " + sign + " to: ");
    System.out.print("Row: ");
    String rowSign = input.nextLine();
    if (rowSign.equals(" ")) {
      System.out.println("saving file...");
      saveGameToFile(ticTacToe);
      isSaved = true;
    } else if (!isSaved) {
      int row = Integer.parseInt(rowSign);
      // row = checkValidRowAndCol(row);
      System.out.print("Column: ");
      String colSign = input.nextLine();
      int col = Integer.parseInt(colSign);
      // col = checkValidRowAndCol(col);
      while (ticTacToe[row][col].getSign() == 'X' || ticTacToe[row][col].getSign() == 'O') {
        System.out.println("this place has been taken please enter again.");
        rowSign = input.nextLine();
        colSign = input.nextLine();
        if (rowSign.equals(" ")) {
          System.out.println("saving file...");
          saveGameToFile(ticTacToe);
          isSaved = true;
        } else if (!isSaved) {

          row = Integer.parseInt(rowSign);
          // row = checkValidRowAndCol(row);
          col = Integer.parseInt(colSign);
          // col = checkValidRowAndCol(col);
        }
      }
      ticTacToe[row][col].setRow(row);
      ticTacToe[row][col].setCol(col);
      ticTacToe[row][col].setSign(sign);
    }
  }

  public static int checkValidRowAndCol(int rowOrCol) {
    while (rowOrCol > 2 || rowOrCol < 0) {
      System.out.println("please enter this variable again: ");
      rowOrCol = Integer.parseInt(input.nextLine());
    }
    return rowOrCol;
  }

  public static boolean checkWinner(Cube[][] board) {
    return (board[0][0].getSign() == board[0][1].getSign()
            && board[0][0].getSign() == board[0][2].getSign()
            && board[0][0].getSign() != ' ')
        || (board[0][0].getSign() == board[1][1].getSign()
            && board[0][0].getSign() == board[2][2].getSign()
            && board[1][1].getSign() != ' ')
        || (board[0][0].getSign() == board[1][0].getSign()
            && board[0][0].getSign() == board[2][0].getSign()
            && board[0][0].getSign() != ' ')
        || (board[2][0].getSign() == board[2][1].getSign()
            && board[2][0].getSign() == board[2][2].getSign()
            && board[2][2].getSign() != ' ')
        || (board[2][0].getSign() == board[1][1].getSign()
            && board[0][0].getSign() == board[0][2].getSign()
            && board[0][2].getSign() != ' ')
        || (board[0][2].getSign() == board[1][2].getSign()
            && board[0][2].getSign() == board[2][2].getSign()
            && board[0][2].getSign() != ' ')
        || (board[0][1].getSign() == board[1][1].getSign()
            && board[0][1].getSign() == board[2][1].getSign()
            && board[2][1].getSign() != ' ')
        || (board[1][0].getSign() == board[1][1].getSign()
            && board[1][0].getSign() == board[1][2].getSign()
            && board[1][0].getSign() != ' ');
  }

  public static boolean checkDraw(Cube[][] board) {
    for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
      for (int colIndex = 0; colIndex < board[0].length; colIndex++) {
        if (board[rowIndex][colIndex].getSign() == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  public static char checkCurrentTurn(Cube[][] board) {
    int xCounter = 0;
    int oCounter = 0;
    for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
      for (int colIndex = 0; colIndex < board[0].length; colIndex++) {
        if (board[rowIndex][colIndex].getSign() == 'X') {
          xCounter++;
        } else if (board[rowIndex][colIndex].getSign() == 'O') {
          oCounter++;
        }
      }
    }
    char returnSign = 'O';

    if (xCounter < oCounter) {
      returnSign = 'X';
    }
    return returnSign;
  }

  public static Cube[][] readGameFromFile(String FILE_PATH) {
    try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
      Cube[][] ticTacToe = (Cube[][]) objectInput.readObject();
      return ticTacToe;

    } catch (FileNotFoundException e) {
      System.out.println("game not found");
    } catch (EOFException e) {
      System.out.println("end of file");
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    Cube[][] brd = new Cube[TIC_TAC_TOE_LENGTH][TIC_TAC_TOE_LENGTH];
    return brd;
  }

  public static void saveGameToFile(Cube[][] board) {
    System.out.println("please enter the file name you want to save in: ");
    try (ObjectOutputStream objectOutput =
        new ObjectOutputStream(new FileOutputStream(input.nextLine()))) {
      objectOutput.writeObject(board);

    } catch (FileNotFoundException e) {
      System.out.println("file name is wrong, please use FileName.dat");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    final int CONTINUE_GAME = 1;
    final int NEW_GAME = 2;
    final int EXIT_GAME = 3;
    printMenu();
    int action = Integer.parseInt(input.nextLine());

    while (action != EXIT_GAME) {
      switch (action) {
        case CONTINUE_GAME:
          System.out.println("please enter the file you want to import the game from: ");
          String FILE_PATH = input.nextLine();
          String outputFromGame = ticTacToe_Continue_Game(FILE_PATH);
          System.out.println(outputFromGame);
          break;
        case NEW_GAME:
          String outputFromNewGame = ticTacToe_New_Game();
          System.out.println(outputFromNewGame);

          break;
        default:
          System.out.println("you have enter invalid action");

          break;
      }
      isSaved = false;
      printMenu();
      action = Integer.parseInt(input.nextLine());
    }
  }
}
