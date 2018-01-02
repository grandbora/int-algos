/* 
ring buffer rate limit
*/


class Node {
  public int timestamp; // epochtime
  public Node next;
}


// RateLimit to 10 requests in last 20 seconds
// capacity = 10
// windowSize = 20

class RingBufferRL {

  private Node head;
  private int windowSize;

  public RingBufferRL(int capacity, int windowSize){

    this.windowSize = windowSize

    // populate ring buffer
    node previous = new Node();
    previous.timestamp = -1;
    head = previous;
    
    for(i = 1; i < capacity; i++) {

      Node current = new Node();
      current.timestamp = -1;

      previous.next = current;
      previous = current;
    }

    previous.next = head;
  }

  public void process(){
    int now = System.getEpochTime();

    if(now > head.next.timestamp + windowSize) {
      
      head.next.timestamp = now;
      head = head.next;

      server.process();
    } else {
      server.failfast();
    }
  }
}
