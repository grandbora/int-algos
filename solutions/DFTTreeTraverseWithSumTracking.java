/* 
You are given a binary tree in which each node contains an integer value.

List all paths that sum to a given target value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).  
       
                                   1
                2                                          3

     4                   5                                               6
          7                                                                   8


find all paths sum up to X
traverse and sum

---------
if this was all the paths starting from root
DFT
  keep in mind the sum so far
  when X is achieved collect the path

TC: n SC: tree depth
-------------

for all the paths starting from any node

for each node run the process above
TC: n^2 SC:n

------------------------

                                   1
                2                                          3

     4                   5                                               6
          7                                                                   8

DFT stack
paths[start, sumSoFar] = 

x=4
st {1}
paths {(1,1)}

st {1,2}
paths {(1,3), (2,2)} ====> can be a linked list, saves space

st {1,2,4}
paths {(1,7), (2,6), (4,4)}

st {1,2,4,7}
paths {(1,14), (2,13), (4,11)}

st {1,2,4}
paths {(1,14), (2,13), (4,11)}

st {1,2}
paths {(1,3), (2,2)}

st {1,2,5}
paths {(1,8), (2,7), (5,5)}

st {1,2}
paths {(1,3), (2,2)}

st {1}
paths {(1,1)}

st {1,3}
paths {(1,4), (3,3)}

...


DFT and track

start with 
empty stack, 
empty results arr
empty paths hasmap => starting Node => Path (Nodes, sum)

push root to the stack
mark as visited

peek at the current node

add the current to paths
    key node; sum is node value;, nodes is current
append all the paths, with current value
    ======= do the sum to X check

push the first unvisited child to the stack && mark as visited

if unvisited child is null
pop the current
remove the entry of current from paths
remove current form every other path in paths

*/

class Node {
    int value;
    Node left;
    Node right;
    Boolean visited = false;
}

class Path {
    Stack<Node> nodes;
    int sum;
    
    public Path(Node n) {
        this.nodes = new Stack();
        this.nodes.push(n);
        this.sum = n.value;
    }
    
    public clone ....
}

public Node[][] findPathsSumUpTo(int target, root Node) {
    Stack<Node> st = new Stack();
    HashMap<Node, Path> paths = new HashMap<>();
    ArrayList<Path> results = new ArrayList();
    
    root.visited = true;
    st.push(root);
    
    while(st.peek() != null) {
        Node current = st.peek();
        
        addToPaths(current, paths);
        savePathsSumUpTo(target, paths, results);
        
        Node firstUnvisitedChild = getFirstUnvisitedChild(current);
        
        if(firstUnvisitedChild == null) {
            st.pop();
            removeFromPaths(current, paths)
        } else {
            firstUnvisitedChild.visited = true;
            st.push(firstUnvisitedChild);
        }
    }
}


private void addToPaths(Node addition, HashMap<Node, Path> paths) {
    
    for(Node n: paths.keys().toArray()) {
        Path p = paths.get(n);
        
        p.nodes.push(addition);
        p.sum = p.sum + addition.value;
    }
    
    Path additionPath = new Path(addition);
    paths.put(addition, additionPath)
}

private void removeFromPaths(Node removal, HashMap<Node, Path> paths) {
    
    paths.remove(removal);
    
    for(Node n: paths.keys().toArray()) {
        Path p = paths.get(n);
        
        p.nodes.pop();
        p.sum = p.sum - removal.value;
    }
}


private void savePathsSumUpTo(int target, HashMap<Node, Path> paths, ArrayList<Path> results) {
    for(Node n: paths.keys().toArray()) {
        Path p = paths.get(n);
        
        if(p.sum == target) {
            results.add(p.clone());
        }
    }
}

private Node getFirstUnvisitedChild(current) {
    if(current.left != null && current.left.visited == false) {
        return current.left;
    }
    
    if(current.right != null && current.right.visited == false) {
        return current.right;
    }
    
    return null;
}









