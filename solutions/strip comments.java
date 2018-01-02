import java.lang.Math;
import java.util.*;

/*


*/


class Solution {

  
    public static void main(String[] args) {

        System.out.println(stripComments("code1 // comments \\n code2"));

    }

    /*

    Edge case : 
    nested comments
    // asdasd /* asdasd
    /* asdasd // asdasd
    
    /* / together
    //\n

    end of file inline or block comment ??


    MISSING :
    unit test
    happy path 
    edge cases
    */

    /*

    text   = "code1 // comment \n code2"
              0123456789012345678901234
    result = "code  "

    commentBlockBegun = F
    inlineCommentBegun = F,T,F
    i = 0,1,2,3,4,5,6,7,8,9,16,17,18,19
    c = c,o,d,e,1, ,/, ,c..,\, ,
    nextChar = o,d,e,1,/,/,c,o,..,n,c

    // don't delete line breaks
    // 
    */

    public static String stripComments(String text) {
        if(text == null || text == "") { return ""; }
        
        StringBuffer result = new StringBuffer();

        boolean commentBlockBegun = false;
        boolean inlineCommentBegun = false;

        for(int i=0; i< text.length(); i++) {
            char c = text.charAt(i);

            if(commentBlockBegun) { 
                Character nextChar = null;
                if(i + 1 < text.length()) {
                    nextChar = text.charAt(i + 1);
                }

                if(c == '*' && nextChar == '/' ){ // EDGE CASE /*/
                    commentBlockBegun = false;
                    i++;
                }
                
            } else if(inlineCommentBegun){
                Character nextChar = null;
                if(i + 1 < text.length()) {
                    nextChar = text.charAt(i + 1);
                }

                if(c == '\\' && nextChar == 'n' ){
                    inlineCommentBegun = false;
                    i++;
                }

            }else{

                Character nextChar = null;
                if(i + 1 < text.length()) {
                    nextChar = text.charAt(i + 1);
                }

                if(c == '/' && nextChar == '*' ){
                    commentBlockBegun = true;
                    i++;
                } else if(c == '/' && nextChar == '/' ) {
                    inlineCommentBegun = true;
                    i++;
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }

    /*

    */
}