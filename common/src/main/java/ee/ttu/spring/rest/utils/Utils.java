package ee.ttu.spring.rest.utils;

import ee.ttu.spring.rest.domain.entity.Book;
import ee.ttu.spring.rest.exception.exceptions.InvalidDataException;

/**
 * Created by Hans on 19.03.2017.
 */
public class Utils {

    public static boolean isStringAnInteger(String input) {
        try {
            int number = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static void validateBook(Book book) {
        if (book.getPageCount() == null || book.getPageCount() < 1) {
            throw new InvalidDataException("Page count cannot be less than 1");
        }

        if (book.getAuthorsNames() == null || book.getAuthorsNames().isEmpty()) {
            throw new InvalidDataException("Author(s) must be provided.");
        }

        if (isStringAnInteger(book.getGenre())) {
            throw new InvalidDataException("Genre can't be a number.");
        }

        if (book.getGenre() == null || book.getGenre().isEmpty()) {
            throw new InvalidDataException("Genre must be provided.");
        }
    }
}
