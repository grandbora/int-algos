import java.lang.Math;
import java.util.*;

/*

If a=1, b=2, c=3,....z=26. 
Given a string, find all possible codes that string can generate. 
//Give a count as well as print the strings.

For example:
Input: "1123". You need to general all valid alphabet codes from this string.

in: 1123
1 1 2 3
11 2 3
11 23
1 12 3
etc/

In: string of digits
// assume valid, only pos integers

out: List[String]

* all valid codes are betw 1-26


1)BF
define all possible starting chars
recurse rest of the chars for each

 - xyzk

 x + rec(yzk)
 xy + rec(zk)

TC: 2^n SC: n

Call tree
xyzk

x + yzk            xy + zk

x+y+ zk | x+ yz+ k   3   4


n deep 
1
2
4
8

2^n


2) optimize? with dynamic programming
cache intermediary results

1234
1 234                12 34
1 2 34 1 23 4           
*/


class Solution {


    public static void main(String[] args) {

    }

    /*

    code: 123
    opt1: 1
    char1: a
    rest: 23
    restRes: REC(23) => "bc", "x"
    resultChunk1: [], "abc", "ax"

    opt2 = 12
    char2 = "f"
    rest2 = 3
    restResult2: decipher(3) => "c"
    resultChunk2: "fc"

            1 2 3   1 23    12 3
    return "abc",   "ax",   "fc"


    code: null
    ret []
    code: ""
    ret [""]

    code:1
    opt1: 1
    char1: a
    rest: ""
    restResult = REC("")= [""]
    resultChunk1= [null], "a"
    */

    public static String[] decipher(String code){
        if(code == null) {
            return new String[0];
        }

        if(code == "") {
            return new String[]{""};
        }

        // check if code is all digits

        // opt 1
        String opt1 = code.subString(0,1);
        String char1 = codeToChar(opt1);

        String rest = code.subString(1);
        String[] restResult = decipher(rest);

        String[] resultChunk1 = new String[restResult.length];

        for(int i=0; i<resultChunk1.length; i++ ) {
            resultChunk1[i] = char1 + restResult[i];
        }

        // opt 2
        if(code.size() < 2) {
            return resultChunk1;
        }

        String opt2 = code.subString(0,2);
        String char2 = codeToChar(opt2);
        
        if(char2 == null) {        
            return resultChunk1;
        } 

        String rest2 = code.subString(2);
        String[] restResult2 = decipher(rest2);

        String[] resultChunk2 = new String[restResult2.length];

        for(int i=0; i<resultChunk2.length; i++ ) {
            resultChunk2[i] = char2 + restResult2[i];
        }

        return Arrays.addAll(resultChunk1, resultChunk2);
        
    }

    private static String codeToChar(String code) {

        // TODO create map a-z => 1-26
        // if map contains return char if not null

        if(code == "1") {
            return "a";
        } else if(code == "2") {
            return "b";
        }//else 
        //...
        //...
        else if(code == "26") {
            return "z";
        }else{
            return null;
        }
    }

//MISSING TODO
    // input validation
    // remove code duplicate
    
    // TESTs
        // null , empty, single digit, two digit
        // happy path (123)
        // edge cases?

    // 12312312423423
}