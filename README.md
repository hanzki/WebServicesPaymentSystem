# WebServicesPaymentSystem
Mock-up payment system for the T-75.5300 Web Services course in Aalto University

# REST API

## POST /v1/transaction - Makes a payment

### parameters
x-www-form-urlencoded values:  
sellerId = Id of the service using the payment system  
amount = Amount of the transaction. Integer number of euro cents.  
mac = mac of sellerId and amount
cardNumber = card number of the customer

### returns
JSON values:  
transactionId = Unique Id of this transaction  
sellerId = Same sellerId as in the request  
amount = Same amount as in the request  
mac = mac of transactionId, sellerId and amount

# MAC checksum codes
Checksum codes used are SHA-256 hashes of parameters. Parameters are sorted alphabeticly and concatenated by 
"paramname=paramvalue" seperated by "&" characters. At the end of parameter string "&SECRETKEY=[secretkey]" 
is appended. The whole string is changed to uppercase before hashing. 
Secret key is known only by the payment service and the seller. Example: "AMOUNT=500&SELLERID=1234&SECRETKEY=SALASANA"  
