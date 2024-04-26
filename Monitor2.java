import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.HashMap;
import java.util.ArrayList;
public class Monitor2 {
    SubsekvensRegister register;
    ReentrantLock laas;
    Condition kanFlette;
    Condition kanTaUt;
    protected int flettecounter;

    public Monitor2(){
        register = new SubsekvensRegister();
        laas = new ReentrantLock();
        kanFlette = laas.newCondition();
        kanTaUt = laas.newCondition();
        flettecounter = 0;

    }
    public void leggTil(HashMap<String , Subsekvens> map){
        laas.lock();
        try{
            register.leggTil(map);
            if(stoerrelse() >1){
                System.out.println("Antall maps: " + stoerrelse());
                kanFlette.signal();
        }
        }finally{
            laas.unlock();
        }
    }

    public void leggTilFlettet(HashMap<String,Subsekvens> map){
        laas.lock();
        try{
        register.leggTil(map);
        System.out.println("Flettetrad lagt til hashmap, antall maps: " + stoerrelse());
        if(stoerrelse()>1){
            kanFlette.signal();
        }
        }finally{
            laas.unlock();
        }
    }
        
    
    public HashMap<String, Subsekvens> taUt(int index){
        
        return register.taUt(index);
        
    }

    
    public ArrayList<HashMap<String,Subsekvens>> taUtTo() throws InterruptedException{
        laas.lock();
        try{
            while(stoerrelse()<2){
            System.out.println( "venter");
            kanFlette.await();
            }
            if(flettecounter != 0){
            System.out.println("Våken");
        ArrayList<HashMap<String,Subsekvens>> hashes = new ArrayList<>();
            HashMap<String, Subsekvens> map1 = taUt(0);
            hashes.add(map1);
            HashMap<String, Subsekvens> map2 = taUt(0);
            hashes.add(map2);
            System.out.println("tatt ut to maps, antall maps: " + stoerrelse());
            return hashes;
            }else{
                return null;
            }
        }finally{
            laas.unlock();
        }
    }

    public int stoerrelse(){
        laas.lock();
        try{
            return register.stoerrelse();
        }finally{
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> lesFil(String filnavn){
        flettecounter ++;
        return SubsekvensRegister.lesFil(filnavn);
    }

    public HashMap<String, Subsekvens> flett(HashMap<String, Subsekvens> map2, HashMap<String, Subsekvens> map1){
        flettecounter--;
        return SubsekvensRegister.flett(map1, map2);
        
    }

    public void skrivStoerste(){
        laas.lock();
        try{
            register.skrivStoerste();
        }finally{
            laas.unlock();
        }
    }
}
