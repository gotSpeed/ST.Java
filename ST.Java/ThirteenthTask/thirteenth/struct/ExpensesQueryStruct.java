package thirteenth.struct;


public class ExpensesQueryStruct {

    public long   id;
    public String receiver;
    public String timestamp;
    public long   amount;



    public ExpensesQueryStruct(long id, String receiver, String timestamp, long amount) {

        this.id        = id;
        this.receiver  = receiver;
        this.timestamp = timestamp;
        this.amount    = amount;
    }

}
