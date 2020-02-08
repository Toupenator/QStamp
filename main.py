from firebase import firebase
import math
import random
firebase = firebase.FirebaseApplication('https://uottahack2020-63b73.firebaseio.com/', None)



#REACT NATIVE WITH FLASK FOR RESTFUL API REQUESTS
#ANDROID SDK, CLITOOL

#we solve the problem of people reusing stamps


#generate some garbo



#Function adds a user to the database with name & mailing address
def addUser(username, fname, lname, street_num, street_name, city, province):

    # user initially has no stamps associated with them, generate an empty array of stamps
    stamps = []

    #create a dictionary of user information
    user = {
        'fName': fname,
        'lName': lname,
        'StreetNum': street_num,
        'StreetName': street_name,
        'city': city,
        'province': province,
        'stamps': stamps
    }
    result = firebase.put('Users', username, user)
    return result




#Function removes a user from the database
def removeUser(username):
    result = firebase.delete('/Users/fname/' + username, None)

def addStamp(username, stampkey):
    result = firebase.post('/Users/' + username + '/stamps/', stampkey)  #add stamp to the user
    stamp = {
        'key': stampkey,
        'locked' : False,
        'used' : False
    }

def generateStamp(stampkey):
    stamp = {
        'key': stampkey,
        'locked' : False,
        'used' : False
    }
    result = firebase.post('/Stamps/', stamp)  # add stamp to global stamp list

def login(username, password):
    result = firebase.get('/Users/' + uID, None)
    print(result)
    return result

def queryMyKeys(username):
    result = firebase.get('/Users/' + username + '/stamps/', None)
    return result

def queryAllKeys():
    result = firebase.get('/Stamps/', None)
    return result

def setKeyUsed(val):
    result = firebase.put('/Stamps/' + val, 'used', True)
    return result



"""
addUser("bob", "test", 123, "new", "city", "nova scotia")
"""



"""
stamp = random.randint(0, 100000000000)
generateStamp(stamp)
addStamp(userID, stamp)

stamp = random.randint(0, 100000000000)
generateStamp(stamp)
addStamp(userID, stamp)
"""



username = "john123"
uID = addUser(username,"sean", "hugg", 224, "old", "town", "BC")


#add some stamp
newstampkey = random.randint(0, 100000000000)
generateStamp(newstampkey)
addStamp(username, newstampkey)
newstampkey = random.randint(0, 100000000000)
generateStamp(newstampkey)
addStamp(username, newstampkey)
newstampkey = random.randint(0, 100000000000)
generateStamp(newstampkey)


#SCAN the QR code and save the key
#Dummy key
scanned_key = newstampkey

print(queryMyKeys(username))

myKeysDict = queryMyKeys(username)

keyFound = False;
for key in myKeysDict:
    print(myKeysDict[key])
    if scanned_key == myKeysDict[key]:  #we have a found a key match
        keyFound = True
        print()

if not keyFound: #user is either the sender trying to link, or an interceptor. We have to search the global list to figure out which one they are
    allKeysDict = queryAllKeys()
    #loop through the master keys
    for val in allKeysDict:
        keyData = allKeysDict[val]
        stampKey = keyData['key']
        if scanned_key == stampKey:  # we have a found a key match in the master keys list
            isUsed = keyData['used']
            #if the key is used, the scanning user is an Interceptor
            if isUsed:
                print("I am an INTERCEPTOR")
            else:   #if the key hasn't been used, the user is a sender and is trying to link a key
                print("I am linking a key {} to my account".format(scanned_key))
                addStamp(username, stampKey)    # add the scanned key to the user's list of stamps
                setKeyUsed(val)


#allow the user to login with a username and password

#username = "shuggins"
#password = "mypass763"

#log the user in
#login(username, password)

