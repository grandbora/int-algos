
import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

import java.io.*;
import java.util.*;

class Solution {
  
  static int numOfPathsToDest(int n) {
    HashMap<String, Integer> cache = new HashMap<String, Integer>();
    return numOfPathsToDest(n, 1, 0, cache);
  }
  
  static int numOfPathsToDest(int n, int stepsTakenToUp, int stepsTakenToRight, HashMap<String, Integer> cache) {
    
    String current = String.format("%s,%s",stepsTakenToUp,stepsTakenToRight);
    if(cache.containsKey(current)){
      return cache.get(current);
    }
    
    if(stepsTakenToUp == n - 1 &&  stepsTakenToRight == n - 2) {
      return 1;  
    } else {
      Boolean canGoUp = true;
      if(stepsTakenToUp == n - 1) {
        canGoUp = false;
      }
      
      Boolean canGoRight = true;
      if(stepsTakenToRight == n - 1) {
        canGoRight = false;
      }
      
      // diagonal condition
      if(!(stepsTakenToUp - 1 > stepsTakenToRight && canGoRight)) {
        canGoRight = false ;
      }
      
      int upPaths = 0;
      if (canGoUp) {
        upPaths = numOfPathsToDest(n, stepsTakenToUp + 1, stepsTakenToRight, cache);
        cache.put(String.format("%s,%s",stepsTakenToUp + 1,stepsTakenToRight), upPaths);
      }
      
      int rightPaths = 0;
      if (canGoRight) {
        rightPaths = numOfPathsToDest(n, stepsTakenToUp, stepsTakenToRight + 1, cache);
        cache.put(String.format("%s,%s",stepsTakenToUp,stepsTakenToRight + 1), rightPaths);
      }

      return upPaths + rightPaths;
    }
  }
  
  public static void main(String[] args) {
    System.out.println(numOfPathsToDest(2));
    System.out.println("===");
    System.out.println(numOfPathsToDest(3));
    System.out.println("===");
    System.out.println(numOfPathsToDest(4));
    System.out.println("===");
    System.out.println(numOfPathsToDest(5));
    System.out.println("===");
    System.out.println(numOfPathsToDest(6));
    System.out.println("===");
    System.out.println(numOfPathsToDest(7));
    System.out.println("===");
  }

}