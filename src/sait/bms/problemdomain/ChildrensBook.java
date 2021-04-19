package sait.bms.problemdomain;
/**
*
*	Class description: This class is the class for Children books.
*   
*	@author Su Wang
*   
*   @version 10-Feb-2021
*
*/
public class ChildrensBook extends Books{
	private String author="";
	
	
	
	public ChildrensBook() {
		super();
		this.type=1;
	}
	
	public String getAuthor() {
		return author;
	}

	  public void setAuthor(String author) {
		this.author = author;
	}

	public String saveToFile() {
	    	String s=String.format("%d;%s;%d;%d;%s;%s;%s%n",isbn,callNum,availableC,total,title,author,format);
	    	return s;
	    }

		public String toString() {
			String type = "";
			if (format.equalsIgnoreCase("p")) {
				type = "Picture Book";
			} else if (format.equalsIgnoreCase("E")) {
				type = "Early Readers";
			} else if (format.equalsIgnoreCase("C")) {
				type = "Chapter Book";
			}
			String s=String.format("%-15s%-15d%n"
					+ "%-15s%-15s%n"
					+ "%-15s%-15d%n"
					+ "%-15s%-15d%n"
					+ "%-15s%-15s%n"
					+ "%-15s%-15s%n"
					+ "%-15s%-15s%n%n", "ISBN:",isbn,"Call Number:",callNum,"Available:",availableC,"Total:",total,
					"Title:",title,"Authors:",author,"Format:",type);
		
			return s;
		}

	}

