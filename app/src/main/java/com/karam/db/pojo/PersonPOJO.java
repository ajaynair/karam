package com.karam.db.pojo;

/**
 * POJO class for storing person attributes. This class will be used while interacting with the database.
 * Fill in the licence information.
 *
 * @author Rishabh Pandita
 */
public class PersonPOJO {
    private long personId;
    private String firstName;
    private String lastName;
    private String gender;
    private long phoneNumber;
    private String address;
    private String adharCard;
    private boolean adharCardActive;
    private String panCard;

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
     * Returns firstName.
     *
     * @return Gets the value of firstName and returns firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns lastName.
     *
     * @return Gets the value of lastName and returns lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns gender.
     *
     * @return Gets the value of gender and returns gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns phoneNumber.
     *
     * @return Gets the value of phoneNumber and returns phoneNumber
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phoneNumber
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns address.
     *
     * @return Gets the value of address and returns address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns adharCard.
     *
     * @return Gets the value of adharCard and returns adharCard
     */
    public String getAdharCard() {
        return adharCard;
    }

    /**
     * Sets the adharCard
     */
    public void setAdharCard(String adharCard) {
        this.adharCard = adharCard;
    }

    /**
     * Returns adharCardActive.
     *
     * @return Gets the value of adharCardActive and returns adharCardActive
     */
    public boolean isAdharCardActive() {
        return adharCardActive;
    }

    /**
     * Sets the adharCardActive
     */
    public void setAdharCardActive(boolean adharCardActive) {
        this.adharCardActive = adharCardActive;
    }

    /**
     * Returns panCard.
     *
     * @return Gets the value of panCard and returns panCard
     */
    public String getPanCard() {
        return panCard;
    }

    /**
     * Sets the panCard
     */
    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }
}
