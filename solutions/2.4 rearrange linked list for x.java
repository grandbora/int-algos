 // Write code to partition a linked list around a value x, 
 // such that all nodes less than x come before al nodes greater than or equal to x.

// given singly linked list
// repetitions are possible
// end is null value
// vals are int // any comparable would work
// fits in the memory
//  list is not sorted :)

// 1-3-5-4-5-2-6-3-7-8-null
// x = 4
// 1-3-2-3-4-5-5-6-7-8-null

// BCR TC:n

// 1)
// iterate the list
// remove and store the elements that are greater than x
// once at the end of the list insert all removed
// TC:n SC:n

// x = 4
// 1-3-5-4-5-2-6-3-7-8-null
// 5
// 1-3-4-5-2-6-3-7-8-null
// 5-4-5
// 1-3-2-6-3-7-8-null
// 5-4-5-6
// 1-3-2-3-7-8-null
// 5-4-5-6-7-8-
// 1-3-2-3-null
// 1-3-2-3-5-4-5-6-7-8-null

// 2)
// sort the list!
// TC:n^2 SC:1

// 3) 
// x = 4
// 1-3-5-4-5-2-6-3-7-8-null
// 


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


// iterate the list
// remove and store the elements that are greater than x
// once at the end of the list insert all removed
// TC:n SC:n


  // T1
  // threshold = 2
  // 3-1-5-null
  // UNHANDLED EDGE CASE, 1st item is greater than threshold

  // T2
  // threshold = 4
  // 1-3-5-4-5-2-6-null

  // st {}
  // head = 3-5-4-5-2-6-null

  // st {5}
  // head = 3-4-5-2-6-null

  // st {5,4}
  // head = 3-5-2-6-null

  // st {5,4,5}
  // head = 3-2-6-null

  // st {5,4,5}
  // head = 2-6-null

  // st {5,4,5,6}
  // head = 2-null

  // st {5,4,5,6}
  // head = 2-null

  // st {5,4,5}
  // head = 2-6-xxx

  // st {5,4,5}
  // head = 6-5-xxx

  // st {5,4}
  // head = 5-4-xxx

  // st {5}
  // head = 4-5-xxx

  // st {}
  // head = 5-null


  public static void rearrange(Node head, int threshold) {
    Solution s = new Solution();
    Stack st = new Stack();
    Node last = null;

    while(head != null) {
      
      if(head.value >= threshold) {
        st.push(head.value);
        head.value = head.next.value;
        head.next = head.next.next;
      } else {
        last = head;
        head = head.next;
      }      
    }

    while(st.empty() == false) {
      last.next = s.new Node((int) st.pop(), null);
      last = last.next;
    }
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    Node n1 = s.new Node(10, null);
    Node n2 = s.new Node(12, n1);
    Node n3 = s.new Node(32, n2);
    Node n4 = s.new Node(34, n3);
    Node n5 = s.new Node(44, n4);
    Node n6 = s.new Node(15, n5);
    Node n7 = s.new Node(56, n6);

    printEx(n7, 30);
  }

  public static void printEx(Node head, int in) {
    System.out.println("in : ");
    printNode(head);
    System.out.println("out:");
    System.out.println("t:" + in);
    rearrange(head,in);
    printNode(head);
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





