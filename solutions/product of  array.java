import java.lang.Math;
import java.util.*;

/*


Given an array of numbers, 
replace each number with the product of all the numbers in the array
 except the number itself *without* using division.


 BF
 double iterate
 TC: n^2 SC:1




On
create array each elm holds multip of all the elms rest
1 => 2X3X4..
2 => 3X4..
3...

iterate arr multip arr value to the value of so far

eg.
10 9 4 6 8


TC: n SC: n




*/


class Solution {
    public static void main(String[] args) {

        


        System.out.println(Arrays.toString(arrMultip(new int[]{4,3,6})));

    }

    public static int[] arrMultip(int[] in) {

      // TODO: empty && null check
      if(in == null || in.length == 0) {
        return new int[0];
      }


      int[] restMultip = new int[in.length];
      int[] result = new int[in.length];

      int multiplier = 1;
      for (int i = in.length - 1; i>-1; i--) {
        restMultip[i] = multiplier;
        multiplier = multiplier * in[i];
      }

      multiplier = 1;
      for (int i = 0; i<in.length; i++) {
        result[i] = multiplier * restMultip[i];
        multiplier = multiplier * in[i];
      }

      return result;
    }

/*
TODO:
unit tests
edge cases


T: 
in: 4 6 3
restMultip 18 3 1
result 18 12 24

multiplier = 1, 3, 18 , 1, 4, 24
i = 2, 1, 0, 0,1,2

Edge:
zero, negative numbers

*/

  
}

