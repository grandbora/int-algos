// Given a directed graph, design an algorithm to find
// out whether there is a route between two nodes.


// 1 ---- 2 ----- 3
//        -
//        ----- 4 ---- 1

// cyclic ? true
// leaves have empt collection as children
// graph stored in the memory


// 1) DFT
// TC : n SC : maxhops

// B) BFT
// TC : n SC : maxhops




// 1 ---- 2 ----- 3
//        -
//        ----- 4 ---- 1

// 1->3
// history = {}
// history = {1}
// filteredChildren 2
// ret true

// 2->3
// history = {1}
// history = {1,2}
// filteredChildren 3,4
// ret true


// 1 ---- 2 ----- 5
//        -
//        ----- 4 ---- 1

// 1->3
// history = {}
// history = {1}
// filteredChildren 2
// isConnected 2->3
// ret false

// 2->3
// history = {1}
// history = {1,2}
// filteredChildren {5,4}
// isConnected 5->3
// isConnected 4->3
// ret false

// 5->3
// history = {1,2}
// history = {1,2,5}
// filteredChildren {} 
// ret false

// 4->3
// history = {1,2}
// history = {1,2,4}
// filteredChildren {}
// ret false

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;


class Solution {

  public enum State {
    Unvisited, Visited, Visiting;
  }

  public class Node {
    int value;
    Node[] children;
    State state = State.Unvisited;

    public Node(int value, Node[] children){
      this.value = value;
      this.children = children;
    }

    public void setState(State value){
      this.state = value;
    }

    @Override
    public String toString() {
        return "Node:" + this.value;
    }
  }


  public static Boolean isConnected(Node start, Node end, ArrayList<Node> history) {
    history.add(start);
    System.out.println(start.value);
    System.out.println(Arrays.toString(history.toArray()));
    
    ArrayList<Node> filteredChildren = filterChildren(start.children, history); // remove children that exist in hist

    if(filteredChildren.contains(end)) {
      return true;
    }

    for(int i = 0; i < filteredChildren.size(); i++) {
      if(isConnected(filteredChildren.get(i), end, history)) {
        return true;
      }
    }

    return false;
  }

  public static ArrayList<Node> filterChildren(Node[] children, ArrayList<Node> history) {
    ArrayList<Node> filteredChildren = new ArrayList<Node>();

    for (int i = 0; i < children.length; i++) {
      if(history.contains(children[i]) == false) {
        filteredChildren.add(children[i]);
      }
    }

    return filteredChildren;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    Node node1 = s.new Node(1, new Node[1]);
    Node node2 = s.new Node(2, new Node[2]);
    Node node3 = s.new Node(3, new Node[1]);
    Node node4 = s.new Node(4, new Node[2]);
    Node node5 = s.new Node(5, new Node[1]);
    Node node6 = s.new Node(6, new Node[2]);

    node1.children[0] = node2;
    node2.children[0] = node3;
    node2.children[1] = node4;
    node3.children[0] = node6;
    node4.children[0] = node5;
    node4.children[1] = node3;
    node5.children[0] = node1;
    node6.children[0] = node1;
    node6.children[1] = node5;

    Node[] allNodes = new Node[]{node1,node2,node3,node4,node5,node6};
    

    Node x = s.new Node(999, new Node[0]);

    ArrayList<Node> history = new ArrayList<Node>();
    System.out.println(isConnected(node1, x, history));

    System.out.println(isConnectedDFS(node1, x));
    
    for(Node tmp: allNodes) {
      tmp.setState(State.Unvisited);
    }

    System.out.println(isConnectedBFS(node1, x));

//                      ------ 5
//                      -
// 1 ---- 2 ----- 3 --- 6 ---- 1   
//        -
//        ----- 4 ---- 5 ---- 1
//              -
//              ----3 
  }



// 1 ---- 2 ----- 3 --- 1
//        -
//        ----- 4 ---- 5 ---- 1
//              _
//              --------6 ---- 1   
  // 1-> 5
  // st = {1}
  // current = 1
  // st = {}
  // 1.visited = true
  // st = {2}
  // current = 2
  // st = {}
  // 2.visited = true
  // st = {3}
  // st = {3,4}
  // current = 4
  // st = {3}
  // 4.visited = true
  // st = {3,5}
  // st = {3,5,6}
  // current = 6
  // st = {3,5}
  // 6.visited = true
  // current = 5
  // st = {3}
  // 5.visited = true


  public static Boolean isConnectedDFS(Node start, Node end) {
    Stack st = new Stack();
    st.push(start);
    start.setState(State.Visited);

    while(st.empty() == false) {
      Node current = (Node) st.pop();      
      System.out.println(current);
      
      if(current == end) {
        return true;
      }

      for(Node child: current.children) {
        if(child.state == State.Unvisited) {
          child.setState(State.Visited);
          st.push(child);
        }
      }
    }

    return false;
  }

//                      ------ 5
//                      -
// 1 ---- 2 ----- 3 --- 6 ---- 1   
//        -
//        ----- 4 ---- 5 ---- 1


  // qu = {1}
  // current = 1
  // qu = {}
  // 1.visited = true
  // qu = {2}
  // current = 2
  // qu = {}
  // 2.visited = true
  // qu = {3}
  // qu = {3,4}
  // current = 3
  // qu = {4}
  // 3.visited = true
  // qu = {4,6}
  // current = 4
  // qu = {6}
  // 4.visited = true
  // qu = {6,5}
  // current = 6
  // qu = {5}
  // 6.visited = true
  // qu = {5,5}
  

//   0
// 1  2
// 3 4 5

  public static Boolean isConnectedBFS(Node start, Node end) {
    Queue<Node> queue = new LinkedList<Node>();
    start.setState(State.Visited);
    queue.add(start);

    while(queue.isEmpty() == false) {
      Node current = queue.remove();
      System.out.println(current);
      
      if(current == end) {
        return true;
      }

      for(Node child: current.children) {
        if(child.state != State.Visited) {
          child.setState(State.Visited);
          queue.add(child);
        }
      }
    }

    return false;
  }
}



