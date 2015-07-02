
# StarBattle

StarBattle is our Software Engeneering Project

## Overview

This Online Game provides the Infrastructure to create and login with an account
to the master server. As a player you can search for matches by your MatchFinder-Settings.
With every game you earn gold, to spend on new items in the shop.
The Game itself is a 2D Platform Shooter, where you fly around as an astronaut and
have to fight the opponent players. Extra gamemodes and features, different maps etc 
are planned!

So stay tuned ;)


## Current Project Folders

### StarBattle_AccountManager

This project defines the player accounts. With an interface the master server
manages the accounts and checks registration data. The accountmanager provides
a database connection to store the accounts into a database.

### StarBattle_Client

This is the client project for registration, login and game lobby. It creates a connection 
to the master server with the StarBattle_Network Library. Here the player starts
the application, manages his account, goes shopping and starts a game. 

### StarBattle_InGameClient

The InGameClient is based on the Slick2D Java Game Library. After a match is found, the 
client opens the ingame-view and manages input. Also the connection to a game server is
created and updates display-data.

### StarBattle_MapEditor

Deprecated: We switched to the TileD Map Editor!

### StarBattle_Network

This is the library for client and server connections. All network objects are defined in this 
project. The client and server implementations use this library for communication. 

### StarBattle_Server

The StarBattle_Server project contains the master server implementation. Clients can connect
to this service and search for games. Registration and account validation functions are provided
using the StarBattle_AccountManager. The master server reacts to client notifications, connections 
and disconnects. The Matchfinder to group players into new matches is working as a parallel process.

### StarBattle_ServerGame

This project contains all the game logic and a server implementation for ingame use only. The master server
creates a game server for every existing game. The gameserver collects all data send from all players 
currently playing in this game. With a game loop and send-scheduler it sends updates to its clients based
on their viewport.

### StarBattle_Client_TestInterface

Our own testinginterface for the client. With this tool you can write tests for gui and network very easy.

### StarBattle_Game_TestInterface

Written to test the IngameClient and GameServer, without using the client and server application. It simulates
a simple network connection between both instances and therefore you can test much faster.

### StarBattle_MapLoader

The Map-Loading-Interface every component uses, that wants to load the TileD-Maps for the game.
(Server needs the map to retrieve game logic, client for render purpose)

## Used Libraries

- Slick2D (InGame Client Engine)
- KryoNet (Serialization Network)
- H2O DB





