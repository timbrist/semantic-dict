package dictionary;
import java.time.Period;

public class BookedCottage {
    private long cottageId;
    private long bookerId;
    private Period bookPeriod;
    public BookedCottage(long cottageId, long bookerId, Period bookPeriod){
        this.cottageId = cottageId;
        this.bookerId = bookerId;
        this.bookPeriod = bookPeriod;
    }

    public long getCottateId(){return this.cottageId;}
    public long getBookerId(){return this.bookerId;}
    public Period getBookPeriod(){return this.bookPeriod;}
}
