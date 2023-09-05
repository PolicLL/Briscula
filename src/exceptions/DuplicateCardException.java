package exceptions;

public class DuplicateCardException extends Exception {
    public DuplicateCardException() {
        super("Two cards with the same type and value were found.");
    }
}
