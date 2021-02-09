/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dto.customer;

/** @author emilelkjaernielsen */
public class CustomerDTOException extends Exception {

  public CustomerDTOException() {}

  public CustomerDTOException(String message) {
    super(message);
  }

  public CustomerDTOException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomerDTOException(Throwable cause) {
    super(cause);
  }
}
