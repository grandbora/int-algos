Chef likes strings a lot but he likes palindromic strings more. Today, Chef has two strings A and B, each consisting of lower case alphabets.
Chef is eager to know whether it is possible to choose some non empty strings s1 and s2 where s1 is a substring of A, s2 is a substring of B such that s1 + s2 is a palindromic string. Here '+' denotes the concatenation between the strings.

A, B strings, all lower case alphabets

s1 : Substring of A
s2 : Substring of B
s1 and s2 are none empty

s2 = Palindrome + s1.reverse

is there any s1,s2 where s1+s2 is a plaindrome?

A: Bora
B: Tunca

s1:a
s2:a
aa is plaindrome

s1:a
s2:ca
aca is plaindrome
==========
s1 : ra
s2 :  … ar
ra … ar
===========
s2: r
s1: palindrome + s2.reverse
s1: r … 

s2 = Palindrome + s1.reverse
s2 = s1.reverse


*- s2 = s1.reverse
*- s2 = s1.substring(0,n).reverse && s1.rest is palindrome 
  s1: ra
  s2: r
  s1: raaba
  s2: ar

  s1: ra
  s2: ar
  s1: r
  s2: r
*- s2 = Palindrome + s1.reverse // not interested 


=========================

ret true if same char exists


n,m lengths of s1,s2
1)BF
take each char from s1
check if exists in s2
TC: n*m 

2)map
s2 is small word, s1 is large
create s2 char map of s2 chars 
take each char from s1
check if exists in s2charmap
TC: n + m SC: m


public Boolean haveSameChar(String word1, String word2){
if(word1 == null || word1.length == 0 || word2 == null || word2.length == 0) {
  return false;
}

String short,long;
if(word1.length > word2.length) {
  short = word2; 
long = word1;
}else{
  short = word1; 
long = word2;
}

HashMap shortMap = getCharMap(short);

for(int i = 0; i < long.length; i++){
  char c = long.charAt(i);
  if(shortMap.contains(c)) {
return true;
}
}

return false;
}

getCharMap
