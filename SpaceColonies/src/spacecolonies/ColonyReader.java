package spacecolonies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import bsh.ParseException;

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
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Zhiyuan Li (lzy9667)
public class ColonyReader {
    private Planet[] planets;
    private ArrayQueue<Person> queue;


    public ColonyReader(String applicantFileName, String planetFileName)
        throws FileNotFoundException,
        ParseException,
        SpaceColonyDataException {
        queue = new ArrayQueue<Person>();
        planets = new Planet[4];
        queue = this.readQueueFile(applicantFileName);
        planets = this.readPlanetFile(planetFileName);
        new SpaceWindow(new ColonyCalculator(queue, planets));
    }


    /**
     * read the file contains planets info
     * 
     * @param fileName
     *            target file
     * @return a planet list
     */
    private Planet[] readPlanetFile(String fileName)
        throws FileNotFoundException,
        ParseException,
        SpaceColonyDataException {
        @SuppressWarnings("resource")
        Scanner file = new Scanner(new File(fileName));
        int index = 1;
        while (file.hasNextLine()) {
            String nextLine = file.nextLine();
            String[] line = nextLine.split(", *");
            if (line.length != 5) {
                throw new ParseException();
            }

            String planetName = line[0];
            Integer planetAgri = Integer.valueOf(line[1]);
            Integer planetMedi = Integer.valueOf(line[2]);
            Integer planetTech = Integer.valueOf(line[3]);
            if (!isInSkillRange(planetAgri, planetMedi, planetTech)) {
                throw new SpaceColonyDataException(
                    "skills are not between 1 and 5");
            }
            else {
                Integer planetCap = Integer.valueOf(line[4]);
                
                planets[index] = new Planet(planetName, planetAgri, planetMedi,
                    planetTech, planetCap);
                index++;
            }
        }
        file.close();
        if (planets.length < 3) {
            throw new SpaceColonyDataException("there are less than 3 planets");
        }
        return planets;
    }


    /**
     * read the file contains applicants info
     * 
     * @param fileName
     *            the target file
     * @return an array queue which contains all applicants
     */
    private ArrayQueue<Person> readQueueFile(String fileName)
        throws FileNotFoundException,
        ParseException,
        SpaceColonyDataException {
        @SuppressWarnings("resource")
        Scanner file = new Scanner(new File(fileName));
        while (file.hasNextLine()) {
            String nextLine = file.nextLine();
            String[] line = nextLine.split(", *");
            if (line.length < 4) {
                throw new ParseException();
            }
            else {
                String name = line[0];
                Integer agri = Integer.valueOf(line[1]);
                Integer medi = Integer.valueOf(line[2]);
                Integer tech = Integer.valueOf(line[3]);
                String preferencePlanet = null;
                if (line.length == 4) {
                    preferencePlanet = "";
                }
                else {
                    preferencePlanet = line[4];
                }
                if (!isInSkillRange(agri, medi, tech)) {
                    throw new SpaceColonyDataException(
                        "skills are not between 1 and 5");
                }
                queue.enqueue(new Person(name, agri, medi, tech,
                    preferencePlanet));
            }

        }
        file.close();
        return queue;
    }


    /**
     * helper method to determine whether the skill is in range
     * 
     * @param num1
     *            argi
     * @param num2
     *            medi
     * @param num3
     *            tech
     * @return true if all skills are in range
     */
    private boolean isInSkillRange(int num1, int num2, int num3) {
// boolean first = (num1 < 1 || num1 > 5) ? true : false;
// boolean second = (num2 < 1 || num2 > 5) ? true : false;
// boolean third = (num3 < 1 || num3 > 5) ? true : false;
// return (first && second && third) ? true : false;
        int max = ColonyCalculator.MAX_SKILL_LEVEL;
        int min = ColonyCalculator.MIN_SKILL_LEVEL;
        return ((num1 <= max && num1 >= min) || (num2 <= max && num2 >= min)
            || (num3 <= max && num3 >= min));
    }
}
