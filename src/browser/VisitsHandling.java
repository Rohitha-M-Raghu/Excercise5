package browser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class InvalidURLException extends Exception {
	public InvalidURLException(String errorMessage) {
		super(errorMessage);
	}
}

class NoHistoryFoundException extends Exception {
	public NoHistoryFoundException(String errorMessage) {
		super(errorMessage);
	}
}

class InvalidPositionException extends Exception {
	public InvalidPositionException(String errorMessage) {
		super(errorMessage);
	}
}

public class VisitsHandling {
	private List<String> history;
	private int currentIndex;
	private List<String> validURLEnding = new ArrayList<>();
	
	public VisitsHandling(String homepage) {
		history = new ArrayList<>();
		history.add(homepage);
		currentIndex = 0;
		validURLEnding.add("com");
		validURLEnding.add("org");
		validURLEnding.add("in");
	}
	
	public void verifyURL(String url) throws InvalidURLException{
		boolean isValid = false;
		int extensionPosition = url.lastIndexOf('.');
		if(!validURLEnding.contains(url.substring(extensionPosition+1))) {
			throw new InvalidURLException("Invalid URL");
		}
	}
	
	public String getCurrentURL() {
		return history.get(currentIndex);
	}
	
	public void visit(String url) throws InvalidURLException {
		verifyURL(url);
		history = history.subList(0, currentIndex + 1);
		history.add(url);
		currentIndex = this.history.size() - 1;
	}
	
	public String forward(int steps) throws NoHistoryFoundException {
		try {
			if(steps<0) {
				throw new IndexOutOfBoundsException("Steps cannot be negative...");
			}
			String url = history.get(currentIndex+steps);
			currentIndex+=steps;
			return url;
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e);
			throw new NoHistoryFoundException("No History Found!!!");
		}
	}
	
	public String backward(int steps) throws NoHistoryFoundException {
		try {
			if(steps<0) {
				throw new IndexOutOfBoundsException("Steps cannot be negative");
			}
			String url = history.get(currentIndex - steps);
			currentIndex-=steps;
			return url;
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println(e);
			throw new NoHistoryFoundException("No History Found!!!");
		}
	}
	
	public String getURL(int position) throws InvalidPositionException {
		if(position < 0) {
			throw new InvalidPositionException("Invalid position");
		}
		else {
			try {
				String  url = history.get(position);
				currentIndex = position;
				return url;
			}
			catch(IndexOutOfBoundsException e) {
				throw new InvalidPositionException("Invalid position");
			}
		}
	}
}
