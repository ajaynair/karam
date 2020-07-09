class JobPOJO:

    def setJobId(self, jobId):
        self.jobId = jobId
        return self

    def getJobId(self):
        return self.jobId

    def setLaborerId(self, laborerId):
        self.laborerId = laborerId
        return self

    def getLaborerId(self):
        return self.laborerId

    def setContractorId(self, contractorId):
        self.contractorId = contractorId
        return self

    def getContractorId(self):
        return self.contractorId

    def setActiveInd(self, activeInd):
        self.activeInd = activeInd
        return self

    def getActiveInd(self):
        return self.activeInd

    def setUpdateDtTm(self, updateDtTm):
        self.updateDtTm = updateDtTm
        return updateDtTm

    def getUpdateDtTm(self):
        return self.updateDtTm
