package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		
		if (!child.from.equals(whereTablePredicate)) {
			return child.next();
		}else{
		
			Tuple selected= child.next(); 
		
			while(selected!=null){
				int n = child.getAttributeList().size();
				for(int i = 0; i < n;i++){
					if(child.getAttributeList().get(i).getAttributeName().equals(whereAttributePredicate) && child.getAttributeList().get(i).getAttributeValue().equals(whereValuePredicate)){
						
							this.attributeList = child.getAttributeList();
							return selected;
						
					}
				}
				return this.next();//recursion
			}
			return null;
		}
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList() {
		return(child.getAttributeList());
	}

	
}