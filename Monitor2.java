import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.HashMap;
public class Monitor2 {
    SubsekvensRegister register;
    ReentrantLock laas;
    Condition kanFlette;
    Condition erFerdig;
    protected int flettecounter;

    public Monitor2(){
        register = new SubsekvensRegister();
        laas = new ReentrantLock();
        kanFlette = laas.newCondition();
        erFerdig = laas.newCondition();
        flettecounter = -1; //Antall flettinger som foretas er antallet innleste filer - 1, kan dette være en condition for å signalere at all fletting er ferdig??

    }
    public void leggTil(HashMap<String , Subsekvens> map){
        laas.lock();
        try{
            register.leggTil(map);
            if(stoerrelse() >1){
                kanFlette.signal();
            }
        }finally{
            laas.unlock();
        }
    }

    public void leggTilFlettet(HashMap<String, Subsekvens> map){
        // laas.lock();
        // try{
        register.leggTilFlettet(map);
        if(stoerrelse()>1){
            kanFlette.signal();
        }
    // }finally{
    //     laas.unlock();
    // }
        
    }
    
    public HashMap<String, Subsekvens> taUt(int index){
        laas.lock();
        try{
            return register.taUt(index);
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
        // laas.lock();
        // try{
            return register.lesFil(filnavn);
        // }finally{
        //     laas.unlock();
        // }
    }

    public void flett(int map2index, int map1index) throws InterruptedException{
        laas.lock();
        kanFlette.await();
        try{
            leggTilFlettet(register.flett(taUt(map2index), taUt(map1index)));
            System.out.println("flettet");
        }finally{
            laas.unlock();
        }
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
