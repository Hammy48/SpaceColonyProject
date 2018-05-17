package spacecolonies;

import java.io.FileNotFoundException;
import bsh.ParseException;

/**
 * The ProjectRunner class begins the program by creating a QueueReader and
 * telling it which file to look at.
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
public class ProjectRunner {
    public ProjectRunner() {
        // ..
    }


    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        SpaceColonyDataException {
        if (args.length < 2) {
            new ColonyReader("input.txt", "planets.txt");
        }
        else {
            new ColonyReader(args[0], args[1]);
        }
    }
}
