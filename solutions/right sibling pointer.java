 
 
// 1)BFT
// traverse each level, add to a queue
// iterate queue, add pointers
TC: n SC: n
 
 
 
1
2 3
4   56
 
 
// node = 1
// q1 {}
// q2 {}
// previous = null
 
// q1 {1}
// q2 {}
// previous = null
// current = 1
// q1 {}
// q2 {2}
// q2 {2,3}
// q1 {2,3}
 
// q2 {}
// previous = null
// current = 2
// q1 {3}
// q2 {4}
// previous = 2
 
// current = 3
// q1 {}
// q2 {4,5}
// q2 {4,5,6}
// 2 => 3 +++++++++++++ 
public void addRightPointer(Node node) {
 
Queue q1 = new LinkedList<Node>();
Queue q2 = new LinkedList<Node>();
Node previous = null;
 
q1.add(node);
 
while(q1.isEmpty == false) {
q2 = new LinkedList<Node>();
previous = null;
 
while(q1.isEmpty == false) {
  Node current = q1.remove();
 
//collect next level
if(current.left != null) {
  q2.add(current.left)
}
 
if(current.right != null) {
  q2.add(current.right)
}
 
if(previous != null) {
previous.nextSibling = current;
}
 
previous = current;
}
 
q1 = q2;
}
}
 
