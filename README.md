# HairSalon
An app for a hair salon. The owner should be able to add a list of the stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist.

## PROJECT AUTHOR
MAKU PAULINE MAZAKPE

## Technologies and frameworks used
1. java 11
2. spark core 2.12
3. Gradle 4.10
4. Spark Template Velocity
5. Junit 4

## Usage
1. Clone this repo
2. Compile "gradle compileJava"
3. Run cd to cd build/classes/main `java App`

## User Stories 
1. As a salon owner, I need to be able to see a list of all our stylists.
2. As a salon owner, I need to be able to select a stylist, see their details, and see a list of all clients that belong to that stylist.
3. As a salon owner, I need to add new stylists to our system when they are hired.
4. As a salon owner, I need to be able to add new clients to a specific stylist.
5. As a salon owner, I need to be able to update a stylist's details.
6. As a salon owner, I need to be able to update a client's details.
7. As a salon owner, I need to be able to delete a stylist if they're no longer employed here.
8. As a salon owner, I need to be able to delete a client if they no longer visit our salon.

## In PSQL:
1. Run psql in terminal
2. CREATE DATABASE hair_salon;
3. CREATE TABLE clients (id serial PRIMARY KEY, name VARCHAR);

## SCREENSHOTS

[![Screenshot-from-2019-04-17-09-00-31.png](https://i.postimg.cc/1XH4j7VS/Screenshot-from-2019-04-17-09-00-31.png)](https://postimg.cc/xNX0kRPp)

[![Screenshot-from-2019-04-17-09-00-38.png](https://i.postimg.cc/fTRbbK03/Screenshot-from-2019-04-17-09-00-38.png)](https://postimg.cc/jCBTgycK)

## Testing

gradle test

## License
[MIT](https://choosealicense.com/licenses/mit/)
