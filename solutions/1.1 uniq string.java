// Implement an algorithm to determine if a string has all unique characters. 
// What if you cannot use additional data structures?

// size of the string? fits in to the memory
// how do we read? Argument to a function

// empty String ? T
// null ? => throw exception

// case sensitivity ?? yes case sensitive

// asdasd => F
// adnabsafsdhfu123 => F
// 123567890 => T
// "" => T
// null => THROW


// solutions
// n: String length

// 1)
// take first char, check if exists in the rest
// if yes RETURN FALSE
// apply same to the next char
// Repeat until the last char
// TC: n^2
// SC: 1

// 2)
// iterate string chars
// create a map, if current char exists retur false
// TC: n
// SC: n

// We can not beat TC: O(n)

import java.util.HashMap;

class Solution {

  public static void main(String[] args) {

    // System.out.println(isUnique(null));
    System.out.println(isUnique(""));


    System.out.println(isUnique("BORA"));
    System.out.println(isUnique("BOROA"));
  }


  // str = BOROA
  // acc = {}

  // i = 0
  // c = B
  // acc = {B}

  // i = 1
  // c = O
  // acc = {B, O}

  // i = 2
  // c = R
  // acc = {B, O, R}

  // i = 3
  // c = O
  // acc = {B, O, R}

  //ret false

  public static Boolean isUnique(String str) {

    if(str == null) {
      throw new IllegalArgumentException();
    }

    if(str.isEmpty()){
      return true;
    }

    HashMap acc = new HashMap(str.length());

    for (int i = 0; i < str.length(); i++){
      char c = str.charAt(i);

      if(acc.containsKey(c)) {
        return false;
      } else {
        acc.put(c, true);
      }
    }

    return true;
  }
}

// Alternatives:
// sort the string, iterate and compare one to the next

// tip to reduce space complexity: use distance to char `a` as a key in accumulator











