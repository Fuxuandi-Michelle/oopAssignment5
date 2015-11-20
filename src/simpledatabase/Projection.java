package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}// project(table, attributePredicator;)//name
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		
		Tuple selected = child.next();//selection.next
		newAttributeList = new ArrayList<Attribute>();
		if (selected!= null){
			int n = selected.getAttributeList().size();
			for(int i = 0; i < n ; i++){
				if(selected.getAttributeName(i).equals(attributePredicate)){
					newAttributeList.add(selected.getAttributeList().get(i));
				}
			}
			 
			selected = new Tuple(newAttributeList);
			return selected;
			
		}else{
			
			return null;
		}
		
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}
