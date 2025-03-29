# GestorEventosDeportivos
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
Due all the data is located in memory it is necessary to handle the data in java collections, to avoid getting null exception accessing data between services.

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

### 1. Retrieve all the teams

**`GET`** /teams

**Response:**

```json
[
  {
    "id": 825,
    "name": "Deportivo Tapitas",
    "sport": "Football",
    "city": "Medellín",
    "fundationDate": "Jan 1, 1970, 12:34:20?AM",
    "logo": "logoD.png",
    "players": []
  },
  {
    "id": 464,
    "name": "Escombros FC",
    "sport": "Football",
    "city": "La Pintada",
    "fundationDate": "Jan 1, 1970, 12:34:30?AM",
    "logo": "logoE.png",
    "players": []
  }
]
```

### 2. Retrieve teams with pagination and size

**`GET`** /teams?page=1&size=1

**Response:**

```json
[
  {
    "id": 825,
    "name": "Deportivo Tapitas",
    "sport": "Football",
    "city": "Medellín",
    "fundationDate": "Jan 1, 1970, 12:34:20?AM",
    "logo": "logoD.png",
    "players": []
  }
]
```
### 2. Retrieve teams with pagination and size

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

