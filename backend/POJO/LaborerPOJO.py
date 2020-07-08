class LaborerPOJO:

    def __init__(self):
        self.laborerId = None
        self.parentId = None
        self.fname = None
        self.lname = None
        self.gender = None
        self.phno = None
        self.aadharNo = None
        self.aadharStatus = None
        self.address = None
        self.panCard = None
        self.skill = None
        self.activeInd = None
        self.preferredJobLocation = None

    def setLaborerId(self, laborerId):
        self.laborerId=laborerId
        return self

    def getLaborerId(self):
        return self.laborerId

    def setParentId(self, parentId):
        self.parentId=parentId
        return self

    def getParentId(self):
        return self.parentId

    def setFirstname(self, fname):
        self.fname=fname
        return self

    def getFirstname(self):
        return self.fname

    def setLastname(self, lname):
        self.lname=lname
        return self

    def getLastname(self):
        return self.lname

    def setGender(self, gender):
        self.gender=gender
        return self

    def getGender(self):
        return self.gender

    def setPhoneNumber(self, phno):
        self.phno=phno
        return self

    def getPhoneNumber(self):
        return self.phno

    def setAddress(self, address):
        self.address=address
        return self

    def getAddress(self):
        return self.address

    def setAadharStatus(self, aadharStatus):
        self.aadharStatus=aadharStatus
        return self

    def getAadharStatus(self):
        return self.aadharStatus

    def setAadharNo(self, aadharNo):
        self.aadharNo=aadharNo
        return self

    def getAadharNo(self):
        return self.aadharNo

    def setPanCard(self, panCard):
        self.panCard=panCard
        return self

    def getPanCard(self):
        return self.panCard

    def setSkill(self, skill):
        self.skill=skill
        return self

    def getSkill(self):
        return self.skill

    def setActiveInd(self, activeInd):
        self.activeInd=activeInd
        return self

    def getActiveInd(self):
        return self.activeInd

    def setPrefLoc(self, preferredJobLocation):
        self.preferredJobLocation=preferredJobLocation
        return self

    def getPrefLoc(self):
        return self.preferredJobLocation
