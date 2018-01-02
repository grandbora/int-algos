
import java.util.*;
/*
Q)

Suppose you have a random list of people standing in a queue. 
Each person is described by a pair of integers (h, k), 
where h is the height of the person 
and k is the number of people in front of this person 
who have a height greater than or equal to h. 

Write an algorithm to reconstruct the queue.

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]


*/































/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

size of queue
same height people
are there multiple solutions?

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]




































1)sort by k and place
TC: n logn SC:1


[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

[5,0] [7,0] [5,2]  [6,1] [4,4] [7,1]


2) Sort by h and place
* sort by first h and then by
    -tallest first, then lowest k first
* start from beginning, and place in the queue
    - pick the elements, iterate k items and place
TC: n logn SC: n

[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]

T1)
personList = [7,0] [7,1] [6,1] [5,0] [5,2] [4,4]
q = {}
current = [7,0]
k = 0
q = {[7,0]}
current = [7,1]
k = 1
q = {[7,0] [7,1]}
current = [6,1]
k=1
q = {[7,0] [6,1] [7,1]}
current = [5,0]
k = 0 
q = {[5,0] [7,0] [6,1] [7,1]}
current = [5,2]
k = 2 
q = {[5,0] [7,0] [5,2] [6,1] [7,1]}
current = [4,4]
q = {[5,0] [7,0] [5,2] [6,1] [4,4] [7,1]}
*/


class Solution {

    class Person{
        int h;
        int k;

        public Person(int h, int k){
            this.h = h;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Person h: " + this.h + " k: " + this.k;
        }
    }

    class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Solution.Person x,Solution.Person y) {

            if(x.h > y.h) {
                return -1;
            }

            if(x.h == y.h) {
                if(x.k < y.k) {
                    return -1;
                }

                if(x.k == y.k) {
                    throw new RuntimeException("invalid data");
                }

                return +1;
            }

            return +1;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Person p1 = s.new Person(5, 0);
        Person p2 = s.new Person(7, 0);
        Person p3 = s.new Person(7, 1);
        Person p4 = s.new Person(4, 1);

        Person[] input = new Person[]{p1,p2,p3,p4};
        
        System.out.println("INPUT =======");
        System.out.println(Arrays.toString(input));

        Person[] q = reconstruct(input);

        System.out.println("ANS ======== ");
        System.out.println(Arrays.toString(q));
    }

    public static Person[] reconstruct(Person[] personList) {
        Solution s = new Solution();
        Arrays.sort(personList, s.new PersonComparator());

        ArrayList<Person> q = new ArrayList<>();

        for(int i = 0; i<personList.length; i++) {
            Person current = personList[i];


            if(current.k > q.size()) {
                throw new RuntimeException("invalid data"); // return err;
            }

            q.add(current.k, current);
        }

        Person[] res = new Person[q.size()];
        for(int i = 0; i < q.size(); i++) {
            Person curr = (Person) q.get(i);
            res[i] = curr;
        }
        return res;
    }
}
