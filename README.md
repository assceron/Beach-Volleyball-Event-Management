# Beach VolleyBall  Management

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Installation](#installation)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contact](#contact)

<!-- ABOUT THE PROJECT -->
## About The Project
The application manages Beach Volleyball social events from December 2020 to January 2021.

It has 3 main tasks. Given a specific date:
  1. Register new teams for a beach volleyball game.
  2. Register existent teams that have already partecipated on a previous date.
  3. Show the details of the event. The event details are available one week before the date of the event. 

### Built With

* [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [SQLite](https://www.sqlite.org/index.html)

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Installation

1. Clone the repo
```sh
https://github.com/assceron/Beach-Volleyball-Event-Management
```

3. Run the application locally
```sh
java -jar RunnableApp.jar
```
The application will show a GUI with the 3 different options mentioned above. 

![main-image]

<!-- USAGE EXAMPLES -->
## Usage
* Register new team

After entering the date of the game and conferming the team name is available (not been used by other teams), the form requires the information of the two players. 
 ![teamreg-image]
 
* Register existent team 

*NOT IMPLEMENTED*

It allows the registration of a team already present in the database. 

* Event Details

It shows the details of the event on a specified date. 
The details include the team partecipating in that event. Every event has 2 rounds of games where teams play against each other. The rounds are generated randomly.

 ![event-image]
 
<!-- ROADMAP -->
## Roadmap
- `main.MainPage` - Entry Point of the application
- `registration.TeamRegistrationFrame` - Defines frame for Registration Form, including checks on user input (name, phone, email, etc.)
- `registration.TeamRegistration` - Registers team and players in the database
- `management.EventManagement` - Creates and registers new event in database, or retrieve event details if the event has already been created  
- `databases` - Package including database management for teams, players and events 
- `main.TeamRegistrationBulk` - Inserts 10 teams in the database for testing 

<!-- CONTACT -->
## Contact

Assunta Cerone - assceron@gmail.com

Project Link: [https://github.com/assceron/Beach-Volleyball-Event-Managemente](https://github.com/assceron/Beach-Volleyball-Event-Management)

<!-- IMAGES -->
[main-image]: images/main.png
[teamreg-image]: images/teamreg.png
[event-image]: images/event.png
