// Implement a function to check if a binary tree is balanced. 
// For the purposes of this question, a balanced tree is defined 
// to be a tree such that the heights of the two subtrees of any
 // node never differ by more than one.

// leaf indicated by null
// values ints


// 1)
// get the root
// calculate the height of the right and left
// check if balanced
// calculating:
// if both nodes are null height = 1
// if one or more is not null, first calculate their height and add 1

// n: depth
// TC: 2^n


import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;

class Solution {

  // 1
  // 2   3
  // 45  67

  // r = 1
  // leftHeight = balancedHeight(2) + 1 == 2 // verify
  // rightHeight = balancedHeight(3) + 1 == 2 // verify

  // ret 2


  // =================

  // r = 2
  // leftHeight = balancedHeight(4) + 1 // 0+1
  // rightHeight = balancedHeight(5) + 1 // 0+1

  // ret 1


  // =================
  // r = 4
  // ret 0

  // =================
  // r = 5
  // ret 0


  // ==============================
  //   1
  // 2   null
  // 45  

  // r = 1
  // leftHeight = balancedHeight(2) + 1 ==> 2
  // rightHeight = 0
  // ret -1

  // r = 2
  // ret 1

  public static int balancedHeight(Node root) {
    
    if(node.left == null && node.right == null) {
      return 0;
    }

    int leftHeight = node.left == null ? 0 : balancedHeight(node.left) + 1;
    if(leftHeight == -1) {
      return -1;
    }

    int rightHeight = node.right == null ? 0 : balancedHeight(node.right) + 1;
    if(rightHeight == -1) {
      return -1;
    }

    if(Math.abs(rightHeight - leftHeight) > 1) {
      return -1;
    }
    
    return Math.max(rightHeight, leftHeight);
    
  }
}





