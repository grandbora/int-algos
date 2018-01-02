// Given a sorted (increasingorder) array with unique integer elements, 
// write an algorithm to create a binary search tree with minimal height.

// 1 - 3 - 5 - 7 - 9 - 11 - 13
    //   7
    // 3   11

// uniq ints / no repeats
// sorted
// size ? 


// 1) recursion
// get median make root
// elms on the left => left tree items
// elms on the right => right tree items
// repeat the process for each side
// TC: n SC: H

// 2) iterative
// get median, add to the medianList
// iterate median list
// pick each slice from values, between the medians
// foreach slice pick a new median add to the medians list
// finish loop when median list size is equal to values list
// TC:n sc:n ???????


import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;


class Solution {

  class Node {
    int value;
    Node left, right;
    Boolean visited = false;
    
    public Node(int value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }

    public void printBF() {
      Queue q1 = new LinkedList<Node>();
      Queue q2 = new LinkedList<Node>();

      q1.add(this);

      while(q1.isEmpty() == false) {
        q2 = new LinkedList<Node>();

        while(q1.isEmpty() == false) {
          Node current = (Node) q1.remove();
          System.out.print(" "+ current.value + " ");

          if(current.left != null) {
            q2.add(current.left);
          }

          if(current.right != null) {
            q2.add(current.right);
          }
        }
        System.out.println();

        q1 = q2;
      }
    }

    public void printBFWithRelation() {
      Queue q1 = new LinkedList<Node>();
      Queue q2 = new LinkedList<Node>();
      int nonNullCount = 0;

      q1.add(this);

      while(q1.isEmpty() == false) {
        q2 = new LinkedList<Node>();

        while(q1.isEmpty() == false) {
          Node current = (Node) q1.remove();
          if(current == null) {
            System.out.print(" "+ "NULL" + " ");  
          } else {
            System.out.print(" "+ current.value + " ");

            q2.add(current.left);
            q2.add(current.right);  
          }
        }
        System.out.println();

        nonNullCount = 0;
        Iterator it = q2.iterator();
        while(it.hasNext()) {
          if(it.next() != null) {
            nonNullCount++;
          }
        }

        if(nonNullCount == 0) {
          q2.clear();
        }

        q1 = q2;
      }
    }

    public void printBFLinear() {
      Queue q1 = new LinkedList<Node>();

      q1.add(this);

      while(q1.isEmpty() == false) {
        Node current = (Node) q1.remove();
        System.out.print(" "+ current.value + " ");

        if(current.left != null) {
          q1.add(current.left);
        }

        if(current.right != null) {
          q1.add(current.right);
        }  
      }

      System.out.println();
    }

    public void printDF() {
      Stack st = new Stack();
      st.push(this);

      while(st.isEmpty() == false) {
        Node current = (Node) st.pop();
        System.out.print(" "+ current.value + " ");

        if(current.left != null) {
          st.push(current.left);
        }
        
        if(current.right != null) {
          st.push(current.right);
        }
      }
      System.out.println();
    }


  //       1
  //   3        5
  // 7   9   11    13

    // st {1}
    // path {1}

    // current = 1
    // st = 1,3
    // current = 3
    // st = 1,3,7
    // current = 7
    // st = 1,3,7
    // ++++++++137
    // st = 1,3

    public void printDFWithHistory() {
      Stack<Node> st = new Stack<Node>();
      ArrayList<Integer> path = new ArrayList<Integer>();
      st.push(this);
      path.add(this.value);
      this.visited = true;

      while (st.isEmpty() == false) {
        Node current = st.peek();
        
        if(current.left == null && current.right == null) {
          System.out.print("PATH: ");
          path.forEach((value) -> System.out.print("-> " + value));
          System.out.println();

          st.pop();
          path.remove(path.size() - 1);
          continue;
        }

        if(current.left != null && current.left.visited == false) {
          st.push(current.left);
          path.add(current.left.value);
          current.left.visited = true;
          continue;
        } 

        if(current.right != null && current.right.visited == false) {
          st.push(current.right);
          path.add(current.right.value);
          current.right.visited = true;
          continue;
        }

        st.pop();
        path.remove(path.size() - 1);
      }
    }
  }

  public static Node makeBST(int[] sortedNodes, int start, int end) {
    Solution s = new Solution();

    if(start > end) {
      return null;
    }
    int medianIndex = (start + end) / 2;
    int medianValue = sortedNodes[medianIndex];

    Node root = s.new Node(medianValue, null, null); // inst new node

    root.left = makeBST(sortedNodes, start, medianIndex - 1);
    root.right = makeBST(sortedNodes, medianIndex + 1, end);

    return root;
  }

  public static void main(String[] args) {
    int[] values = new int[]{1,3,5,7,9,11,13,15};
    Node root = makeBST(values, 0, values.length - 1);

    System.out.println("root1 BF");
    root.printBF();
    
    System.out.println("root1 BF with relation");
    root.printBFWithRelation();

    System.out.println("root1 BF Linear");
    root.printBFLinear();
    
    System.out.println("root1 DF");
    root.printDF();

    System.out.println("root1 DF with History");
    root.printDFWithHistory();

    Solution s = new Solution();
    Node root2 = s.new Node(1, null, null);
    Node c2 = s.new Node(2, null, null);
    Node c3 = s.new Node(3, null, null);
    Node c4 = s.new Node(4, null, null);
    Node c5 = s.new Node(5, null, null);
    Node c6 = s.new Node(6, null, null);

    root2.left = c2;
    c2.left = c3;
    c3.left = c4;
    c3.right = c5;
    c5.right = c6;
    
    System.out.println("root2 BF");
    root2.printBF();
    
    System.out.println("root2 BF with relation");
    root2.printBFWithRelation();

    System.out.println("root2 BF Linear");
    root2.printBFLinear();
    
    System.out.println("root2 DF");
    root2.printDF();

    System.out.println("root2 DF with History");
    root2.printDFWithHistory();
  }


  // values 1 - 3 - 5 - 7 - 9 - 11 - 13 // 7 items
  // medianIndexes = {}
  // lastItemIndex = 6
  // lastIndex = 0
  // newMedian = 3
  // medianIndexes = {3}
  // lastIndex = 0
  // current = 3
  // newMedian = 2
  // medianIndexes = {3,2}
  // lastIndex = 3
  // newMedian = 5
  // medianIndexes = {3,2,5}
  // lastIndex = 0
  

  // public static void makeBSTIT(int[] values) {

  //   if(values.isEmpty()) {
  //     return;
  //   }

  //   int[] medianIndexes = new ArrayList<int>();
  //   int lastItemIndex = (values.length - 1);
  //   int newMedian, lastIndex, lastUsed = 0;

  //   while(medianIndexes.size < values.length) {
  //     lastIndex = 0;
  //     for(int i = 0; i < medianIndexes.size; i++) {
  //       int current = medianIndexes[i];

  //       if(current - lastIndex > 1) {
  //         newMedian = getMedianIndex(lastIndex, current);
  //         medianIndexes.add(newMedian);
  //         lastIndex = current;
  //       }
  //     }

  //     if(lastItemIndex - lastIndex > 1) {
  //         newMedian = getMedianIndex(lastIndex, lastItemIndex);
  //         medianIndexes.add(newMedian);
  //       }
  //   }

  //   for(int i = 0; Math.pow(2, i) < medianIndexes.size; i++) {

  //     for(lastUsed; lastUsed < Math.pow(2, i); lastUsed++) {
  //       System.out.print(medianIndexes[lastUsed])
  //       System.out.print(" - ");
  //     }

  //     System.out.println();
  //   }

  // }

  // public static int getMedianIndex(int start, int end) {
  //   return (start + end + 1) / 2 ;
  // }
}




