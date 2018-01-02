import java.lang.Math;
import java.util.*;

/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.


1)

traverse depth first, 
  for each node 
  save in order serialized value in a kv storage

loop kv, check if there are any duplicates


        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4


        1
       / \
      2   3

node: 1,2,1,3, 1
stack 1, 

kv: 2=>2, 3=>3, 1=> 213


         1
       /   \
      2     3
    5   6  7 



*/


class Solution {

  class Node {
    String value;
    Node left;
    Node right;
    Boolean visited = false;

    public Node(String value, Node left, Node right) {
      
      
      
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    
    Node nl = s.new Node("2", null, null);
    Node nxxx = s.new Node("3", null, null);
    
    Node n = s.new Node("1", nxxx, nl);

    
    System.out.println("RES:");
    System.out.println(Arrays.toString(s.getDups(n).toArray()));
  }


/*
        1
      2   2
    5  6  5  6
serMap = {5=>5, 6=>6 2=>526, 5=>5, 6=>6 2=>526}


dupSerial = {5, 6, 526}
 dupRoots= {5, 6, 2}

 key = 5, 6, 2, 5, 6, 2
 serial = 5, 6, 526, 5, 6, 526

*/
  public List<String> getDups(Node n) {

    Map<Node, String> serMap = serialize(n);
    Set<String> dupSerial = new HashSet<String>();
    List<String> dupRoots = new ArrayList<String>();

    for(Node key: serMap.keySet()) {
      String serial = serMap.get(key);



      if(dupSerial.contains(serial)) {
        dupRoots.add(key.value);
      } else {
        dupSerial.add(serial);
      }
    }
    
    return dupRoots;
  }

  /*

  smoke test:
        1
       /      \
      2         3
    5* 6*    7 


results = {5=>5,6=>6,2=>526}
st = (1)
cur = 1,2,5,2,6,2,1
serial="526"


  */

  private Map<Node, String> serialize(Node n) {
    Map<Node,String> results = new HashMap();
    Stack<Node> st = new Stack<Node>();

    st.push(n);

    while(st.isEmpty() == false) {
      Node cur = st.peek();
      


      if(cur.left == null && cur.right == null) { // leaf
        
        
        

        results.put(cur, cur.value);
        cur.visited = true;
        st.pop();
        continue;
      }

      if(cur.left !=null && cur.left.visited == false) {
        st.push(cur.left);
        continue;
      }

      

      if(cur.right !=null && cur.right.visited == false) {
        st.push(cur.right);
        continue;
      }

      // generate cur serial & pop
      StringBuilder serial = new StringBuilder();
      if(cur.left != null) {
        serial.append(results.get(cur.left));
      }
      
      serial.append(cur.value);
      
      if(cur.right != null) {
        serial.append(results.get(cur.right));
      }
      

      
      
      


      results.put(cur, serial.toString());
      st.pop();
    }

    return results;
  }

}


/*
TODO: 
input validation
edge cases

*/

