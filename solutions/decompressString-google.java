import java.lang.Math;
import java.util.*;

/*

Given a compressed string in which a number followed by [] indicate how many times those characters occur,
 decompress the string
Eg. : a3[b2[c1[d]]]e will be decompressed as abcdcdbcdcdbcdcde.
Assume the string is well formed and number will always be followed by a [].



*/

class Solution {
    public static void main(String[] args) {   
        System.out.println(decomp("aa"));
        System.out.println(decomp("a1[b]"));
        System.out.println(decomp("a1[b1[c]]"));
        System.out.println(decomp("a2[b3[c]]"));
        System.out.println(decomp("a3[b2[c1[d]]]e"));
        System.out.println(decomp("a3[e]a3[a]2[x]y"));
    }


    public static String decomp(String txt) {

        StringBuffer resp = new StringBuffer();
        List<Integer> digits = new ArrayList<Integer>();

        for (int i = 0; i<txt.length(); i++) {

            char c = txt.charAt(i);

            if(Character.isDigit(c)) {
                
                digits.add(Character.getNumericValue(c));
                
            } else if(c == '['){

                // find rec input & result
                String recText = getBracketSubStr(txt.substring(i));
                String recResult = decomp(recText);

                // find repeat number
                int repeatNumber = getRepeatNumber(digits);

                // append rec result, repeat number times
                for(int k = 0; k<repeatNumber; k++) {
                    resp.append(recResult);    
                }

                // set i 
                i = i + recText.length() + 1; // OFF BY ONE ERR
                
                // clean digits
                digits = new ArrayList<Integer>();

            }else{
                resp.append(c);
            }
        }

        return resp.toString();
    }


    private static String getBracketSubStr(String txt){
        Stack st = new Stack();
        st.push('[');

        int i = 0;
        while (st.size() > 0) {
            i++;
            char c = txt.charAt(i);
            if(c == ']') {
                st.pop();
            } else if(c == '[') {
                st.push('[');                
            }
        }

        return txt.substring(1, i);
    }

    private static Integer getRepeatNumber(List<Integer> digits) {
        StringBuffer st = new StringBuffer();

        for(int i=0; i<digits.size(); i++) {
            st.append(digits.get(i));
        }

        return Integer.parseInt(st.toString());
    }
}

