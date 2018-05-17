package spacecolonies;

/**
 * These objects contain a string, for the planet��s name, three ints for their
 * minimum skill requirements (on a scale of 1 to 5), an array of Person objects
 * for current planet population, an int for storing the current population
 * size, and a final int for the maximum allowed capacity of the planet.
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
public class Planet implements Comparable<Planet> {
    // Fields
    private String name;
    private Skills minSkills;
    private Person[] population;
    private int populationSize;
    private int capacity = 10;


    /**
     * The Planet constructor take 5 parameters, the planet's name, the minimum
     * skill level required in all three areas and the maximum capacity for that
     * planet. This data is listed on each line of the planet input file.
     * 
     * @param planetName
     *            the name of the planet
     * @param planetAgri
     *            the min requirement on argi
     * @param planetMedi
     *            the min requirement on medi
     * @param planetTech
     *            the min requirement on tech
     * @param planetCap
     *            the max capacity
     */
    public Planet(
        String planetName,
        int planetAgri,
        int planetMedi,
        int planetTech,
        int planetCap) {
        populationSize = 0;
        population = new Person[planetCap];
        name = planetName;
        minSkills = new Skills(planetAgri, planetMedi, planetTech);
        capacity = planetCap;
    }


    /**
     * setter method, set the name
     * 
     * @param name
     *            the name of the planet
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the name of the planet
     */
    public String getName() {
        return name;
    }


    /**
     * 
     * @return min skills
     */
    public Skills getSkills() {
        return minSkills;
    }


    /**
     * 
     * @return the current population
     */
    public Person[] getPopulation() {
        return population;
    }


    /**
     * 
     * @return the current population size
     */
    public int getPopulationSize() {
        return populationSize;
    }


    /**
     * 
     * @return the max capacity
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * 
     * @return the number of available places left in the planet
     */
    public int getAvailability() {
        return this.getCapacity() - this.getPopulationSize();
    }


    /**
     * 
     * @return true if the planets population has reached max capacity.
     */
    public boolean isFull() {
        return populationSize == capacity;
    }


    /**
     * attempt to add a Person to the Planet
     * 
     * @param newbie
     *            the new residence
     * @return true if add was success
     */
    public boolean addPerson(Person newbie) {
        if (isFull() || !isQualified(newbie)) {
            return false;
        }
        else {
            population[populationSize] = newbie;
        }
        populationSize++;
        return true;
    }


    /**
     * helper method to determine whether the applicant is qualified to live on
     * the
     * colony.
     * 
     * @param applicant
     *            the new applicant
     * @return true if qualified
     */
    public boolean isQualified(Person applicant) {
        return !applicant.getSkills().isBelow(minSkills);
    }


    /**
     * A planet with more available slots will be greater than one with less.
     * 
     * @param other
     *            other object
     * @return 1 if this planet has more available slots -1 if this planet has
     *         less
     *         available slots 0 if this planet has same available slots
     */
    @Override
    public int compareTo(Planet other) {
        if (other == null) {
            throw new IllegalStateException();
        }
// else if (this == other) {
// return 0;
// }
// else {
//
// Planet temp = (Planet)other;
// if (this.getAvailability() < temp.getAvailability()) {
// return -1;
// }
// else if (this.getAvailability() > temp.getAvailability()) {
// return 1;
// }
// else {
// return 0;
// }
// }
        int a = this.getAvailability();
        int b = other.getAvailability();
        return a - b;

    }


    /**
     * Two planets are equal if all 5 their input fields are equal and
     * populationSize is equal.
     * 
     * @param obj
     *            another planet
     * @return true if all 5 their input fields are equal and populationSize is
     *         equal.
     */
    public boolean equals(Object obj) {
// if (obj == null) {
// return false;
// }
// else if (this == obj) {
// return true;
// }
// else {
// if (this.getClass() == obj.getClass()) {
// Planet temp = (Planet)obj;
// if (this.getName().equals(temp.getName()) && this.getSkills()
// .equals(this.getSkills()) && this.getCapacity() == temp
// .getCapacity()) {
// return true;
// }
// }
// }
// return false;
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        else if (obj == this) {
            return true;
        }
        Planet planet = (Planet)obj;
        return this.getName().equals(planet.getName()) && this.getSkills()
            .equals(planet.getSkills()) && this.getPopulationSize() == planet
                .getPopulationSize() && this.getCapacity() == planet
                    .getCapacity();

    }


    /**
     * Using a StringBuilder, concatenate the name, “population” followed by the
     * current population size. Next concatenate the capacity of the planet, and
     * then the required skill values in the following format:
     * 
     * "Caturn, population 5 (cap: 10), Requires: A >= 3, M >= 2, T >= 1"
     * @return string representation 
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getName() + ", ");
        builder.append("population " + this.getPopulationSize());
        builder.append(" (cap: " + this.getCapacity() + "),");
        builder.append(" Requires: A >= " + this.getSkills().getAgriculture());
        builder.append(", M >= " + this.getSkills().getMedicine());
        builder.append(", T >= " + this.getSkills().getTechonology());
        return builder.toString();
    }

}
