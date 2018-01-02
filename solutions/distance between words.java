/*

Given an initial dictionary, an initial word and a target word. Create an algorithm that outputs the minimum edit distance between both words and every intermediate word has to be contained in the dictionary.  


start :abc
end: jkl

dict: [jbc, jkc, akc]


abc
  |        |
jbc        akc   
 |        |
jkc
|
jkl


1) traverse the graph, find the shortest path 
 -do not visit a node twice
 -do DFT find all the paths, return the min
  - short cut paths that are larger than previously found


TC: n^2     SC: n

===============
ALG:
in: start word
in: end word
in: dictionary

make sure dictionary includes end word **

update history with current word
find children of the current word

filter out the ones that are visited, (look at history)
if children is empty indicate dead end, null etc.

check if any is end word
if yes return 1
if no 
for each children repeat the process (pass down history)
get the min of the returned value
add one and return

===================

Q)  what happens when there is no way? ret -1

// Class name must be "Main"
// Libraries included:
// json simple, guava, apache commons lang3, junit, jmock


// ASSUMPTION:
// all words are of the same length

// WHAT"S MISSING
// * dump history reduce dictionary at each step
// * implement get children ++++++++++++
// EDGE CASES??
// walk through
// unit test
//     - empty dict
//     - start can not go to end
//     - start is 1 step away
// .......

class Main {
     public static void main(String[] args) {
          System.out.println("Hello, world!2");
     }
    
    public int findDistance(String start, String end, Set<String> dict) {

        if(dict.contains(end) == false) {
            dict.add(end);
        }

        if(dict.contains(start)) {
            dict.remove(start);
        }

        HashMap<String> hist = new HashMap<>();

        return findDistanceLoop(start, end, dict, hist);
    }

    private int findDistanceLoop(String start, String end, Set<String> dict, HashMap<String> hist) {
        Set<String> children = getChildren(start, dict);        
        removeVisited(children, hist);
        
        if(children.isEmpty()) {
            return -1;
        }
        
        if(children.contains(end)) {
            return 1;
        }
        
        ArrayList<Integer> childResults = new ArrayList<>();
        
        for(String child: children) {
            HashMap<String> newHist = hist.clone(); // TODO: get rid
            newHist.add(child);
            int childPathLenght = findDistanceLoop(child, end, dict, newHist); 
            if(childPathLength != -1) {
                childResults.add(childPathLength);
            }
        }
        
        if(childResults.isEmpty()) {
            return -1;
        }
        
        int minPath = Arrays.min(childResults.toArray());
        
        return minPath + 1;
    }
    
    //private Set<String> removeVisited // .......................TODO:IMP
    
    private Set<String> getChildren(String start,Set<String> dict) {
        Set<String> result = new Set<String>();
        
        for(String word: dict) {
            if(isOneStepAway(start, word)){
                result.add(word);
            }
        }
        
        return word;
    }
    
    
    private Boolean isOneStepAway(String start,String word) {
        int diffCount = 0;
        for(int i = 0; i < start.length; i++){
            Char c1 = start.charAt(i);
            Char c2 = word.charAt(i);
            
            if(c1 != c2) {
                diffCount++;
            }
        }
        
        return diffCount == 1;   
    }

}



