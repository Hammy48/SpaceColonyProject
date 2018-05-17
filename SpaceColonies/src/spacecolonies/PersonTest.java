package spacecolonies;

import student.TestCase;

/**
 * The ColonyReader parses the input data from two text files. It generates the
 * planets and queue of applicants based on one file of comma separated values
 * about applicants and the other about each planet. Then it gives SpaceWindow
 * this queue in order to tie everything together.
 * 
 * @author Zhiyuan Li
 * @version 2018.04.13
 *
 */
public class PersonTest extends TestCase {
    private Person person;
    
    /**
     * set up
     */
    public void setUp() {
        person = new Person("Justin Wong", 4, 4, 4, "Planet1");
        
    }
    
    /**
     * test get name
     */
    public void testGetName() {
        assertEquals("Justin Wong", person.getName());
    }
    
    /**
     * test get skill
     */
    public void testGetSkills() {
        Skills test = new Skills(4, 4, 4);
        assertTrue(person.getSkills().equals(test));
    }
    
    /**
     * test get name
     */
    public void testGetPlanetName() {
        assertEquals("Planet1", person.getPlanetName());
        
    }
    
    /**
     * test to string
     */
    public void testToString() {
//        "No-Planet Justin Wong A:4 M:4 T:4"
        String test = "Justin Wong A:4 M:4 T:4 Wants: Planet1";
        assertEquals(person.toString(), test);
        Person newPerson = new Person("Justin Wong", 4, 4, 4, "");
        test = "No-Planet Justin Wong A:4 M:4 T:4";
        assertEquals(newPerson.toString(), test);
    }
    
    
    /**
     * test equals
     */
    public void testEqauls() {
        Person newPerson = null;
        assertFalse(person.equals(newPerson));
        assertTrue(person.equals(person));
        newPerson = new Person("Justin Wong", 4, 4, 4, "Planet1");
        assertTrue(person.equals(newPerson));
        
        newPerson = new Person("Justin Lee", 4, 4, 4, "Planet1");
        assertFalse(person.equals(newPerson));
        newPerson = new Person("Justin Wong", 3, 3, 3, "Planet1");
        assertFalse(person.equals(newPerson));
        newPerson = new Person("Justin Wong", 4, 4, 4, "Planet2");
        assertFalse(person.equals(newPerson));
        String testStr = "";
        assertFalse(person.equals(testStr));
        
    }
}
