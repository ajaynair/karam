package com.karam.db.pojo;


/**
 * POJO class for storing personnel attributes.
 * Fill in the licence information.
 *
 * @author Rishabh Pandita
 */
public class PersonnelPOJO {
    private long prsnlId;
    private long personId;
    private String roleType;
    private String skillDescription;
    private boolean isActive;
    private String locationPref;

    /**
     * Returns prsnlId.
     *
     * @return Gets the value of prsnlId and returns prsnlId
     */
    public long getPrsnlId() {
        return prsnlId;
    }

    /**
     * Sets the prsnlId
     */
    public void setPrsnlId(int prsnlId) {
        this.prsnlId = prsnlId;
    }

    /**
     * Returns personId.
     *
     * @return Gets the value of personId and returns personId
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * Sets the personId
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * Returns roleType.
     *
     * @return Gets the value of roleType and returns roleType
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * Sets the roleType
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * Returns skillDescription.
     *
     * @return Gets the value of skillDescription and returns skillDescription
     */
    public String getSkillDescription() {
        return skillDescription;
    }

    /**
     * Sets the skillDescription
     */
    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    /**
     * Returns isActive.
     *
     * @return Gets the value of isActive and returns isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the isActive
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Returns locationPref.
     *
     * @return Gets the value of locationPref and returns locationPref
     */
    public String getLocationPref() {
        return locationPref;
    }

    /**
     * Sets the locationPref
     */
    public void setLocationPref(String locationPref) {
        this.locationPref = locationPref;
    }
}
