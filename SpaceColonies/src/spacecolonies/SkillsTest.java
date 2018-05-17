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
public class SkillsTest extends TestCase {
    private Skills skills;
    private Skills test;


    /**
     * set up
     */
    public void setUp() {
        skills = new Skills(3, 3, 3);
        test = new Skills(4, 4, 4);
    }


    /**
     * test get argi
     */
    public void testGetAgriculture() {
        assertEquals(3, skills.getAgriculture());
    }


    /**
     * test get medi
     */
    public void testGetMedicine() {
        assertEquals(3, skills.getMedicine());
    }


    /**
     * test get tech
     */
    public void testGetTechonology() {
        assertEquals(3, skills.getTechonology());
    }


    /**
     * test is below
     */
    public void testIsBelow() {
        assertTrue(skills.isBelow(test));
        assertFalse(test.isBelow(skills));
        test = new Skills(4, 3, 3);
        assertTrue(skills.isBelow(test));
        test = new Skills(3, 4, 3);
        assertTrue(skills.isBelow(test));
        test = new Skills(3, 3, 4);
        assertTrue(skills.isBelow(test));

    }


    /**
     * test to steing
     */
    public void testToString() {
        assertEquals("A:3, M:3, T:3", skills.toString());
    }


    /**
     * test equals
     */
    public void testEquals() {
        test = null;
        assertFalse(skills.equals(test));
        assertTrue(skills.equals(skills));
        test = new Skills(3, 3, 3);
        assertTrue(skills.equals(test));

        test = new Skills(4, 3, 3);
        assertFalse(skills.equals(test));
        test = new Skills(3, 4, 3);
        assertFalse(skills.equals(test));
        test = new Skills(3, 3, 4);
        assertFalse(skills.equals(test));

        test = new Skills(4, 4, 3);
        assertFalse(skills.equals(test));
        test = new Skills(3, 4, 4);
        assertFalse(skills.equals(test));
        test = new Skills(4, 3, 4);
        assertFalse(skills.equals(test));
        test = new Skills(4, 4, 4);
        assertFalse(skills.equals(test));

        String str = "";
        assertFalse(skills.equals(str));

    }
}
