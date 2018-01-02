
/* 
 Implement an Event Bus  


EVENT BUS




users connect to certain channels
event bus will notify whenever there is a message for that channel

interface

emit in: message, channel out: void
subscribe in: consumer, channel out: void
unsub ?

type of messages / irrelevant
number of channels/consumers fits in the memory
throughput/ nt of messages / one at a time, infnite -- 

----------

emit:
message arrives for a chan
find the consumers of chan
chan -> consumer[]
HashMap of chan=>consumer
TC: size of consumer[]  SC: 1

sub:
consumer intents to sub to a chan
add consumer to chan's consumer[]
TC:1 SC:size of all consumers

*/

import java.util.*;

class Channel{
    
}

abstract class Consumer{
    abstract public void send(String m);
}

class EventBus {
    
    // O1 insert time
    // On traverse
    
    // linked list > keep the head, insertion takes n
    // doubly linked list > keep the last, insertion takes 1, traverse back in n times
    // Array List traverse On, insert effectively constant => once in a while it will double the size of arr and copy => On
    // hashmap traverse On, insert constant but requires a key (hashcode ?) handy for unsub
    // tree ??
    // stack, queue? traverse is pain
    
    
 private HashMap<Channel, HashMap<Integer, Consumer>> consumers = new HashMap<>();
    
 public void emit(String message, Channel channel) {
     HashMap<Integer, Consumer> cons = consumers.get(channel);
     
     if(cons == null) {
         return;
     }
     
     Iterator it = cons.values().iterator();
     
     Consumer c = (Consumer) it.next();
     while(c != null) {
         c.send(message);
         c = (Consumer) it.next();
     }
 }
 
 public void subscribe(Consumer consumer, Channel channel) {
    
    if(consumers.containsKey(channel) == false) {
    
        HashMap<Integer, Consumer> channelCons = new HashMap<>();
        channelCons.put(consumer.hashCode(), consumer);
        consumers.put(channel, channelCons);
    
    } else {
     
        HashMap<Integer, Consumer> channelCons = consumers.get(channel);
        channelCons.put(consumer.hashCode(), consumer);   
    }
 }
    
}









