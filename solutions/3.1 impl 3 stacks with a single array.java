// Describe how you could use a single array to implement three stacks

// 1)
// divide array to three pieces
// when ran out of space shift them

// 2)
// prefix each element with stack id
// when popping start from top, iterate backwards,
// find the first item with the right prefix, remove it,
// shift the items

// 3) store linked list nodes
// each element points to the elment before in the same stack
// store the index of the top level item for each stack

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Stack;
import java.util.HashMap;

class Solution {


}





