// Given a real number between 0 and 1 (e.g., 0.72) 
// that is passed in as a double, 
// print the binary representation. 
// If the number cannot be represented accurately in binary with at most 32 characters, 
// print "ERROR."

// a decimal number to binary
// contains arbitrary number of digits, starting with 0.
// 0 - 1 is not included





import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;


// 1)
// 0.25 0.01
// 0.75 0.11
// 0.5 0.1
// 1   1
// 2   10
// 3   11

// 0.82 1
// 0.32 1
// 0.07 0 12,5
// 0.07 1 6,75
// 0.0025 0 3,375
// 0.0025 0 3,375

// x
// if x > 0.5 => x - 0.5 => 0.1..
// if y > 0.25 => x - 0.25 => 0.11.
// if y > 1/2^3 => x - 0.25 => 0.11.

// if result gets over 32 chars return err




// t1 0.75
// n= 0.75
// result = {}
// i = 1
// substraction = 0.5
// n = 0.25
// result = {1}
// i = 2
// substraction = 0.25
// result = {1,1}
// ret {1,1}


// t1 0.70
// n= 0.75
// result = {}
// i = 1
// substraction = 0.5
// n = 0.20
// result = {1}
// i = 2
// substraction = 0.25
// result = {1,0}
// i = 3
// substraction = 0.125
// result = {1,0,1}
// n = 0.075
// i = 4
// substraction = 0.0675
// result = {1,0,1,1}....

class Solution {

  public static Queue toBinary(double n) {
    if(n <= 0 || n >= 1) {
      System.out.println("ERROR");
      return null; // err case
    }

    Queue result = new LinkedList();

    for(int i = 1; i < 33; i++) {
      double substraction = Math.pow(2, -1 * i); 
      if(n == substraction){
        result.add(1);
        return result;
      }else if(n > substraction){
        n = n - substraction;
        result.add(1);
      } else {
        result.add(0);
      }
    }
    System.out.println("ERROR");
    return null; // 32 digits were not enough
  }

  public static void printQ(Queue q) {
    if(q == null) {
      return;
    }
    System.out.print("0.");
    while(q.isEmpty() == false) {
      int i = (int)q.remove();
      System.out.print(i);
    }
    System.out.println();
  }
  public static void main(String[] args) {
    printQ(toBinary(0.75));
    printQ(toBinary(0.7));
    printQ(toBinary(0));
    printQ(toBinary(1));
    printQ(toBinary(1123));
    printQ(toBinary(-1123));
    printQ(toBinary(0.625));
    printQ(toBinary(0.62625));
    printQ(toBinary(0.000000007450580596923828125));
    printQ(toBinary(0.76776123046875));
    
  }
}




