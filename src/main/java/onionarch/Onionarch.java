package onionarch;

import domain.customer.entity.CustomerRepository;
import domain.customer.exceptions.CustomerException;
import domain.customer.exceptions.CustomerNotFound;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import onionarch.dto.CustomerDTO;

public class Onionarch {
  private final CustomerRepository customerRepository;

  public Onionarch(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public List<CustomerDTO> getAllCustomers(){
    try {
      return customerRepository.getAllCustomers().stream()
          .map(CustomerDTO::new)
          .collect(Collectors.toList());
    } catch (domain.customer.exceptions.CustomerNotFound customerNotFound) {
      customerNotFound.printStackTrace();
    }
    return new ArrayList<>();
  }

  public CustomerDTO getCustomerById(int id) throws CustomerNotFound {
    return new CustomerDTO(customerRepository.getCustomerById(id));
  }

  public void createCustomer(CustomerDTO dto) throws CustomerException {
    customerRepository.createCustomer(dto);
  }

  public boolean deleteCustomerById(int id) throws CustomerNotFound {
    return customerRepository.deleteCustomer(
        new CustomerDTO(customerRepository.getCustomerById(id)));
  }

  public CustomerDTO updateCustomerById(int id, CustomerDTO dto) throws CustomerNotFound {
    dto.setCustomerId(id);
    return customerRepository.updateCustomer(dto);
  }

  public Object getCustomerByNumber(String accNum) throws CustomerNotFound {
    return new CustomerDTO(customerRepository.getCustomerByAccountNumber(accNum));
  }
}
