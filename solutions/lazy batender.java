
import java.util.*;

/*
Lazy Bartender :

There are N number of possible drinks.(n1,n2..)
Has C number of fixed customers.
Every customer has fixed favourite set of drinks.
Bartender has to create least possible number of drinks
to suffice need of all the customers
Example:
Cust1: n3,n7,n5,n2,n9
Cust2: n5
Cust3: n2,n3
Cust4: n4
Cust5: n3,n4,n3,n5,n7,n4

Output: 3(n3,n4,n5)


*/

/*
N amount of drinks => n1 n2 n3 …  nN
C number of customer => cust1 cust2 … custC

satisfy every customer by making least variety of drinks.
How many different types of drinks are required

Cust1: n3,n7,n5,n2,n9
Cust2: n5
Cust3: n2,n3
Cust4: n4
Cust5: n3,n4,n3,n5,n7,n4

Output: 3 (n3,n4,n5)

size of  N, C  ? reasonable.. 
what if there are multiple solutions? still number is the same

if customer has no fav drink // err or skip
no customers => 0 drinks


S1) Try all the drinks
start from n1
check how many cust can be satisfied, if 0 discard
if the cust set is a subset of other drink discard
else record drink -> CustSet(C1,C2...)
repeat for all
….

S2) Look for 1 drinks
Custs with 1 drink has to be served that drink
reduce the problem, 
in case there are no such custs

S3) Try fail customer drinks / divide&conq
pick customer 1, pick drink1
eliminate all the customers that has this drink
recurse with the rest of the customers
move onto next drink of cust1
repeat this for all of the drinks
return the min

TC: n ^ c  SC: c


Cust1: n3,n7,n5,n2,n9
Cust2: n5
Cust3: n2,n3
Cust4: n4
Cust5: n3,n4,n3,n5,n7,n4

input
n: int
c: int
favorites: HashMap (customer -> Set(drink))
        int => Set<int>

out: int



/*  T1
Cust1: n3,n7
Cust2: n5
Cust3: n2,n3

customer= 1
drinks = 3,7
minDrinks= MAX_VALUE

-------------- it1
currentDrink= 3
tmpCF = CLONE

tmpCF = 2->5
currentMin = recurse(2->5) ⇒ 1
minDrinks = 1

-------------- it2
currentDrink= 7
tmpCF = CLONE

tmpCF = 2->5 ,  3-> 2,3
currentMin = recurse(2->5 ,  3-> 2,3) ⇒ 2
minDrinks = 1

ret 1 + 1 = 2
*/


/*

CF:
Cust2: n5

customer = 2
drinks = 5
currentDrink = 5

filterOutCustomersWithDrink(2=>5, 5);
tmpCF = {}
minDrinks= 0
ret 1 + 0 = 1

*/
class Solution {

    public static void main(String[] args) {
        System.out.println("Hello world.");

        HashMap customerFavorites = new HashMap();
        Set<Integer> c1drinks = new HashSet<Integer>();
        c1drinks.add(1);
        c1drinks.add(2);
        customerFavorites.put(1, c1drinks);

        Set<Integer> c2drinks = new HashSet<Integer>();
        c2drinks.add(3);
        // c2drinks.add(2);
        customerFavorites.put(2, c2drinks);

        Integer res = lazyBartender(customerFavorites);


        System.out.println("res =============");
        System.out.println(res);

    }

    public static Integer lazyBartender(HashMap customerFavorites) {
        if (customerFavorites == null) {
            return -1; // throw new Exception("null customers");
        }

        if (customerFavorites.isEmpty()) {
            return 0;
        }

        int customer = (int) customerFavorites.keySet().iterator().next();
        Set<Integer> drinks = (Set<Integer>) customerFavorites.get(customer);

        if (drinks == null) {
            return -1; //throw new Exception("null drinks"); // some form of err or skip this cust

        }

        if (drinks.isEmpty()) {
            return -1; //throw new Exception("empty drinks"); // some form of err or skip this cust
        }

        int minDrinks = Integer.MAX_VALUE;

        for (int currentDrink : drinks) {
            HashMap tmpCF = (HashMap) customerFavorites.clone();

            System.out.println("DEBUG1 size");
            System.out.println(tmpCF.size());
            filterOutCustomersWithDrink(tmpCF, currentDrink);

            System.out.println("DEBUG2 size");
            System.out.println(tmpCF.size());

            int currentMin = lazyBartender(tmpCF);

            if (currentMin < minDrinks) {
                minDrinks = currentMin;
            }
        }

        return minDrinks + 1;
    }


    /*
    CF:
    Cust1: n3,n7
    Cust2: n5
    Cust3: n2,n3

    drink=3

    ----------- it1
    customer:1
    cDrinks =3,7
    CF:
    Cust2: n5
    Cust3: n2,n3

    ----------- it2
    customer:2
    cDrinks =5
    CF:
    Cust2: n5
    Cust3: n2,n3

    ----------- it3
    customer:3
    cDrinks =2,3
    CF:
    Cust2: n5



    ret cust2-> 5

    */
    private static HashMap filterOutCustomersWithDrink(HashMap customerFavorites, int drink) {


        System.out.println("11111 ==");
        System.out.println(customerFavorites.size());
        System.out.println(drink);

        ArrayList<Integer> toBeRemoved = new ArrayList<>();

        for (Integer customer : (Set<Integer>) customerFavorites.keySet()) {

            System.out.println(customer);

            Set<Integer> cDrinks = (Set<Integer>) customerFavorites.get(customer);

            System.out.println(cDrinks);
            System.out.println(Arrays.toString(cDrinks.toArray()));

            if (cDrinks != null && cDrinks.contains(drink)) {
                toBeRemoved.add(customer);
            }
        }

        for (Integer removeKey : toBeRemoved) {
            System.out.println("REMOVING:::");
            System.out.println(removeKey);
            customerFavorites.remove(removeKey);
        }

        System.out.println("111222 ==");
        System.out.println(customerFavorites.size());

        return customerFavorites;
    }

}
  
 
 
 
