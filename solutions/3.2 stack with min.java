// How would you design a stack which, 
// in addition to push and pop, 
// also has a function min which returns the minimum element? 
// Push, pop and min should all operate in 0(1) time.

// stack
// push 
// pop
// min

// 1)
// keep ref to min.
// update during push if item is smaller
// update during pop is smallest item is popped. 
// In that case iterate the stack, find the new min

// 2)
// keep a second stack for mins
// push to that stack everytime a new min is pushed
// pop from that stack everytime the min is popped

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;

class Solution {


}





