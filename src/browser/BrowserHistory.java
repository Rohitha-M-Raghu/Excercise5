package browser;

import java.sql.Timestamp;
import java.time.Instant;

import org.json.JSONArray;

public class BrowserHistory {
	private JSONArray browserHistory = new JSONArray();
	
   	public void addNewHistory(String url) {
   		JSONArray history = new JSONArray();
		history.put(url);
		Timestamp accessTime = Timestamp.from(Instant.now());
		history.put(accessTime);
		browserHistory.put(history);
	}
	
	public void displayHistory() {
		if(browserHistory.length() == 0) {
			System.out.println("No BrowserHistory To Display");
			return;
		}
		System.out.println(" Browser BrowserHistory");
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
		System.out.println("BrowserHistory Reset Successfully... ");
	}
	public int getNumberOfRecords() {
		return this.browserHistory.length();
	}
}