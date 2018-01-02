import java.lang.Math;
import java.util.*;

/*
        
*/


class Solution {

  
    public static void main(String[] args) {
        String k = "aabb";
        Map<String, String> d = new HashMap<>();
        d.put("a","");
        d.put("b","");
        d.put("aa","");
        d.put("ab","");
        int result = checkWordNumber(k, d);
        System.out.println(result);
    }

/*
input valdiation
happy path Unit test
edge cases



T multiple ways of break down
d: ab cd c d
s: abcd 

result = 0,2
i = 1,2,3
w = a,ab,abc
rest = cd
result = 0 + checkWordHelper(cd, dict); => 2
ret 2

checkWordHelper(cd, dict);
result = 0,1,2
i=1
w=c
rest = d
result = 0 + checkWordHelper(d, dict) ==> 1
ret 2

checkWordHelper(d, dict);        
result = 0, 1
ret 1
*/

    public static int checkWordNumber(String s, Map<String, String> dict) {
        if(s == "") {
            return 0;
        }

        dict.remove(s); // wrap in if

        Map<String, Integer> acc = new HashMap<String, Integer>();

        return checkWordNumberHelper(s, dict, acc);
    }

    public static int checkWordNumberHelper(String s, Map<String, String> dict, Map<String, Integer> acc) {
        if(acc.containsKey(s)) {
            return acc.get(s);
        }
        int result = 0;

        for (int i = 1; i < s.length(); i++) {
            String w = s.substring(0, i);

            if(dict.containsKey(w)) {
                String rest = s.substring(i, s.length());
                System.out.println("rec call with " +w + " - "+ rest);
                result = result + checkWordNumberHelper(rest, dict, acc);
            }
        }

        if(dict.containsKey(s)) {
            result++;
        }

        acc.put(s, result);
        return result;
    }


/*

* not in dict not decomposable

* word is made of 3 words


* empty input
T1 empty  => false

* word is decomposable and in the dict
T2
dict= aabc aab c cd
s = aabc

* word is NOT decomposable and in the dict
T3
dict= aabc
s = aabc

* not in dict decomposable
T
dict= abc d
s = abcd

i=1,2,3
w=a,ab,abc
rest = d

checkWordHelper(d ,dict)
i=1
w= d
rest 1,1 => ""
checkWordHelper("", dict)
*/

/*
    public boolean checkWord(String s, Map<String, String> dict) {
        if(s == "") {
            return false;
        }

        dict.remove(s); // wrap in if

        return checkWordHelper(s);
    }


    private boolean checkWordHelper(String s, Map<String, String> dict) {
        if(s == "") {
            return true;
        }

        for (int i = 1; i < s.length; i++) {
            String w = s.subString(0, i);

            if(dict.contains(w)) {
                String rest = s.subString(i, s.length); // off by one // out of bounds
                if(checkWordHelper(rest, dict)) {
                    return true;
                }
            }
        }

        if(dict.contains(s)) {
            return true;
        }

        return false;
    }

*/
}