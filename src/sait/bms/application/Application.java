package sait.bms.application;

import sait.bms.problemdomain.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
*	Class description:
*This class is to loading booking list from .txt file, helping user search books and check out. 
*
*	@author Su Wang (845593)
*
*/
public class Application {
	private static ArrayList<Books> bookList = new ArrayList<>();
	private static File file = new File("res/books.txt");
	private static Scanner input = new Scanner(System.in);

	public static void application() throws IOException {

		int choice = 0;
		readDatabase();
		String selection;
		printMenu();
		while (choice != 5) {
			selection = input.nextLine();
			if (checkDigits(selection) == false) {
				System.out.println("Please input correct number:1,2,3,4 or 5");
			} else {
				choice = Integer.parseInt(selection);
				if (choice == 1) {
					System.out.print("Enter ISBN of book: ");
					String isbn = input.nextLine();
					if ((checkDigits(isbn) == false) || (isbn.length() != 13)) {
						System.out.print("It is an invalid ISBN;");
					} else {

						checkout(Long.parseLong(isbn));
						System.out.println();
					}
				} else if (choice == 2) {
					System.out.print("Enter title to search for: ");
					String title = input.nextLine();
					System.out.println();
					searchTitle(title);
				} else if (choice == 3) {
					System.out.printf("%-10s%s%n", "#", "Type");
					System.out.printf("%-10d%s%n", 1, "Children's Books");
					System.out.printf("%-10d%s%n", 2, "Cookbooks");
					System.out.printf("%-10d%s%n", 3, "Paperbacks");
					System.out.printf("%-10d%s%n", 4, "Periodicals");
					System.out.print("Enter type of book: ");
					String type = input.nextLine();
					searchType(type);
				} else if (choice == 4) {
					System.out.print("Enter number of books: ");
					String random = input.nextLine();
					if (checkDigits(random) == false) {
						System.out.print("It is an invalid number.");
					} else if (Integer.parseInt(random) > bookList.size()) {
						System.out.print("We do not have that many books.");
					} else {
						System.out.println();
						System.out.println("Random Books");
						shuffleList(Integer.parseInt(random));
					}

				}
			}
		    if(choice!=5) {
			System.out.printf("Press Enter to continue...");
			input.nextLine();
			printMenu();
		    }
		    else {
		    	saveFile();
		    System.exit(0);
		    }
		}
	}

	public static void readDatabase() throws IOException {
		String[] bookInfo = null;
		Scanner in = new Scanner(file);
		while (in.hasNextLine()) {
			bookInfo = in.nextLine().split(";");
			String isbn;
			isbn = bookInfo[0];
			int digit;
			digit = Integer.parseInt(isbn.substring(12));
			if (bookInfo.length == 6 || digit == 8 || digit == 9) {
				Periodicals periodicals = new Periodicals();
				periodicals.setIsbn(Long.parseLong(isbn));
				periodicals.setCallNum(bookInfo[1]);
				periodicals.setAvailableC(Integer.parseInt(bookInfo[2]));
				periodicals.setTotal(Integer.parseInt(bookInfo[3]));
				periodicals.setTitle(bookInfo[4]);
				periodicals.setFormat(bookInfo[5]);
				bookList.add(periodicals);
			} else if (bookInfo.length == 8 || digit == 4 || digit == 7) {
				Paperbacks paperbacks = new Paperbacks();
				paperbacks.setIsbn(Long.parseLong(isbn));
				paperbacks.setCallNum(bookInfo[1]);
				paperbacks.setAvailableC(Integer.parseInt(bookInfo[2]));
				paperbacks.setTotal(Integer.parseInt(bookInfo[3]));
				paperbacks.setTitle(bookInfo[4]);
				paperbacks.setAuthor(bookInfo[5]);
				paperbacks.setYear(Integer.parseInt(bookInfo[6]));
				paperbacks.setFormat(bookInfo[7]);
				bookList.add(paperbacks);
			} else if (digit == 2 || digit == 3) {
				Cookbook cookbooks = new Cookbook();
				cookbooks.setIsbn(Long.parseLong(isbn));
				cookbooks.setCallNum(bookInfo[1]);
				cookbooks.setAvailableC(Integer.parseInt(bookInfo[2]));
				cookbooks.setTotal(Integer.parseInt(bookInfo[3]));
				cookbooks.setTitle(bookInfo[4]);
				cookbooks.setPublisher(bookInfo[5]);
				cookbooks.setFormat(bookInfo[6]);
				bookList.add(cookbooks);
			} else if (digit == 0 || digit == 1) {
				ChildrensBook childrensBook = new ChildrensBook();
				childrensBook.setIsbn(Long.parseLong(isbn));
				childrensBook.setCallNum(bookInfo[1]);
				childrensBook.setAvailableC(Integer.parseInt(bookInfo[2]));
				childrensBook.setTotal(Integer.parseInt(bookInfo[3]));
				childrensBook.setTitle(bookInfo[4]);
				childrensBook.setAuthor(bookInfo[5]);
				childrensBook.setFormat(bookInfo[6]);
				bookList.add(childrensBook);
			}
		}
		in.close();
		
	}

	public static void checkout(long isbn) {
		boolean available = false;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getIsbn() == isbn) {
				available = true;
				bookList.get(i).checkoutAvailableC();
				System.out.printf("The book \"%s\" has been cheked out.%n", bookList.get(i).getTitle());
				System.out.printf("It can be located using a call number: %s.", bookList.get(i).getCallNum());
				i = bookList.size();
			}
		}
		if (available == false) {
			System.out.println("The book you are searching is not recorded in our system.");
		}
	}

	public static void searchTitle(String words) {
		boolean available = false;
		int count=0;
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).getTitle().toLowerCase().contains(words.toLowerCase()) == true) {
				System.out.println("Matching Books:");
				System.out.printf("%s%n", bookList.get(i).toString());
				available = true;
				count++;
			}
		}
		if (available == false) {
			System.out.println("We can not find books relating to your search.");
		}
		else {
			System.out.println("There are "+count+" books have been found");
		}
	}

	public static void searchType(String typeS) {
		int type = 0;
		if (checkDigits(typeS) == false) {
			System.out.println("It is an invalid book type.");
		} else {
			type = Integer.parseInt(typeS);

			if ((type != 1) && (type != 2) && (type != 3) && type != 4) {
				System.out.println("It is an invalid book type.");
			} else {
				String format = "";
				if (type == 1) {
					System.out.print("Enter a format (P for Picture book, E for Early reader or C for Chapter book): ");

				} else if (type == 2) {
					System.out.print(
							"Enter a Diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International or N for None: ");
				} else if (type == 3) {
					System.out.print(
							"Enter a Genre (D for Drama, E for Education, C for Classic, F for Fantasy or S for Science Fiction: ");
				} else if (type == 4) {
					System.out.print(
							"Enter a frequency (D for Daily, W for weekly, M for Monthly, B for Bimonthly or Q for Quarterly: ");
				}
				format = input.nextLine();
				boolean available = false;
				for (int i = 0; i < bookList.size(); i++) {
					if (bookList.get(i).getType() == type && bookList.get(i).getFormat().equalsIgnoreCase(format)) {
						
						System.out.println();
						if(available==false) {
						System.out.println("Matching Books:");
						}
						available = true;
						System.out.printf(bookList.get(i).toString());
					}
				}
				if(available==false) {
					System.out.println();
					System.out.println("The type you are searching is not available now.");
				}
			}
		}
	}

	public static void saveFile() throws IOException {
		PrintWriter save = new PrintWriter("res/books.txt");
		int count=0;
		for (int i = 0; i < bookList.size(); i++) {
			save.print(bookList.get(i).saveToFile());
		    count++;
		}
		save.close();
		
	}

	public static void shuffleList(int n) {
		ArrayList<Books> shuffle = new ArrayList<>();
		for (int i = 0; i < bookList.size(); i++) {
			shuffle.add(bookList.get(i));
		}
		Collections.shuffle(shuffle);
		for (int i = 0; i < n; i++) {
			System.out.print(shuffle.get(i).toString());
		}
	}

	public static void printMenu() {
		System.out.println();
		System.out.println("Welcome in ABC Book COmpany: How May We Assist You?");
		System.out.printf("%-8d%s%n", 1, "Checkout Book");
		System.out.printf("%-8d%s%n", 2, "Find Books by Title");
		System.out.printf("%-8d%s%n", 3, "Display Books by Type");
		System.out.printf("%-8d%s%n", 4, "Produce Random Book List");
		System.out.printf("%-8d%s%n%n", 5, "Save & Exit");
		System.out.print("Enter Option: ");

	}

	public static boolean checkDigits(String checkDigits) {
		for (int i = 0; i < checkDigits.length(); i++) {
			if (!Character.isDigit(checkDigits.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
}
