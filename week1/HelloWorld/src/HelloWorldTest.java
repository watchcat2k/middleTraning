import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HelloWorldTest {
	public HelloWorld helloworld = new HelloWorld();
	@Test
	public void testHello() {
		helloworld.hello();
		assertEquals("Hello sWorld!", helloworld.getStr());
	}
}