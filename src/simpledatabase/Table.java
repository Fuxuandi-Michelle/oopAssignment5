package simpledatabase;

import java.lang.String;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute;
	private Tuple selected;

	private String attributeName ;
	private String attributeType ;
	private String attributeValue;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
			attributeName = br.readLine();
			attributeType = br.readLine();
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		
		 try {
			 if ((attributeValue = br.readLine())==null) {
				 return null;
			 }else{
				 this.selected = new Tuple(attributeName, attributeType, attributeValue);
				 selected.setAttributeName();
				 selected.setAttributeType();
				 selected.setAttributeValue(); 
				 return selected;
			}
			
		 } catch (Exception e) {
             System.out.println("Exception while reading file:" + e);
         }
		 return null;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return selected.getAttributeList();
	}
	
}

	
