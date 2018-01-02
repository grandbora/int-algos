// Given a binary tree, design an algorithm which creates a linked list of all the nodes at
// each depth (e.g., if you have a tree with depth D, you'll have D linked lists).

// given BT
// balanced? No, doesn't matter
// node {
// value
// Node left
// Node right
// } 


// 1)
// get root add to the queue
// until the queue is empty at the end
// process all the items in the queu
// add all of their children
// at each loop create the linkedList
// TC:n SC:n



import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;




class Solution {

  //       1
  //    3        5
  //  4   null  8     7 

  // q1 = {}
  // q2 = {}
  // result = {}

  // q1 = {1}
  // result = {{1}, }
  // q2 = {}
  // current = 1
  // q1 = {}
  // q2 = {3,5}
  // result = {{1}, {3,5}}
  // q1 = {3,5}
  // q2 = {}
  // current = 3
  // q1 = {5}
  // q2 = {4}
  // current = 5
  // q1 = {}
  // q2 = {4,8}
  // q2 = {4,8,7}
  // result = {{1}, {3,5}, {4,8,7}}
  // q1 = {4,8,7}
  // q2 = {}
  // current = 4
  // q1 = {8,7}
  // current = 8
  // q1 = {7}
  // current = 7
  // q1 = {}
  // q1 = {}



  public static ArrayList breadthFirstLink(Node root) {

    LinkedList<Node> q1 = new LinkedList<Node>();
    LinkedList<Node> q2 = new LinkedList<Node>();
    ArrayList result = new ArrayList();

    q1.add(root);
    result.add(q1.clone());

    while(q1.isEmpty() == false) {
      q2 = new LinkedList<Node>();
      
      while(q1.isEmpty() == false) {
        Node current = (Node) q1.remove();
        
        if(current.left != null) {
          q2.add(current.left);  
        }
        
        if(current.right != null) {
          q2.add(current.right);
        }
      }

      result.add(q2.clone());

      q1 = q2;
    }

    return result;
  }





public static void main(String[] args) {
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
    
    System.out.println("tree BF");
    root2.printBFWithRelation();
    
    System.out.println("The Answer (3-76ers)");

    ArrayList a = breadthFirstLink(root2);

    for(int i = 0; i< a.size(); i++) {
      LinkedList b = (LinkedList)a.get(i);
      while (b.isEmpty() == false){
        Node current = (Node) b.remove();
        System.out.print(" " + current.value);
      }
      System.out.println();
    }
    
  }










  class Node {
    int value;
    Node left, right;
    Boolean visited = false;
    
    public Node(int value, Node left, Node right) {
      this.value = value;
      this.left = left;
      this.right = right;
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

 
  }





}




