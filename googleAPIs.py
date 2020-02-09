import urllib.request
import json
endpoint = 'https://maps.googleapis.com/maps/api/directions/json?'
api_key = 'AIzaSyB3II57rEPzwbslrw2_5U9hkvmcFst9wMo'
origin = input('What is your address?: ').replace(' ', '+')
destination = input('Which address do you want the parcel to be delivered?: ').replace(' ', '+')
nav_request = 'origin={}&destination={}&key={}'.format(origin, destination, api_key)
request = endpoint + nav_request
response = urllib.request.urlopen(request).read()
directions = json.loads(response)
print(directions)
