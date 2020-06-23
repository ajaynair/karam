package com.karam.db.transaction;

import com.karam.db.pojo.JobPOJO;

import java.util.List;

/**
 * Interface for read and write transactions related to job.
 * Fill in the licence information.
 *
 * @author Rishabh Pandita
 */
public interface JobTransaction {

    /**
     * Inserts a row in <code>Job</code> table.
     *
     * @param jobPOJO job pojo
     * @return job id
     */
    public int createJob(JobPOJO jobPOJO);

    /**
     * Updates a row in <code>Job</code> table.
     *
     * @param jobPOJO the job pojo
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public boolean updateJob(JobPOJO jobPOJO);

    /**
     * Delete a row in <code>Job</code> table.
     *
     * @param jobId the job id to be deleted
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public boolean deleteJob(long jobId);


    /**
     * Updates active indicator of a row in <code>Job</code> table.
     *
     * @param jobId     job id
     * @param activeInd active indicator
     * @return <code>true<code/> is transaction was successful otherwise <code>false<code/>
     */
    public boolean updateJobActiveInd(long jobId, boolean activeInd);

    /**
     * Queries database to return active jobs information.
     *
     * @return list of active jobs
     */
    public List<JobPOJO> getAllActiveJobs();

    /**
     * Queries database to return active jobs information related to person id passed.
     *
     * @param personId person id
     * @return list of active jobs
     */
    public List<JobPOJO> getAllActiveJobsByPersonId(long personId);
}
