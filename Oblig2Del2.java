import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Oblig2Del2 {
    public static void main(String[] args){
        final int antFletteTrader = 8;
        Monitor2 monitor = new Monitor2();
        File fil = new File(args[0]);
        try{
        Scanner sc = new Scanner(fil);
        while (sc.hasNextLine()){
            String line = sc.nextLine().strip();
            LeseTrad trad = new LeseTrad(args[0].strip().split("/")[0] + "/" + line, monitor);
            trad.start();

            
        }
        sc.close();
        }catch(FileNotFoundException e){
         System.out.println("Kan ikke finne filen");
        

    
        }
        for(int i = 0; i<antFletteTrader-1; i++){
            FletteTrad trad = new FletteTrad(monitor);
            trad.start();
        }
        monitor.skrivStoerste();

    }
    
}


 