// You have two numbers represented by a linked list, 
// where each node contains a singledigit.
// The digits are stored in reverse order,such that the 1's digit is at the head of the list. 
// Write a function that adds the two numbers and returns the sum as a linked list.


// 76
// 6->7->null

// 3451
// 1->5->4->3->null

// 1->6->X->X->null

// end is null
// vals are int, representing digits
// no decimals!! ?? // Assumption
// no negative numbers?? // Assumption
// two positive, natural number 
// both arg fits in the memory

// n: number of digits, max

// 1)
// sum them up digit by digit
// TC: n SC:1

// 2)
// parse to int
// sum
// parse back to list
// TC: n SC:1


import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;

class Solution {

  public class Node {
    int value;
    Node next;

    public Node(int value, Node next){
      this.value = value;
      this.next = next;
    }
  }


// 76
// 6->7->null

// 3451
// 1->5->4->3->null

// 1->6->X->X->null

  // in1 = 6->7->null
  // in2 = 1->4->9->null

  // carryOver = 0 
  // result,resultStart = null;

  // d1 = 6 
  // d2 = 1 
  // rawSum = 7
  // sum = 7
  // carryOver = 0

  // result = 7-null
  // resultStart = 7-null

  // in1 = 7->null
  // in2 = 4->9->null
  // d1 = 7
  // d2 = 4
  // rawSum = 11
  // sum = 1
  // carryOver = 1
  // result = 7-1-null

  // in1 = null
  // in2 = 9->null
  // d1 = 0
  // d2 = 9
  // rawSum = 10
  // sum = 0
  // carryOver = 1
  // result = 7-1-0-null

  // in1 = null
  // in2 = null

  // carryOver = 1
  // result = 7-1-0-1-null
  // resultStart = 7-...

  // 1017
  //   76
  //  941
  public static Node sum(Node in1, Node in2) {
    Solution s = new Solution();

    int carryOver = 0, sum, rawSum;
    Node result = null ,resultStart = null;

    while((in1 != null) || (in2 != null)) {
      
      int d1 = 0;
      if(in1 != null) {
        d1 = in1.value;
      }
      
      int d2 = 0;
      if(in2 != null) {
        d2 = in2.value;
      }

      rawSum = d1 + d2 + carryOver;
      sum = rawSum % 10;
      carryOver = rawSum / 10;

      if(result == null){
        result = s.new Node(sum, null);
        resultStart = result;
      } else {
        result.next = s.new Node(sum, null);
        result = result.next;
      }

      in1 = in1 == null ? null : in1.next;
      in2 = in2 == null ? null : in2.next;
    }

    if(carryOver > 0) {
      result.next = s.new Node(carryOver, null);
    }

    return resultStart;
  }

    public static void main(String[] args) {
    Solution s = new Solution();
    Node n1 = s.new Node(9, null);
    Node n2 = s.new Node(6, n1);
    Node n3 = s.new Node(2, n2);
    Node n4 = s.new Node(9, null);
    Node n5 = s.new Node(4, n4);
    Node n6 = s.new Node(8, n5);
    Node n7 = s.new Node(6, n6);

    printEx(n7, n3);
  }

  public static void printEx(Node in1, Node in2) {
    System.out.println("in : ");
    printNode(in1);
    printNode(in2);
    System.out.println("out:");
    printNode(sum(in1,in2));
    System.out.println();
  }

  public static void printNode(Node in) {
    if(in == null) {
      System.out.println("null");
      return;
    }
    System.out.print(in.value);
    System.out.print("->");
    printNode(in.next);
  }
}





