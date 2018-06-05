package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import imagereader.IImageIO;

public class ImplementImageIOTest {
	ImplementImageIO imageioer1 = new ImplementImageIO();
	ImplementImageIO imageioer2 = new ImplementImageIO();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHeight() {
		try {
			imageioer1.myRead("../../assets/source/1.bmp");
			imageioer2.myRead("../../assets/1.bmp");
			assertEquals(imageioer1.getTestHeight(), imageioer2.getTestHeight());
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testWidth() {
		try {
			imageioer1.myRead("../../assets/source/1.bmp");
			imageioer2.myRead("../../assets/1.bmp");
			assertEquals(imageioer1.getTestWidth(), imageioer2.getTestWidth());
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Not yet implemented");
		}
	}

	@Test
	public void testData() {
		try {
			imageioer1.myRead("../../assets/source/1.bmp");
			imageioer2.myRead("../../assets/1.bmp");
			assertArrayEquals(imageioer1.getTestData(), imageioer2.getTestData());
		} catch (Exception e) {
			// TODO: handle exception
			fail("Not yet implemented");
		}	
	}

}
