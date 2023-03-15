package com.browser;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;

public class Browser {

    private String currentUrl;
    private List<Bookmark> bookmarks = new ArrayList<>();
    private History history = new History();
    
    public Browser(String currentUrl) {
        this.currentUrl = currentUrl;
    }
    
    public String getCurrentUrl() {
    	return this.currentUrl;
    }

	public void goToUrl(String url) {
        System.out.println("Navigating to " + url);
        this.currentUrl = url;
        history.addNewHistory(url);
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
            System.out.println("Bookmarks:");
            for (Bookmark bookmark : bookmarks) {
                System.out.println(bookmark.getName() + " - " + bookmark.getUrl());
            }
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
            this.url = currentUrl;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
    
    public static class History {
    	private JSONArray browserHistory = new JSONArray();
    	
    	public void addNewHistory(String url, String ip) {		//add new History
			JSONArray history = new JSONArray();
			history.put(url);
			history.put(ip);
			Timestamp accessTime = Timestamp.from(Instant.now());
			history.put(accessTime);
			browserHistory.put(history);
		}
    	
       	public void addNewHistory(String url) {
       		JSONArray history = new JSONArray();
			history.put(url);
			Timestamp accessTime = Timestamp.from(Instant.now());
			history.put(accessTime);
			browserHistory.put(history);
    	}
    	
    	public void displayHistory() {
			if(browserHistory.length() == 0) {
				System.out.println("No History To Display");
				return;
			}
			System.out.println(" Browser History");
			System.out.println("-------------------");
			String history = browserHistory.toString();
			int n = 0;
			for(int i=1;i<history.length(); ++i) {
				char ch = history.charAt(i);
				if(ch == '[') {
					n++;
					System.out.print("\n" + n + "  ");
				}
				else if (ch == ']'){
					System.out.println("");
				}
				else if(ch == ',') {
					System.out.print("\t");
				}
				else {
					System.out.print(ch);
				}
			}
		}
    	
    	public void removeHistory(int n) {
			browserHistory.remove(n-1);
			System.out.println("Record removed Successfully...");
		}
    	
    	public void resetHistory() {
			while(this.browserHistory.length()> 0) {
				browserHistory.remove(0);
			}
			System.out.println("History Reset Successfully... ");
		}
		public int getNumberOfRecords() {
			return this.browserHistory.length();
		}
    }
    
    public void historyMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n1. Display Browser History");
		System.out.println("2. Add New History Entry");
		System.out.println("3. Remove History Entry");
		System.out.println("4. Reset History");
		System.out.print("Enter your Choice: ");
		int option = scanner.nextInt();
		
		switch(option) {
			case 1: history.displayHistory();
					break;
			case 2: System.out.print("Enter url: ");
					String url = scanner.next();
					System.out.print("Enter ip: ");
					String ip = scanner.next();
					history.addNewHistory(url, ip);
					break;
			case 3: removeFromHistory();
					break;
			case 4: history.resetHistory();
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
}
