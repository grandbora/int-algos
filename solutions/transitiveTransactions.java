import java.lang.Math;
import java.util.*;

/*

transactions = [ 
{"payee": "BoA", "amount": 132, "payer": "Chase"}, 
{"payee": "BoA", "amount": 827, "payer": "Chase"}, 
{"payee": "Well Fargo", "amount": 751, "payer": "BoA"}, 
{"payee": "BoA", "amount": 585, "payer": "Chase"}, 
{"payee": "Chase", "amount": 877, "payer": "Well Fargo"}, 
{"payee": "Well Fargo", "amount": 157, "payer": "Chase"}, 
{"payee": "Well Fargo", "amount": 904, "payer": "Chase"}, 
{"payee": "Chase", "amount": 976, "payer": "BoA"}, 
{"payee": "Chase", "amount": 548, "payer": "Well Fargo"}, 
{"payee": "BoA", "amount": 872, "payer": "Well Fargo"}, 


There are multiple transactions from payee to payer. Consolidate all these transactions to 
minimum number of possible transactions. 
HINT: Consolidate transitive transactions along with similar transactions 

For Example in the above program, the result is a single transaction [ Boa -> 482 -> Wells Fargo ]



// get entry key
// check if the key is still there
// traverse trans
// track changes

/*
    {a => a30b, a10c}
    {b => b20c,}

    ==
    {a => a10b, a20c, a10c}
    {b => }


    -------------
    {a => a10b, a10c}
    {b => b20c,}

    {a => a10c, a10c}
    {b => b10c,}

    min of the transitive


    //////////////


    Transaction {payer, amount, payee}
    
    T1

    {a => a30b, a10c}
    {b => b20c,}

    =============

    after eliminate:
    {a => a10b, a10c, a20c}
    {b => b0c}


    /*
        // remove no trans entries
        // remove 0 amount trans
        // sum trans to same payee

        after eliminate:
        {a => a10b, a10c, a20c}
        {b => b0c}


    // MISSING
    //  first method swap all payee<>payer

    // unit tests
        // input validation
            // empty, null

        // edge cases
            // circular transactions
            // transactions to self

    // TODO:
    // createTMap
    // consolidateFromSamePayee // remove 0 amount trans , remove no trans entries
    // eliminateTransitive
    // toTransArr
*/

class Solution {

    class Transaction {
        String payer;
        int amount;
        String payee;

        public Transaction(String payer, int amount, String payee) {
            this.payer = payer;
            this.amount = amount;
            this.payee = payee;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        HashMap<String, ArrayList<Transaction>> tMap = new HashMap();
        ArrayList<Transaction> aTrans = new ArrayList<>();
        aTrans.add(s.new Transaction("A", 30, "B"));
        aTrans.add(s.new Transaction("A", 10, "C"));
        tMap.put("A", aTrans);

        ArrayList<Transaction> bTrans = new ArrayList<>();
        bTrans.add(s.new Transaction("B", 10, "D"));
        bTrans.add(s.new Transaction("B", 10, "A"));
        bTrans.add(s.new Transaction("B", 10, "C"));
        tMap.put("B", bTrans);

        System.out.println("IN:");
        pprint(tMap);
        consolidate(tMap);
        System.out.println("OUT:");
        pprint(tMap);
    }
    public static void pprint(HashMap<String, ArrayList<Transaction>> tMap) {

        Set<String> keys = tMap.keySet();

        for(String key: keys) {

            System.out.println(key);
            ArrayList<Transaction> keyTrans = tMap.get(key);
            
            for(int i = 0; i<keyTrans.size(); i++) {

                Transaction t = keyTrans.get(i);
                System.out.print(t.payer + "-" + t.amount + "-" + t.payee);
                System.out.println();
            }
        }
    }

    public static HashMap<String, ArrayList<Transaction>> consolidate(HashMap<String, ArrayList<Transaction>> tMap) {
   
        boolean hasChanged = true;

        while(hasChanged) {
            consolidateFromSamePayee(tMap);
            hasChanged = eliminateTransitive(tMap);
        }
        
        return tMap;
    }

    private static boolean eliminateTransitive(HashMap<String, ArrayList<Transaction>> tMap){
        boolean hasChanged = false;
        Set<String> keys = tMap.keySet();

        for(String key: keys) {

            if(tMap.containsKey(key) == false) { continue; }

            ArrayList<Transaction> keyTrans = tMap.get(key);
            ArrayList<Transaction> newDirectTrans = new ArrayList<>();

            for(int i = 0; i<keyTrans.size(); i++) {

                Transaction t = keyTrans.get(i);

                if(tMap.containsKey(t.payee)) {
                    hasChanged = true;
                    ArrayList<Transaction> transitiveTrans = tMap.get(t.payee);

                    for(Transaction transitiveT: transitiveTrans) {

                        if(t.amount == 0) {
                            break;
                        }

                        int eliminateAmount = Math.min(transitiveT.amount, t.amount);
                        t.amount = t.amount - eliminateAmount; // t.amount can get to 0!
                        transitiveT.amount = transitiveT.amount - eliminateAmount; // can get to 0!

                        Transaction directT = new Solution().new Transaction(t.payer, eliminateAmount, transitiveT.payee);

                        // add directT to source entry
                        newDirectTrans.add(directT);
                    }
                }
            }

            keyTrans.addAll(newDirectTrans);
        }

        return hasChanged;
    }

    private static void consolidateFromSamePayee(HashMap<String, ArrayList<Transaction>> tMap) {
        

        Set<String> keys = tMap.keySet();

        for(String key: keys) {

            ArrayList<Transaction> keyTrans = tMap.get(key);

            HashMap<String, Transaction> consolidationMap = new HashMap<>();
            for(int i = 0; i<keyTrans.size(); i++) {
                Transaction current = keyTrans.get(i);

                if(current.amount == 0) {
                    keyTrans.remove(i);
                    i--;
                }else if(current.payee == key) {
                    keyTrans.remove(i);
                    i--;
                }else{
                    
                    if(consolidationMap.containsKey(current.payee)){
                        Transaction t = consolidationMap.get(current.payee);
                        t.amount = t.amount + current.amount;
                    } else {
                        consolidationMap.put(current.payee ,current);
                    }   
                }
            }

            //overwrite key values
            Transaction[] consolidatedTs = new Transaction[consolidationMap.values().size()];
            Object[] values = consolidationMap.values().toArray();
            for(int ck = 0; ck<consolidatedTs.length; ck++){
                consolidatedTs[ck] = (Transaction) values[ck];
            }
            

            if(consolidatedTs.length == 0) {
                tMap.remove(key);    
            } else{
                tMap.put(key, new ArrayList<Transaction>(Arrays.asList(consolidatedTs)));    
            }
            
        }
    }
}

