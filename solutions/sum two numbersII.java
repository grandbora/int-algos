import java.lang.Math;
import java.util.*;

/*
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

1126
0098

6->2->1->1
8->9


implement sum
with carry
for null values use 0
keep in mind the last carry

* return value is also a reversed linked list of the result number
* decimal numbers
* non negative


1126
0098

6->2->1->1
8->9

*/


class Solution {

    class Node {
        Node next;
        int value; //pos or 0
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        Node n1 = s.new Node();
        n1.value = 6;
        n1.next = s.new Node();
        n1.next.value = 2;
        n1.next.next = s.new Node();
        n1.next.next.value = 9;

        Node n2 = s.new Node();
        n2.value = 8;
        n2.next = s.new Node();
        n2.next.value = 9;

        Node res = s.addLL(n1,n2);

        while(res != null) {
            System.out.println(res.value);
            res = res.next;
        }
    }

    /*
     926
     098
    +
    1024

    n1: 6->2->9, 2->9->null, 9->null
    n2: 8->9, 9->null

    ans: 4-> null, 4 -> 2 -> 0-> 1-> null, 
    carry: 1 , 1 , 1
    current: 4-> null, 4 -> 2 -> null, 2 -> null, 2 -> 0->null, 0->1->null
    next: 0 -> null, 2 -> null, 0 -> null, 0->null
    n1NVal: 0, 2, 0 , 9 
    n2NVal: 0, 9, 0

    */

    public Node addLL(Node n1, Node n2) {
        if(n1 == null || n1.value == 0) {
            return n2;
        }

        if(n2 == null || n2.value == 0) {
            return n1;
        }

        Node ans = new Node();
        ans.value = (n1.value + n2.value) % 10;
        int carry = (n1.value + n2.value) / 10;

        Node current = ans;
        while(n1.next != null || n2.next != null) {
            
            Node next = new Node();
            
            int n1NVal = 0;
            if(n1.next != null) {
                n1NVal = n1.next.value;
                n1 = n1.next;
            }

            int n2NVal = 0;
            if(n2.next != null) {
                n2NVal = n2.next.value;
                n2 = n2.next;
            }

            next.value = (n1NVal + n2NVal + carry) % 10;
            carry = (n1NVal + n2NVal + carry) / 10;

            current.next = next;
            current = next;
        }

        if(carry != 0) {
           current.next = new Node(); 
           current.next.value = carry;
        }

        return ans;
    }


    public Node addLL2(Node n1, Node n2) {

        if(n1 == null || n1.value == 0) {
            return n2;
        }

        if(n2 == null || n2.value == 0) {
            return n1;
        }

        Node ans = new Node();
        int carry = 0;
        Node current = ans;
        
        while(n1 != null || n2 != null) {
            int n1Val = 0;
            if(n1 != null) {
                n1Val = n1.value;
                n1 = n1.next;
            }

            int n2Val = 0;
            if(n2 != null) {
                n2Val = n2.value;
                n2 = n2.next;
            }

            current.value = (n1Val + n2Val + carry) % 10;
            carry = (n1Val + n2Val + carry) / 10;

            current.next = new Node();
            current = current.next;
        }

        if(carry != 0) {
           current.value = carry;
        } 

        return ans;
    }

    
}