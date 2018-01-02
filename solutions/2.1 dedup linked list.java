
// FOLLOW UP
// How would you solve this problem if a temporary buffer is not allowed?


// Write code to remove duplicates from an unsorted linked list. 

// 1-2-1-3-4-1-4-4-null

// end of the chain is null
// function receives head
// access via .next
// type? arbitrary assume int

// size ? matter? not as long as we don't run out of memory // SC

// repeated items expected
// which item to remove ? first stays or the last stays? Assume first

// not sorted
// assume we want to keep the order
// ...

// n : number of elms


// 1) 
// iterate the list & create a map
// for each item check if the item is in the map
// if not update
// if exists remove the node
// TC: n  SC: n

// 1-2-1-3-4-1-4-4-null

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.HashMap;

class Solution {

  // 1-2-1-4-null
  // head 1

  // current 1
  // acc {1}
  // next = null

  // next = 2
  // acc {1,2}
  // current = 2

  // next = 1
  // current = 2-4-null

  // next = 4
  // acc {1,2,4}
  // current = 4

  // 1-2-4-null


  public static void dedup(Node head) {
    if(head == null) return;

    Node current = head;
    HashMap acc = new HashMap(); // import
    acc.put(current.value, true);
    Node next = null;
    
    while (current.next != null) {
      next = current.next;

      if(acc.containsKey(next.value)) {
        current.next = next.next;
      } else {
        acc.put(next.value, true);
        current = next;
      }
    }
  }

  public class Node {

    public Node(int value, Node next){
      this.value = value;
      this.next = next;
    }
    
    public int value = 1;

    public Node next = null;
    
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    Node n1 = s.new Node(1, null);
    Node n2 = s.new Node(2, n1);
    Node n3 = s.new Node(1, n2);
    Node n4 = s.new Node(3, n3);
    Node n5 = s.new Node(4, n4);
    Node n6 = s.new Node(1, n5);
    Node n7 = s.new Node(4, n6);
    // printEx(null);
    // printEx(n1);
    // printEx(n2);
    // printEx(n3);
    // printEx(n4);
    // printEx(n5);
    // printEx(n6);
    printEx(n7);

    // 1-2-1-3-4-1-4-4-null
  }

  public static void printEx(Node in) {
    System.out.println("in:");
    printNode(in);
    System.out.println();
    System.out.println("out:");
    dedup2(in);
    printNode(in);
    System.out.println();
    System.out.println();
  }

  public static void printNode(Node in) {
    if(in == null) {
      System.out.print("null");
      return;
    }
    System.out.print(in.value);
    System.out.print("->");
    printNode(in.next);
  }

  // iterate list
  // for each node iterate rest of the chain remove dups
  // TC:n^2 SC:1

  // head: 1-2-2-1-null
  // current = 1-2-2-1-null
  // removeFromChain key = 1 head = 1-2-2-1-null
  // current = 1-2-2-null

  // current = 2-2-null
  // removeFromChain key = 2 head = 2-2-null
  // current = 1-2-null

  public static void dedup2(Node current) {
    if(current == null) return;
    while (current.next != null) {
      removeFromChain(current.value, current);
      current = current.next;
    }
  }

  // key = 1 head = 1-2-2-1-null
  // current 1-2-2-1-null
  // next = 2-2-1-null

  // current 2-2-1-null
  // next = 2-1-null

  // current 2-1-null
  // next = 1-null
  // current 2-null

  // ========================

  // key = 2 head = 2-2-null
  // current = 2-2-null
  // next = 2-null
  // current = 2-null

  public static void removeFromChain(int key, Node current) {
    while(current.next != null) {
      Node next = current.next;

      if(next != null && next.value == key) {
        current.next = next.next;
      } else {
        current = next;
      }
    }
  }
}


// TODO IMPLEMENT THE RUNNER APPROACH! :PIZZA:










