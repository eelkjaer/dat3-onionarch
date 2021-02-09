package domain.entity.customer;

public class CustomerException extends Exception {

  public CustomerException() {}

  public CustomerException(String message) {
    super(message);
  }

  public CustomerException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomerException(Throwable cause) {
    super(cause);
  }
}
