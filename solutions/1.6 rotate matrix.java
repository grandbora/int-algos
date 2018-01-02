// Given an image represented by an NxN matrix, 
// where each pixel in the image is 4 bytes, 
// write a method to rotate the image by 90 degrees.
//  Can you do this in place?


// 12
// 34

// 31
// 42
// =========
// 123
// 456
// 789

// 741
// 852
// 963
// ============
// 1234
// 5678
// 9abc
// defg

// d951
// ea62
// fb73
// gc84

// ===============

// 1 

// 1

// 12
// 34

// 31
// 42

// 1)
// get the most outer ring
// traverse it, shift it by one
// move onto inner ring
// TC: n SC:1



import org.apache.commons.lang3.ArrayUtils;

class Solution {

  // img assumed to be NxN
  
  // img
  // 1234
  // 5678
  // 9abc
  // defg

  // n = 4

  // i = 4
  // rotateRing(4, img);

  public static String[][] rotate(String[][] img){
    if(img == null) return null;
    int n = img.length;

    for(int i = n; i > -1; i = i - 2) {
      
      rotateRing2(i, img);  
      // for(int x = 0; x < i - 1; x ++) {
      //   rotateRing(i, img);  
      // }
    }

    return img;
  }

  // img
  // 1234
  // 5678
  // 9abc
  // defg

  // r = 4
  // n = 4
  // offset = 0
  // toBeInserted = img[1][0] = 5

  // i = 0 
  // temp = img[0][0] = 1
  // img[0][0] = 5
  // toBeInserted = 1
  // img
  // 5234
  // 5678
  // 9abc
  // defg

  // i = 1 
  // temp = img[0][1] = 2
  // img[0][1] = 1
  // toBeInserted = 2
  // img
  // 5134
  // 5678
  // 9abc
  // defg

  // i = 2 
  // temp = img[0][2] = 3
  // img[0][2] = 2
  // toBeInserted = 3
  // img
  // 5124
  // 5678
  // 9abc
  // defg

  // i = 3 
  // temp = img[0][3] = 4
  // img[0][3] = 3
  // toBeInserted = 4
  // img
  // 5123
  // 5678
  // 9abc
  // defg

  // i = 1
  // temp = img[1][3] = 8
  // img[1][3] = 4
  // toBeInserted = 8
  // img
  // 5123
  // 5674
  // 9abc
  // defg

  // i = 2
  // temp = img[2][3] = c
  // img[2][3] = 8
  // toBeInserted = c
  // img
  // 5123
  // 5674
  // 9ab8
  // defg

  // i = 3
  // temp = img[3][3] = g
  // img[3][3] = c
  // toBeInserted = g
  // img
  // 5123
  // 5674
  // 9ab8
  // defc

  // i = 1
  // temp = img[3][2] = f
  // img[3][2] = g
  // toBeInserted = f
  // img
  // 5123
  // 5674
  // 9ab8
  // degc

  public static String[][] rotateRing(int r, String[][] img){
    if(r == 0 || r == 1) return img;

    int n = img.length;
    int offset = (n - r) / 2;

    String toBeInserted = img[offset+1][offset];

    // first row
    for(int i = 0; i < r ; i ++) {
      String temp = img[offset][offset + i];
      img[offset][offset + i] = toBeInserted;
      toBeInserted = temp;
    }

    // last col
    for(int i = 1; i < r ; i ++) {
      String temp = img[offset + i][n - 1 - offset];
      img[offset + i][n - 1 - offset] = toBeInserted;
      toBeInserted = temp;
    }

    // last row
    for(int i = 1; i < r ; i ++) {
      String temp = img[n - 1 - offset][n - 1 - offset - i];
      img[n - 1 - offset][n - 1 - offset - i] = toBeInserted;
      toBeInserted = temp;
    }

    // first col
    for(int i = 1; i < r ; i ++) {
      String temp = img[n - 1 - offset - i][offset];
      img[n - 1 - offset - i][offset] = toBeInserted;
      toBeInserted = temp;
    }

    return img;
  }

  public static void main(String[] args) {
    String[][] img1 = new String[][]{};
    System.out.println("in:");
    printImg(img1);
    System.out.println("out:");
    printImg(rotate(img1));

    String[][] img2 = new String[][]{
      {"1"}
    };
    System.out.println("in:");
    printImg(img2);
    System.out.println("out:");
    printImg(rotate(img2));

    String[][] img3 = new String[][]{
      {"1","2"},
      {"3","4"},
    };
    System.out.println("in:");
    printImg(img3);
    System.out.println("out:");
    printImg(rotate(img3));

    String[][] img4 = new String[][]{
      {"1","2","3"},
      {"4","5","6"},
      {"7","8","9"},
    };
    System.out.println("in:");
    printImg(img4);
    System.out.println("out:");
    printImg(rotate(img4));

    String[][] img5 = new String[][]{
      {"1","2","3","4"},
      {"5","6","7","8"},
      {"9","a","b","c"},
      {"d","e","f","g"},
    };
    System.out.println("in:");
    printImg(img5);
    System.out.println("out:");
    printImg(rotate(img5));
  }

  public static void printImg(String[][] img) {
    for(int i = 0; i < img.length; i++){

      for(int z = 0; z < img.length; z++){
        System.out.print(img[i][z]);
      }
      System.out.println();
    }
    System.out.println();
  }

  // xxxx
  // xxxx
  // xxxx
  // xxxx



  public static void rotateRing2(int r, String[][] img) {
    if(r == 0 || r == 1) return;

    int n = img.length;
    int offset = (n - r) / 2;

    for(int i = 0; i < r - 1; i ++){

      String top = img[offset][offset + i];
      String right = img[offset + i][n - 1 - offset];
      String bottom = img[n - 1 - offset][n - 1 - offset - i];
      String left = img[n - 1 - offset - i][offset];

      String temp = top;

      // top
      img[offset][offset + i] = left;

      // left
      img[n - 1 - offset - i][offset] = bottom;

      // bottom
      img[n - 1 - offset][n - 1 - offset - i] = right;

      // right
      img[offset + i][n - 1 - offset] = temp;
    }
  }
}






