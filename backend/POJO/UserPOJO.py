class UserPOJO:

    def setUserId(self, userId):
        self.userId = userId
        return self

    def getUserId(self):
        return self.userId

    def setRoleType(self, roleType):
        self.roleType = roleType
        return self

    def getRoleType(self):
        return self.roleType

    def setUserName(self, userName):
        self.userName = userName
        return self

    def getUserName(self):
        return self.userName

    def setPasswordHash(self, passwordHash):
        self.passwordHash = passwordHash
        return self

    def getPasswordHash(self):
        return self.passwordHash

    def setUpdateDtTm(self, updateDtTm):
        self.updateDtTm = updateDtTm
        return updateDtTm

    def getUpdateDtTm(self):
        return self.updateDtTm
