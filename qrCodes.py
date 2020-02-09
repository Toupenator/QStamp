import keyGeneration
import uuid
import pyqrcode

def createQRCode (x):
    code = pyqrcode.create(x)
    code.png('qrcodes/qrcode{}.png'.format(x))

# x =  str(keyGeneration.generate_key())
# print(x)
# createQRCode(x)