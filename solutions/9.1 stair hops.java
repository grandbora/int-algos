import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;



// A child is running up a staircase with n steps, 
// and can hop either 1 step, 2 steps, or 3 steps at a time. 
// Implement a method to count how many possible 
// ways the child can run up the stairs.
 
//  Steps
// 1,2,3 hops
 
// 1)
// Recurse through the variations
// 1 + n - 1
// 2 + n - 2
// 3 + n - 3
 
// N
 
// N-1                           n-2                     n-3
 
// N-1 n-2 n-3             N-1 n-2 n-3
// …
// …
// 1   2
// 0  2 1 
//    0   1
//           0
 
// TC: 3^n SC:n
 
 
public class Solution {
 
public int getCombinations(int n){
  if(n == 0){
    return 0;
} else if(n==1){
  return 1;
} else if(n==2){
  return 2;
} else if(n==3){
  return 4;
} else {
  return getCombinations(n-1) + getCombinations(n-2) + getCombinations(n-3);
}
}
}
