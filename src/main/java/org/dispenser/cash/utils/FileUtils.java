package org.dispenser.cash.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;

/**
 * File Utility.
 * @author cdacr
 */
public class FileUtils {

  /**
   * Load denomination mapping from properties file.
   * @param denominationMap
   *          denominationMap
   * @param fileName
   *          properties file name
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  public final void loadDenominationMap(final DenominationMap denominationMap,
      final String fileName) throws IOException, DenominationException {
    final ClassLoader classLoader = getClass().getClassLoader();

    InputStream inStream = classLoader.getResourceAsStream(fileName);
    try {
      if (inStream == null) {
        inStream = new FileInputStream(fileName);
      }
      try (InputStreamReader inReader = new InputStreamReader(inStream);
          BufferedReader reader = new BufferedReader(inReader);) {
        String entry = "";
        while ((entry = reader.readLine()) != null) {
          final String[] keyValue = entry.split("=");
          if (keyValue == null || keyValue.length != 2) {
            throw new DenominationException(
                String.format(Constants.INVALID_DENOMINATION_MAPPING, entry));
          }
          try {
            denominationMap.add(Integer.valueOf(keyValue[0]),
                Integer.valueOf(keyValue[1]));
          } catch (final NumberFormatException ex) {
            throw new DenominationException(ex.getMessage());
          }
        }
      }
    } finally {
      try {
        if (inStream != null) {
          inStream.close();
        }
      } catch (final Exception e2) {
      }
    }
  }

}
