import java.util.*;
import java.io.*;

public class USACO {

  public static int bronze(String filename) throws FileNotFoundException {
    //reads & scans in file
    File fileIn = new File(filename);
    Scanner scan = new Scanner(fileIn);

    //reads in first line
    String firstLine = "";
    if (scan.hasNextLine()) {
      firstLine = scan.nextLine();
    }
    //makes an array of strings; separates values based on spaces between
    String[] numbersArray = firstLine.split(" ");
    //initialize vars
    int index = 0;
    int row = 0;
    int col = 0;
    int num = 0;
    int elevation = 0;

    //parses ints from the array of strings
    while (index != 4) {
      if (index == 0) row = Integer.parseInt(numbersArray[0]);
      if (index == 1) col = Integer.parseInt(numbersArray[1]);
      if (index == 2) elevation = Integer.parseInt(numbersArray[2]);
      if (index == 3) num = Integer.parseInt(numbersArray[3]);
      index++;
    }

    //initializes array of Lake, reads in from file and adds value to array
    int[][] Lake = new int[row][col];
    for (int x = 0; x < row; x++) {
      String newLine = scan.nextLine();
      String[] parseLine = newLine.split(" ");
      for (int y = 0; y < parseLine.length; y++) {
        Lake[x][y] = Integer.parseInt(parseLine[y]);
      }
    }

    //reads in the directions
    //creates an array of directions, based on the parameter num and 3
    int[][] directions = new int[num][3];
    int rowNum = 0;
    //reuse index
    index = 0;
    while (scan.hasNextLine()) {
      String steps = scan.nextLine();
      String[] parseSteps = steps.split(" ");
      directions[rowNum][index] = Integer.parseInt(parseSteps[index]);
      index++;
      directions[rowNum][index] = Integer.parseInt(parseSteps[index]);
      index++;
      directions[rowNum][index] = Integer.parseInt(parseSteps[index]);
      //resets index back to 0, row number goes up by 1, repeats
      index = 0;
      rowNum++;
    }

  //  System.out.println(row + " " + col + " " + elevation + " " + num);
  //  System.out.println(toString(Lake));
  //  System.out.println(toString(directions));

  //SOLUTION:
    for (int ind = 0; ind < directions.length; ind++) {
      int row2 = directions[ind][0];
      int col2 = directions[ind][1];
      int depth = directions[ind][2];
      //calls helper stomp method
      stomp(Lake,row2,col2,depth);
    }

    //calculates the depths
    int ret = 0;
    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        Lake[r][c] = elevation - Lake[r][c];
        if (Lake[r][c] < 0) Lake[r][c] = 0;
        ret = ret + Lake[r][c];
      }
    }
    ret = ret * 72 * 72;
    return ret;
}

    //helper stomp method
    private static void stomp(int[][] Lake, int row, int col, int depth) {
      //creates a temporary grid
      int[][] grid = new int[3][3];
      //finds the maximum val (starting stomp coords)
      int max = 0;
      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
          grid[r][c] = Lake[row+r-1][col+c-1];
          if (grid[r][c] > max) max = grid[r][c];
        }
      }
      //starting stomp coords will always stomp depth amount of times -> min
      //for every coord that is bigger than the min, set that to min
      //then sets the actual Lake coord to the min value
      int min = max - depth;
      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
          if (grid[r][c] > min) grid[r][c] = min;
          Lake[row+r-1][col+c-1] = grid[r][c];
        }
      }
    }

    //toString method
    public static String toString(int[][] Lake) {
      String ret = "";
      for (int x = 0; x < Lake.length; x++) {
        for (int y = 0; y < Lake[0].length; y++) {
          ret = ret + Lake[x][y] + " ";
        }
        //adds new line, unless its the last line
        if (x != Lake.length-1) {
          ret = ret + "\n";
        }
      }
      return ret;
    }

  public static int silver(String filename) throws FileNotFoundException {
      //reads & scan sin file
      File fileIn = new File(filename);
      Scanner scan = new Scanner(fileIn);

      //reads in first line
      String firstLine = "";
      if (scan.hasNextLine()) {
        firstLine = scan.nextLine();
      }

      //makes an array of Strings; seperates values based on spaces between
      String[] numbersArray = firstLine.split(" ");
      int row = 0;
      int col = 0;
      int time = 0;
      int index = 0;

      //parses ints from the array of strings
      while (index != 3) {
        if (index == 0) row = Integer.parseInt(numbersArray[0]);
        if (index == 0) col = Integer.parseInt(numbersArray[1]);
        if (index == 0) time = Integer.parseInt(numbersArray[2]);
        index++;
      }

      //initializes the array of pasture
      //reads in values from file and sets . to 0, * to 1
      int[][] pasture = new int[row][col];
      for (int r = 0; r < row; r++) {
        String newLine = scan.nextLine();
        for (int c = 0; c < col; c++) {
          if (newLine.charAt(c) == '.') pasture[r][c] = 0;
          if (newLine.charAt(c) == '*') pasture[r][c] = -1;
        }
      }

      //reads in the coordinates
      String lastLine = scan.nextLine();
      String[] numbersArray2 = lastLine.split(" ");
      //-1 to keep things in bounds 
      int row1 = Integer.parseInt(numbersArray2[0])-1;
      int col1 = Integer.parseInt(numbersArray2[1])-1;
      int row2 = Integer.parseInt(numbersArray2[2])-1;
      int col2 = Integer.parseInt(numbersArray2[3])-1;

      //sets starting coords, calls helper method
      pasture[row1][col1] = 1;
      for (int a = 0; a < time; a++) {
        pasture = move(pasture);
      }
      return pasture[row2][col2];
  }

  //helper counting moves method
  private static int[][] move(int[][] land) {
    //possible moves, creates a new pasture
    int[][] moves = {{1,-1,0,0}, {0,0,-1,1}};
    int[][] newLand = new int[land.length][land[0].length];
    for (int x = 0; x < newLand.length; x++) {
      for (int y = 0; y < newLand[0].length; y++) {
        if (land[x][y] != -1) {
          int sum = 0;
          for (int z = 0; z < 4; z++) { //checks all four directions
            int newRow = x + moves[0][z];
            int newCol = y + moves[1][z];
            //makes sure that it is in bounds
            if (newRow >= 0 && newRow < land.length && newCol >= 0 &&
                newCol < land[0].length && land[newRow][newCol] != -1) {
              sum = sum + land[newRow][newCol];
            }
          }
          newLand[x][y] = sum;
        }
        else{
          newLand[x][y] = -1;
        }
      }
    }
    return newLand;
  }


public static void main(String[] args) {
  try {
    //  System.out.println(bronze("makelake.1.in"));
    //  System.out.println(bronze("makelake.2.in"));
    //  System.out.println(bronze("makelake.3.in"));
        System.out.println(silver("ctravel.3.in"));
    }
  catch (FileNotFoundException e) {

  }
}
}
