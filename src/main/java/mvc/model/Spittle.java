package mvc.model;

public class Spittle {
    private long id;
    private Spitter spitter;
    private String content;  
         

    public Spittle() {
    }
        
    public Spittle(String content) {
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setSpitter(Spitter spitter) {
        this.spitter = spitter;
    }

    public Spitter getSpitter() {
        return spitter;
    }      
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    } 
    
    @Override
    public String toString() {
        return spitter.toString()+ ": " +content;
    }    
    
}
