// You are given two 32-bit numbers, Nand M, andtwo bit positions, land j.Write
// a method to insert M into Nsuch that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit all of M. That is, if M = 10011, you canassumethat there areat least 5 bits between j and i. You would not,forexample, have j = 3 and i = 2,because M could notfully fit between bit 3 and bit 2.
          
// EXAMPLE
          
// Input: N=10000000000,M=10011,i=2,j=6 Output:N = 10001001100
 
// M,N  insert M int N at points i,j
// M,N: Strings
// I,j ints / valid positions
// public String substring(int beginIndex)


import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
 
 
 
class Solution {
 
// n= 10000
// m=    101
// i= 2
// i= 4
 
// beginning = 10
// end = null // ??
 
// 10101
 
  public static String insertNum(String n, String m, int i, int j) {
 
    String beginning = n.substring(0, i-1); 
    String end = n.substring(j+1); 
     
    return beginning + m + end;
  }
  
  public static void main(String[] args) {
    System.out.println(insertNum("10000", "101",3,5));
  }

}