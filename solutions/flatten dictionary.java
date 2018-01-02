// given dictionary dict
// dict has values that are raw string and dictionaries that contains string or dictionaries
// flatten the dictionary
// concact parent names with '.' to create flattened key names

// dict size? assume fits in the memmory
// conflicting keys ? assume no
// neting may be n levels deep where n is finite


// n: total amount of entries

//1) iterate & collect
//  iterate the keys
// for each dictionary in the value
// repeat the process recursively
// concatanate all the dictionaries with the prefix

// TC: n SC: size of nesting // ==> n 


import java.io.*;
import java.util.*;


// dict = {
// Key1 : 1,
// Key2 : {
//     a : 2,
//     b : 3,
// }

// dict above
// result = {}
// keySet == dict.keyset
// i = 0
// currentKey = key1
// currentValue = 1

// result = {key1=>1}

// i = 1
// currentKey = key2
// currentValue = dictionary2

//  flattened = flattenDictionary(dictionary2)
// flattened
//    a : 2,
//    b : 3,

// inner loop
// i = 0
// flattenedKey = a
// flattenedValue = 2
// result = {key1=>1, key2.a=>2}

//   // inner loop is over
// result = {key1=>1, key2.a=>2, key2.b=>3}


//i =2
// ret {key1=>1, key2.a=>2, key2.b=>3}


class Solution {

    static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {

        HashMap<String, String> result = new HashMap<String, String>();

        for (String currentKey : dict.keySet()) {
            Object currentValue = dict.get(currentKey);

            if (currentValue instanceof String) {
                result.put(currentKey, (String) currentValue);

            } else {

                HashMap<String, String> flattened = flattenDictionary((HashMap<String, Object>) currentValue);
                for (String flattenedKey : flattened.keySet()) {
                    String flattenedValue = flattened.get(flattenedKey);
                    result.put(currentKey + "." + flattenedKey, flattenedValue);
                }
            }
        }

        return result;
    }


    static HashMap<String, String> flattenDictionary2(HashMap<String, Object> dict) {

        HashMap<String, String> result = new HashMap<String, String>();

        Queue q = new LinkedList<HashMap<String, Object>>();
        q.add(dict);

        while (q.isEmpty() == false) {

            HashMap<String, Object> currentDict = (HashMap<String, Object>) q.remove();

            for (String currentKey : currentDict.keySet()) {
                Object currentValue = currentDict.get(currentKey);
                if (currentValue instanceof String) {
                    result.put(currentKey, (String) currentValue);
                } else {
                    HashMap<String, Object> replacementMap = new HashMap<String, Object>();
                    HashMap<String, Object> nestedDict = (HashMap<String, Object>) currentValue;
                    for (String nestedKey : nestedDict.keySet()) {
                        Object nestedValue = nestedDict.get(nestedKey);
                        replacementMap.put(currentKey + "."+nestedKey, nestedValue);
                    }
                    q.add(replacementMap);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {

        HashMap<String, Object> dict = new HashMap<String, Object>();
        dict.put("dict1-1", "a");
        dict.put("dict1-2", "b");
        HashMap<String, Object> dict2 = new HashMap<String, Object>();
        dict2.put("dict2-1", "a2");
        dict2.put("dict2-2", "b2");

        HashMap<String, Object> dict3 = new HashMap<String, Object>();
        dict3.put("dict3-1", "a3");
        dict3.put("dict3-2", "b3");

        dict2.put("dict2-3", dict3);

        dict.put("dict1-3", dict2);
        System.out.println(flattenDictionary(dict));
        System.out.println(flattenDictionary2(dict));
    }

}
  
 
