package org.dispenser.cash.exception;

/**
 * When denomination is not valid while loading it from properties file then
 * this exception has been thrown.
 * @author cdacr
 */
public class DenominationException extends Exception {

  /** serial version. **/
  private static final long serialVersionUID = 8331062817919230969L;

  /**
   * @param message
   *          message
   */
  public DenominationException(final String message) {
    super(message);
  }

}
