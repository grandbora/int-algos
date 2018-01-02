import java.lang.Math;
import java.util.*;

/*

Given a contiguous sequence of numbers in which each number repeats thrice, there is exactly one missing number. Find the missing number.
eg: 11122333 : Missing number 2
11122233344455666 Missing number 5

*/

class Solution {
    public static void main(String[] args) {   

        System.out.println(missingNumber(new int[]{}));
        System.out.println(missingNumber(new int[]{1,1,1}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,2}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,2,3,3}));
        System.out.println(missingNumber(new int[]{1,1,2,2,2,3,3,3}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,3,3,3}));
        System.out.println(missingNumber(new int[]{1,1}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,2,3,3,3,4,4,4,5,5,6,6,6}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,2,3,3,3,4,4,5,5,5,6,6,6}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,2,3,3,4,4,4,5,5,5,6,6,6}));
        System.out.println(missingNumber(new int[]{1,1,1,2,2,3,3,3,4,4,4,5,5,5,6,6,6}));
        System.out.println(missingNumber(new int[]{1,1,2,2,2,3,3,3,4,4,4,5,5,5,6,6,6}));
    }




public static int missingNumber(int[] list) {

    if((list.length % 3) == 0) { return -1; }

    int start = 0;
    int end = list.length - 1;

    while(end - start > 1) {
        int medianIndex = (start + end) / 2;

        int expectedNumber = (medianIndex / 3) + 1;
        int expectedOrder = (medianIndex % 3) + 1;
        int actual = list[medianIndex];

        if(actual == 1 && expectedOrder == (medianIndex + 1)) {
            start = medianIndex;
        } else {
         
            if(actual != expectedNumber || list[medianIndex - expectedOrder] != expectedNumber - 1 ) { 
                end = medianIndex;
            } else {
                start = medianIndex;
            }
        }
    }

    return list[start];
}


}

