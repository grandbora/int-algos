
import java.util.*;


class Solution {

PriorityQueue<Integer> q = new PriorityQueue<Integer>();
 
for(int i = 0; i < k; i++ ){
q.add(arr[i]);
}
 
for(int i = 0; i < arr.length - k; i++ ){
    arr[i] = q.remove();
    q.push(arr[i+k]);
}
 
while(q.isEmpty() == false) {
arr[i] = q.remove();
i++;
}
}



}
  
 
 
 
