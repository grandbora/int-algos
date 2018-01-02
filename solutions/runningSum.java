



-3 -5 -10 -8
mx runS curr candidateSum
-*  0    -3    -3
-3  0    -5    -5
-3  0

3 5 -10 -8
mx runS curr candidateSum
-*  0    3    

public int sum(int[] arr) {

  int max = integer.min_value;
  int runningSum = 0;

  for(int i = 0; i<arr.length; i++) {

    int curr = arr[i];
    int candidateSum = runningSum + curr;
    
    if(curr > 0) {
      runningSum = candidateSum;

    } else {
    
      if(candidateSum >= 0) {
        runningSum = candidateSum;
      } else {
        max = Math.max(runningSum, max);
        runningSum = 0;
      }
    }
  }

  return Math.max(runningSum, max);
}