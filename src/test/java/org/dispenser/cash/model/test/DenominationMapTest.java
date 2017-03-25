/**
 * 
 */
package org.dispenser.cash.model.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.dispenser.cash.model.DenominationMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author cdacr
 *
 */
public class DenominationMapTest {

  /** DenominationMap instance. */
  private DenominationMap denominationMap;

  /**
   * Initialization before executing test case.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    denominationMap = new DenominationMap();
  }

  /**
   * Release resources after executing test case.
   * @throws Exception
   *           Exception
   */
  @After
  public final void tearDown() throws Exception {
    denominationMap = null;
  }

  /**
   * Test method for {@link DenominationMap#add(Integer, Integer)}.
   */
  @Test
  public final void testAdd() {
    denominationMap.add(20, 3);
    Integer value = denominationMap.getDenominationCount(20);
    assertNotNull(value);
    assertThat(value, is(3));

    denominationMap.add(20, 3);
    value = denominationMap.getDenominationCount(20);
    assertNotNull(value);
    assertThat(value, is(6));
  }

  /**
   * Test method for {@link DenominationMap#deduct(Integer, Integer)}.
   */
  @Test
  public final void testDeduct() {
    denominationMap.add(50, 10);
    denominationMap.deduct(50, 4);
    Integer value = denominationMap.getDenominationCount(50);
    assertNotNull(value);
    assertThat(value, is(6));

    denominationMap.deduct(50, 4);
    value = denominationMap.getDenominationCount(50);
    assertNotNull(value);
    assertThat(value, is(2));
  }

  /**
   * Test method for {@link DenominationMap#getDenominations()}.
   */
  @Test
  public final void testGetDenominations() {
    denominationMap.add(30, 7);
    denominationMap.add(50, 2);
    denominationMap.add(20, 6);
    denominationMap.add(10, 4);

    final List<Integer> sortedList = denominationMap.getDenominations();

    assertNotNull(sortedList);

    final int[] expectedA = new int[] {50, 30, 20, 10};

    for (int i = 0; i < expectedA.length; i++) {
      assertThat(sortedList.get(i), is(expectedA[i]));
    }
  }

}
