package org.dispenser.cash.utils.it;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.dispenser.cash.exception.DenominationException;
import org.dispenser.cash.model.DenominationMap;
import org.dispenser.cash.utils.Constants;
import org.dispenser.cash.utils.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test class for {@link FileUtils}.
 * @author cdacr
 */
public class FileUtilsIT {

  /** FileUtils instance. */
  private FileUtils utils = null;
  /** Expected exception rule. */
  @Rule
  public ExpectedException expect = ExpectedException.none();

  /**
   * Initialization before executing test case.
   * @throws Exception
   *           Exception
   */
  @Before
  public final void setUp() throws Exception {
    utils = new FileUtils();
  }

  /**
   * Release resources after executing test case.
   * @throws Exception
   *           Exception
   */
  @After
  public final void tearDown() throws Exception {
    utils = null;
  }

  /**
   * Test method for
   * {@link FileUtils#loadDenominationMap(DenominationMap, String)}.
   */
  @Test
  public final void testLoadDenominationMap() {
    final DenominationMap denominationMap = new DenominationMap();
    try {
      utils.loadDenominationMap(denominationMap, Constants.DENO_MAP_FILE);
      assertNotNull(denominationMap);
      assertThat(denominationMap.getDenominationCount(50), is(4));
      assertThat(denominationMap.getDenominationCount(20), is(6));
    } catch (IOException | DenominationException e) {
      fail("Must not throw exception!");
    }
  }

  /**
   * Test method for
   * {@link FileUtils#loadDenominationMap(DenominationMap, String)} Exception
   * scenario.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMapException()
      throws IOException, DenominationException {
    final DenominationMap denominationMap = new DenominationMap();
      expect.expect(IOException.class);
      utils.loadDenominationMap(denominationMap,
          Constants.DENO_MAP_FILE + "asfds");
      fail("Must throw exception as file does not exists!");
  }

  /**
   * Test method for
   * {@link FileUtils#loadDenominationMap(DenominationMap, String)}
   * DenominationException scenario.
   * @throws IOException
   *           IOException
   * @throws DenominationException
   *           DenominationException
   */
  @Test
  public final void testLoadDenominationMapDenominationException()
      throws IOException, DenominationException {

    final DenominationMap denominationMap = new DenominationMap();
    expect.expect(DenominationException.class);
    utils.loadDenominationMap(denominationMap, "Invalid_Data.properties");
    fail("Must throw exception as file does not exists!");
  }

}
