// Implement an algorithm to delete a node in the middle of a singly linked list
// , given only access to that node.



// int values ?? arbitrary
// end null
// size fits to memory
// singly linked
// sorted ? No


// 1-2-3-4-5-6-7->null

// given 4
// 3.next = 5

// n7 = Node(7, null)
// n6 = Node(6, n7)
// n5 = Node(5, n6)

// n6

 // a->b->c->d->e
 // a->b->d->e

// 1)
// iterate rest of the chain
// copy each item to previous
// TC: n SC:1

// a->b->c->d->e
// a->b->d->e

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
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

  // 2-3-4-5-6-7-null
  
  // node = 4-5-6-7-null
  // next = 5-6-7-null
  // node = 5-5-6-7-null
  // 2-3-5-6-7-null

  // node = 5-6-7-null
  // next = 6-7-null
  // node = 6-7-null
  // 2-3-5-6-7-null


  // 2-3-5-6-7-null
  
  public static void bombNode(Node node) {
    if (node == null || node.next == null) return;

    node.value = node.next.value;
    node.next = node.next.next;

    // while (node.next != null) {
    //   node.value = node.next.value;
      
    //   if(node.next.next == null){
    //     node.next = null;
    //   } else {
    //     node = node.next;
    //   }
    // }
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    Node n1 = s.new Node(0, null);
    Node n2 = s.new Node(1, n1);
    Node n3 = s.new Node(2, n2);
    Node n4 = s.new Node(3, n3);
    Node n5 = s.new Node(4, n4);
    Node n6 = s.new Node(5, n5);
    Node n7 = s.new Node(6, n6);

    printEx(n7, n2);
    printEx(n7, n7);
  }

  public static void printEx(Node head, Node in) {
    System.out.println("in : ");
    printNode(head);
    System.out.println("out:");
    System.out.println("bomb:" + in.value);
    bombNode(in);
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





