import pyrebase
import qrCodes
import keyGeneration

#Flask stuff
from flask import Flask, request
app = Flask(__name__)

#Firebase stuff
config = {
    "apiKey": "AIzaSyBJYrBHpb86nDMUSinz-IyXZcivoy-DSBM",
    "authDomain": "uottahack2020-63b73.firebaseapp.com",
    "databaseURL": "https://uottahack2020-63b73.firebaseio.com",
    "projectId": "uottahack2020-63b73",
    "storageBucket": "uottahack2020-63b73.appspot.com",
    "messagingSenderId": "1064177743414",
    "appId": "1:1064177743414:web:200667a6bac538a0b0ca97"
}
firebase = pyrebase.initialize_app(config)
db = firebase.database()




#Function adds a user to the database with name & mailing address
@app.route('/register', methods=['POST'])
def registerUser():
    req_data = request.get_json()   #store the request data
    
    #extract the data necessary from the request
    fname = req_data['fName']
    lname = req_data['lname']
    street_num = req_data['street_num']
    street_name = req_data['street_name']
    city = req_data['city']
    province = req_data['province']
    username = req_data['username']

    # user initially has no sent/recieved stamps
    stamps_sending = []
    stamps_recieving = []

    #user initially has no friends
    favorites = []

    #create a dictionary of user information
    user = { username :
    {
        'fName': fname,
        'lName': lname,
        'StreetNum': street_num,
        'StreetName': street_name,
        'city': city,
        'province': province,
        'favorites' : favorites,
        'stamps_sending': stamps_sending,
        'stamps_recieving' : stamps_recieving
    }
    }
    #add the user to the database
    db.child('Users').set(user)



#add new recipient

#Send & Pay

#Function removes a user from the database
@app.route('/get-friends', methods=['POST'])
def getFriends(username):
    db.child('Users').child(username).child('favorites').get()


#Function removes a user from the database
@app.route('/remove-user', methods=['POST'])
def removeUser(username):
    db.child('Users').remove(username)

#method allows a user to add or 'link' a stamp to their profile
@app.route('/link-stamp', methods=['POST'])
def linkStamp():
    req_data = request.get_json()  # store the request data

    # extract the data necessary from the request
    stampkey = req_data['stamp_key'] #retrieve the stamp key that the user is trying to link
    sender = req_data['sender'] #retrieve sender username
    reciever = req_data['reciever']   #retrieve recipient username

    #create a stamp json object
    stamp = { stampkey :
    {
        'locked' : False,
        'sender' : sender,
        'reciever' : reciever
    }
    }

    db.child('Users').child(sender).child('stamps_sending').set(stamp) #add stamp key to the sender's profile
    db.child('Users').child(reciever).child('stamps_reciever').set(stamp)  # add stamp key to the reciever's profile

    db.child('Stamps').child(stampkey).child('sender').set(sender)  #set the sender of the stamp
    db.child('Stamps').child(stampkey).child('reciever').set(reciever)  #set the reciever of the stamp

    #Register Payment for Postage!
    #Send them to the map page after this









#method allows a user to add or 'link' a stamp to their profile
@app.route('/login', methods=['POST'])
def login(username):
    result = db.child('Users').child(username).get()

    #HANDLE IF THE USER WASN'T FOUND

    return False

def queryMySendingStamps(username):
    stamps = db.child('Users').child(username).child('stamps_sending').get()
    return stamps

def queryMyRecievingStamps(username):
    stamps = db.child('Users').child(username).child('stamps_recieving').get()
    return stamps

def queryAllStamps():
    stamps = db.child('Stamps').get()
    return stamps

def setKeyUsed(val):
    result = db.put('/Stamps/' + val, 'used', True)
    return result


#to be used only by company
def generateStamp():
    stampkey = keyGeneration.generate_key()     #generate a unique stamp key
    qrCodes.createQRCode(stampkey)              #create a QR code using the stamp key

    #create a stamp json object
    stamp = { stampkey :
    {
        'locked' : False,
        'sender' : "",
        'reciever' : ""
    }
    }
    db.child('Stamps').set(stamp)   #add the stamp to the master stamps list







#method is called when a user scans a QR code
#method only returns useful information about the code
@app.route('/scan', methods=['POST'])
def scanStamp():
    req_data = request.get_json()  # store the request data

    # extract the data necessary from the request
    scanned_key = req_data['scanned_key']
    myUsername = req_data['username']

    #TODO if my username is admin, I do admin stuff


    #get a dictionary of the user's sending stamps
    mySendingStampsDict = queryMySendingStamps(myUsername)
    #loop through user's sending stamps
    for key in mySendingStampsDict:
        if scanned_key == mySendingStampsDict[key]:  #we have a found a stamp key match
            #I am sender checking the code, probably trying to edit, promt user to edit
            return "sender trying to edit!"

    #get a dictionary of the user's recieving stamps
    myRecievingStampsDict = queryMyRecievingStamps(myUsername)
    #loop through user's recieving stamps
    for key in myRecievingStampsDict:
        if scanned_key == mySendingStampsDict[key]:  #we have a found a stamp key match

            #I am reciever, I have obviously recieved the mail! Mark it as recieved (i.e remove it from mine, the senders, and the globals stamp list)
            #TODO send a message to the sender saying that I have recieved the mail


            #grab the username of the sender
            sender = db.child('Users').child(myUsername).child('stamps_recieving').child(key).child('sender').get()

            #remove the stamp from my recieving list
            db.child('Users').child(myUsername).child('stamps_recieving').child(key).remove()

            #TODO add it to recievers "recieved" list


            #remove the stamp from the senders sending list
            db.child('Users').child(sender).child('stamps_sending').child(key).remove()

            #TODO add it to senders "sent" list


            #remove the stamp from the master list of active stamps
            db.child('Stamps').child(key).remove()


            #I have found the scanned stamp in my list and have dealt with it
            return "reciever recieved mail!"


    #I am not a sender OR a reciever, I intercepted the mail, show the interceptor who the sender/reciever is

    #get a dictionary of all the stamps
    allStampsDict = queryAllStamps()
    #loop through the master keys
    for key in allStampsDict:
        if scanned_key == allStampsDict[key]:  # we have a found a key match in the master keys list
            sender = db.child('Stamps').child(key).child('sender')
            reciever = db.child('Stamps').child(key).child('reciever')

            #TODO notify the reciever that I have found their mail
            return {'sender': sender, 'reciever': reciever}


#run our app
if __name__ == '__main__':
    app.run(debug=True, port=5000)