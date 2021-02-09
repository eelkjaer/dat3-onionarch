package domain.entity.customer;

import domain.dto.customer.CustomerDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

  private static final int serialVersionUID = 1;

  @Column(name = "firstname", length = 75, nullable = false, unique = false)
  private String firstName;

  @Column(name = "lastname", length = 75, nullable = false, unique = false)
  private String lastName;

  @Column(name = "accountnumber", length = 75, nullable = false, unique = true)
  private int accountNumber;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "balance", length = 75, nullable = false, unique = false)
  private double balance;

  @Column(name = "ranking", length = 10, nullable = false, unique = false)
  private Enum<Ranking> customerRanking;

  public Customer(
      int id,
      String firstName,
      String lastName,
      int accountNumber,
      double balance,
      Enum<Ranking> customerRanking) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.customerRanking = customerRanking;
  }

  public Customer() {}

  public Customer(
      String firstName,
      String lastName,
      int accountNumber,
      double balance,
      Enum<Ranking> customerRanking) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.customerRanking = customerRanking;
  }

  public Customer(CustomerDTO dto, Enum<Ranking> ranking) {
    String names[] = dto.getFullName().split(" ");
    this.firstName = names[0];
    this.lastName = names[1];
    this.accountNumber = dto.getAccountNumber();
    this.id = dto.getCustomerId();
    this.balance = dto.getBalance();
    this.customerRanking = ranking;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public Enum<Ranking> getCustomerRanking() {
    return customerRanking;
  }

  public void setCustomerRanking(Enum<Ranking> customerRanking) {
    this.customerRanking = customerRanking;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Customer{id=").append(id);
    sb.append(", firstName=").append(firstName);
    sb.append(", lastName=").append(lastName);
    sb.append(", accountNumber=").append(accountNumber);
    sb.append(", balance=").append(balance);
    sb.append(", customerRanking=").append(customerRanking);
    sb.append('}');
    return sb.toString();
  }

  public enum Ranking {
    Low,
    Medium,
    High
  }
}
