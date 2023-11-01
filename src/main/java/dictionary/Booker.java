package dictionary;
public class Booker {
    private long id;        //generative
    private String name;

    //requirements from clients
    private Integer numOfPlaces;    //number of occupants
    private Integer numOfBeds; 
    private Integer numOfDays;

    public Booker(long id, String name, Integer numOfPlaces, Integer numOfBeds, Integer numOfDays){
        this.id = id;
        this.name = name;
        this.numOfPlaces = numOfPlaces;
        this.numOfBeds = numOfBeds;
        this.numOfDays = numOfDays;
    }

    public long getId(){return this.id;}
    public String getName(){return this.name;}
    public Integer getNumOfPlaces(){return this.numOfPlaces;}
    public Integer getNumOfBeds(){return this.numOfBeds;}
    public Integer getNumOfDays(){return this.numOfDays;}


    public void setId(long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setNumOfPlaces(Integer numOfPlaces){this.numOfPlaces = numOfPlaces;}
    public void setNumOfBeds(Integer numOfBeds){this.numOfBeds = numOfBeds;}
    public void setNumOfDays( Integer numOfDays){this.numOfDays = numOfDays;}
}
