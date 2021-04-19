package sait.bms.problemdomain;


/**
*	Class description: This class is the parent class for every type of book. To provide basic attributes of child class.
*   
*	@author Su Wang
*   
*   @version 10-Feb-2021
*/
public abstract class Books{
 long isbn;
 String callNum;
 int availableC;
 int total;
 boolean available;
 String title="";
 int type;
 String format="";
 
 public long getIsbn() {
	return isbn;
}
public void setIsbn(long isbn) {
	this.isbn = isbn;
}

public int getType() {
	return type;
}
public String getCallNum() {
	return callNum;
}
public void setCallNum(String callNum) {
	this.callNum = callNum;
}
public int getAvailableC() {
	return availableC;
}
public void checkoutAvailableC() {
	if(this.availableC>0) {
	this.availableC--;}
	else {
		System.out.println("This book is not available");
	}
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public boolean isAvailable() {
	return available;
}
public void setAvailable(boolean available) {
	this.available = available;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

public String getFormat() {
	return format;
}
public void setFormat(String format) {
	this.format = format;
}

public void setAvailableC(int availableC) {
	this.availableC = availableC;
}
public abstract String toString();
public abstract String saveToFile();
}
