import java.lang.Math;
import java.util.*;

/*

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3

begin to intersect at node c1.


in: node1, node 2
out: node3

a1, b1
c1


   a1 a2 c1 c2 
b1 b2 b3 c1 c2


1) BF
double loop
TC: m*n SC:1

2) Loop both sep.
loop short one, collect in a set
loop longer one check for the first match in the set
TC: m + n SC: m

3)loop backwards
reverse linked lists
loop both reversed stop at first divergent
count how many are common

reverse any of the reversed Linked list, up to count elements
    
TC:m+n SC:1




*/


class Solution {


    public static void main(String[] args) {

    }

// edge cases
    // b contains whole a
    // no common

/*

abcdef
xydef
a = fedcba, edcba, dcba, cba
b = fedyx, edyx, dyx, yx
lastnode = null, fedcba, edcba,dcba

stack = fedcba, edcba,dcba

head = dcba, d, dedcba, efedcba, fedcba, f
next = edcba, fedcba
resp = def

*/

    public Node findCommon(Node a, Node b) {
        if(a == null || b == null) {
            return null;
        }

        a = reverse(a, -1);
        b = reverse(b, -1);
        int common = 0

        while(a == b) {
            common++;
            a = a.next;
            b = b.next;
        }

        if(common == 0) {
            return null;
        }

        return reverse(a, common);
    }

/*
// a - b - c

cur = abc , a, ba, cba
next = bc, ba, c, cba, null
tmp = c, null

*/
    // private Node reverse(Node n, int count) {

    //     if(count == -1) {
    //         count = Integer.MAX_VALUE;
    //     }

    //     Node current = n;
    //     Node next = n.next;
    //     current.next = null;

    //     while(next != null || count == 0) {
    //         count--;
    //         Node tmp = next.next;
    //         next.next = current;

    //         current = next;
    //         next = tmp;
    //     }

    //     return current;
    // }


/*
n = abcd
n1 abcd, a, ba, cba, dcba
n2 bcd, ba, cd, cba, dcba, null
n3 cd, d, null

n = abcd count = 3 // exp: cba

count 3, 2, 1
n1 abcd, a, ba, cba
n2 bcd, ba, cd, cba
n3 cd, d
*/

    private Node reverse(Node n, int count) {

        if(count == -1) {
            count = Integer.MAX_VALUE;
        }

        Node n1 = n;
        Node n2 = n1.next;
        n1.next = null;

        while(n2 != null || count > 1) {
            count--;
            Node n3 = n2.next;

            n2.next = n1;

            n1 = n2;
            n2 = n3;
        }

        return n1;
    }

/*
    n1
    n2
    n3

    while n2
    n2.next = n1
    n1 = n2
    n2 = n3
    n3 = n3.next

    */
}