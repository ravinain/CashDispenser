package org.dispenser.cash.service;

/**
 * Observer contract.
 * @author cdacr
 */
public interface Observer {
  /**
   * @param message
   *          message
   */
  void notify(String message);
}
