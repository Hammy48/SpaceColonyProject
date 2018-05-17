package spacecolonies;

/**
 * These objects contain a string, for a perso
 * n��s name, a skills object, and a
 * String representation of their planet preference.
 * 
 * @author Zhiyuan Li
 * @version 2018.04.13
 *
 */
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with 
// honor and integrity at all times.
// I will not lie, cheat, or steal, nor will 
// I accept the actions of those who
// do.
// -- Zhiyuan Li (lzy9667)
public class Person {

    private String name;
    private Skills skills;
    private String planetPreference;


    // Constructor
    /**
     * The Person constructor should take 5 
     * parameters, the person's name, their
     * skills levels in all these areas and
     * their planet preference. This data
     * should be listed on each line of 
     * the applicant input file.
     * 
     * @param name
     *            the name of the person
     * @param agriculture
     *            agri score
     * @param medicine
     *            medi score
     * @param technology
     *            tech score
     * @param planetPreference
     *            preference of planet
     */
    public Person(
        String name,
        int agriculture,
        int medicine,
        int technology,
        String planetPreference) {
        this.name = name;
        this.planetPreference = planetPreference;
        skills = new Skills(agriculture, 
            medicine, technology);
    }


    /**
     * 
     * @return person's name
     */
    public String getName() {
        return name;
    }


    /**
     * 
     * @return a skills variable which contains three scores
     */
    public Skills getSkills() {
        return skills;
    }


    /**
     * 
     * @return planet preference
     */
    public String getPlanetName() {
        return planetPreference;
    }


    /**
     * Using a StringBuilder, concatenate the name,
     * the first letter for each skill
     * (in alphabetical order) and a colon,
     * then the skill value. Then, if the
     * person has a planetPreference String
     * that has a length greater than 0, add
     * ��Wants: �� and the planet name.
     * Note the space after the : in "Wants: ".
     * 
     * Sample output: ��Jane Doe A:3 M:2 T:1 Wants:
     * Nars�� or ��No-Planet Jane Doe A:2
     * M:5 T:4��
     * 
     * @return a string representation of personal info
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name + " ");
        builder.append("A:");
        builder.append(skills.getAgriculture() + " ");
        builder.append("M:");
        builder.append(skills.getMedicine() + " ");
        builder.append("T:");
        if (planetPreference.length() == 0) {
            builder.append(skills.getTechonology());
            builder.append("");
            builder.insert(0, "No-Planet ");

        }
        else {
            builder.append(skills.getTechonology() + " ");
            builder.append("Wants: " + this.getPlanetName());
        }
        return builder.toString();
    }


    /**
     * Two Person objects are considered equal when
     * their name, skills, and planet
     * preference value is the same. You will need
     * to write your own equals method.
     * 
     * @param object
     *            the object
     * @return true if equals
     */
    public boolean equals(Object object) {
        // obj is null
        if (object == null) {
            return false;
        }
        // compare with itself
        else if (this == object) {
            return true;
        }
        // same class but diff value or same class same value
        else if (this.getClass() == object.getClass()) {
            Person tempPerson = (Person)object;
            if (this.getName() == tempPerson.getName() 
                && this.getSkills()
                .equals(tempPerson.getSkills()) && 
                this.getPlanetName().equals(
                    tempPerson.getPlanetName())) {
                return true;
            }
        }
        return false;
    }
}
