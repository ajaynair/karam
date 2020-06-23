package com.karam.db.pojo;

/**
 * POJO class for storing job attributes. This class will be used while interacting with the database.
 * Fill in the licence information.
 *
 * @author Rishabh Pandita
 */
public class JobPOJO {
    private long jobId;

    private long labId;

    private long contractorId;

    private boolean activeInd;

    /**
     * Returns jobId.
     *
     * @return Gets the value of jobId and returns jobId
     */
    public long getJobId() {
        return jobId;
    }

    /**
     * Sets the jobId
     */
    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    /**
     * Returns labId.
     *
     * @return Gets the value of labId and returns labId
     */
    public long getLabId() {
        return labId;
    }

    /**
     * Sets the labId
     */
    public void setLabId(long labId) {
        this.labId = labId;
    }

    /**
     * Returns contractorId.
     *
     * @return Gets the value of contractorId and returns contractorId
     */
    public long getContractorId() {
        return contractorId;
    }

    /**
     * Sets the contractorId
     */
    public void setContractorId(long contractorId) {
        this.contractorId = contractorId;
    }

    /**
     * Returns activeInd.
     *
     * @return Gets the value of activeInd and returns activeInd
     */
    public boolean isActiveInd() {
        return activeInd;
    }

    /**
     * Sets the activeInd
     */
    public void setActiveInd(boolean activeInd) {
        this.activeInd = activeInd;
    }
}
