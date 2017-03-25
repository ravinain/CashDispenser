package org.dispenser.cash.utils.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dispenser.cash.utils.CommonUtils;
import org.junit.Test;

/**
 * CommonUtils test class.
 * @author cdacr
 */
public class CommonUtilsTest {

  /**
   * Test case of {@link CommonUtils#prepareCashDispenseOutput(java.util.Map)}.
   */
  @Test
  public final void testPrepareCashDispenseOutput() {
    final Map<Integer, Integer> inputMap = new HashMap<>();
    inputMap.put(50, 2);
    inputMap.put(20, 4);
    inputMap.put(10, 3);

    final String expectedOutput = "Dispensed Cash : 50x2 20x4 10x3 ";
    final String actualOutput = CommonUtils.prepareCashDispenseOutput(inputMap);
    assertNotNull(actualOutput);
    assertTrue(actualOutput, expectedOutput.equals(actualOutput));
  }

  /**
   * Test case of {@link CommonUtils#prepareCashAvailableOutput(java.util.Map)}.
   */
  @Test
  public final void testPrepareCashAvailableOutput() {
    final Map<Integer, Integer> inputMap = new HashMap<>();
    inputMap.put(50, 2);
    inputMap.put(20, 4);
    inputMap.put(10, 3);

    final String expectedOutput = "Available Cash : 50x2 20x4 10x3 ";
    final String actualOutput =
        CommonUtils.prepareCashAvailableOutput(inputMap);
    assertNotNull(actualOutput);
    assertTrue(actualOutput, expectedOutput.equals(actualOutput));
  }

  /**
   * Test case of {@link CommonUtils#listToStringWithSeparator(List, String)}.
   */
  @Test
  public final void testListToStringWithSeparator() {
    final List<Integer> inputList = new ArrayList<>();
    inputList.add(50);
    inputList.add(20);
    inputList.add(10);

    final String expectedStr = "50, 20, 10";
    final String actualStr =
        CommonUtils.listToStringWithSeparator(inputList, ", ");

    assertNotNull(actualStr);
    assertTrue(expectedStr.equals(actualStr));
  }

}
