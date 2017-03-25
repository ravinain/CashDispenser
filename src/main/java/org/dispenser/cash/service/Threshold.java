package org.dispenser.cash.service;

/**
 * @author cdacr
 *
 */
public interface Threshold {

  /**
   * Register Observer.
   * @param observer
   *          observer
   */
  void registerObserver(Observer observer);

  /**
   * Remove Observer.
   * @param observer
   *          observer
   */
  void removeObserver(Observer observer);

  /**
   * Notify all registered observers.
   */
  void notifyObservers();

}
