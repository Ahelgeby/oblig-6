import java.util.HashMap;
public class LeseTrad implements Runnable{
    Monitor2 monitor;
    String filnavn;

    public LeseTrad(String f, Monitor2 m){
        monitor = m;
        filnavn = f;
    }
    public void run(){
        HashMap<String, Subsekvens> hash = monitor.lesFil(filnavn);
        monitor.leggTil(hash);
    }


    public String toString(){
        return "filnavn: " + filnavn;
    }

}
