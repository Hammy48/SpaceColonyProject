package spacecolonies;

/**
 * This will be thrown if data is incorrect in the input files.
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
@SuppressWarnings("serial")
public class SpaceColonyDataException extends Exception {

    /**
     * exception 
     * @param string ... 
     */
    public SpaceColonyDataException(String string) {
        super(string);
    }
}
