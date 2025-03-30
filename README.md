# Sports Event Manager
This is the CES3 project repository.

# Summary
In this project you can manage sporting events and manage teams with their respective players. In addition to this, you can obtain basic statistics of the teams and events that are created in the system.

# Project Structure

The idea of the project folder management is make it modular and easy to learn.
To achieve that the structure is the following:

```plaintext
├───main
│   ├───java
│   │   └───com
│   │       └───ces3
│   │           └───project
│   │               └───ces3project
│   │                   │   HelloServlet.java // created by default
│   │                   │
│   │                   ├───config
│   │                   │       ServiceConfig.java // due all the data is located in memory, this class manages the data
│   │                   │
│   │                   ├───controller // where all the servles are located to handle http requests
│   │                   │       EventServlet.java
│   │                   │       PlayerServlet.java
│   │                   │       StatisticsServlet.java
│   │                   │       TeamServlet.java
│   │                   │
│   │                   ├───dao // all the data access object config
│   │                   │       Dao.java
│   │                   │       EventDAO.java
│   │                   │       PlayerDAO.java
│   │                   │       TeamDAO.java
│   │                   │
│   │                   ├───dto // this is made to retrive most compact information
│   │                   │       PaginationDTO.java
│   │                   │       PlayerDTO.java
│   │                   │       StatisticsDTO.java
│   │                   │       TeamDTO.java
│   │                   │
│   │                   ├───model // where the models are setup
│   │                   │       Event.java
│   │                   │       EventStatus.java
│   │                   │       Player.java
│   │                   │       Team.java
│   │                   │
│   │                   ├───service // these are used to reach the data and hande it over
│   │                   │       EventService.java
│   │                   │       PlayerService.java
│   │                   │       StatisticsService.java
│   │                   │       TeamService.java
│   │                   │
│   │                   └───utils
│   │                           UtilMethods.java // here are located some util methods to create dynamic ids for the models
│   │
│   ├───resources
│   └───webapp
│       │   index.jsp
│       │
│       └───WEB-INF
│               web.xml
│
└───test
    ├───java
    └───resources
```

---

# Folder and content explanation

### **`config`**
Due to all the data is located in memory it is necessary to handle the data in java collections, to avoid getting null exception accessing data between services.

### **`controller`**
In this folder are located the Servlets classes which handles the https request made to the different endpoint configured for the API.

### **`dao`**
Folder that contains the DAO interface and the classes Event, Player and Team that implement its methods.

### **`dto`**
The classes inside this folder are used to compact the information necessary in the responses to avoid retrieving irrelevant or sensible information to the client.

### **`model`**
Here are the entities for Event, Player and Team which handles the structure required for the wanted data.

### **`service`**
This contains the classes that interact wit the DAO implementations and handles the business logic.

### **`utils`**
This saves some util methods to generate random ids for the models and a way to iterate over the request body.

### **`remaining folders`**
Contains some folders for frontend purposes and additional configuration

The project was created using intellij selecting Java EE template -> web application -> with maven using java 22 JDK.

---

## **`API core`**

#### **`events`**
for managing events, including defining event data models, their persistence (via DAOs), and the data transfer objects (DTOs) used for communication. It also handles any business logic related to events.

#### **`players`**
Similar to events, this module handles player data. It includes DAOs for interacting with the database, DTOs for data exchange, and models representing players.

#### **`teams`**
The **`teams`** Manages the teams involved in the sports events. It defines team-related models, data access objects, and DTOs.

---
# API Documentation

## Base URL

`{{base_url}}`

---
### Team Endpoints

### 1. Retrieve all teams

**`GET`** /teams

**Response:**

```json
[
  {
    "id": 492,
    "name": "Deportivo Tapitas",
    "sport": "Football",
    "city": "Medellín",
    "fundationDate": "Jan 1, 1970, 12:34:20?AM",
    "logo": "logoD.png",
    "players": []
  },
  {
    "id": 177,
    "name": "Escombros FC",
    "sport": "Football",
    "city": "La Pintada",
    "fundationDate": "Jan 1, 1970, 12:34:30?AM",
    "logo": "logoE.png",
    "players": []
  }
]
```

### 2. Retrieve one team by id

**`GET`** /teams?id=492

**Response:**

```json
{
    "id": 492,
    "name": "Deportivo Tapitas",
    "sport": "Football",
    "city": "Medellín",
    "fundationDate": "Jan 1, 1970, 12:34:20?AM",
    "logo": "logoD.png",
    "players": []
}
```

### 3. Retrieve team with players

**`GET`** /teams?id=492

**Response:**

```json
{
  "id": 492,
  "name": "Deportivo Tapitas",
  "sport": "Football",
  "city": "Medellín",
  "fundationDate": "Jan 1, 1970, 12:34:20?AM",
  "logo": "logoD.png",
  "teamPlayers": [
    1
  ],
  "players": [
    {
      "name": "Rodrigo",
      "lastName": "Rodriguez",
      "position": "Goalkeeper",
      "number": 1,
      "isActive": true
    }
  ]
}
```

### 4. Retrieve teams with pagination and size

**`GET`** /teams?page=1&size=1

**Response:**

```json
[
  {
    "id": 492,
    "name": "Deportivo Tapitas",
    "sport": "Football",
    "city": "Medellín",
    "fundationDate": "Jan 1, 1970, 12:34:20?AM",
    "logo": "logoD.png",
    "players": []
  }
]
```
### 5. Create team with players

**`POST`** /teams

**Body:**

```json
{
    "name": "Los feos",
    "sport": "Football",
    "city": "Medellin",
    "fundationDate": 20060101,
    "logo": "logoE.png",
    "teamPlayers": [1,2]
}
```

**Response:**

```json
{
    "message": "Team created successfully"
}
```

### 6. Create team without players

**`POST`** /teams

**Body:**

```json
{
    "name": "Los feos",
    "sport": "Football",
    "city": "Medellin",
    "fundationDate": 20060101,
    "logo": "logoE.png",
    "teamPlayers": []
}
```

**Response:**

```json
{
    "message": "Team created successfully"
}
```

---

### Player Endpoints

### 1. Retrieve all players

**`GET`** /players

**Response:**

```json
[
    {
        "id": 1,
        "name": "Rodrigo",
        "lastName": "Rodriguez",
        "birthDate": "Jan 1, 1970, 12:33:21?AM",
        "nationality": "Colombian",
        "position": "Goalkeeper",
        "number": 1,
        "teamId": 492,
        "team": {
            "name": "Deportivo Tapitas"
        },
        "isActive": true
    },
    {
        "id": 2,
        "name": "Ramiro",
        "lastName": "Ramirez",
        "birthDate": "Jan 1, 1970, 12:33:21?AM",
        "nationality": "Colombian",
        "position": "Goalkeeper",
        "number": 13,
        "teamId": 177,
        "team": {
            "name": "Escombros FC"
        },
        "isActive": true
    }
]
```

### 2. Retrieve one player by id

**`GET`** /players?id=1

**Response:**

```json
{
  "id": 1,
  "name": "Rodrigo",
  "lastName": "Rodriguez",
  "birthDate": "Jan 1, 1970, 12:33:21?AM",
  "nationality": "Colombian",
  "position": "Goalkeeper",
  "number": 1,
  "teamId": 492,
  "team": {
    "name": "Deportivo Tapitas"
  },
  "isActive": true
}
```

### 3. Transfer player to another team by player id and team by

**`GET`** /players?action=transfer&playerId=1&newTeam=177

**Response:**

```json
{
    "message": "Player transferred successfully."
}
```

### 4. Create player

**`POST`** /players

**Body:**

```json
{
    "id": 1,
    "name": "Ramiro",
    "lastName": "Ramirez",
    "birthDate": 20001210,
    "nationality": "Colombian",
    "position": "Goalkeeper",
    "number": 1,
    "teamId": 492,
    "isActive": true
}
```

**Response:**

```json
{
    "message": "Player created successfully"
}
```

---

### Events Endpoints

### 1. Retrieve all events

**`GET`** /events

**Response:**

```json
[
  {
    "id": 1010,
    "name": "Champions League Final",
    "date": "Jan 1, 1970, 12:37:30?AM",
    "place": "Wembley Stadium",
    "sport": "Football",
    "teamsId": [
      492,
      177
    ],
    "capacity": 90000,
    "soldTickets": 85000,
    "status": "SCHEDULED"
  },
  {
    "id": 222,
    "name": "Europa League Final",
    "date": "Jan 1, 1970, 12:37:30?AM",
    "place": "Wembley Stadium",
    "sport": "Football",
    "teamsId": [
      492,
      177
    ],
    "capacity": 90000,
    "soldTickets": 85000,
    "status": "SCHEDULED"
  }
]
```

### 2. Retrieve event by id

**`GET`** /events?id=1010

**Response:**

```json
{
    "value": {
        "id": 1010,
        "name": "Champions League Final",
        "date": "Jan 1, 1970, 12:37:30?AM",
        "place": "Wembley Stadium",
        "sport": "Football",
        "teamsId": [
            492,
            177
        ],
        "capacity": 90000,
        "soldTickets": 85000,
        "status": "SCHEDULED"
    }
}
```

### 3. Create event

**`POST`** /events

**Body:**

```json
{
  "name": "Champions League Final",
  "date": 20250610,
  "place": "Wembley Stadium",
  "sport": "Football",
  "teamsId": [492, 177],
  "capacity": 90000,
  "soldTickets": 85000,
  "status": "SCHEDULED"
}
```

**Response:**

```json
{
    "message": "Event created successfully"
}

```

---

### 1. Retrieve all statistics

**`GET`** /statistics

**Response:**

```json
{
    "eventsPerSport": {
        "Football": 2
    },
    "teamsWithMostEvents": {
        "Deportivo Tapitas": 2,
        "Escombros FC": 2
    },
    "avgPlayersPerTeam": {
        "Deportivo Tapitas": 1.0,
        "Escombros FC": 1.0
    },
    "occupancyPerEvent": {
        "Champions League Final": 94.44,
        "Europa League Final": 94.44
    }
}
```

---

The project was tested using Tomcat 9.0.100 as server