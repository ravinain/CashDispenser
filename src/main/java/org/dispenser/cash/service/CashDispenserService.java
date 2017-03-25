package org.dispenser.cash.service;

import java.io.IOException;
import java.util.Map;

import org.dispenser.cash.exception.CashDispenseException;
import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;

/**
 * Service layer of cash dispenser.
 * @author cdacr
 */
public interface CashDispenserService extends Threshold {

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
   * Prepare the combination of notes which machine can withdraw based on input
   * amount.
   * @param amount
   *          amount
   * @return combination of notes to be withdrawn.
   * @throws CashDispenseException
   *           throws exception if any error occurred while dispensing cash.
   */
  Map<Integer, Integer> prepareCombination(final int amount)
      throws CashDispenseException;

  /**
   * Fetch the denominations which machine has.
   * @return denomination mapping.
   */
  DenominationMap getDenominationMap();
}
