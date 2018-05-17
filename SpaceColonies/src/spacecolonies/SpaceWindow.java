package spacecolonies;

import java.awt.Color;
// import java.util.Arrays;
import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
// import CS2114.SquareShape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import list.AList;

/**
 * This object is the front end. Here we build our window, its buttons, and
 * render the Planets and the queue of applicants on the window in a meaningful
 * way.
 * 
 * There is also an UI Example Guide at the end of the page that can help you to
 * build your SpaceWindow.
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
public class SpaceWindow {
    private Window window;
    private ColonyCalculator colonyCalculator;
    private Button accept;
    private Button reject;
    private AList<CircleShape> personCircles;
    private static final int PLANET_HEIGHT = 150;
    private static final int PERSON_CIRCLE_RADIUS = 50;
    private static final int QUEUE_FRONT_X_INDEX = 100;
    private static final int QUEUE_FRONT_Y_INDEX = 100;
// private AList<Person> personList;
    private Shape planet1;
    private Shape planet2;
    private Shape planet3;
    private Planet planetA;
    private Planet planetB;
    private Planet planetC;
    private TextShape textA;
    private TextShape textB;
    private TextShape textC;
    private TextShape textA1;
    private TextShape textB1;
    private TextShape textC1;
    private TextShape personInfo;


    /**
     * initialize all variables
     * 
     * @param calculator
     *            take calculaot
     */
    public SpaceWindow(ColonyCalculator calculator) {
        personCircles = new AList<CircleShape>();
        colonyCalculator = calculator;
        window = new Window("Space Colony Placement");
        accept = new Button("Accept");
        reject = new Button("Reject");
        window.addButton(accept, WindowSide.SOUTH);
        window.addButton(reject, WindowSide.SOUTH);
        planet1 = new Shape(150, 180, PLANET_HEIGHT, PLANET_HEIGHT,
            Color.LIGHT_GRAY);
        planet2 = new Shape(350, 180, PLANET_HEIGHT, PLANET_HEIGHT, Color.PINK);
        planet3 = new Shape(550, 180, PLANET_HEIGHT, PLANET_HEIGHT,
            Color.YELLOW);
        window.addShape(planet1);
        window.addShape(planet2);
        window.addShape(planet3);
        textA1 = new TextShape(180, 360, ColonyCalculator.getPlanets()[1]
            .getSkills().toString());
        textB1 = new TextShape(380, 360, ColonyCalculator.getPlanets()[2]
            .getSkills().toString());
        textC1 = new TextShape(580, 360, ColonyCalculator.getPlanets()[3]
            .getSkills().toString());
        addPersonCircle();
        textA = new TextShape(180, 345, "");
        takeResponseOnText(textA, 1);
        textB = new TextShape(380, 345, "");
        takeResponseOnText(textB, 2);
        textC = new TextShape(580, 345, "");
        takeResponseOnText(textC, 3);
        textA.setBackgroundColor(Color.WHITE);
        textB.setBackgroundColor(Color.WHITE);
        textC.setBackgroundColor(Color.WHITE);
        textA1.setBackgroundColor(Color.WHITE);
        textB1.setBackgroundColor(Color.WHITE);
        textC1.setBackgroundColor(Color.WHITE);
        window.addShape(textA1);
        window.addShape(textB1);
        window.addShape(textC1);
        planetA = ColonyCalculator.getPlanets()[1];
        planetB = ColonyCalculator.getPlanets()[2];
        planetC = ColonyCalculator.getPlanets()[3];

        personInfo = new TextShape(50, 50, colonyCalculator.getQueue()
            .getFront().toString());
        personInfo.setBackgroundColor(Color.WHITE);
        window.addShape(personInfo);

        accept.onClick(this, "clickedAccept");
        reject.onClick(this, "clickedReject");
        if (!ColonyCalculator.getPlanets()[1].isQualified(colonyCalculator
            .getQueue().getFront()) && !ColonyCalculator.getPlanets()[2]
                .isQualified(colonyCalculator.getQueue().getFront())
            && !ColonyCalculator.getPlanets()[3].isQualified(colonyCalculator
                .getQueue().getFront())) {
            accept.disable();
        }
    }


    /**
     * if accept, then update all shapes
     * if not, disable accept
     * 
     * @param acceptButton
     *            a button
     */
    public void clickedAccept(Button acceptButton) {

        if (!colonyCalculator.getQueue().isEmpty()) {
            int prePopuA = ColonyCalculator.getPlanets()[1].getPopulationSize();
            int prePopuB = ColonyCalculator.getPlanets()[2].getPopulationSize();
            int prePopuC = ColonyCalculator.getPlanets()[3].getPopulationSize();
            if (colonyCalculator.accept()) {
                int afterPopuA = ColonyCalculator.getPlanets()[1]
                    .getPopulationSize();
                int afterPopuB = ColonyCalculator.getPlanets()[2]
                    .getPopulationSize();
                int afterPopuC = ColonyCalculator.getPlanets()[3]
                    .getPopulationSize();
                int changedPlanet = -1;
                if (afterPopuA - prePopuA != 0) {
                    changedPlanet = 1;
                }
                if (afterPopuB - prePopuB != 0) {
                    changedPlanet = 2;
                }
                if (afterPopuC - prePopuC != 0) {
                    changedPlanet = 3;
                }
                if (changedPlanet != -1) {
                    takeResponse(ColonyCalculator.getPlanets()[changedPlanet]
                        .getName());
                }
                else {
                    takeResponse("rejected");
                }
                if (!colonyCalculator.getQueue().isEmpty()) {
                    if (colonyCalculator.getPlanetForPerson(colonyCalculator
                        .getQueue().getFront()) == null) {
                        accept.disable();
                    }
                }
                else {
                    gameOver();
                }

            }
            else {
                accept.disable();
            }
        }

    }


    /**
     * update all shape after jected
     * enable aceept after all actions
     * show game over when all shape was added
     * 
     * @param rejectButton
     *            a button
     */
    public void clickedReject(Button rejectButton) {
        colonyCalculator.reject();
        updatePersonCircleQueue();
        if (!colonyCalculator.getQueue().isEmpty()) {
            personInfo.setText(colonyCalculator.getQueue().getFront()
                .toString());
        }
        boolean haveDisabled = false;
        if (!colonyCalculator.getQueue().isEmpty()) {
            if (colonyCalculator.getPlanetForPerson(colonyCalculator.getQueue()
                .getFront()) == null) {
                accept.disable();
                haveDisabled = true;
            }
        }
        if (haveDisabled == false) {
            accept.enable();
        }
        if (colonyCalculator.getQueue().getSize() == 0) {
            gameOver();
        }
    }


    /**
     * take repnse when accept was cliked
     * update circles. testShape.
     * 
     * @param name
     *            a name
     */
    private void takeResponse(String name) {
        if (name.equals(planetA.getName())) {
            takeResponseOnSquareShape(planetA);
            takeResponseOnText(textA, 1);
        }
        else if (name.equals(planetB.getName())) {
            takeResponseOnSquareShape(planetB);
            takeResponseOnText(textB, 2);
        }
        else if (name.equals(planetC.getName())) {
            takeResponseOnSquareShape(planetC);
            takeResponseOnText(textC, 3);
        }
        updatePersonCircleQueue();
        if (!colonyCalculator.getQueue().isEmpty()) {
            personInfo.setText(colonyCalculator.getQueue().getFront()
                .toString());
        }
    }


    /**
     * update circle
     * remove all circle and re-add use cirle list
     */
    private void updatePersonCircleQueue() {
        for (int i = 0; i < personCircles.getLength(); i++) {
            window.removeShape(personCircles.getEntry(i));
        }
        personCircles.clear();
        addPersonCircle();
    }


    /**
     * update texts
     * 
     * @param text
     *            target text shape
     * @param planetIndex
     *            update on which planet
     */
    private void takeResponseOnText(TextShape text, int planetIndex) {
// planetIndex--;
        text.setText(ColonyCalculator.getPlanets()[planetIndex].getName() + "  "
            + ColonyCalculator.getPlanets()[planetIndex].getPopulationSize()
            + "/" + ColonyCalculator.getPlanets()[planetIndex].getCapacity());
        window.addShape(text);
    }


    /**
     * visualize addition on planet
     * 
     * @param planet
     *            target planet
     */
    private void takeResponseOnSquareShape(Planet planet) {
        String tempName = planet.getName();
        int x_co = 0;
        int height = PLANET_HEIGHT / planet.getCapacity();
        int y_co = (planet1.getY() + planet1.getHeight()) - (height * (planet
            .getPopulationSize()));
        Shape addSquare = null;
        if (tempName.equals(ColonyCalculator.getPlanets()[1].getName())) {
            x_co = 150;
            addSquare = new Shape(x_co, y_co, PLANET_HEIGHT, height,
                Color.GRAY);
        }
        if (tempName.equals(ColonyCalculator.getPlanets()[2].getName())) {
            x_co = 350;
            addSquare = new Shape(x_co, y_co, PLANET_HEIGHT, height,
                Color.GREEN);
        }
        if (tempName.equals(ColonyCalculator.getPlanets()[3].getName())) {
            x_co = 550;
            addSquare = new Shape(x_co, y_co, PLANET_HEIGHT, height,
                Color.ORANGE);
        }
        window.addShape(addSquare);
        window.moveToFront(addSquare);
        window.repaint();
    }


    /**
     * add all cooresponding circles to circle list
     * add each circle to window
     */
    private void addPersonCircle() {
        if (!colonyCalculator.getQueue().isEmpty()) {
            String planetAName = ColonyCalculator.getPlanets()[1].getName();
            String planetBName = ColonyCalculator.getPlanets()[2].getName();
            String planetCName = ColonyCalculator.getPlanets()[3].getName();

            CircleShape circle = null;

            Object[] personArray = colonyCalculator.getQueue().toArray();

            for (int i = 0; i < personArray.length; i++) {

                Person person = (Person)personArray[i];
                if (person.getPlanetName().equals(planetAName)) {
                    circle = new CircleShape(QUEUE_FRONT_X_INDEX * (i + 1) - 20,
                        QUEUE_FRONT_Y_INDEX, PERSON_CIRCLE_RADIUS,
                        Color.LIGHT_GRAY);
                }
                else if (person.getPlanetName().equals(planetBName)) {
                    circle = new CircleShape(QUEUE_FRONT_X_INDEX * (i + 1) - 20,
                        QUEUE_FRONT_Y_INDEX, PERSON_CIRCLE_RADIUS, Color.PINK);
                }
                else if (person.getPlanetName().equals(planetCName)) {
                    circle = new CircleShape(QUEUE_FRONT_X_INDEX * (i + 1) - 20,
                        QUEUE_FRONT_Y_INDEX, PERSON_CIRCLE_RADIUS,
                        Color.YELLOW);
                }
                else {
                    circle = new CircleShape(QUEUE_FRONT_X_INDEX * (i + 1) - 20,
                        QUEUE_FRONT_Y_INDEX, PERSON_CIRCLE_RADIUS, Color.GREEN);
                }
                personCircles.add(circle);
                window.addShape(circle);
            }
            window.repaint();
        }
    }


    /**
     * show over over page when all person were added
     */
    public void gameOver() {
        window.removeAllShapes();
        int x = window.getWidth() / 2;
        int y = window.getHeight() / 2;
        TextShape endText = new TextShape(x, y,
            "All Applicants Processed - Good Work!");
        window.addShape(endText);
    }
    
    

}
