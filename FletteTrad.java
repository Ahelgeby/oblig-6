import java.util.HashMap;
import java.util.ArrayList;
public class FletteTrad implements Runnable{
    Monitor2 monitor;
    static int teller = 1;
    int tradnr;
    public FletteTrad(Monitor2 m){
        monitor = m;
        tradnr = teller;
        teller++;
    }

    public void run(){
        while(monitor.stoerrelse() >=2){
        ArrayList<HashMap<String,Subsekvens>> hashes = new ArrayList<>();
        try{
            hashes = monitor.taUtTo();
            HashMap<String,Subsekvens> flettet = monitor.flett(hashes.get(0), hashes.get(1));
            monitor.leggTilFlettet(flettet);
        }catch(InterruptedException e){
            ;
    }
    }
}
}
