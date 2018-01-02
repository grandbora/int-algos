
import java.util.*;


class Solution {

    /*
        Given an array of unique characters arr and a string str, 
        implement a function getShortestUniqueSubstring that 
        finds the smallest substring of str containing all the characters in arr. 
        Return "" (empty string) if such a substring doesn’t exist.

        input:  arr = ['x','y','z'], str = "xyyzyzyx"
        output: "zyx"
    */

    public static void main(String[] args) {
        char[] arr = new char[]{'x', 'c', 'o', 'y', 'z'};
        String str = "xcoaayyzyoczyxa";
        System.out.println(new Solution().getShortestUniqueSubstring(arr, str));
    }

    public String getShortestUniqueSubstring(char[] arr, String str) { // empty/null str,arr check

        String shortestSub = null;
        int begin = 0;
        int end = 0;
        int uniqueCount = 0;

        Map<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (char c : arr) {
            charMap.put(c, 0);
        }

        for(;end < str.length(); end++) {
            char currentChar = str.charAt(end);
            if (charMap.containsKey(currentChar) == false) {
                continue;
            }

            int currentSubLength = end - begin + 1;
            String currentSub = str.substring(begin, begin + currentSubLength);

            int charCount = charMap.get(currentChar);
            charMap.put(currentChar, charCount + 1);

            if (charCount != 0) {
                continue;
            }

            uniqueCount += 1;
            if (uniqueCount != arr.length) {
                continue;
            }

            while (uniqueCount == arr.length) {
                if (null == shortestSub || currentSubLength < shortestSub.length()) {
                    shortestSub = currentSub;
                }

                char beginChar = str.charAt(begin);

                if (charMap.containsKey(beginChar)) {
                    int beginCharCount = charMap.get(beginChar);
                    charMap.put(beginChar, beginCharCount - 1);
                    if (beginCharCount == 1) {
                        uniqueCount -= 1;
                    }
                }

                begin += 1;
                currentSubLength = end - begin + 1;
                currentSub = str.substring(begin, begin + currentSubLength);
            }

        }

        if (null == shortestSub) {
            return "";
        } else {
            return shortestSub;
        }
    }

        /*
        Given an array of unique characters arr and a string str, 
        implement a function getShortestUniqueSubstring that 
        finds the smallest substring of str containing all the characters in arr. 
        Return "" (empty string) if such a substring doesn’t exist.


        input:  arr = ['x','y','z'], str = "xyyzyzyx"
        output: "zyx"



        1)BF
        Pick first char
        Iterate through all of the substrings that start from this char
        Keep the shortest that applies
        Repeat the process for all chars
        Pick the shortest of shortests
        TC: n * n SC:1

        2)
        Create map from arr, char to int (count)
        Iterate the str
        Keep a begin index and an end index

        In each iteration record how many chars occurred so far
        Whenever all chars have at least one we have a candidate
        Track the shortest candidate
        Update begin index, go as far as possible
        Continue iteration until next candidate or end of the string

        TC:n SC: a
        */


        /*
        arr x y z
        str xyyzyzyx
        str.length = 8
        charMap = {x y z}
        shortestSub = null
        begin = 0
        end = 2
        currentSubLength = 3
        currentSub = xyy
        end = 3
        currentSubLength = 4
        currentSub = xyyz
        begin = 1
        shortestSub = xyyz

        currentSubLength = 3
        currentSub = yyz
        end = 4

        currentSubLength = 4
        currentSub = yyzy
        end = 5


        
        public String getShortestUniqueSubstring(char[] arr, String str) {
            Map<Character, Boolean> charMap = createCharMap(arr);
            int begin = 0;
            String shortestSub = null;

            for (int end = arr.length - 1; end < str.length(); end++) {
                int currentSubLength = end - begin + 1;
                String currentSub = str.substring(begin, currentSubLength);

                if (isCandidate(currentSub, charMap)) {
                    begin += 1;

                    // return if candidate length is arr length

                    if (null == shortestSub || currentSubLength < shortestSub.length()) {
                        shortestSub = currentSub;
                    }
                } else {
                    end += 1;
                    // continue if char is not in map
                }
            }

            if (null == shortestSub) {
                return "";
            } else {
                return shortestSub;
            }
        }

        private Map<Character, Boolean> createCharMap(char[] arr) {
            Map<Character, Boolean> res = new HashMap<Character, Boolean>();
            for (char c : arr) {
                res.put(c, true);
            }
            return res;
        }

        private Boolean isCandidate(String str, Map<Character, Boolean> charMap) {
            for (int i = 0; i < str.length(); i++) {
                if (charMap.containsKey(str.charAt(i)) == false) {
                    return false;
                }
            }
            return true;
        }
        */



    /*
    Given an array of unique characters arr and a string str, Implement a function getShortestUniqueSubstring that finds the smallest substring of str containing all the characters in arr. Return "" (empty string) if such a substring doesn’t exist.


    input:  arr = ['x','y','z'], str = "xyyzyzyx"
    output: "zyx"



    1)BF
    Pick first char
    Iterate through all of the substrings that start from this char
    Keep the shortest that applies
    Repeat the process for all chars
    Pick the shortest of shortests
    TC: n * n SC:1

    2)
    Create map from arr, char to int (count)
    Iterate the str
    Keep a begin index and an end index

    In each iteration record how many chars occurred so far
    Whenever all chars have at least one we have a candidate
    Track the shortest candidate
    Update begin index, go as far as possible
    Continue iteration until next candidate or end of the string

    TC:n SC: a
    */


    /*
    arr x y z
    str xyyzyzyx
    str.length = 8
    charMap = {x y z}
    shortestSub = null
    begin = 0
    end = 2
    currentSubLength = 3
    currentSub = xyy
    end = 3
    currentSubLength = 4
    currentSub = xyyz
    begin = 1
    shortestSub = xyyz

    currentSubLength = 3
    currentSub = yyz
    end = 4

    currentSubLength = 4
    currentSub = yyzy
    end = 5


    
    public String getShortestUniqueSubstring(char[] arr, String str) {
        String shortestSub = null;
        int begin = 0;
        int end = arr.length - 1;

        while (end < str.length()) {
            int currentSubLength = end - begin + 1;
            String currentSub = str.substring(begin, begin + currentSubLength);

            if (isCandidate(currentSub, arr)) {
                // return if candidate length is arr length

                if (null == shortestSub || currentSubLength < shortestSub.length()) {
                    shortestSub = currentSub;
                }

                begin += 1;
            } else {
                end += 1;
                // continue if char is not in map
            }
        }

        if (null == shortestSub) {
            return "";
        } else {
            return shortestSub;
        }
    }

    private Map<Character, Boolean> createCharMap(char[] arr) {
        Map<Character, Boolean> res = new HashMap<Character, Boolean>();
        for (char c : arr) {
            res.put(c, true);
        }
        return res;
    }

    private Boolean isCandidate(String str, char[] charArr) {
        Map<Character, Boolean> subMap = new HashMap<Character, Boolean>();
        for (int i = 0; i < str.length(); i++) {
            subMap.put(str.charAt(i), true);
        }

        for (int i = 0; i < charArr.length; i++) {
            if (subMap.containsKey(charArr[i]) == false) {
                return false;
            }
        }
        return true;
    }

    */

}
  
 
 
 
