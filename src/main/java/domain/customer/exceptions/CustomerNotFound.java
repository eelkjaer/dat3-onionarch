package domain.customer.exceptions;

public class CustomerNotFound extends Exception {

  public CustomerNotFound() {
    super("Customer not found");
  }

  public CustomerNotFound(String message) {
    super(message);
  }
}
