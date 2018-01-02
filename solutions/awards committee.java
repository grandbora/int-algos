The awards committee of your alma mater (i.e. your college/university) asked for your assistance with a budget allocation problem they’re facing. Originally, the committee planned to give N research grants this year. However, due to spending cutbacks, the budget was reduced to newBudget dollars and now they need to reallocate the grants. The committee made a decision that they’d like to impact as few grant recipients as possible by applying a maximum cap on all grants. Every grant initially planned to be higher than cap will now be exactly cap dollars. Grants less or equal to cap, obviously, won’t be impacted.
Given an array grantsArray of the original grants and the reduced budget newBudget, write a function findGrantsCap that finds in the most efficient manner a cap such that the least number of recipients is impacted and that the new budget constraint is met (i.e. sum of the N reallocated grants equals to newBudget).
Analyze the time and space complexities of your solution.
 
 
// n grants are given
// budget is reduced to newBudget
// find the cap that will be applied to high end and will allow least amount of impacted grants
 
// Are the grants sorted ?
 
//1)
// target delta is budget - newBudget
// sort grants
// start from the high end, reduce it to the next grant
// check if delta is achieved
// check if delta is achieved, if not move on to next one
// apply cap
// if saved amount is greater than target delta, apply wieghted average
 
// TC: nlogn SC: 1
 
 
// grants = 100 80 70 60 => 310
// newBudget = 280
// budget = 310
// target = 30
// grants = 100 80 70 60
 
// saved = 0
// cap = 0
// i = 0
// currentGrant = 100
// cap = 80
// saved = 20
// i = 1
// currentGrant = 80
// cap = 70
// saved = 20 + 10 * 2 = 40
// cap = (80 * 2 + 70) / 3 = 76
// ret 76
 
 
// grants = 100 80 => 
// newBudget = 10
// budget 180
// saved = 0
// cap = 0
// currentGrant = 100
// cap = 80
// saved = 20
 
public in findGrantsCap(int[] grants, newBudget) {
  int budget = 0;
  for(int i = 0; i < grants.length; i++) {
    budget = budget + grants[i];
}
 
int target = budget - newBudget // Assume newBudget < budget // or validate
 
Arrays.sort(grants); // Assume descending order, highest is at the beginning;
 
int saved = 0;
int cap = 0;
 
  for(int i = 0; i < grants.length - 1; i++) { 
    int currentGrant = grants[i];
    int cap = grants[i + 1];
    int saved = saved + (currentGrant - cap) * (i +1)
    if(saved == target) {
      return cap;
} else if(saved > target) {
  cap = (currentGrant * (i+1) + cap) / (i + 2);
  return cap;
}
}
 
// reaches only when lowest grant is not a cap
// either err or newBudget / grants.length
return -1;
}
