import java.util.*;
import java.io.*;

public class USACO {

  public static int bronze(String filename) throws FileNotFoundException {

    File fileIn = new File(filename);
    Scanner scan = new Scanner(fileIn);

    //reads in first line
    String firstLine = "";
    if (scan.hasNextLine()) {
      firstLine = scan.nextLine();
    }
    //makes an array of Strings; separates values based on spaces between
    String[] numbersArray = firstLine.split(" ");
    //initialize vars
    int index = 0;
    int row = 0;
    int col = 0;
    int num = 0;
    int elevation = 0;

    //parses Ints from the array of Strings
    while (index != 4) {
      if (index == 0) row = Integer.parseInt(numbersArray[0]);
      if (index == 1) col = Integer.parseInt(numbersArray[1]);
      if (index == 2) elevation = Integer.parseInt(numbersArray[2]);
      if (index == 3) num = Integer.parseInt(numbersArray[3]);
      index++;
    }

    //initializes array of Lake, reads in from file and adds value to array
    int[][] Lake = new int[row][col];
    for (int x = 0; x < Lake.length; x++) {
      String newLine = scan.nextLine();
      String[] parseLine = newLine.split(" ");
      for (int y = 0; y < parseLine.length; y++) {
        Lake[x][y] = Integer.parseInt(parseLine[y]);
      }
    }

    int[][] directions = new int[num][3];
    int rowNum = 0;
    index = 0;
    while (scan.hasNextLine()) {
      String steps = scan.nextLine();
      String[] parseSteps = steps.split(" ");
      directions[rowNum][index] = Integer.parseInt(parseSteps[index]);
      index++;
      directions[rowNum][index] = Integer.parseInt(parseSteps[index]);
      index++;
      directions[rowNum][index] = Integer.parseInt(parseSteps[index]);
      index = 0;
      rowNum++;
    }

    System.out.println(toString(directions));

    return 1;
}

    public static String toString(int[][] Lake) {
      String ret = "";
      for (int x = 0; x < Lake.length; x++) {
        for (int y = 0; y < Lake[0].length; y++) {
          ret = ret + Lake[x][y] + " ";
        }
        ret = ret + "\n";
      }
      return ret;
    }

//  public static int silver(String filename) {  }


public static void main(String[] args) {
  try {
      bronze("makelake.2.in");
    }
  catch (FileNotFoundException e) {

  }
}
}
