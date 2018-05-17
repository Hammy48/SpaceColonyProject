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
public class PlanetTest extends TestCase {
    private Planet planet;


    /**
     * set up
     */
    public void setUp() {
        planet = new Planet("Planet1", 4, 4, 4, 5);
    }


    /**
     * test set naem
     */
    public void testSetName() {
        assertEquals("Planet1", planet.getName());
        planet.setName("Planet2");
        assertEquals("Planet2", planet.getName());
    }


    /**
     * test get nanem
     */
    public void testGetName() {
        assertEquals("Planet1", planet.getName());
    }


    /**
     * test get skills
     */
    public void testGetSkills() {
        Skills test = new Skills(4, 4, 4);
        assertTrue(planet.getSkills().equals(test));
    }


    /**
     * test get population
     */
    public void testGetPopulation() {
        Person[] test = planet.getPopulation();
        Person person1 = new Person("Wong", 4, 4, 4, "Planet1");
        Person person2 = new Person("Lee", 4, 4, 4, "Planet1");
        Person person3 = new Person("Lam", 4, 4, 4, "Planet1");
        planet.addPerson(person1);
        planet.addPerson(person2);
        planet.addPerson(person3);
        test[0] = person1;
        test[1] = person2;
        test[2] = person3;
        assertEquals(test, planet.getPopulation());
    }


    /**
     * test get size
     */
    public void testGetPopulationSize() {
        assertEquals(0, planet.getPopulationSize());
        Person person1 = new Person("Wong", 4, 4, 4, "Planet1");
        Person person2 = new Person("Lee", 4, 4, 4, "Planet1");
        Person person3 = new Person("Lam", 4, 4, 4, "Planet1");
        planet.addPerson(person1);
        planet.addPerson(person2);
        planet.addPerson(person3);
        assertEquals(3, planet.getPopulationSize());
    }


    /**
     * test get capacity
     */
    public void testGetCapacity() {
        assertEquals(5, planet.getCapacity());
    }


    /**
     * test get available
     */
    public void testGetAvailability() {
        assertEquals(5, planet.getAvailability());
        Person person1 = new Person("Wong", 4, 4, 4, "Planet1");
        Person person2 = new Person("Lee", 4, 4, 4, "Planet1");
        Person person3 = new Person("Lam", 4, 4, 4, "Planet1");
        planet.addPerson(person1);
        planet.addPerson(person2);
        planet.addPerson(person3);
        assertEquals(2, planet.getAvailability());
    }


    /**
     * test is full
     */
    public void testIsFull() {
        for (int i = 0; i < 10; i++) {
            Person person1 = new Person("Wong", 4, 4, 4, "Planet1");
            planet.addPerson(person1);
        }
        assertTrue(planet.isFull());
    }


    /**
     * test add person
     */
    public void testAddPerson() {
        Person person1 = new Person("Lam", 4, 4, 4, "Planet1");
        assertTrue(planet.addPerson(person1));
        Person person2 = new Person("Lam", 3, 3, 3, "Planet1");
        assertFalse(planet.addPerson(person2));
        Person person3 = new Person("Lam", 4, 4, 4, "Planet1");
        assertTrue(planet.addPerson(person3));
        Person person4 = new Person("Lam", 4, 4, 4, "Planet1");
        assertTrue(planet.addPerson(person4));
        Person person5 = new Person("Lam", 4, 4, 4, "Planet1");
        assertTrue(planet.addPerson(person5));
        Person person6 = new Person("Lam", 4, 4, 4, "Planet1");
        assertTrue(planet.addPerson(person6));
        Person person7 = new Person("Lam", 4, 4, 4, "Planet1");
        assertFalse(planet.addPerson(person7));
    }


    /**
     * test is qualified
     */
    public void testIsQualified() {
        Person person1 = new Person("Lam", 4, 4, 4, "Planet1");
        assertTrue(planet.isQualified(person1));
        Person person2 = new Person("Lam", 3, 3, 3, "Planet1");
        assertFalse(planet.isQualified(person2));
    }


    /**
     * test compare to
     */
    public void testCompareTo() {
        Planet test = null;
        Exception exception = null;
        try {
            planet.compareTo(test);
        }
        catch (IllegalStateException e) {
            exception = e;
        }
        assertTrue(exception instanceof IllegalStateException);
        assertEquals(0, planet.compareTo(planet));
        test = new Planet("Planet1", 1, 1, 1, 10);
        assertEquals(-5, planet.compareTo(test));
        Person person = new Person("Wong", 4, 4, 4, "Planet1");
        test.addPerson(person);
        assertEquals(-4, planet.compareTo(test));
        Person person1 = new Person("Lee", 4, 4, 4, "Planet1");
        Person person2 = new Person("Lam", 4, 4, 4, "Planet1");
        planet.addPerson(person1);
        planet.addPerson(person2);
        assertEquals(-6, planet.compareTo(test));
    }


    /**
     * test equals
     */
    public void testEquals() {
        Planet test = null;
        assertFalse(planet.equals(test));
        assertTrue(planet.equals(planet));
      //same name same skill diff cpapacity 
        test = new Planet("Planet1", 4, 4, 4, 15);
        assertFalse(planet.equals(test));
        
        
        
        test = new Planet("Planet1", 3, 1, 1, 15);
        assertFalse(planet.equals(test));
        test = new Planet("Planet1", 4, 4, 4, 15);
        planet.addPerson(new Person("Lee", 5, 5, 5, "Planet1"));
        planet.addPerson(new Person("Lee", 5, 5, 5, "Planet1"));
        assertFalse(planet.equals(test));
      
        
        
        //diff name same skill same cpacity
        test = new Planet("Planet2", 4, 4, 4, 5);
        assertFalse(planet.equals(test));
        //same name diff skill same capcity 
        test = new Planet("Planet1", 3, 4, 4, 5);
        assertFalse(planet.equals(test));
        test = null;
        test = new Planet("Planet1", 4, 4, 4, 10);
        assertFalse(planet.equals(test));
        //same naem sname skills sname numberof people diff capcacity 
        //
        
        
//("Planet1", 4, 4, 4, 5)
        String str = "";
        assertFalse(planet.equals(str));
    }
    
    /**
     * test to string
     */
    public void testToString() {
        Planet planetA = new Planet("Planet1", 3, 4, 2, 5);
        String str = 
            "Planet1, population 0 (cap: 5), Requires: A >= 3, M >= 4, T >= 2";
        assertEquals(str, planetA.toString());
    }
}
