package browser;
import java.sql.Timestamp;


import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import exceptions.*;

public class Browser {
	
    private List<Bookmark> bookmarks = new ArrayList<>();
    private History history = new History();
    private VisitsHandling visitHandling;
    
	public Browser(String homepage) {
		super();
		visitHandling = new VisitsHandling(homepage);
	}
    
    public void addBookmark(String name) {
        Bookmark bookmark = new Bookmark(name);
        bookmarks.add(bookmark);
        System.out.println("Added bookmark " + name);
    }

    public void listBookmarks() {
        if (bookmarks.isEmpty()) {
            System.out.println("No bookmarks found.");
        } else {
            System.out.println("\nBookmarks");
            System.out.println("--------------");
            for (Bookmark bookmark : bookmarks) {
                System.out.println(bookmark.getName() + " - " + bookmark.getUrl());
            }
            System.out.println();
        }
    }
    
    public void deleteBookmarks(String name) {
    	try {
    		boolean foundBookmark = false;
        	for (Bookmark bookmark : bookmarks) {
    			if(bookmark.getName().equalsIgnoreCase(name)) {
    				bookmarks.remove(bookmark);
    				System.out.println("Removed " + name + "Successfully");
    				foundBookmark = true;
    			}
    		}
        	if(!foundBookmark) {
        		System.out.println("No Such Bookmark Exist...");
        	}
		} catch (Exception e) {
			// Handled unknown exception
		}
    }

    public class Bookmark {
        private String name;
        private String url;

        public Bookmark(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
    
    public void historyMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("HISTORY");
		System.out.println("=====================");
		System.out.println("\n1. Display Browser History");
		System.out.println("2. Remove History Entry");
		System.out.println("3. Reset History");
		System.out.print("Enter your Choice: ");
		int option = scanner.nextInt();
		
		switch(option) {
			case 1: history.displayHistory();
					break;
			case 2: removeFromHistory();
					break;
			case 3: history.resetHistory();
					break;
			default:System.out.println("Invalid Choice... ");
		}
	}
    
    public void removeFromHistory() {
    	Scanner scanner = new Scanner(System.in);
    	char choice = 'y';
		while(choice == 'y') { 
			if(history.getNumberOfRecords() == 0) {
				System.out.println("\nNo records to delete...");
				return;
			}
			else {
				history.displayHistory();
				System.out.print("Enter the record to be removed: ");
				int line = scanner.nextInt();
				history.removeHistory(line);
			}
			System.out.print("Do you want to continue adding more records(y/n): ");
			choice = scanner.next().charAt(0);
		}
    }
    
    public void visitURL(String url) {
    	try {
    		visitHandling.visit(url);
    		history.addNewHistory(url);
    		System.out.println("Navigate to " + url);
    	}
    	catch(Exception e) {
    		System.out.println("Invalid URL.... Try Again!!!");
    	}
    }
    
    public String getCurrentURL() {
    	return visitHandling.getCurrentURL();
    }
    
    public String getURL() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter position:");
    	int position = scanner.nextInt();
    	try {
			return visitHandling.getURL(position);
		} catch (InvalidPositionException e) {
			System.out.println(e);
		}
    	return "";
    }
    
    public void actionForward() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter steps: ");
    	int steps = scanner.nextInt();
    	try {
			String url = visitHandling.forward(steps);
			history.addNewHistory(url);
			System.out.println("Navigate to " + url);
		} catch (NoHistoryFoundException e) {
			System.out.println(e);
		}
    	
    }
    
    public void actionBackward() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Enter steps: ");
    	int steps = scanner.nextInt();
    	try {
			String url = visitHandling.backward(steps);
			history.addNewHistory(url);
			System.out.println("Navigate to " + url);
		} catch (NoHistoryFoundException e) {
			System.out.println(e);
		}
    }
}
