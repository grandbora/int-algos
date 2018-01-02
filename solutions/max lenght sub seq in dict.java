import java.lang.Math;
import java.util.*;

/*

Question:
given a dictionary and a long string
find the longest word in the dictionary which also happens to be a subsequence of the word.

subsequence meaning all the chars of a subsequence word should appear 
in the long string in the same order. There may be other chars in between

long string : "sdfdgdfhjoghjgjghj"
word: dog
"dog" is subsequence of "sdfdgdfhjoghjgjghj"



in: 
dict Map<String,String>, Set<String>
longW String


1)BF
loop dictionary, for each
  check if it is a subSeq
  keep track of longest

n: dict size
  
TC: n * charCount(dictW + longWord)  SC: 1

dict Word xyz
long Word xasdydasdaszdad


2)cand map

create empty candidate map

create prefix tree for first char (Map<Character, List<String>>)
this will be used for fetching all the words starting with X

loop long W, for each char
  progress all the words that expect this char as next one
  store all the words start with char, store by the second char, index1
  
  keep track of longest finished word
    
LW= xasdydasdaszdad
dict: xas, xyz, asd

map(1):
a : (xas,1)
y: (xyz,1)

map(2):
y: (xyz,1)
s: (xas, 2), (asd,1)
...

keep track of LONGEST finised

TC: charCount(LW) * n SC:n
*/

class Solution {


    public static void main(String[] args) {
      System.out.println("Start");

      Solution s =  new Solution();

      Set<String> dict = new HashSet<>();
      dict.add("xyz");
      dict.add("xas");
      dict.add("asd");
      dict.add("asdy");

      String result = s.maxSubSeq(dict, "xasdydasdaszdad");

      System.out.println(result);
      System.out.println("end");
    }

    class WordState {
        String word;
        int pointer;

        public WordState(String word, int pointer) {
          this.word = word;
          this.pointer = pointer;
        }

        public boolean isFinished() {
            return pointer == word.length();
        }

        public char expectedChar() {
            return word.charAt(pointer);
        }

        @Override public String toString() {
            return word + "," + pointer;
        }
    }

    public String maxSubSeq(Set<String> dict, String lw) {

        Map<Character, List<WordState>> candidateMap = new HashMap<>();
        Map<Character, List<String>> firstCharMap = createFirstCharMap(dict);

        String longestFinished = null;

        for (int i = 0; i < lw.length(); i++) {

            char curChar = lw.charAt(i);

            System.out.println(Arrays.toString(candidateMap.entrySet().toArray()));
            System.out.println(longestFinished);
            System.out.println(i);
            System.out.println(curChar);
            


            //progress existing words
            if (candidateMap.containsKey(curChar)) {
                String currentLongestFinished = progressWords(curChar, candidateMap);
                longestFinished = largest(longestFinished, currentLongestFinished);
            }

            // add new words
            if (firstCharMap.containsKey(curChar)) {
                List<String> firstCharWords = firstCharMap.get(curChar);

                for (int k = 0; k < firstCharWords.size(); k++) {
                    WordState curWord = new WordState(firstCharWords.get(k), 1);
                    if (curWord.isFinished()) {
                        longestFinished = largest(longestFinished, curWord.word);
                    } else {
                        char curKey = firstCharWords.get(k).charAt(1);
                        appendOrInitToMap(curKey, curWord, candidateMap);
                    }
                }
            }
        }

        System.out.println(Arrays.toString(candidateMap.entrySet().toArray()));


        return longestFinished;
    }

    private String progressWords(char expectedChar, Map<Character, List<WordState>> candidateMap) {
        String largestFinished = null;
        List<WordState> wordsExpectingChar = candidateMap.get(expectedChar);
        candidateMap.remove(expectedChar);
        for (int i = 0; i < wordsExpectingChar.size(); i++) {
            WordState wordState = wordsExpectingChar.get(i);
            wordState.pointer++;
            if (wordState.isFinished()) {
                largestFinished = largest(largestFinished, wordState.word);
            } else {
                appendOrInitToMap(wordState.expectedChar(), wordState, candidateMap);
            }
        }
        return largestFinished;
    }

    private void appendOrInitToMap(char key, WordState wordState, Map<Character, List<WordState>> map) {
        if (map.containsKey(key)) {
            List<WordState> list = map.get(key);
            list.add(wordState);
        } else {
            List<WordState> list = new ArrayList<>();
            list.add(wordState);
            map.put(key, list);
        }
    }

    private String largest(String largest, String candidate) {
        if (candidate == null) {
            return largest;
        }

        if (largest == null) {
            return candidate;
        }

        if (candidate.length() > largest.length()) {
            return candidate;
        }

        return largest;
    }

    private Map<Character, List<String>> createFirstCharMap(Set<String> dict) {
        Map<Character, List<String>> firstCharMap = new HashMap<>();

        Iterator<String> dictIt = dict.iterator();

        while(dictIt.hasNext()) {
          String word = dictIt.next();
          char firstChar = word.charAt(0);

          if (firstCharMap.containsKey(firstChar)) {
              List<String> list = firstCharMap.get(firstChar);
              list.add(word);
          } else {
              List<String> list = new ArrayList<>();
              list.add(word);
              firstCharMap.put(firstChar, list);
          }

        }

        return firstCharMap;
    }

}


/*

*/


