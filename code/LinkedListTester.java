/**
* @author Kailey Bergeron 
* @version 11.0.8
* @since September 26th, 2022
* Class for LinkedList Tester
*/

//imports 
import static org.junit.Assert.*;
import org.junit.*;

public class LinkedListTester{
	
	private MyLinkedList<String> testerList;

	@Before 
	public void set(){
		testerList = new MyLinkedList<>();
	}
	@Test 
	public void testSize(){
		testerList.add("Dog");
		testerList.add("Cat");
		testerList.add("Cow");
		testerList.add("Mouse");
		testerList.add("Rat");
		assertTrue(testerList.size() == 5);
		assertFalse(testerList.size() != 5);
	}

	@Test 
	public void testAdd(){
		testerList.add("Dog");
		testerList.add("Cat");
		testerList.add("Cow");
		testerList.add("Mouse");
		testerList.add("Rat",2);

        assertTrue(testerList.get(2) == "Rat");
        assertTrue(testerList.get(1) == "Cat");
        assertFalse(testerList.get(1) == "Rat");
   	}
   
   	@Test 
   	public void testGet(){
		testerList.add("Dog");
		testerList.add("Cat");
		testerList.add("Cow"); 
		testerList.add("Mouse");
		testerList.add("Rat");

		assertTrue(testerList.get(1) == "Cat");
		assertFalse(testerList.get(1) == "Dog"); 
	}
	
	@Test 
	public void testLazyRemove(){
		testerList.add("Dog");
		testerList.add("Cat");
		testerList.add("Cow");
		testerList.add("Mouse");
		testerList.add("Rat");

		testerList.lazyRemove(3);
		testerList.lazyRemove(0);

		assertTrue(testerList.size() == 5);
		assertTrue(testerList.get(1) == "Cat");
		assertTrue(testerList.get(3) == null);
	}
}