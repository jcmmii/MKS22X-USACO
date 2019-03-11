import java.util.*;
import java.io.*;

public class USACO {
  
  public static int bronze(String filename) throws FileNotFoundException {
    File fileIn = new File(filename);
    Scanner scan = new Scanner(fileIn);

    String firstLine = "";
    if (scan.hasNextLine()) {
      firstLine = scan.nextLine();
    }
    String[] numbersArray = firstLine.split(" ");
    int index = 0;
    int row = 0;
    int col = 0;
    int num = 0;
    int elevation = 0;

    while (index != 4) {
      if (index == 0) row = Integer.parseInt(numbersArray[0]);
      if (index == 1) col = Integer.parseInt(numbersArray[1]);
      if (index == 2) elevation = Integer.parseInt(numbersArray[2]);
      if (index == 3) num = Integer.parseInt(numbersArray[3]);
      index++;
    }

    System.out.println(row);
    System.out.println(col);
    System.out.println(elevation);
    System.out.println(num);
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
