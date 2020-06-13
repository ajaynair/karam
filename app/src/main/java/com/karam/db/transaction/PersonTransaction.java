package com.karam.db.transaction;

import com.karam.db.pojo.PersonPOJO;

import java.util.List;

/**
 * Interface for read and write transactions related to person.
 * Fill in the licence information.
 * @author Rishabh Pandita
 */
public interface PersonTransaction {
    /**
     * Creates a database connection and inserts a new row in <code>Person</code> table.
     * @param personPOJO pojo for person table
     * @return the personId
     */
    public int registerPerson(PersonPOJO personPOJO);

    /**
     * Creates a database connection and deletes row from <code>Person</code> table.
     * @param personId person id to be deleted
     */
    public void deletePerson(int personId);

    /**
     * Creates a database connection and updates a row in <code>Person</code> table.
     * @param personPOJO pojo for person table
     */
    public void updatePerson(PersonPOJO personPOJO);

    /**
     * Queries the database based on person's phone number
     * @param phoneNumber phone number of the person
     * @return personId person id of the person's phone number
     */
    public int getPersonId(long phoneNumber);

    /** Queries database for all the persons.
     * TODO add paging logic in future.
     * @return list of person pojo containing all the person information
     */
    public List<PersonPOJO> getAllPersons();


    /** Queries database for all the labourers.
     * TODO add paging logic in future.
     * @return list of person pojo containing only the labourers information
     */
    public List<PersonPOJO> getLabourers();


    /** Queries database for all the contractors.
     * TODO add paging logic in future.
     * @return list of person pojo containing only the contractor information
     */
    public List<PersonPOJO> getContractor();

}
