import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class represents a test class for our project, which tests all the statistics that we display to the user.
 * The methods which are tested from the StatisticsPage class are:
 * calcAvgRevNum()
 * availableProp()
 * homeApt()
 * expNeigh()
 * mostAvgReviewed()
 * The test class tests that these methods return the statistic values that they are intended to return.
 *
 */
public class StatisticsPageTest {

    StatisticsPage statisticsPageTest;

    /**
 * Constructor for the test class StatisticsPageTest.
 */
public StatisticsPageTest() {}

/**
 * Sets up the test fixture.
 * Creates a new StatisticsPage object.
 * Called before every test case method.
 */
@Before
public void setUp()
{
    statisticsPageTest = new StatisticsPage();
}

/**
 * Tears down the test fixture.
 * Sets the StatisticsPage object to null.
 * Called after every test case method.
 */
@After
public void tearDown()
{
    statisticsPageTest = null;
}

    /**
     * A test method designed to test the method calcAvgRevNum() located in the StatisticsPage class.
     * The method being tested originally returns the average reviews per property.
     * This method will check whether or not the correct value is returned.
     */
    @Test
public void testCalcAvgRevNum() {
    setUp();
    assertEquals(12, 12.47, statisticsPageTest.calcAvgRevNum());
    tearDown();
    }

    /**
     * A test method designed to test the method availableProp() located in the StatisticsPage class.
     * The method being tested originally returns the total number of available properties.
     * This method will check whether or not the correct value is returned.
     */
    @Test
    public void testAvailableProp() {
    setUp();
    assertEquals(53904, statisticsPageTest.availableProp());
    tearDown();
    }

    /**
     * A test method designed to test the method homeApt() located in the StatisticsPage class.
     * The method being tested originally returns the total number of properties labelled as "home" or "entire apt".
     * This method will check whether or not the correct value is returned.
     */
    @Test
    public void testHomeApt() {
    setUp();
    assertEquals(27175, statisticsPageTest.homeApt());
    tearDown();
    }

    /**
     * A test method designed to test the method expNeigh() located in the StatisticsPage class.
     * The method being tested originally returns the most expensive neighborhood.
     * This method will check whether or not the correct value is returned.
     */
    @Test
    public void testExpNeigh() {
    setUp();
    assertEquals("Richmond upon Thames", statisticsPageTest.expNeigh());
    tearDown();
    }

    /**
     * A test method designed to test the method mostAvgReviewed() located in the StatisticsPage class.
     * The method being tested originally returns the borough with highest average review numbers.
     * This method will check whether or not the correct value is returned.
     */
    @Test
    public void testMostAvgReviewed() {
        setUp();
        assertEquals("Westminster", statisticsPageTest.mostAvgReviewed());
        tearDown();
    }

    /**
     * A test method designed to test the method mostLikelyNight() located in the StatisticsPage class.
     * The method being tested originally returns the borough with the highest availability when considering
     * the minimum amount of nights a property is available for.
     * This method will check whether or not the correct value is returned.
     */
    @Test
    public void testMostLikelyNight()
    {
        setUp();
        assertEquals("Harrow", statisticsPageTest.mostLikelyNight());
        tearDown();
    }
}

