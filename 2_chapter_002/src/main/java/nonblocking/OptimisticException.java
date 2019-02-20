package nonblocking;
/**
 * Class  - Исключение о нарушении целостности данных.
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
