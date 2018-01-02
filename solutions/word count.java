
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

import java.io.*;
import java.util.*;

class Solution {

    public static void main(String[] args) {
      Solution s = new Solution();

      s.punctuation.put('.', true);

      s.wordCountEngine("practice more practice.");
    }

    private Map<Character, Boolean> punctuation = new HashMap<>();

    public Map<String, Integer> wordCountEngine(String text) {
        HashMap<String, Integer> acc = new HashMap<String, Integer>();

        while(text.length() > 0) {
            String firstRawWord = readUntilFirstSpace(text);

// OFF BY ONE ERR 
// EDGE what if no space
            String rest = text.substring(firstRawWord.length() + 1); // inclusive + space
            if (rest.charAt(0) == ' '){
                rest = rest.substring(1);
            }

            processRawWord(firstRawWord, acc);

            text = rest;
        }
        return acc;
    }


    private String readUntilFirstSpace(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' '){
                return text.substring(0, i); // OFF BY ONE ERR
            }
        }
        return text;
    }

    private void processRawWord(String rawWord, Map<String, Integer> acc) {
        String sanitizedWord = sanitize(rawWord);
        if (acc.containsKey(sanitizedWord)) {
            Integer newCount = acc.get(sanitizedWord) + 1;
            acc.put(sanitizedWord, newCount);
        } else {
            acc.put(sanitizedWord, 1);
        }
    }

    
    private String sanitize(String rawWord) {
        StringBuilder q = new StringBuilder();
        for (int i = 0; i < rawWord.length(); i++) {
            Character current = rawWord.toLowerCase().charAt(i);
            if (punctuation.containsKey(current) == false) {
                q.append(current);
            }
        }

        return q.toString();
    }


}
