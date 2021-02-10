package onionarch;

import onionarch.dto.CustomerDTO;
import domain.customer.entity.CustomerRepository;
import domain.customer.exceptions.CustomerException;
import java.util.List;
import java.util.stream.Collectors;

public class Onionarch {
  private final CustomerRepository customerRepository;

  public Onionarch(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<CustomerDTO> getAllCustomers() throws CustomerException {
    return customerRepository.getAllCustomers().stream()
        .map(CustomerDTO::new)
        .collect(Collectors.toList());
  }

  public CustomerDTO getCustomerById(int id) throws CustomerException {
    return new CustomerDTO(customerRepository.getCustomerById(id));
  }

  public void createCustomer(CustomerDTO dto) throws CustomerException {
    customerRepository.createCustomer(dto);
  }

  public void deleteCustomerById(int id) throws CustomerException {
    customerRepository.deleteCustomer(
        customerRepository.getCustomerById(id)
    );
  }

  public void updateCustomerById(int id, CustomerDTO dto) throws CustomerException {
    dto.setCustomerId(id);
    customerRepository.updateCustomer(dto);
  }
}
