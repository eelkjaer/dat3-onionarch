package domain.dto.customer;

import domain.entity.customer.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

  private int customerId;
  private String fullName;
  private int accountNumber;
  private double balance;

  public CustomerDTO() {}

  public CustomerDTO(Customer customer) {
    this.customerId = customer.getId();
    this.fullName = String.format("%s %s", customer.getFirstName(), customer.getLastName());
    this.accountNumber = customer.getAccountNumber();
    this.balance = customer.getBalance();
  }

  public static List<CustomerDTO> getAllBankCustomersDTO(List<Customer> customers) {
    List<CustomerDTO> tmpList = new ArrayList<>();
    for (Customer c : customers) {
      tmpList.add(new CustomerDTO(c));
    }
    return tmpList;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("CustomerDTO{customerId=").append(customerId);
    sb.append(", fullName=").append(fullName);
    sb.append(", accountNumber=").append(accountNumber);
    sb.append(", balance=").append(balance);
    sb.append('}');
    return sb.toString();
  }
}
