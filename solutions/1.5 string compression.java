// Implement a method to perform basic string compression using the counts of repeated characters. 
// For example, the string a a b c c c c c a a a would become a2blc5a3. 
// If the "compressed" string would not become smaller than the original string, 
// your method should return the original string.


// aabcccccaaa ==> a2blc5a3
// abc ==> abc
// a1b2c ==> a1b2c
// 1111 ==> 14
// "" => ""
// null => ??? 

// "   " => " 3"  // ws

// "KKKKk" => K4k1

// "aa" ==> aa

// 1)
// iterate and collect
// TC:n SC:n

import org.apache.commons.lang3.ArrayUtils;

class Solution {


  // str = aaab
  // repeatingChar = a
  // repeatCount = 1
  // compressed = ""

  // i = 1
  // currentChar = a
  // repeatCount = 2

  // i = 2
  // currentChar = a
  // repeatCount = 3

  // i = 3
  // currentChar = b
  // compressed = "a3"
  // repeatCount = 1
  // repeatingChar = b


  // compressed = "a3b1"
  // ret aaab

  public static String compress(String str){
    if(str == null) return null;
    if(str.isEmpty()) return str;

    char repeatingChar = str.charAt(0);
    int repeatCount = 1;

    StringBuffer compressed = new StringBuffer();

    for(int i = 1; i < str.length(); i++) {
      char currentChar = str.charAt(i); 

      if(currentChar == repeatingChar) {
        repeatCount++;
      } else {
        compressed.append(repeatingChar);
        compressed.append(repeatCount);

        repeatCount = 1;
        repeatingChar = currentChar;
      }
    }

    compressed.append(repeatingChar);
    compressed.append(repeatCount);

    if(str.length() > compressed.length()){
      return compressed.toString();
    } else {
      return str;
    }
  }

  public static void main(String[] args) {
    System.out.println(compress("aaaaa"));
    System.out.println(compress("aaaaabc"));
    System.out.println(compress("aaaaabcd"));
    System.out.println(compress(""));
    System.out.println(compress("aaaaabccccccdddxrrrr"));
  }
}







