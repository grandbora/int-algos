

// Implement an algorithm to find the kth to last element of a singly linked list.

// 1-2-3-4-5-6-null

// terminated on null
// fits in the memory
// elms are ints // assume can be arbitrary
// duplicates elms, doesn't matter
// ....

// k is int, and smaller than the list size !!!! doesn't have to be !!!!
// is k negative!!!!


// 1)
// go k elements further from head
// if reach null return null / throw ex
// iterate both runner and start until reached to the end
// the head is now at last kth elm
// TC: n SC: 1

// BCR n-k

// 2) collect the elms on an array
// iterate and collect in an array
// pick n-k th elm from the list
// TC: n SC: n

// 3) count!
// iterate to the end, 
// calculate length - k 
// iterate again
// TC: n SC: 1

// 4) recurse
// recursively iterate on the list, each recursion send back the number of items ahead
// on kth return the node
// TC: n SC: 1


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


// go k elements further from head
// if reach null return null / throw ex
// iterate both runner and start until reached to the end
// the head is now at last kth elm

  // key = 1-2-3-4-5-6-null
  // k = 4
  // runner = 5-6-null
  
  // key = 2-3-4-5-6-null
  // runner = 6-null

  // ret 2-3-4-5-6-null

  
// 1-2-3-4-5-6-null , 4

// 2-3-4-5-6-null

  public static Node lastKth(Node key, int k) throws Exception {
    Node runner = getElmK(key, k); // may throw!

    while(runner != null) {
      key = key.next;
      runner = runner.next;
    }

    return key;
  }

  // head 1-2-3-4-5-6-null
  // k = 4
  // i = 0
  // head = 2-3-4-5-6-null

  // i = 1
  // head = 3-4-5-6-null

  // i = 2
  // head = 4-5-6-null

  // i = 3
  // head = 5-6-null

  // ret 5-6-null
  public static Node getElmK(Node head, int k) throws Exception {
    for(int i = 0; i < k; i++) {
      if(head == null) throw new Exception("k is bigger than the list size");
      head = head.next;
    }

    return head;
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
    // printEx(null);
    // printEx(n1);
    // printEx(n2);
    // printEx(n3);
    // printEx(n4);
    // printEx(n5);
    // printEx(n6);
    printEx(n7, 0);
    printEx(n7, 1);
    printEx(n7, 2);
    printEx(n7, 3);
    printEx(n7, 4);
    printEx(n7, 5);
    printEx(n7, 6);
    printEx(n7, 7);
    printEx(n7, 8);
  }

  public static void printEx(Node in, int k) {
    System.out.println("in : " + k);
    printNode(in);
    System.out.println("out:");
    try {
      System.out.println("1:");
      printNode(lastKth(in, k));
    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println("2:");
    printNode(lastKth2(in, k));
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

  class LoopResult{
    int aheadCount;
    Node currentNode;

    public LoopResult(int aheadCount, Node currentNode) {
      this.aheadCount = aheadCount;
      this.currentNode = currentNode;
    }
  }

  // key 1-2-null
  // k = 1

  public static Node lastKth2(Node key, int k) {
    LoopResult res = loopLastKth2(key, k);
    return res.currentNode;
  }

  // key 1-2-null
  // k = 1
  // {1, 2-null}
  // {2, 2-null}


  // key 2-null
  // k = 1
  // {0, null}
  // node = 2-null
  // {1, 2-null}

  // key null
  // k = 1
  // {0, null}
  private static LoopResult loopLastKth2(Node key, int k) {
    Solution s = new Solution();
    Node node = null;
    int nextCount = 0;

    if(key == null) {
      nextCount = 0;
    } else {
      LoopResult res = loopLastKth2(key.next, k);
      nextCount = res.aheadCount + 1;
      node = res.currentNode;
    }

    if(k == nextCount) {
      node = key;
    }

    return s.new LoopResult(nextCount, node);
  }
}





