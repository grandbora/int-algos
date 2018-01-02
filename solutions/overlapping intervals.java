// startTime, endTime, QPS
// 10, 30, 6M
// 20, 40, 10M
// 25, 35, 2M
 
// 25, 30, 18M
 
// given the intervals above, find the interval with the max qps
 
 
------------------------------------------------------ 
   +6       +10  -6  -10  +2 -2
 
// n: amount of input interval
 
// 1) 
// process intervals
// mark additions and substractions on a line
// go through the line calculate the sum qps  as you go
// keep track of the max qps and the interval
 
//Without sorting:
// TC: n+MaxTime SC: MaxTime
 
//With sorting:
// TC: n^2 log n SC: n
 
 
 
 
 
public int maxInterval(Interval[] intervals) {
 
Event[] events = createEvents(intervals);
 
Queue q = getSortedQueue(events);
 
Int runningQps = 0;
Int maxQps = 0;
while(q.isEmpty == false) {
  Event current = q.pop();
runningQps = runningQps + current.delta;
if(runningQps > maxQps) {
  maxQps = runningQps;
}
}
 
return maxQps;
}
 
private Queue getSortedQueue(Event[] events) {
  Queue q = new LinkedList();
 
 
while(q.size() != events.length) {
int minEventIndex = -1;
 
for(int i = 0; i < events.length; i ++) {
  Event current = events[i];
 
if(current != null) {
if(minEventIndex == -1 || current.time < events[minEventIndex].time) {
minEventIndex = i;
}
}
}
 
q.add(events[minEventIndex]);
events[minEventIndex] = null;
}
  
}
 
// 10, 30, 6M
// 30, 40, 10M
// 20, 40, 5M
private  Event[] createEvents(Interval[] intervals) {
Event[] events = new Event[intervals.length * 2];
for(int i = 0; i < intervals.length; i++) {
  Interval current = intervals[i]
  Event e1 = new Event(current.start, current.qps);
Events[i*2] = e1;
  Event e2 = new Event(current.end, current.qps * -1);
Events[i*2 + 1] = e2;
}
return events;
}
 
 
 
 
 
 
 
 
 
 
// 10, 30, 6M
// 20, 40, 10M
// 25, 35, 2M
// 25, 30, 18M
 
//line = {} size 40
//i=0
//current = 10,30, 6M
//line = {10->6M}
//line = {10->6M, 30->-6M}
 
//i=1
//current = 20,40, 10M
//line = {10->6M, 20->10M, 30->-6M, 40->-10M}
 
//i=2
//current = 25,35, 2M
//line = {10->6M, 20->10M,25->2M, 30->-6M, 35->-2M, 40->-10M}
 
public int maxQps(Interval[] intervals) {
 
  int[] line = new int[MAX_TIME];
  for(int i = 0; i < intervals.length; i++) {
    Interval current = intervals[i];
 
    if(line[current.start] != null) {
      line[current.start] = line[current.start] + current.qps;
} else {
line[current.start] = current.qps;
}
 
    if(line[current.end] != null) {
      line[current.end] = line[current.start] - current.qps;
} else {
line[current.end] = current.qps * -1;
}
}
 
Int currentQps = 0;
Int maxQps = 0;
for(int i = 0; i < line.lenght; i++) {
  if(line[i] != null) {
    currentQps = currentQps + line[i];
    if(currentQps > maxQps) {
      maxQps = currentQps;
}
}
}
 
return maxQps;
}
 
//line = {10->6M, 20->10M,25->2M, 30->-6M, 35->-2M, 40->-10M}
// currentQps = 0;
// maxQps = 0;
// i = 0
// i = 1
// i = 10
// currentQps = 6
// maxQps = 6
 
// i = 20
// currentQps = 16
// maxQps = 16
 
// i = 25
// currentQps = 18
// maxQps = 18
 
// i = 30
// currentQps = 12
// maxQps = 18
 
 
 
 
 
 
========================================================
 
 
import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
 
class Solution {
 
class Interval{
  int start;
  int end;
  int qps;
 
  public Interval(int start, int end, int qps) {
    this.start = start; 
    this.end = end; 
    this.qps = qps; 
  }
}
 
class Event{
  int time;
  int delta;
 
  public Event(int time, int delta) {
    this.time=time; 
    this.delta=delta; 
  }
}
 
public static void main(String[] args) {
  Solution s = new Solution();
 
  Interval[] intervals = new Interval[]{
    s.new Interval(10,20,100),
    s.new Interval(10,25,150),
    s.new Interval(34,45,150),
    s.new Interval(22,45,50),
    s.new Interval(5,6,1150),
  };
  System.out.println(s.maxInterval(intervals));
  
}
 
public int maxInterval(Interval[] intervals) {
 
  Event[] events = createEvents(intervals); 
 
  Queue q = getSortedQueue2(events); 
 
  int runningQps = 0;
  int maxQps = 0;
  while(q.isEmpty() == false) {
    Event current = (Event) q.remove();
    runningQps = runningQps + current.delta;
    if(runningQps > maxQps) {
      maxQps = runningQps;
    }
  }
   
  return maxQps;
}
 
  private Queue getSortedQueue(Event[] events) {
    Queue q = new LinkedList();
   
    while(q.size() != events.length) {
      int minEventIndex = -1;
       
      for(int i = 0; i < events.length; i ++) {
        Event current = events[i];
       
        if(current != null) {
          if(minEventIndex == -1 || current.time < events[minEventIndex].time) {
            minEventIndex = i;
          }
        }
      }
     
      q.add(events[minEventIndex]);
      events[minEventIndex] = null;
    }
    
    return q;  
  }

  private Queue getSortedQueue2(Event[] events) {
    
    Arrays.sort(events, new Comparator<Event>() {
      public int compare(Event e1, Event e2) {
          if(e1.time > e2.time) {
            return 1;
          } else if(e1.time == e2.time) {
            return 0;
          } else {
            return -1;
          }
      }
    });

    Queue q = new LinkedList();
   
    for(int i = 0; i < events.length; i++) {
      q.add(events[i]);
    }
    
    return q;  
  }
 
  private  Event[] createEvents(Interval[] intervals) {
    Event[] events = new Event[intervals.length * 2];
    for(int i = 0; i < intervals.length; i++) {
      Interval current = intervals[i];
      Event e1 = new Event(current.start, current.qps);
      events[i*2] = e1;
      Event e2 = new Event(current.end, current.qps * -1);
      events[i*2 + 1] = e2;
    }
    return events;
  }
}
 
