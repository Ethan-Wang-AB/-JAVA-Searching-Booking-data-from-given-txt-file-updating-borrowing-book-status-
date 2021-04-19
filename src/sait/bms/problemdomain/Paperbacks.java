package sait.bms.problemdomain;


/**
*   Class description: This class is the class for Paperbacks books.
*   
*	@author Su Wang
*   
*   @version 10-Feb-2021
*/
public class Paperbacks extends Books {
	private String author = "";

	private int year;

	public Paperbacks() {
		super();
		this.type = 3;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String saveToFile() {
		String s = String.format("%d;%s;%d;%d;%s;%s;%d;%s%n", isbn, callNum, availableC, total, title, author, year,
				format);
		return s;
	}

	public String toString() {
		String genre = "";
		if (format.equalsIgnoreCase("a")) {
			genre = "Adventure";
		} else if (format.equalsIgnoreCase("d")) {
			genre = "Drama";
		} else if (format.equalsIgnoreCase("e")) {
			genre = "Education";
		} else if (format.equalsIgnoreCase("c")) {
			genre = "Classic";
		} else if (format.equalsIgnoreCase("f")) {
			genre = "Fantasy";
		} else if (format.equalsIgnoreCase("s")) {
			genre = "Science Fiction";
		}
		String s = String.format(
				"%-15s%-15d%n" + "%-15s%-15s%n" + "%-15s%-15d%n" + "%-15s%-15d%n" + "%-15s%-15s%n" + "%-15s%-15s%n"
						+ "%-15s%-15d%n" + "%-15s%-15s%n%n",
				"ISBN:", isbn, "Call Number:", callNum, "Available:", availableC, "Total:", total, "Title:", title,
				"Author:", author, "Year:", year, "Genre:", genre);

		return s;
	}

}
