package com.demoweb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    @org.junit.Test
    public void test()
    {
    	App app = new App();
        assertEquals("10 is a even number", true, app.isEvenNumber(10));
        
    }
    
    @org.junit.Test(timeout=1000)
    public void testWithTimeout()
    {
    	App app = new App();
    	 assertEquals("String convert to int", true, app.isStringIsInterger("3   "));
    }
    
    @BeforeClass
    public void testBeforeClass()
    {
    	App app = new App();
    	assertEquals("4 is a even number", true, app.isEvenNumber(4));
    }
    
    @Before
    public void testBefore()
    {
    	App app = new App();
        assertEquals("Spaces are empty string", true, app.isEmptyString("      "));
    }
    
    
    @AfterClass
    public void testAfterClass()
    {
    	App app = new App();
    	 assertEquals("6 is a even number", true, app.isEvenNumber(6));
    }
    
    @After
    public void testAfter()
    {
    	App app = new App();
    	 assertEquals("String convert to int", true, app.isStringIsInterger(" 23132434   "));
    }
    

}
