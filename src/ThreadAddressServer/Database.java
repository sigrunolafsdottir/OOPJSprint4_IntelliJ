package ThreadAddressServer;

//import AddressServer.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


public class Database {
    
    private final Person Anna = new Person("Anna", "Kungsgatan 12, 12456 Stockholm",
            LocalDate.of(1989, Month.JULY, 12), "Diabetes", false);
    private final Person Bertil = new Person("Bertil", 
            "Drottninggatan 3 12, 1226 Stockholm",LocalDate.of(2008, Month.MARCH, 9), "", true);
    private final Person Cecil = new Person("Cecil", 
            "Allgatan 12, 12456 Stockholm",LocalDate.of(1989, Month.NOVEMBER, 23), "Nageltrång", false);
    private final Person Danne = new Person("Danne", 
            "Betgatan 12, 12456 Stockholm",LocalDate.of(1984, Month.SEPTEMBER, 26), "Öroninflammation", false);
    private final Person Ella = new Person("Ella", 
            "Cevgatan 12, 12456 Stockholm",LocalDate.of(1967, Month.SEPTEMBER, 27), "Struma", false);
    
    private final List <Person> allPersons = new ArrayList<>();
    
    Database(){
        allPersons.add(Anna);
        allPersons.add(Bertil);
        allPersons.add(Cecil);
        allPersons.add(Danne);
        allPersons.add(Ella);
    }
    
    public List<Person> getAllPersons(){
        return allPersons;
    }
    
   public Person getPersonByName(String s){
       for (Person p :allPersons){
           if (p.getName().equalsIgnoreCase(s)){
               return p;
           }
       }
       return null;
   }
   
   public String getAddress(String s){
       for (Person p :allPersons){
           if (p.getName().equalsIgnoreCase(s)){
               return p.getAddress();
           }
       }
       return null;
   }
    
}
