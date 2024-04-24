import java.io.File;
public class TestRegister {
    public static void main(String[] args){
        SubsekvensRegister s = new SubsekvensRegister();
        SubsekvensRegister s2 = new SubsekvensRegister();


        s.flett(s.lesFil("fil.csv"), s2.lesFil("testfil.csv"));
        //s.skrivHashMap();
    }
}
