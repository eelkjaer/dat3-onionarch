package domain.customer;

public interface CustomerFactory {

  Customer createCustomer(CustomerDTO customerdto) throws CustomerException;
}
