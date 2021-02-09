package domain.dto.customer;

import java.util.List;

public interface CustomerDTORepository extends CustomerDTOFactory {

  List<CustomerDTO> getAllCustomers() throws CustomerDTOException;

  CustomerDTO getCustomerById(int id) throws CustomerDTOException;

  List<CustomerDTO> getCustomersByName(String name) throws CustomerDTOException;

  void updateCustomer(CustomerDTO customer) throws CustomerDTOException;

  void deleteCustomer(CustomerDTO customer) throws CustomerDTOException;
}
