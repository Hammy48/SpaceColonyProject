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
public class ColonyCalculatorTest extends TestCase {
    private ColonyCalculator calculator;
    private ArrayQueue<Person> queue;
    private Planet[] planets;


    /**
     * set up
     */
    public void setUp() {
        queue = new ArrayQueue<Person>();
        planets = new Planet[4];

        planets[1] = new Planet("Planet1", 4, 4, 4, 2);
        planets[2] = new Planet("Planet2", 4, 4, 4, 2);
        planets[3] = new Planet("Planet3", 4, 4, 4, 2);
        calculator = new ColonyCalculator(queue, planets);
    }


    /**
     * test get queue
     */
    public void testGetQueue() {
        assertEquals(queue, calculator.getQueue());
    }


    /**
     * test get plaent for person
     */
    public void testGetPlanetForPerson() {
        assertNull(calculator.getPlanetForPerson(null));
        Person person1 = new Person("Justin Wong", 4, 4, 4, "Planet1");
        assertEquals("Planet1", calculator.
            getPlanetForPerson(person1).getName()
            .toString());
        planets[1].addPerson(person1);
        Person person2 = new Person("Justin Wong", 4, 4, 4, "");
        assertEquals("Planet3", calculator.
            getPlanetForPerson(person2).getName()
            .toString());
        planets[3].addPerson(person2);
        Person person3 = new Person("Justin Wong", 5, 5, 5, "Planet1");
        planets[1].addPerson(person3);
        assertTrue(planets[1].isFull());
        Person person4 = new Person("Justin Wong", 3, 4, 5, "Planet1");
        assertNull(calculator.getPlanetForPerson(person4));
//        Person person5 = new Person("Justin Wong", 5, 5, 5, "Planet2");
        //planet is full 
        // planet is not full && person is not qualified 
    }


    /**
     * test accept
     */
    public void testAccept() {
        assertFalse(calculator.accept());
        Person person8 = new Person("Justin Wong", 5, 5, 5, "Planet1");
        queue.enqueue(person8);
        assertTrue(calculator.accept());
        assertEquals(planets[1].getPopulationSize(), 1);
        assertEquals(0, queue.getSize());
// queue.dequeue();
        Person person9 = new Person("Justin Wong", 1, 1, 1, "");
        queue.enqueue(person9);
        assertFalse(calculator.accept());
    }


    /**
     * test reject
     */
    public void testReject() {
        calculator.reject();
        assertEquals(queue.getSize(), 0);
        Person person8 = new Person("Justin Wong", 5, 5, 5, "Planet1");
        queue.enqueue(person8);
        assertEquals(queue.getSize(), 1);
        calculator.reject();
        assertEquals(queue.getSize(), 0);
    }


    /**
     * test by number
     */
    public void testPlanetByNumber() {
        assertEquals(planets[1], ColonyCalculator.planetByNumber(1));
        assertEquals(planets[2], ColonyCalculator.planetByNumber(2));
        assertEquals(planets[3], ColonyCalculator.planetByNumber(3));
        assertNull(ColonyCalculator.planetByNumber(0));
    }


    /**
     * test inde x
     */
    public void testGetPlanetIndex() {
        assertEquals(1, calculator.getPlanetIndex("Planet1"));
        assertEquals(2, calculator.getPlanetIndex("Planet2"));
        assertEquals(3, calculator.getPlanetIndex("Planet3"));
        assertEquals(0, calculator.getPlanetIndex(""));
        planets[1] = null;
        assertEquals(0, calculator.getPlanetIndex("Planet1"));
        planets[2] = null;
        assertEquals(0, calculator.getPlanetIndex("Planet2"));
        planets[3] = null;
        assertEquals(0, calculator.getPlanetIndex("Planet3"));
    }


    /**
     * test get plaents
     */
    public void testGetPlanets() {
        assertEquals(ColonyCalculator.
            getPlanets()[1].getName().toString(), "Planet1");
        assertEquals(ColonyCalculator.
            getPlanets()[2].getName().toString(), "Planet2");
        assertEquals(ColonyCalculator.getPlanets()[3].
            getName().toString(), "Planet3");         
        
    }
    
    /**
     * test constructoe
     */
    public void testConstructor() {
        Exception exception = null;
//        ColonyCalculator temp = null;
        try {
            ColonyCalculator temp = new ColonyCalculator(null, null);
            temp.getClass();
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertTrue(exception instanceof IllegalArgumentException);
        
    }

}
