import java.lang.Math;
import java.util.*;

/*

keep counter of R and D
loop until either is 0


create linked list, 
and each round one senatore bans the nearest opposite senator
recude R&D counter


TC: n*n SC: n

=============

create two queues
each holds R&D senator indexes

loop until either is empty
 each loop is a round
 in each round ban nearest one by one 
 the remaining senators are put back in the queue again

TC: n SC:n 

====================
create two linked list
each holds R&D senator indexes

loop until either is empty
 each loop get first elms, lower index bans the other
 keep a pointer to the next elm in each list
   ---if pointer elm is banned pointer should advance

rdd

r dd
0 0
1 23

1 0
1 3

- 3

TC:n SC:n

*/

class Solution {


    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Start");

        System.out.println(s.getWinner("RRDRDDDRDRR"));

        System.out.println("end");
    }

    class Node {
        int value;
        Node next;

        public Node(int value) {
          this.value = value;
        }

        @Override public String toString(){
          String nextStr = "";
          if(next != null) {
            nextStr = next.toString();
          }
          return value + "-" + nextStr;
        }
    }

    public char getWinner(String senate) {

        Node radRoot = null;
        Node direRoot = null;

        Node radLast = null;
        Node direLast = null;

        for (int i = 0; i < senate.length(); i++) {
            char c = senate.charAt(i);
            if (c == 'R') {
                if (radRoot == null) {
                    radRoot = new Node(i);
                    radLast = radRoot;
                } else {
                    Node cur = new Node(i);
                    radLast.next = cur;
                    radLast = cur;
                }
            } else {
                if (direRoot == null) {
                    direRoot = new Node(i);
                    direLast = direRoot;
                } else {
                    Node cur = new Node(i);
                    direLast.next = cur;
                    direLast = cur;
                }
            }
        }

        System.out.println("R: " + radRoot.toString());
        System.out.println("D: " + direRoot.toString());
        //RDRDDDR
        //R: 0-2-6-
        //D: 1-3-4-5-
        // 026 R
        //  *       root
        //    *       pointer
        // 1345 D
        //   *       root
        //    *       pointer 

        while (radRoot != null && direRoot != null) {
            Node radPointer = radRoot;
            Node direPointer = direRoot;

            while (radPointer != null || direPointer != null) {
              System.out.println("R: " + radRoot.toString());
              System.out.println("D: " + direRoot.toString());

                if (radPointer == null) {
                    direPointer = direPointer.next;
                    radRoot = radRoot.next;
                    continue;
                }

                if (direPointer == null) {
                    radPointer = radPointer.next;
                    direRoot = direRoot.next;
                    continue;
                }

                int radInd = radPointer.value;
                int direInd = direPointer.value;
                
                if (radInd < direInd) {
                  radPointer = radPointer.next;
                  if(direPointer == direRoot) {
                    direPointer = direPointer.next;
                  }
                  direRoot = direRoot.next;
                } else {
                  direPointer = direPointer.next;
                  if(radPointer == radRoot){
                    radPointer = radPointer.next;
                  }
                  radRoot = radRoot.next;
                    
                }
            }
        }

        if (radRoot == null) {
            return 'D';
        } else {
            return 'R';
        }
    }


}


/*


radRoot = R1R2, R2
rPointer = R1, R2, null, R2, null

direRoot = D3D4D5, D4D5, D5, null
dPointer = D3, D4, D5, null, D5, null

radInd = 1, 2, 2
direInd = 3, 4, 5


*/


