import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class Oblig2Del2 {
    public static void main(String[] args){
        final int antFletteTrader = 8;
        Monitor2 monitor = new Monitor2();
        File fil = new File(args[0]);
        ArrayList<Thread> lesetrader = new ArrayList<>();
        Thread[] flettetrader = new Thread[8];
        try{
        Scanner sc = new Scanner(fil);
        while (sc.hasNextLine()){
            String line = sc.nextLine().strip();
            Thread trad = new Thread(new LeseTrad(args[0].strip().split("/")[0] + "/" + line, monitor));
            lesetrader.add(trad);
            trad.start();

            
        }
        sc.close();
        }catch(FileNotFoundException e){
         System.out.println("Kan ikke finne filen");
        
        for(int i = 0; i<lesetrader.size()-1; i++){
            try{
            lesetrader.get(i).join();
            }catch(InterruptedException interrupted){
                System.out.println("Thread interrupted");
            }
        }

    
        }
        for(int i = 0; i<antFletteTrader-1; i++){
            Thread trad = new Thread(new FletteTrad(monitor));
            flettetrader[i] = trad;
            trad.start();
        }

        for(int i = 0; i<flettetrader.length; i++){
            try{
                flettetrader[i].join();
            }catch(InterruptedException interrupt){
                System.out.println("Thread interrupted");
            }
        }

    }
    
}


 