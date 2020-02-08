#Sign-In & Sing-Out implementations

from firebase import firebase
firebase = firebase.FirebaseApplication('https://uottahack2020-63b73.firebaseio.com/', None)

def signUp(fName, lName, contactNum, email, password,streetNum, streetName, postalCode, city, province, country):
    fName = fName
    lName = lName
    contactNum = contactNum
    email = email
    password = password
    streetNum = streetNum
    streetName = streetName
    postalCode = postalCode
    city = city
    province = province
    country = country
    signup_data = {'fName': fName, 'lName': lName, 'contactNum': contactNum, 'email': email, 'password':password,
                   'streetNum': streetNum, 'streetName': streetName, 'postalCode': postalCode, 'city': city, 'province': province,
                   'country': country,
                   }
    firebase.post('/Users', signup_data)

def signIn(email,password):
    valid = False
    for n in firebase.get('/Users',email):
        if n == email:
            valid = True
    for x in firebase.get('/Users',password):
        if x == email:
            valid = True
    if valid:
        #SEND TO NEXT ACTIVITY --> FRONT-END IMPLEMENTATION!!!

