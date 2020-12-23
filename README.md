# mailing-service
This service helps sending a personalized message to a group of emails stored in a airtable database using google smtp server. It's a rest API made with JAX-RS Jersey implementation, JavaMail Api and Airtable API.

## Airtable
is a cloud collaboration service and easy-to-use online platform for creating and sharing relational databases providing us with an API to help us connect our applications with the service. https://airtable.com/<br>
The Java Airtable API used in this project: https://github.com/broadlume/airtable-java

## Project structure
This project consist of 4 main parts:<br>
a modal that contains the object that will helps us request the service.<br>
a repository to retrieve the data from Airtable.<br>
a rest endpoint (POST).<br>
a mail utility that take care of sending the message.
## Demo
![demo](https://github.com/mozaw39/mailing-service/blob/main/demo-video/output_converted.gif)


