import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
public class Oblig2Hele{
    public static void main(String[] args){
    Monitor2 syke = new Monitor2();
    Monitor2 friske = new Monitor2();
    final int antFletteTrader = 8;
    File fil = new File(args[0]);
    ArrayList<Thread> leseTraderSyke = new ArrayList<>();
    ArrayList<Thread> leseTraderFriske = new ArrayList<>();
    ArrayList<Subsekvens> dominanteSekvenser = new ArrayList<>();
    Thread[] fletteTraderSyke = new Thread[8];
    Thread[] fletteTraderFriske = new Thread[8];


    try{
    Scanner sc = new Scanner(fil);
    while(sc.hasNextLine()){
        String line = sc.nextLine().strip();
        if(line.split(",")[1].equals("True")){
            Thread leseTradSyke = new Thread(new LeseTrad(args[0].strip().split("/")[0] + "/" + line.split(",")[0], syke));
            leseTraderSyke.add(leseTradSyke);
            leseTradSyke.start();
        }else if(line.split(",")[1].equals("False")){
            Thread leseTradFriske = new Thread(new LeseTrad(args[0].strip().split("/")[0] + "/" + line.split(",")[0], friske));
            leseTraderFriske.add(leseTradFriske);
            leseTradFriske.start();

        }
    }sc.close();
    }catch(FileNotFoundException e){
        System.out.println("Fant ikke filen");
    }
    for(int i = 0; i<leseTraderSyke.size(); i++){
        try{
        leseTraderSyke.get(i).join();
        }catch(InterruptedException interrupt){
            System.out.println("Syk lesetrad interrupted");
        }
    }
    for(int i = 0; i<leseTraderFriske.size(); i++){
        try{
        leseTraderFriske.get(i).join();
        }catch(InterruptedException interrupt){
            System.out.println("frisk lesetrad interrupted");
        }
    }

    for(int i = 0; i<antFletteTrader; i++){
        Thread fletteTradSyk = new Thread(new FletteTrad(syke));
        fletteTraderSyke[i] = fletteTradSyk;
        fletteTradSyk.start();
        Thread fletteTradFrisk = new Thread(new FletteTrad(friske));
        fletteTraderFriske[i] = fletteTradFrisk;
        fletteTradFrisk.start();
    }

    for(int i = 0; i<antFletteTrader; i++){
        try{
        fletteTraderSyke[i].join();
        fletteTraderFriske[i].join();
        }catch(InterruptedException interrupt){
            System.out.println("flettetrad interrupted");
        }
    }
    for(String key : syke.hent(0).keySet()){
        if(friske.hent(0).containsKey(key)){
            if(syke.hent(0).get(key).hentAntall() - friske.hent(0).get(key).hentAntall() >=7){
                dominanteSekvenser.add(syke.hent(0).get(key));

            }
        }
    }
    System.out.println("dominante sekvenser: " + dominanteSekvenser);    


    }
}