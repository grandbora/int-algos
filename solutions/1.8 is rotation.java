
// Assume you have a method isSubstring which checks 
// if one word is asubstring of another. 

// Given two strings, s1 and s2, write code to check 
// If s2 is a rotation of s1 using only one call to isSubstring 
// (e.g., "waterbottLe" is a rotation of "erbottlewat").


// s1: waterbottle 
// s2 + s2: erbottlewaterbottlewat

// a + b 
// b + a + b + a

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;

class Solution {

  public static Boolean isRotation(String s1, String s2) {
    if(s1 == s2) return true; // ?? or false
    if(s1 == null || s2 == null) return false;
    if(s1.length() != s2.length) return false;

    return isSubstring(s2+s2, s1);
  }

  public static Boolean isSubstring(String s1, String s2) {
    return false; // given
  }
}













