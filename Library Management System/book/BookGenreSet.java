package system.book;
import java.util.HashSet;
import java.util.Set;

public class BookGenreSet {
Set<String> genreSet = new HashSet<String>();

//Method to add a genre
public void addGenre(String genre) {
    genreSet.add(genre);
}

// Method to get the set of genres
public Set<String> getGenres() {
    return genreSet;
}

}