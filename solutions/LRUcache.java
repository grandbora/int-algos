
/*
Design a LRU cache

Definition of “least recently used” : An access to an item is defined as a get or a set operation of the item. “Least recently used” item is the one with the oldest access time.

api:
get(key) : Resource
set(key, Resource) : void

evict the least recently used item from the cache

Use case:

set(1, res)
set(2, res)
get(1)
get(2)
set(3, res)
set(4, res) ====> cache is full
get(1)
get(4)
set(5, res) ====> oldest accessed has to be evicted (2 is oldest at this point)
cache: {1,3,4,5} ==> 2 is evicted
get(2) ===> No resource
get(1)
get(5)
set(5, res) ===> edge case, update existing key


Usecases:
set new key when cache has free space
get existing key from cache

get inexisting key from cache

set new key when cache is full, evicts lru
set existing key when cache is full / or not


priority queue
linked list
array
tree

reqs:
TC O(1) in get and set methods

*hashmap for key value
*for last acc timestap: linked list



0)hasmap and timestamps
requires TC:n 
does not meet crit

1)hashmap and linked list
requires random access on linked list
TC:n 
does not meet crit

cache: {}

set(1, res)
cache: {1:1} evict: 1 -> null

set(2, res)
cache: {1:1, 2:2} evict: 1 -> 2-> null

get(1)
cache: {1:1, 2:2} evict: 2-> 1 -> null

set(3, res)
cache: {1:1, 2:2, 3:3} evict: 2-> 1 -> 3 -> null

get(1)
cache: {1:1, 2:2, 3:3} evict: 2-> 3 -> 1 -> null

set(4, res) ====> cache is full
get(1)
get(4)
set(5, res) ====> oldest accessed has to be evicted (2 is oldest at this point)
cache: {1,3,4,5} ==> 2 is evicted
get(2) ===> No resource
get(1)
get(5)
set(5, res) ===> edge case, update existing key


2)hashmap and pointers to next
requires update on the record pointing the adressed one
TC:n 
does not meet crit

cache: {}

set(1, res)
cache: {1:nextNull} lru:1 mru:1

set(2, res)
cache: {1:next=2 , 2:next=Null } lru:1 mru:2

get(1)
cache: {1:next=Null , 2:next=1 } lru:2 mru:1

set(3, res)
cache: {1:next=3 , 2:next=1, 3:next=Null } lru:2 mru:3

get(1)
cache: {1:next=Null , 2:next=1, 3:next=1 } lru:2 mru:1
**** need to update the record points to the accessed element O(n)



3)hashmap and doubly linked list
//edge case update lru value

cache: [(key, prev,next)] = { } 

set(1, res)
cache: {(1,null,null)} lru:1 mru:1

set(2, res)
cache: {(1,null,2), (2,1,null)} lru:1 mru:2

get(1)
cache: {(1,2,null), (2,null,1)} lru:2 mru:1

set(3, res)
cache: {(1,2,3), (2,null,1), (3,1,null)} lru:2 mru:3

=== cache is full ===

get(1)
cache: {(1,3,null), (2,null,3), (3,2,1)} lru:2 mru:1

set(4)
cache: {(1,3,4), (3,null,1), (4,1,null)} lru:3 mru:1



get(x): evict(x) + set(x)
if x is NOT in cache ret err
else
evict x
set x

set(x): evict(x) + evict(lru) if necessary +set(x)
if x is in cache evict x
else if cache is full evict lru
set x
  update mru.next
  add x to hashmap, set prev and next
  update mru pointer

evict:
if item is lru
update lru pointer to lru.next
reset newLru.prev
if item is mru
***
if neither
***
*/

import java.util.*;


class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        MyLRUCache c = s.new MyLRUCache();

        c.set(1, 1);
        c.set(2, 2);
        System.out.println(c.get(2));
        System.out.println(c.get(1));
        c.set(3, 3);
        System.out.println(c.get(3));
        System.out.println(c.get(1));
    }

    class Entry {
        Integer key;
        Integer value;
        Entry prev;
        Entry next;

        public Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    class MyLRUCache {

        private Integer size = 2;
        private HashMap<Integer, Entry> cache = new HashMap<>();
        private Integer lru = null;
        private Integer mru = null;

        private Boolean isFull() {
            return cache.size() == size;
        }

        public Integer get(Integer key) {
            if (cache.containsKey(key) == false) {
                throw new RuntimeException(); // return err; // some sort of
            } else {
                Integer value = cache.get(key).value;
                evict(key); // is this necessary??
                set(key, value);
                return value;
            }
        }

        public void set(Integer key, Integer value) {
            evict(key);
            if (isFull()) {
                evict(lru);
            }

            if (cache.isEmpty()) {
                lru = key;
                mru = key;
                Entry newEntry = new Entry(key, value);
                cache.put(key, newEntry);
            } else {
                Entry newEntry = new Entry(key, value);
                newEntry.prev = cache.get(mru);
                cache.get(mru).next = newEntry;
                cache.put(key, newEntry);
                mru = key;
            }
        }

        private void evict(Integer key) {
            if (cache.containsKey(key) == false) {
                return;
            }

            if (key == mru) {
                Integer newMru = cache.get(key).prev.key;
                cache.remove(key);
                mru = newMru;
            } else if (key == lru) {
                Integer newLru = cache.get(key).next.key;
                cache.remove(key);
                lru = newLru;
            } else {
                Integer next = cache.get(key).next.key;
                Entry nextE = cache.get(next);
                Integer prev = cache.get(key).prev.key;
                Entry prevE = cache.get(prev);

                cache.remove(key);

                prevE.next = nextE;
                nextE.prev = prevE;
            }
        }

    }
}
