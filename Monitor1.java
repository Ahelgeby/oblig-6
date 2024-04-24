
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;
public class Monitor1 {
        SubsekvensRegister register;
        ReentrantLock laas;
    public Monitor1(){
        register = new SubsekvensRegister();
        laas = new ReentrantLock();
    }
    public void leggTil(HashMap<String , Subsekvens> map){
        laas.lock();
        try{
            register.leggTil(map);
        }finally{
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> taUt(int index){
        laas.lock();
        try{
            return register.taUt(index);
        }finally{
            laas.unlock();
        }
    }

    public HashMap<String, Subsekvens> hent(int index){
        laas.lock();
        try{
            return register.hent(index);
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
        laas.lock();
        try{
            return register.lesFil(filnavn);
        }finally{
            laas.unlock();
        }
    }

    public void skrivHashMap(){
        laas.lock();
        try{
            register.skrivHashMap();
        }finally{
            laas.unlock();
        }
    }

    public void flett(int map2index, int map1index){
        laas.lock();
        try{
            leggTil(register.flett(taUt(map2index), taUt(map1index)));
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

    // public HashMap<String,Subsekvens> flettAlle(){
    //     laas.lock();
    //     try{
    //         return register.flettAlle();
    //     }finally{
    //         laas.unlock();
    //     }
    // }

}
