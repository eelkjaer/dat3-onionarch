/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.entity.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  private static final int serialVersionUID = 1;
  private final String firstName;
  private final String lastName;
  private final int accountNumber;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private double balance;
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
