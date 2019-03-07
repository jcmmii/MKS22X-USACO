import java.util.*;
import java.io.*;

public class USACO {
  private int[][] Lake;

  public static int bronze(String filename) throws FileNotFoundException {
    File fileIn = new File(filename);
    Scanner scan = new Scanner(fileIn);

    int height = 1;
    int width = 0;

    String firstLine = "";

    if (scan.hasNextLine()) {
      firstLine = scan.nextLine();
      /*
    }
    while (scan.hasNextLine()) {
      String findWidth = scan.nextLine();
      width = findWidth.length();
      height = height + 1;
    }

    Lake = new int[height][width];
*/
  }
  System.out.println(firstLine);
  return 1;
}

//  public static int silver(String filename) {  }
public static void main(String[] args) {
  try {
      bronze("makelake.1.in");
    }
  catch (FileNotFoundException e) {
    
  }
}
}
