package onionarch.dto;

import domain.customer.entity.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

  private int customerId;
  private String fullName;
  private String accountNumber;
  private double balance;

  public CustomerDTO() {}

  public CustomerDTO(Customer customer) {
    this.customerId = customer.getId();
    this.fullName = String.format("%s %s", customer.getFirstName(), customer.getLastName());
    this.accountNumber = customer.getAccountNumber();
    this.balance = customer.getBalance();
  }

  public CustomerDTO(String firstname, String lastname, double balance) {
    this.fullName = String.format("%s %s", firstname, lastname);
    this.balance = balance;
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

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
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
    sb.append('}');
    return sb.toString();
  }
}
