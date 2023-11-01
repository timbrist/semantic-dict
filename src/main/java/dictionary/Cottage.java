package dictionary;

public class Cottage {
    private long id;        //cottage id
    private String name;    //name of the cottage
    private String address;   
    private Integer numOfPlaces;
    private Integer numOfBeds;
    private Integer distanceToLake;
    private String closestCity;
    private Integer distanceToCity;
    private boolean isBooked;

    public Cottage(  long id,        //cottage id
                     String name,    //name of the cottage
                     String address,   
                     Integer numOfPlaces,
                     Integer numOfBeds,
                     Integer distanceToLake,
                     String closestCity,
                     Integer distanceToCity,
                     boolean isBooked){

        this.id = id;
        this.name = name;
        this.address = address;
        this.numOfPlaces = numOfPlaces;
        this.numOfBeds = numOfBeds;
        this.distanceToLake = distanceToLake;
        this.closestCity = closestCity;
        this.distanceToCity =distanceToCity;
        this.isBooked = isBooked;
    }

    public long getId(){return this.id;}
    public String getName(){return this.name;}
    public String getAddress(){return this.address;}
    public Integer getNumOfPlaces(){return this.numOfPlaces;}
    public Integer getNumOfBeds(){return this.numOfBeds;}
    public Integer getDistanceToLake(){return this.distanceToLake;}
    public String getClosestCity(){return this.closestCity;}
    public Integer getDistanceToCity(){return this.distanceToCity;}
    public boolean isBooked(){return this.isBooked;}

    public void setId(long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setNumOfPlaces(Integer numOfPlaces){this.numOfPlaces = numOfPlaces;}
    public void setNumOfBeds(Integer numOfBeds){this.numOfBeds = numOfBeds;}
    public void setDistanceToLake(Integer distanceToLake){this.distanceToLake = distanceToLake;}
    public void setClosestCity(String closestCity){this.closestCity = closestCity;}
    public void setDistanceToCity(Integer distanceToCity){this.distanceToCity =distanceToCity;}
    public void isBooked(boolean isBooked){this.isBooked = isBooked;}
}
