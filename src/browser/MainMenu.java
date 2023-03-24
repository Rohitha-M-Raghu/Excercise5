package browser;

import java.util.Scanner;

public class MainMenu {
	private static Browser browser = new Browser("www.google.com");

	public static void main(String[] args) {
				Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		while(!quit) {
			System.out.println("Main Menu");
			System.out.println("===================");
			System.out.println("1. Visit url");
			System.out.println("2. Current url");
			System.out.println("3. Get url");
			System.out.println("4. Forward");
			System.out.println("5. Backward");
			System.out.println("6. Bookmarks");
			System.out.println("7. BrowserHistory");
			System.out.println("8. Quit");
			System.out.print("Enter your Choice: ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: System.out.print("Enter url: ");
			        String url = scanner.next();
			        browser.visitURL(url);
				break;
			case 2: System.out.println(browser.getCurrentURL());
				break;
			case 3: System.out.println(browser.getURL());
				break;
			case 4: browser.actionForward();
				break;
			case 5: browser.actionBackward();
				break;
			case 6: bookmarksMenu();
				break;
			case 7: browser.historyMenu();
				break;
			case 8: System.out.println("Exiting Application....");
					quit = true;
				break;
			default:
				break;
			}
		}
	}
	
	public static void bookmarksMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("BookMarks Menu");
		System.out.println("---------------------");
		System.out.println("1. Add Bookmarks");
		System.out.println("2. List Bookmarks");
		System.out.println("3. Delete Bookmarks");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();
		String name;
		switch (choice) {
		case 1:
			System.out.print("Enter Name: ");
			name = scanner.next();
			browser.addBookmark(name);
			break;
		case 2:
			browser.listBookmarks();
			break;
		case 3:
			browser.listBookmarks();
			System.out.print("Enter name: ");
			name = scanner.next();
			browser.deleteBookmarks(name);
		default:
			break;
		}
	}
}
