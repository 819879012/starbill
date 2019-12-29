package entity;
 
public class Category {
	
    private int id;
    private int uid;
    private String name;
    private int recordNumber;
    
    public Category()
    {
    	
    }
    
    public Category(String name) {
		// TODO Auto-generated constructor stub
	}
    
    public int getRecordNumber() {
        return recordNumber;
    }
    
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    
    public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString(){
        return name;
    }
    
}