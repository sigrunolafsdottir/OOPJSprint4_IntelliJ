package ÖvnUppg8_AdressBok.Server;

import ÖvnUppg8_AdressBok.POJOs.Kompis;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class DAO {
    
    private final Kompis Anna = new Kompis("Anna", "Kungsgatan 12, 12456 Stockholm",
            LocalDate.of(1989, Month.JULY, 12));
    private final Kompis Bertil = new Kompis("Bertil",
            "Drottninggatan 3 12, 1226 Stockholm",LocalDate.of(2008, Month.MARCH, 9));
    private final Kompis Cecil = new Kompis("Cecil",
            "Allgatan 12, 12456 Stockholm",LocalDate.of(1989, Month.NOVEMBER, 23));
    private final Kompis Danne = new Kompis("Danne",
            "Betgatan 12, 12456 Stockholm",LocalDate.of(1984, Month.SEPTEMBER, 26));
    private final Kompis Ella = new Kompis("Ella",
            "Cevgatan 12, 12456 Stockholm",LocalDate.of(1967, Month.SEPTEMBER, 27));
    
    private final List <Kompis> allPersons = new ArrayList<>();
    
    public DAO(){
        allPersons.add(Anna);
        allPersons.add(Bertil);
        allPersons.add(Cecil);
        allPersons.add(Danne);
        allPersons.add(Ella);
    }
    
    public List<Kompis> getAllPersons(){
        return allPersons;
    }
    
   public Kompis getPersonByName(String s){
       for (Kompis p :allPersons){
           if (p.getName().equalsIgnoreCase(s)){
               return p;
           }
       }
       return null;
   }
   
   public String getAddress(String s){
       for (Kompis p :allPersons){
           if (p.getName().equalsIgnoreCase(s)){
               return p.getAddress();
           }
       }
       return null;
   }
    
}
