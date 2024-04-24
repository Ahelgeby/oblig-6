public class LeseTrad extends Thread{
    Monitor2 monitor;
    String filnavn;

    public LeseTrad(String f, Monitor2 m){
        monitor = m;
        filnavn = f;
    }
    @Override
    public void run(){
        monitor.leggTil(monitor.lesFil(filnavn));
    }


    public String toString(){
        return "filnavn: " + filnavn;
    }

}
