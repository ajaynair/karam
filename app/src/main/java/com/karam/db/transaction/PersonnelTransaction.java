package com.karam.db.transaction;

import com.karam.db.pojo.PersonnelPOJO;

/**
 * Interface for read and write transactions related to personnel.
 * Fill in the licence information.
 * @author Rishabh Pandita
 */
public interface PersonnelTransaction {

    /** Inserts a row in <code>Personnel<code/> table.
     * @param prsnlpojo pojo for personnel information
     * @return personnel id
     */
    public int registerPersonnel(PersonnelPOJO prsnlpojo);

    /** Updates a row in <code>Personnel<code/> table.
     * @param prsnlpojo pojo for personnel information
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public int UpdatePersonnel(PersonnelPOJO prsnlpojo);

    /** Deletes a row in <code>Personnel<code/> table.
     * @param prsnlId personnel id
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public boolean deletePersonnel(PersonnelPOJO prsnlId);

    /**
     * Returns prsnlId of a personnel based on person id of the person.
     * @param personId the prsnl id
     * @return the person id
     */
    public int getPrsnlId(int personId);

    /** Update active indicator of <code>Personnel</code> table.
     * @param prsnlId personnel id
     * @param activeInd active indicator
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public boolean updatePersonnelActiveInd(int prsnlId, boolean activeInd);

    /** Update skill of <code>Personnel</code> table.
     * @param prsnlId personnel id
     * @param skillDesc skill of personnel
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public boolean updatePersonnelSkill(int prsnlId, String skillDesc);


    /** Update location of <code>Personnel</code> table.
     * @param prsnlId personnel id
     * @param location location of personnel
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public boolean updatePersonnelActiveInd(int prsnlId, String location);
}
