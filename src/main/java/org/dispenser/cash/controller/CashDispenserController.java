package org.dispenser.cash.controller;

import java.io.IOException;

import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;
import org.dispenser.cash.service.Observer;

/**
 * This class is a controller of cash dispenser device.
 * @author cdacr
 */
public interface CashDispenserController extends Observer {
  /**
   * Prepare and print the combination of notes which machine can withdraw based
   * on input amount.
   * @param amount
   *          An amount which user wants to withdraw.
   */
  void prepareCombination(int amount);

  /**
   * Load the denomination mapping from properties time.
   * @param fileName
   *          properties file name which has denomination mapping
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  void loadDenominationMap(final String fileName)
      throws IOException, DenominationException;

  /**
   * Fetch the denominations which machine has.
   * @return denomination mapping.
   */
  DenominationMap getDenominationMap();
}
