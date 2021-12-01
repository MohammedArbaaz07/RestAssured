package APITest;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Demo2_TestNG {

	@BeforeClass
	public void BC()
	{
		System.out.println("this will execute before class");
	}
	
	@BeforeMethod
	public void BM()
	{
		System.out.println("Before every test");
	}
	

	@Test
	public void testcase3()
	{
		System.out.println("Test is my second test case");
		Assert.assertEquals("Arbaaz", "Arbaaz");
	}
	
	
	@AfterTest
	public void AT()
	{
		System.out.println("After test");
	}
	
	
	@AfterSuite
	public void AS() {
		System.out.println("This will execute at the end");
	}
	
}
