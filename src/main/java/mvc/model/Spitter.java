package mvc.model;

import javax.validation.constraints.Size;

public class Spitter {

    public static Spitter DEF = new Spitter("anonymous");        
    
    private long id;

    @Size(min=2, max=10, message="name must have size 2-10 characters")
    private String name;

    public Spitter() {
    }
    
    private Spitter(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
