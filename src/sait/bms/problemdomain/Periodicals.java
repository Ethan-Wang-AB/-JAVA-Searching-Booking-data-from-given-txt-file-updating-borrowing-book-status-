package sait.bms.problemdomain;


/**
*   Class description: This class is the class for Periodicals books.
*   
*	@author Su Wang
*   
*   @version 10-Feb-2021
*/
public class Periodicals extends Books{

	public Periodicals() {
		super();
		this.type=4;
	}
    public String saveToFile() {
    	String s=String.format("%d;%s;%d;%d;%s;%s%n",isbn,callNum,availableC,total,title,format);
    	return s;
    }

	public String toString() {
		String frequence="";
		if(format.equalsIgnoreCase("d")){
			frequence="Daily";
		}
		else if(format.equalsIgnoreCase("w")){
			frequence="Weekly";
		}
		if(format.equalsIgnoreCase("d")){
			frequence="Daily";
		}
		else if(format.equalsIgnoreCase("M")){
			frequence="Monthly";
		}
		else if(format.equalsIgnoreCase("b")){
			frequence="Bimonthly";
		}
		else if(format.equalsIgnoreCase("q")){
			frequence="Quarterly";
		}
		String s=String.format("%-15s%-15d%n"
				+ "%-15s%-15s%n"
				+ "%-15s%-15d%n"
				+ "%-15s%-15d%n"
				+ "%-15s%-15s%n"
				+ "%-15s%-15s%n%n", "ISBN:",isbn,"Call Number:",callNum,"Available:",availableC,"Total:",total,
				"Title:",title,"Frequence:",frequence);
	
		return s;
	}
}
