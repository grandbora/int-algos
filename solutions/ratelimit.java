/*
Rate limit api calls


there is a server, we want to rate limit to reqs coming to this server to N over last x mins (sliding window)


Q) what hapens when client is over the rate? fail fast/queue ⇒ fail fast
server exposes process()
ratelimiter will implement same interface


rate limit is 1 request per 10sec
10 => process(); 
22 => process();
24 => process(); // fail fast
26 => process(); // fail fast
32 => process(); 
38 => process(); // fail fast
…

rate limit is 2 request per 10sec
10 {10}
22 {22}
24 {22,24}
26 {22,24} X
32 {24,32}
38 {32,38}


1)store TS in fifo
Fifo queue to store req TS
for each request
pop from queue until TS larger than now - N seconds
check queu size
if lower than X
call api.process
push TS to queue
else
fail fast

TC: X SC:X

2)out of bound process
updates the req queue
*/

class RateLimit {

  Server api;
  Queue<TimeStamp> q = new LinkedList<TimeStamp>();
  int rateLimit;
  int window;

public void process(){
  TimeStamp now = Time.now();
  removeOldRecords(now);
  if(q.size() > rateLimit) {
throw new RuntimeExceptio("");
}

q.push(now);
api.process();
}

private removeOldRecords(TimeStamp now){
  TimeStamp current = q.peek();
  while(current != null && current < (now - window)) {
    q.pop();
    current = q.peek();
}
}

}




