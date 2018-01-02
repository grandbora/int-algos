import java.lang.Math;
import java.util.*;

/*

[ 'p', 'e', 'r', 'f', 'e', 'c', 't', ' ', 'm', 'a', 'k', 'e', 's', ' ', 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' ]


1)

prep 
    * Map word index => word length
    

iterate chars, 
calculate new pos
put in

init res arr with spaces
given i => 
    which word it is
    which word it will be
        
*/


class Solution {

  
    public static void main(String[] args) {

    }

    /*
    // abc dfg
    // 0123456
    //     x
    // dfg abc
    // x
    // 4 -3 -1
    // 6 - 3 + 1

    TODO= getWordLength


    * T: happy path
    wordLength = 0=> 4 , 1=> 5, 2=> 3


    s=prac makes per
      01234567890123
      x   x     x
    r=per makes prac

    
    currentWordLength = 4,5,3
    x = 14 - 4 = 10, 4,0
    
    wordCount = 0,1,2
    currentWordBegin = 5,11
    i=0,1,2,3,4,5,6,7,8,9,0,1
    c=p,r,a,c, ,m,a,k,e,s, ,p
        
    result[x + (i - currentWordBegin)] = sentence[i];
            0 + 11 - 11
    */

    public static char[] reverseSentence(char[] sentence) {

        // if empty return same
        // if no space return same

        Map<Integer, Integer> wordLength = getWordLength(sentence);
        char[] result = new char[sentence.length].fill(" ");

        int currentWordLength = wordLength[0];
        int x = sentence.length - currentWordLength ;
        
        int wordCount = 0;
        int currentWordBegin = 0;

        for(int i = 0; i < sentence.length; i++) {

            char c = sentence.charAt(i);

            if(c == ' ') {
                wordCount++;
                currentWordLength = wordLength[wordCount];
                x = x - 1 - currentWordLength;
                currentWordBegin = i + 1;
            } else {
                result[x + (i - currentWordBegin)] = sentence[i];
            }
        }
    }

    private Map<Integer, Integer> getWordLength(char[] sentence) {
        int wordCount = 0;
        int curLength = 0;
        Map<Integer, Integer> wl = new HashMap<Integer, Integer>();

        for(int i = 0; i < sentence.length; i++) {
            char c = sentence.charAt(i);

            if(c == ' ') {
                wl.put(wordCount, curLength);
                curLength = 0;
            } else {
                curLength++;
            }
        }

    }
}