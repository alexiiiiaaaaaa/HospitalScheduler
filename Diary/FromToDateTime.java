import java.time.LocalDateTime;

public class FromToDateTime {
    private LocalDateTime from;
    private LocalDateTime to;

    public FromToDateTime(LocalDateTime from, LocalDateTime to)
    {
        this.from = from;
        this.to = to;
    }
    
    public LocalDateTime getFrom()
    {
        return this.from;
    }

    public LocalDateTime getTo()
    {
        return this.to;
    }

    public void setFrom(LocalDateTime from)
    {
        this.from = from;
    }

    public void setTo(LocalDateTime to)
    {
        this.to = to;
    }

    public void print()
    {
        System.out.println("FromToDateTime: {from: " + from + ", to:" + to + " }");
    } 
}
