import java.lang.Math;
import java.util.*;

/*

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.


In: int array
* not sorted
* has pos/negative/0
* has repeats
* each input would have exactly one solution

IN: target : int

out: indices of two numbers
* all of the indices of pairs?
* any / first (any)


eg 
target = 21
         0   1  2  3 4 5   6
list = [10, 20, 4,-9,0,11, 4]
ans: 0,5


1) BF
loop arr
    check if complement is in the arr (another loop)

repeat until find one

TC n^2 SC:1

2) Map
Loop & add to map(value->index)
    for each item check if complement is in the list

TC:n SC:n

2) sorting

keep copy of original

sort 
loop
    start with two pointers begin & end 
    sum begin and end 
        move end until sum is lower than target (or eq)
        when some is lower
            bump begin
        continue process with new begin

    when target is found, find original indexes from the copy of org list 

TC: n logn SC: n

*/


class Solution {

    public static void main(String[] args) {

        int[] l = new int[]{0,7,-13,-6,22,10,20};
        int t = 20;
        int[] r = twoSum(l,t);
        System.out.println(r[0] + " " + r[1]);

    }

    /*
                 0   1 2 3 4  5  6
    sorted-list:-13 -6 0 7 10 20 22
    target:17

beginIndex: 0, 1, 2, 3
endIndex: 6, 5, 4
sum = 9, 16, 22, 20, 10, 17


EDGE CASES:
no pair
multiple pairs
repeat elements?
    */

    public static int[] twoSum(int[] list, int target) {
        
        if(list.length == 0) {
            return new int[]{};    
        }

        Arrays.sort(list);
        
        int beginIndex = 0;
        int endIndex = list.length - 1;
        
        int sum = list[beginIndex] + list[endIndex];

        while(endIndex > beginIndex) {
            
            if(sum == target){
                // keep copy of original and find org indexes of begin end
                return new int[]{beginIndex, endIndex};
            } else if(sum > target) {
                endIndex = endIndex - 1;
            } else {
                beginIndex = beginIndex + 1;
            }

            sum = list[beginIndex] + list[endIndex];
        }

        // no pair that adds up to target
        return new int[]{};
    }
}


