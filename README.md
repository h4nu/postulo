# Postulo
A very basic requirements repository poc for the Server Programming SWD4TF021-3007 course  with following features

- Bacic requirements management features
  - Create, Read, Update and Delete
- Register and activate a new account by email
- Change and reset password
- Two Factor authentication
- GeoIP if enabled
- An API for the requirements
- Downloads the requirements as CSV file

Disable the GeoIP feature if testing locally as it doesn't work with localhost addresses. 
Toggle the feature off by setting the geo.ip.lib.enabled parameter as false in your application.properties file:  
geo.ip.lib.enabled=false
