package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}


	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		
		Tuple left;
		while((left = leftChild.next()) !=null){
			tuples1.add(left);
		}
		
		Tuple right;
		if((right = rightChild.next()) !=null){
			int m = tuples1.size();
			for(int e = 0; e < m; e++){
				left = tuples1.get(e);
				int n = left.getAttributeList().size();
				int k = right.getAttributeList().size();
		
				for(int i = 0;i < n;i++){
					for(int j = 0;j < k;j++){
						if(left.getAttributeName(i).equals(right.getAttributeName(j)) && left.getAttributeValue(i).equals(right.getAttributeValue(j))){
							right.getAttributeList().addAll(left.getAttributeList());
							return right;
						}
						
					}
				}
			}
			
		}
		return null;
		
	}
		
	
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}
