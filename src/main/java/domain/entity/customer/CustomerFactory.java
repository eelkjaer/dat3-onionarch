package domain.entity.customer;

public interface CustomerFactory {

  Customer createCustomer(Customer customer) throws CustomerException;
}
