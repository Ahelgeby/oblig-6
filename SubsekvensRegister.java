import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class SubsekvensRegister {


    ArrayList<HashMap<String,Subsekvens>> sekvensListe;
    HashMap<String, Subsekvens> mestflettet = new HashMap<>();

    public SubsekvensRegister(){
        sekvensListe = new ArrayList<>();
    }

    public void leggTil(HashMap<String,Subsekvens> sekvensmap){
        sekvensListe.add(sekvensmap);
    }

    public void leggTilFlettet(HashMap<String,Subsekvens> sekvensmap){
        sekvensListe.add(sekvensmap);
    }

    public HashMap<String, Subsekvens> taUt(int index){
        HashMap<String, Subsekvens> temp = sekvensListe.get(index);
        sekvensListe.remove(index);
        return temp;
    }

    public HashMap<String, Subsekvens> hent(int index){
        return sekvensListe.get(index);
    }

    public int stoerrelse(){
        return sekvensListe.size();
    }
    
    public void skrivStoerste(){
        Subsekvens s = null;
        int max = 0;
        for(String key : sekvensListe.get(0).keySet()){
            // if(key.equals("QYF")){
            //     System.out.println(sekvensListe.get(0).get(key).hentAntall());
            //}
            if (sekvensListe.get(0).get(key).hentAntall() > max){
                max = sekvensListe.get(0).get(key).hentAntall();
                s = sekvensListe.get(0).get(key);
            }
        }
        System.out.println("Sekvensen som forekommer i flest personer er: " + s);
    }
        
        
    

    public static HashMap<String,Subsekvens> lesFil(String filnavn){
        HashMap<String, Subsekvens> sekvensMap = new HashMap<>();
        try{
        Scanner sc = new Scanner(new File(filnavn));
        while(sc.hasNextLine()){
            String line = sc.nextLine().strip();

             if(line.length() >= 3){
                for(int i = 0; i<line.length() -2; i++){
                    String sekvens = line.substring(i, i+3);
                    Subsekvens s = new Subsekvens(sekvens, 1);
                        if(!sekvensMap.containsKey(sekvens)){
                            sekvensMap.put(sekvens,s);
                        }
                    }
                }else{
                sc.close();
                System.out.println("feil et sted");
                System.exit(-1);
                }

            }
        
        sc.close();

        }catch (FileNotFoundException e){
            System.out.println("Fant ikke filen.");
        }

        return sekvensMap;
    }
    public void skrivHashMap(){
        for(int i = 0; i<sekvensListe.size(); i++){
        System.out.println(sekvensListe.get(i).values());
    }
    }
    
    public static HashMap<String, Subsekvens> flett(HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2){
        for(String key : map2.keySet()){
            if(map1.containsKey(key)){
                for(int i = 0; i<map2.get(key).hentAntall(); i++){
                map1.get(key).oekAntall();
                }
                
            }else{
                map1.put(key, map2.get(key));
            }
        }
        return map1;

    }
}
