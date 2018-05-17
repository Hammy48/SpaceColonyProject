package spacecolonies;

import java.util.Arrays;
import list.AList;

/**
 * This object handles all the major calculations and decision-making for the
 * program. It is in charge of handling accept and reject instructions and
 * checking that all requirements for a person are met before they are added to
 * a planet. It works together with SpaceWindow.
 * 
 * @author Zhiyuan Li
 * @version 2018.04.13
 *
 */
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Zhiyuan Li (lzy9667)
public class ColonyCalculator {
    // Fields
    /**
     * number of plaents
     */
    public static final int NUM_PLANETS = 3;
    /**
     * minimum skill level
     */
    public static final int MIN_SKILL_LEVEL = 1;
    /**
     * max skill level
     */
    public static final int MAX_SKILL_LEVEL = 5;
    private ArrayQueue<Person> applicantQueue;
    private AList<Person> rejectBus;
    private static Planet[] planets = new Planet[NUM_PLANETS + 1];


    /**
     * An "IllegalArgumentException" will be thrown if the input ArrayQueue is
     * null.
     * initialize variables
     * 
     * @param queue
     *            ArrayQueue<Person>
     * @param planet
     *            Planet[] planets
     */
    public ColonyCalculator(ArrayQueue<Person> queue, Planet[] planet) {
        if (queue == null) {
            throw new IllegalArgumentException();
        }
        applicantQueue = queue;
        planets = planet;
        rejectBus = new AList<Person>();
    }


    /**
     * getter method
     * 
     * @return applicantQueue
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }


    /**
     * getter method
     * 
     * @return planets
     */
    public static Planet[] getPlanets() {
        return planets;
    }


    /**
     * get the target plaent for this person
     * 
     * @param person
     *            target person
     * @return the target plaent
     */
    public Planet getPlanetForPerson(Person person) {

        if (person == null) {
            return null;
        }
        int a = getPlanetIndex(person.getPlanetName());
       
        if (a != 0) {
            return getPreferredPlanet(person, a);
        }
        else {
            return getMostAvailablePlanet(person);
        }
    }


    /**
     * get prefered panet when the person has preference
     * 
     * @param person
     *            target person
     * @param n
     *            number of planet s
     * @return the target plaent
     */
    private Planet getPreferredPlanet(Person person, int n) {
        /**
         * if (person.getPlanetName() == null) {
         * return null;
         * }
         * else {
         * if (planetByNumber(n) != null && planetByNumber(n)
         * .getAvailability() != 0 && planetByNumber(n).isQualified(
         * person)) {
         * return planetByNumber(n);
         * }
         * }
         * return null;
         */
        Planet tempPlanet = planetByNumber(n);
        if (!tempPlanet.isFull() && tempPlanet.isQualified(person)) {
            return tempPlanet;
        }
        else {
            return null;
        }

    }


    /**
     * get the suitable planet if peroson does not have preference
     * 
     * @param person
     *            target perosn
     * @return the suitable plaent
     */
    private Planet getMostAvailablePlanet(Person person) {
        Planet[] copy = new Planet[getPlanets().length];
        for (int i = 1; i <= planets.length - 1; i++) {
            copy[i] = planets[i];
        }
// copy[0] = new Planet("Plaent0", 5, 5, 5, 0);//
        Arrays.sort(copy, 1, copy.length);
        Planet returnPlanet = null;
        for (int i = copy.length - 1; i > 0; i--) {
            returnPlanet = copy[i];
            /**
             * if (copy[i].getAvailability() > copy[i - 1].getAvailability()) {
             * if (copy[i].isQualified(person) && !copy[i].isFull()) {
             * return returnPlanet;
             * }
             * else {
             * if (copy[i - 1].isQualified(person) && !copy[i - 1].isFull()) {
             * returnPlanet = copy[i - 1];
             * return returnPlanet;
             * }
             * }
             * }
             */
            if (!returnPlanet.isFull() && returnPlanet.isQualified(person)) {

                return returnPlanet;
            }
        }
        return null;

    }


    /**
     * update backend , add person to planet and dequeue
     * 
     * @return true if successed
     */
    public boolean accept() {
        if (!applicantQueue.isEmpty()) {
            Person person = applicantQueue.getFront();
            Planet planet = getPlanetForPerson(person);
            if (planet != null) {
                planet.addPerson(person);
                applicantQueue.dequeue();
                return true;
            }
        }
        return false;
    }


    /**
     * dequeue the queue and send the person to bus
     * 
     */
    public void reject() {
        if (!applicantQueue.isEmpty()) {
            rejectBus.add(applicantQueue.dequeue());
        }
    }


    /**
     * get planet by index
     * 
     * @param planet
     *            index
     * @return the target planet
     */
    public static Planet planetByNumber(int planet) {
        switch (planet) {
            case 1:
            case 2:
            case 3:
                return getPlanets()[planet];
            default:
                return null;
        }

    }


    /**
     * get planet by name
     * 
     * @param planet
     *            plaent name
     * @return target plaent
     */
    public int getPlanetIndex(String planet) {
        Planet planetA = planets[1];
        Planet planetB = planets[2];
        Planet planetC = planets[3];
        int index = 0;
// }
        if (planetA != null && planetA.getName().equals(planet)) {
            index = 1;
        }
        if (planetB != null && planetB.getName().equals(planet)) {
            index = 2;
        }
        if (planetC != null && planetC.getName().equals(planet)) {
            index = 3;
        }
        return index;
    }

}
