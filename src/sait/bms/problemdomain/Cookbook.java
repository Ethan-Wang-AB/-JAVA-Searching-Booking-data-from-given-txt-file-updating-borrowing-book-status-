package sait.bms.problemdomain;


/**
*   Class description: This class is the class for Cook books.
*	@author Su Wang
*   
*   @version 10-Feb-2021
*/
public class Cookbook extends Books {
	private String publisher="";
	
	public Cookbook() {
		super();
		this.type=2;
		// TODO Auto-generated constructor stub
	}
	
     
	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	  public String saveToFile() {
	    	String s=String.format("%d;%s;%d;%d;%s;%s;%s%n",isbn,callNum,availableC,total,title,publisher,format);
	    	return s;
	    }

		public String toString() {
			String diet = "";
			if (format.equalsIgnoreCase("d")) {
				diet = "Diabetic";
			} else if (format.equalsIgnoreCase("v")) {
				diet = "Vegetarian";
			} else if (format.equalsIgnoreCase("g")) {
				diet = "Gluten-free";
			} else if (format.equalsIgnoreCase("i")) {
				diet = "International";
			} else if (format.equalsIgnoreCase("n")) {
				diet = "None";
			}
			String s=String.format("%-15s%-15d%n"
					+ "%-15s%-15s%n"
					+ "%-15s%-15d%n"
					+ "%-15s%-15d%n"
					+ "%-15s%-15s%n"
					+ "%-15s%-15s%n"
					+ "%-15s%-15s%n%n", "ISBN:",isbn,"Call Number:",callNum,"Available:",availableC,"Total:",total,
					"Title:",title,"Publisher:",publisher,"Diet:",diet);
		
			return s;
		}

}
