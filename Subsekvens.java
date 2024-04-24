public  class Subsekvens {
    public final String subsekvens;
    private int antall;
    
    public Subsekvens(String s, int a){
        subsekvens = s;
        antall = a;
    }

    public int hentAntall(){
        return antall;
    }

    public void oekAntall(){
        antall ++;
    }


    @Override
    public String toString(){
        return subsekvens +"," + antall;
    }
}
