
import java.util.*;

/*
Chef has recently started becoming a fan of computer games. He is currently playing a game in which he controls a warrior who is fighting evil monsters.
Before the start of each stage, N weapons appear on the screen in circular order. Each weapon has an integer associated with it, which represents its level. The chef can choose two adjacent weapons of the same level and fuse them into a single weapon of level A+1, where A is the level of the weapons before fusing. Both the old weapons will disappear and the new weapon will be placed in the place of the old weapons, shrinking the circle.
Chef can fuse as many times as he wants, and in each stage, he wants to make a weapon with as high a level as possible. Each stage is independent of other stages.
Please help Chef by figuring out the maximum level of a weapon that he can get in each stage.


IN : { 1 1 2 3 1 4 1}

Q: make the max level of weapon


{ 1 1 2 3 1 4 1}
{ 2 2 3 1 4 1}
{ 3 3 1 4 1}
{ 4 1 4 1}

== 

{ 1 2 3 1 4 2}

A: 4

input size 
Array of Int
each value is positive 0 < x < INT.Max_VAL

!first and last ar adjacent
we can rotate the arr as man times as we want, answer is the same

sort? No this would break order/ logic / answer


{ 1 1 2 3 1 4 1}

can we come up with smth greater than 4?
if no
ans : 4
if yes:
Max value



{ 1 1 2 3 1 4 1}
{1 1 1 2 3 1 } =>>>  4}

============= 

{ 1 1 2 3 1 4 1}
{ 2 2 3 1 4 1}
{ 3 3 1 4 1}
{ 4 1 4 1}

{ 1 1 2 3 1 4 1}
{ 1 2 3 1 4 2}

n + n -1 + n -2 + n-3 … = n * n / 2

1)BF
iterate from beginning, fuse first two adjacent same values
repeat the process on the result array, keep the max elm
move on to the next adjacent same values

pick the highest of max values

TC: n * 2 * k  SC: n
k: amount of adjacent same values


// UNIT TESTS
// Assumptions: ELMS ARE POS INTs


//U1
// { 1 1 1 2}

elms = { 1 1 1 2}
highest = 2
prev = 1
i = 1 ================
current = 1
fuseElements(elms, 0);  // fuseElements({ 1 1 1 2}, 0);  ===> { 2 1 2} UNIT TEST++++
fusedElms = { 2 1 2}
int result = getMax(fusedElms);  ===>  rets 3  UNIT TEST++++
result = 3
highest = 3
prev = 1

i=2 ================
current = 1
fusedElms = fuseElements({ 1 1 1 2}, 1); 
fusedElms = {1 2 2 }
result = getMax(fusedElms) = 3
prev = 1

i=3 ================
current = 2
prev = 2

rets 3
*/

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        Integer[] input = new Integer[]{1, 1, 1, 2};

        System.out.println("=====");
        System.out.println("in:");
        System.out.println(Arrays.toString(input));
        int res = s.getMax(input);
        System.out.println("out:");
        System.out.println(res);

    }

    public int getMax(Integer[] elms) {
        if (elms == null || elms.length == 0) {
            throw new RuntimeException(); // return err; // some sort of
        }

        if (elms.length == 1) {
            return elms[0];
        }

        int highest = getHighestElm(elms);

        int prev = elms[elms.length - 1];
        for (int i = 0; i < elms.length; i++) {
            int current = elms[i];
            if (prev == current) {
                Integer[] fusedElms = fuseElements(elms, i); // space eff??
                int result = getMax(fusedElms);
                if (result > highest) {
                    highest = result;
                }
            }

            prev = current;
        }
        return highest;
    }


/*
//TEST:  fuseindex = 0, anything between, last elm index
//UNIT TEST1
fuseElements({ 1 1 1 2}, 0);
elms { 1 1 1 2}, 
fuseIndex = 0
newArr = int[3]

i = 0
newArr[0] = elms[0] + 1;
newArr = {2}
i = 1
newArr[1] = elms[2];
newArr = {2 1}
i = 2
newArr[2] = elms[3];
newArr = {2 1 2}

rets {2 1 2};
*/

    private Integer[] fuseElements(Integer[] elms, int fuseIndex) {
        ArrayList<Integer> res = new ArrayList(Arrays.asList(elms));

        Integer fusedValue = res.get(fuseIndex) + 1;

        if(fuseIndex == 0) {
            res.remove(0);    
            res.remove(res.size() - 1);    
            res.add(0, fusedValue);
        } else {
            res.remove(fuseIndex);
            res.remove(fuseIndex - 1);
            res.add(fuseIndex - 1, fusedValue);
        }
        
        Integer[] resArr = res.toArray(new Integer[res.size()]);
        return resArr;
    }

    private int getHighestElm(Integer[] elms) {
        int highest = -1;

        for (int i = 0; i < elms.length; i++) {
            if (elms[i] > highest) {
                highest = elms[i];
            }
        }
        return highest;
    }
}
