package org.dispenser.cash.exception;

/**
 * Any error occurs while dispensing cash, throw this exception.
 * @author cdacr
 */
public class CashDispenseException extends Exception {

  /** serial version. **/
  private static final long serialVersionUID = 6333492495284801132L;

  /**
   * @param message
   *          message
   */
  public CashDispenseException(final String message) {
    super(message);
  }

}
