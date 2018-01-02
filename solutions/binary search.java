// find item in a sorted list
// BST
// log n

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
 
 
 
// list = {1,2,3,4}
// x = 4
// beginIndex = 0
// endIndex = 3
// medianIndex = 1
// median = 2
// beginIndex= 2
// medianIndex = 2
// median=3 =======
// beginIndex=3
// medianIndex = 3

class Solution{

 
public Boolean bs(int[] list, int x) {
  int beginIndex = 0;
  int endIndex = list.length - 1;
 
while(endIndex >= beginIndex) {
  int medianIndex = (beginIndex + endIndex) / 2;
  int median = list[medianIndex];
 
  if(median == x) {
      return true;
  } else if(median > x) {
    endIndex = medianIndex - 1;
  } else{
    beginIndex = medianIndex + 1;
  }
}
 
return false;
}

  public static void main(String[] args) {
    int[] list = new int[]{1,2,3,4,5,6,7};
    int x = 4;
   Solution s = new Solution();
   System.out.println(s.bs(list, x));

   System.out.println(s.bs(new int[]{1,2,3,4,5,6,7}, 22));
   System.out.println(s.bs(new int[]{1,2,3,4,5,6,7}, 1));
   System.out.println(s.bs(new int[]{1,2,3,4,5,6,7}, 7));
   System.out.println(s.bs(new int[]{1,2,3,4,5,6,7}, 8));
  }

}