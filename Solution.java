import java.lang.Math;
import java.util.*;

import java.io.*;
import java.util.*;


class Solution {
 
  static class Node {
    int key;
    Node left;
    Node right; 
    Node parent;
    
    Node(int key) {
      this.key = key;
      left = null;
      right = null;
      parent = null;
    }
  }
  
  
  /*
  
  num 
  
  root
  
   cases 
   - equality
   -  when to return -1
  
       20
  9
 5  18
 ==> 9
 
        20
  18
 5  19
 ==> 5
       20
   9
 5   16
====> 16

       20
   17
 5   18
====> 16



  comp root to the num
   if num is greater, 
     move right 
      if no right => return that node
   else (smaller or equal)
     move left
      if no left => 
       check the parent 
        if less return
        if not return -1  
  */
  
               // keep track of max seen so far
             // start from null
             // 
             //  15
              //        30
             //     20

  
  static class BinarySearchTree {
    
    Node root;
    
    boolean maxFound = false;
    int maxSofar = -1;
    
    int findLargestSmallerKey(int num) {
      
      if(num > root.key) {
        
        maxFound = true;
        maxSofar = root.key;
          
        if(root.right == null) {
          return root.key;
        }
        
        root = root.right;
        return findLargestSmallerKey(num);
        
      } else { // smaller or eq
        
        if(root.left == null) {
          
          if(maxFound) {
           return maxSofar; 
          } else {
            return -1;
          }          
        } else {
           root = root.left;
           return findLargestSmallerKey(num);
        }
      }      
    }
    
             
    
    //  inserts a new node with the given number in the
    //  correct place in the tree
    void insert(int key) {
      
      // 1) If the tree is empty, create the root
      if(this.root == null) {
        this.root = new Node(key);
        return;
      }
      
      // 2) Otherwise, create a node with the key
      //    and traverse down the tree to find where to
      //    to insert the new node
      Node currentNode = this.root;
      Node newNode = new Node(key); 
      
      while(currentNode != null) {
        if(key < currentNode.key) {
          if(currentNode.left == null) {
            currentNode.left = newNode;
            newNode.parent = currentNode;
            break;
          } else {
            currentNode = currentNode.left;
          }
        } else {
          if(currentNode.right == null) {
            currentNode.right = newNode;
            newNode.parent = currentNode;
            break;
          } else {
            currentNode = currentNode.right;
          }
        }
      }
    }
  }

  /*********************************************
   * Driver program to test above function     *
   *********************************************/ 
   
   public static void main(String[] args) {
     
     // Create a Binary Search Tree
     BinarySearchTree bst = new BinarySearchTree();
     bst.insert(20);
     bst.insert(9);
     bst.insert(25);
     bst.insert(5);
     bst.insert(12);
     bst.insert(11);
     bst.insert(14);
     
     int result = bst.findLargestSmallerKey(17);
     System.out.println("Largest smaller number is " + result);
    
  }
}