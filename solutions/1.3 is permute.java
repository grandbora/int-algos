// Giventwostrings,write a method to decide if 
// one is a permutation of the other.

// case sensitive
// fit in the memory
// ascii string

// equal strings => true
// different size => false
// a null string => false
// empty string  => false

// asdf , asdg => F
// asdf , afsd => T

// permutation => 
// + size should be same
// + order is different

// n: Str length


// 1)
// pick first char from str1, 
// search in str2
// if not found ret false
// if found remove it from str2 move on to char 2
// repeat until the end of str1

// TC: n * n
// SC: 1


// 2)
// create map from str2
// pick first char from str1, 
// check in the map
// if not found ret false
// if found mark as used
// move on to char 2
// repeat until the end of str1

// TC: n
// SC: n


// 3)
// sort both strings
// check if they are equal

// TC: n logn
// SC: n

import java.util.HashMap;

class Solution {

  public static void main(String[] args) {
    System.out.println(isPermute("asa", "aas"));
    System.out.println(isPermute("asa", "aas2"));
    System.out.println(isPermute("asa", "axs"));
    System.out.println(isPermute(null, null));
    System.out.println(isPermute(null, "null"));
    System.out.println(isPermute("null", "null"));
    System.out.println(isPermute("asdasda", "asd"));
    System.out.println(isPermute("123", "asd"));
  }

// str1 = asa , str2 = aas 
// acc = {a2, s1}

  // i = 0
  // c = a
  // usageCount = 2
  // acc = {a1, s1}

  // i = 1
  // c = s
  // usageCount = 1
  // acc = {a1, s0}

  // i = 2
  // c = a
  // usageCount = 1
  // acc = {a0, s0}

  public static Boolean isPermute(String str1, String str2) {

    if(str1 == str2) return true;
    if(str1 == null || str2 == null) return false;
    if(str1.length() != str2.length()) return false;
    if(str1.isEmpty() || str2.isEmpty()) return false;

    HashMap str2Map = getMap(str2);

    for(int i = 0; i < str1.length(); i++) {
      char c = str1.charAt(i);

      if(str2Map.containsKey(c)){

        int usageCount = (int)str2Map.get(c);

        if(usageCount == 0) {
          return false;  
        } else {
          str2Map.put(c, usageCount - 1);
        }
      } else {
        return false;
      }
    }

    return true;
  }

  // str = aas
  // acc = {}

  // i = 0 
  // c = a
  // acc = {a1}

  // i = 1 
  // c = a
  // currentCount = 1
  // acc = {a2}

  // i = 2 
  // c = s
  // acc = {a2, s1}


  public static HashMap getMap(String str) {
    HashMap acc = new HashMap(str.length());

    for(int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);

      if(acc.containsKey(c)){
        int currentCount = (int)acc.get(c);
        acc.put(c, currentCount + 1);
      } else {
        acc.put(c, 1);
      }
    }
    return acc;
  }
}


// ALTERNATIVE
// count the number of chars in each, make an array / hashmap
// check if they are equal






