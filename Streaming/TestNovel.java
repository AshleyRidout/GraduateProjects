/**
 * @author Ashley.Ridout
 * @date July 30, 2017
 */
package homework4;

//import java util packages
import java.util.*;
import java.util.stream.Stream;

//class to create and explore novel streams
public abstract class TestNovel {

	public static void main(String[] args) {
		// create novels ArrayList and add Novel objects to the ArrayList
		ArrayList<Novel> novels = new ArrayList<>();
		novels.add(new Novel("Dreams", "B. Sleeping", 1920));
		novels.add(new Novel("Sunshine", "R.U. Happy", 1971));
		novels.add(new Novel("Aliens", "Out O. Space", 2004));
		novels.add(new Novel("Puppies", "Kat Fleeing", 2017));
		novels.add(new Novel("Time", "E. Levin Thirty", 1995));

		// create novelStream stream
		Stream<Novel> novelStream = novels.stream();

		// list of books output to console
		System.out.println("Here's the list of available books: ");
		novelStream.forEach(System.out::println);

		// list of books in order of publication output to console
		System.out.println("\nHere's the list of book in order of publication: ");
		novels.stream().sorted(Comparator.comparing(Novel::getYear)).forEach(System.out::println);

		// list of books published between 1920 and 1995 output to console
		System.out.println("\nHere's the list of books published between 1920 and 1995: ");
		novelStream.filter(book -> book.getYear() >= 1920 && book.getYear() <= 1995).forEach(System.out::println);
	}

}
