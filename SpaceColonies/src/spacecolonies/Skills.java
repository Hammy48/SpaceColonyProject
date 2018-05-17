package spacecolonies;

/**
 * These objects contain three ints for their skills(on a scale of 1 to 5).
 * 
 * @author Zhiyuan Li
 * @version 2018.04.13
 *
 */
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with
// honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I 
// accept the actions of those who
// do.
// -- Zhiyuan Li (lzy9667)
public class Skills {

    private int agriculture;
    private int medicine;
    private int technology;


    // Constructor
    /**
     * The Skills constructor should take 3 parameters,
     * one for each skill category,
     * in alphabetical order. The values can be from 1 to 5.
     * 
     * @param agriculture
     *            agri score
     * @param medicine
     *            medi score
     * @param technology
     *            tech score
     */
    public Skills(int agriculture, int medicine, int technology) {
        this.agriculture = agriculture;
        this.medicine = medicine;
        this.technology = technology;
    }


    /**
     * get agriculture score
     * 
     * @return agri score
     */
    public int getAgriculture() {
        return agriculture;
    }


    /**
     * get medicine score
     * 
     * @return medi score
     */
    public int getMedicine() {
        return medicine;
    }


    /**
     * get tech score
     * 
     * @return tech score
     */
    public int getTechonology() {
        return technology;
    }


    /**
     * Compare a given Skill "other" to "this" Skill. Returns
     * 
     * @param other
     *            taken another skill variable
     * @return True only if "this.agriculture" is less or
     *         equal to
     *         "other.agriculture" AND "this.medicine" is
     *         less or equal to
     *         "other.medicine" AND "this.technology" is
     *         less or equal to
     *         "other.technology".
     */
    public boolean isBelow(Skills other) {
        return this.getAgriculture() < other.getAgriculture() || this
            .getMedicine() < other.getMedicine() || this
                .getTechonology() < other.getTechonology();
    }


    /**
     * @return a string representation of scores on three aspects
     */
    public String toString() {
        return new String("A:" + Integer.toString(getAgriculture()) 
            + ", M:" + Integer.toString(getMedicine()) 
                + ", T:" + Integer.toString(getTechonology()));
    }


    /**
     * Two Skills objects are equal
     * 
     * @return true if all three fields,
     *         agriculture, medicine,
     *         and technology, are
     *         equal.
     * @param object
     *            obj
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
            Skills tempSkills = (Skills)object;
            if (this.getAgriculture() == 
                 tempSkills.getAgriculture() && this.getMedicine() 
                     == tempSkills.getMedicine() && this.getTechonology() 
                         == tempSkills.getTechonology()) {
                return true;
            }
        }
        return false;
    }
}
