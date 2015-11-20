package simpledatabase;

import java.util.ArrayList;

public class Sort extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	public Sort(Operator child, String orderPredicate) {
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();

	}

	/**
	 * The function is used to return the sorted tuple
	 * 
	 * @return tuple
	 */
	@Override
	public Tuple next() {

		while (tuplesResult.size()==0){
			
			Tuple tuple;
			ArrayList<Tuple> selectedtuples = new ArrayList<Tuple>();
			
			while ((tuple = child.next())!= null) {
				selectedtuples.add(tuple);
			}
			
			if(selectedtuples.size()==0){
				return null;
			}else{
				int n = selectedtuples.get(0).getAttributeList().size();
				int key=-1;
				for(int i=0;i<n;i++){
					if(selectedtuples.get(0).getAttributeName(i).equals(orderPredicate)){
						key = i;
					}
				}
				
			
				int m = selectedtuples.size();
				String min;
				int smallest;
				int cnt = 0;
				for(int i = 0;i<m;i++){
					min = selectedtuples.get(0).getAttributeValue(key).toString();
					smallest = 0;
					for(int j = 0;j<m-cnt;j++){
						if(min.compareTo(selectedtuples.get(j).getAttributeValue(key).toString())>0){
							smallest = j;
						}
					}
					tuplesResult.add(selectedtuples.get(smallest));
					selectedtuples.remove(smallest);
					cnt++;
				}
			}
		}
		return tuplesResult.remove(0);
	}			
	

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		return child.getAttributeList();
	}

}