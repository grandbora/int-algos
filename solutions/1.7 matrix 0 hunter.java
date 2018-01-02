
// Write an algorithm such that if an element in an MxN matrix is 0, 
// its entire row and column are set to 0.

// in: int[][]

// 123412
// 320356
// 876511

// 1204
// 0000

// if multiple zero elms exist?  // apply to all
// no 0 => no changes
// matrix size? fits in the memory
// assume all elms are ints


// 1a) BF + modify
// iterate all the elements
// on 0 => save the colm and row indexes
// finally; adjust all the rows and the cols
// TC: m*n SC: m+n

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;

class Solution {


  // unbalanced matrix ????


  // matrix
  // 1234
  // 8705

  // rowCount = 2
  // colCount = 4

  // rc = 0 
  // cc = 0 
  // current = 1

  // rc = 0 
  // cc = 1 
  // current = 2

  // rc = 0 
  // cc = 2 
  // current = 3

  // rc = 0 
  // cc = 3 
  // current = 4

  // rc = 1 
  // cc = 0 
  // current = 8

  // rc = 1 
  // cc = 1 
  // current = 7

  // rc = 1 
  // cc = 2 
  // current = 0
  // rowsToBeUpdated = {1T}
  // colsToBeUpdated = {2T}

  // 1204
  // 0000


  public static int[][] modifyZero(int[][] matrix) {

    if(matrix == null) return null;

    int rowCount = matrix.length;
    if(rowCount == 0) return matrix;

    int colCount = matrix[0].length;
    if(colCount == 0) return matrix;

    Boolean[] rowsToBeUpdated = new Boolean[rowCount];
    Arrays.fill(rowsToBeUpdated, false);

    Boolean[] colsToBeUpdated = new Boolean[colCount];
    Arrays.fill(colsToBeUpdated, false);

    for(int rc = 0; rc < rowCount; rc++) {

      for(int cc = 0; cc < colCount; cc++) {
        int current = matrix[rc][cc];

        if(current == 0) {
          rowsToBeUpdated[rc] = true;
          colsToBeUpdated[cc] = true;
        } 
      }
    }

    for(int i = 0; i < rowCount; i++) {
      if(rowsToBeUpdated[i] == true){
        updateRow(i, matrix);
      }
    }

    for(int i = 0; i < colCount; i++) {
      if(colsToBeUpdated[i] == true){
        updateCol(i, matrix);
      }
    }

    return matrix;
  }  

  public static void updateRow(int rc, int[][] matrix) {
    for(int i = 0; i < matrix[0].length; i++) {
      matrix[rc][i] = 0;
    }
  }

  public static void updateCol(int cc, int[][] matrix) {
    for(int i = 0; i < matrix.length; i++) {
      matrix[i][cc] = 0;
    }
  }


  public static void main(String[] args) {
    printM(new int[][] {});

    printM(new int[][] {
      {1,2,3},
      {4,5,6},
    });

    printM(new int[][] {
      {1,0,3},
      {4,5,6},
    });

    printM(new int[][] {
      {1,0,3},
      {4,5,6},
      {0,5,6},
    });

    printM(new int[][] {
      {1,0,3},
      {4,5,6},
      {8,0,6},
    });
  }

  public static void printM(int[][] matrix) {
    System.out.println("in:");
    printMatrix(matrix);
    System.out.println("out:");
    printMatrix(modifyZero(matrix));
    System.out.println();
  }

  public static void printMatrix(int[][] img) {
    for(int i = 0; i < img.length; i++){

      for(int z = 0; z < img[0].length; z++){
        System.out.print(img[i][z]);
      }
      System.out.println();
    }
    System.out.println();
  }


}













