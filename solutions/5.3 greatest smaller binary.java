// Given a positive integer, print the next smallest and the next largest number 
// that have the same number of 1 bits in their binary representation. 

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;

 
// Eg. 
// 4 = 100 => 8 = 1000
// 5 = 101 => 6 = 110
// 11 = 1011 => 13 = 1101
 
 
// n = number of bits

// 1)
// Find the last 0 that has 1 after it, 
// Swap their places, 
// for the following zeroes put them to the most right
// If there is no such zero, add one zero to the end
// TC: n SC:1
 
// 2)
// Add one check if it has the same nr of 1s
// Repeat until cond is met
// TC: 2*N
 
// 4 = 100 => 8 = 1000
// 5 = 101 => 6 = 110
// 11 = 1011 => 13 = 1101
 
// n = 4
// binaryStr = 100
// firstZeroFollowedByOneIndex = -1
// ret 8
// ======================
 
// n = 5
// binaryStr = 101
// firstZeroFollowedByOneIndex = 1
// newBinaryStr = 110
// ret 6
// ====================
 
// n = 11
// binaryStr = 1011
// firstZeroFollowedByOneIndex = 1
// newBinaryStr = 1101
// ret 13
 
class Solution {
  
  public static int nextBig(int n) {
    String binaryStr = Integer.toBinaryString(n);
    int firstZeroFollowedByOneIndex = getFirstZeroFollowedByOneIndex(binaryStr);
   
    if(firstZeroFollowedByOneIndex == -1) {
      return 2 * n;
    } else {
      String newBinaryStr = binaryStr.substring(0, firstZeroFollowedByOneIndex) + "10";
      String trailing = binaryStr.substring(firstZeroFollowedByOneIndex + 2);
      String newTrailing = moveOnesToMostRight(trailing);
      newBinaryStr = newBinaryStr + newTrailing;
      return Integer.parseInt(newBinaryStr, 2);
    }
  }
 
// binaryStr = 100
// i =0
//curr = 1
//next = 0
// i =1
//curr = 0
//next = 0
// ret -1
  private static int getFirstZeroFollowedByOneIndex(String binStr) {
    for(int i = 0; i< binStr.length() - 1; i++) {
      Character current = binStr.charAt(i);
      Character next = binStr.charAt(i+1);
      if(current == '0' && next == '1') {
        return i;
      }
    }
    return -1;
  }

  private static String moveOnesToMostRight(String trailing) {
    String result = "";
    for(int i = 0; i < trailing.length(); i++) {
      if('1' == trailing.charAt(i)) {
        result = result+ "1";
      } else {
        result = "0" + result;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(nextBig(4));
    System.out.println(nextBig(5));
    System.out.println(nextBig(11));
    System.out.println(nextBig(16));
    System.out.println(nextBig(22));
  }


  // 111001
  // 11
  // 001
  public static int greatestSmaller(int n) {
    String binStr = Integer.toBinaryString(n);
    int lastOneFollowedByZeroIndex = getLastOneFollowedByZeroIndex(binStr);
    String headPiece = binStr.substring(0, lastOneFollowedByZeroIndex);
    String trailPiece = binStr.substring(lastOneFollowedByZeroIndex + 1);

    return Integer.parseInt(headPiece + trailPiece + "1", 2);
    
    // String zeroPiece = "";    
    // for(int i = 0; i < trailPiece.length(); i++) {
    //   Character current = trailPiece.charAt(i);
    //   if(current == '0') {
    //     zeroPiece = zeroPiece + "0";
    //   } else {
    //     break
    //   }
    // }

  }

  // getLastOneFollowedByZeroIndex(binStr)

  // 101011
  // 100111
  // 1010011
  // 1001110
  // 100111
}





 
