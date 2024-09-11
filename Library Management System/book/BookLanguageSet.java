package system.book;
import java.util.HashSet;
import java.util.Set;

public class BookLanguageSet {
	
	Set<String> languageSet = new HashSet<String>();
	//Method to add a original Language
	public void addOriginalLanguage(String originalLanguage) {
	    languageSet.add(originalLanguage);
	}

	// Method to get the set of genres
	public Set<String> getOriginalLanguage() {
	    return languageSet;
	}

	}

