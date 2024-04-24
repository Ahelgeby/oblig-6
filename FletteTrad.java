public class FletteTrad extends Thread{
    Monitor2 monitor;
    static int teller = 1;
    int tradnr;
    public FletteTrad(Monitor2 m){
        monitor = m;
        tradnr = teller;
        teller++;
    }

    @Override
    public void run(){
        try{
        while(monitor.stoerrelse() != 1){
            monitor.flett(1,0);
            System.out.println("Trad nummer: " + tradnr + " eksekverer");
        }
        }catch(InterruptedException e){
            System.out.println("interrupted");
        }
        
    }


    

}
