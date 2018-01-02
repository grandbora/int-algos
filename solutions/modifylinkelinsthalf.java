

/*
1)multiple pointers
splithafl
reverse 2nd half
concurrent iterate, modify
reverse 2nd half
concat
TC: N SC: 1

2 -> 9 -> 8 -> 12 -> 7 -> 10

2 -> 9 -> 8  
12 -> 7 -> 10
12 <- 7 <- 10

2 -> 9 -> 8  
10 -> 7 -> 12
-8 -> 2 -> -4 -> 12 <- 7 <- 10

null checks
walkthrough
unit tests
edgecases?
1elm
2elm
*/

class Solution {

    class Node{
        int value;
        Node next = null;

        public Node(int v){
            this.value = v;
        }

        @Override
        public String toString(){
            String pre = " N:" + value;
            if(next == null) {
                return pre;
            } else {
                return pre + next.toString();
            }
        }
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println("=========");

        Node head = s.new Node(2);
        Node n1 = s.new Node(1);
        Node n2 = s.new Node(2);
        Node n3 = s.new Node(5);
        Node n4 = s.new Node(-3);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println(head);
        s.modify(head);
        System.out.println(head);
    }


    public void modify(Node head) {

        if (head == null || head.next == null) {
            return; //return err; // ****
        }

        Node secondHead = getSecondHead(head); // 2 -> 9 -> 8 -> 12 -> 7 -> 10 // 12
        Node reverseSecondHead = reverse(secondHead); // 2 -> 9 -> 8 -> 12 <- 7 <- 10 // 10
        
        //modify
        Node current1 = head;
        Node current2 = reverseSecondHead;
        while (current2 != null) {

            current1.value = current1.value - current2.value;

            current1 = current1.next;
            current2 = current2.next;
        }

        reverse(reverseSecondHead);
    }

    private Node getSecondHead(Node head) {
        int length = 1;
        Node current = head;
        while (current.next != null) {
            current = current.next;
            length++;
        }

        int limit = (length - length / 2);
        for (int i = 0; i < limit; i++) {
            head = head.next;
        }
        return head;
    }

    private Node reverse(Node head) {
        Node next = head.next;
        head.next = null;
        while (next != null) {
            Node tmp = next.next;
            next.next = head;
            head = next;
            next = tmp;
        }

        return head;
    }
}
