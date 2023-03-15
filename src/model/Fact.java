package model;

public class Fact {

    private boolean state;
    private String factDescription;
    private int factID;
    private int value;
    
    public Fact() {
    	
    }
    
    public Fact(boolean state, String factDescription,int factID, int value) {
        this.state = state;
        this.factDescription = factDescription;
        this.factID = factID;
        this.value = value;
    }
    
    
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public String getFactDescription() {
        return factDescription;
    }
    public void setFactDescription(String factDescription) {
        this.factDescription = factDescription;
    }
    public int getFactID() {
		return factID;
	}
    public void setFactID(int factID) {
		this.factID = factID;
	}
    public int getValue() {
		return value;
	}
    public void setValue(int value) {
		this.value = value;
	}
    

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return getFactDescription();
    }
}
