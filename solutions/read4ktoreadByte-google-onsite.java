import java.lang.Math;
import java.util.*;

class Solution {
    public static void main(String[] args) {   
    }




/*

givent read 4k method:

public int read4k(byte[] buffer)

write read byte method

public int readByte(byte[] buffer, int n)



*/

abstract class LegacyReader {
    public abstract int read4k(byte[] buf);
}

public class NewReader {

    private LegacyReader legacyReader;
    private ArrayList<Byte> mem = new ArrayList<>();
    private boolean depleted = false;

    public int readByte(byte[] buf, int n) {

        //read as much as necessary
        while(mem.size() < n && depleted == false) {

            byte[] tmp = new byte[4096];
            int fillSize = legacyReader.read4k(tmp);

            //copy bytes
            for(int i=0; i<fillSize ;i++) {
                mem.add(tmp[i]);
            }

            // set depleted
            if(fillSize < 4096){
                depleted = true;
            }
        }

        // give out pieces
        int respSize = Math.min(n, mem.size());
        for(int i=0; i<respSize ;i++) {
            buf[i] =  mem.get(i);
        }

        // remove used bytes
        ArrayList<Byte> newMem = new ArrayList<>();
        for(int i=respSize; i<mem.size() ;i++) {
            newMem.add(mem.get(i));
        }        

        mem = newMem;

        return respSize;
    }
}

// SC: n
// TC: n
}