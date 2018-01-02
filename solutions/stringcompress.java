/*
Write a function that compresses a string (similar to LZ77).
compress the string only if it is getting smaller


aaabbb222ce77jj

a3b323c1e172j2

string fits in the memory
ascii chars, no utf-8 encoding, number & letters, symbols

1)
init string buffer

hold a counter for repeating chars, starting from 0
init previous first char


traverse the string
get the current char
if it has been different then previous 
  inc counter
if not
append the char and the count to str buffer
  reset counter to 1

finish the loop

compare str lengths

TC: n SC:word.length

*/

public string compress(String word) {

if(word == null || word.length == 0 || word.length == 1) {
  return word;
}

StringBuffer sb = new StringBuffer();

char prev = word.charAt(0);
int repeatCount = 1;
  
  for(int i = 1; i<word.length; i++){
    char curr = word.charAt(i);
    if(curr == prev) {
      repeatCount =+ 1;
} else {
  sb.append(prev);
  sb.append(repeatCount);
    repeatCount = 1;
    prev = curr;
}
}

String resCand = sb.toString();
if(word.length > resCand.length) {
  return resCand;
} else {
  return word;
}
}






