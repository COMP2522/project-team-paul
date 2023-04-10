# COMP 2522 Term Project: Team Paul # 

## Team Members/Roles
1. Ravdeep Aulakh: Back end 
2. Shawn Birring: UX/UI Lead
3. Brian Kwon: Test Maker
4. Hyuk Park: Architect Assistant 
5. Maximillian Yong: Architect 

## Project Pitch ##

### One-liner 
Our team, Team Paul, is implementing a 2D platform game where 
the player must travel as high as possible, while avoiding enemies
and obstacles on the way.

### Outline 
#### 1. Visual interface using Processing.org libraries 
PApplet visual interface to create a windows where user can play the game
#### 2. Non-blocking asynchronous processing in intervals (push/fetch data in the background)
Setting up new levels and being able to save between stages and pause mid game
#### 3. Persistent data state that must be read, processed, and written in intervals (e.g. save game state in JSON file)
Refer to 2. 
#### 4. Self-managing custom iterable data structure (e.g. Collection of enemies that are added/deleted based on statistics)
Collection of enemies/platforms that have different properties (platforms are deleted and then randomly generated after every stage)
#### 5. Well-documented, complete and run without errors 
Using the github project planning/issues

### Communication Policies 
We will be using discord as the primary way to communicate with each other 
about the project scope. In person meetings will be held every friday during our 
3 hour gap (12:30pm to 3:30 pm)

### Milestones 
* 01/23/2023 ~> 01/30/2023 : UML Diagrams finished, initial tasks developed and underway 
* 01/30/2023 ~> 03/13/2023 : MVPs of project finished and ready to be used as demo
* 03/13/2023 ~> 04/21/2023 : Troubleshooting and polishing app (finished up back-end)

## Idea 

### Doodle Jump/ Jump King style 2d platform game 
+ Player attempts to go as high as possible, jumping off of platforms while avoiding enemies and obstacles 
+ Player can jump, and also has movement keys to strafe from left to right 
+ Obstacles can vary with different properties (some platforms are more buoyant/some break easily)
+ Terrain varies from stage to stage 
+ Possible powerups to enhance user abilities
+ Tracking variable that keeps track of user's score (how high they go)
+ Savable areas between stages (ability to pause mid game)

### Features
* Background music 
* Levels
  * 5 Levels 
  * Each level with a different colour scheme
* Lives 
  * 3 lives per level 
* Coins 
  * Purchase more lives 
  * Increase score 
* Powerups
  * Jetpack
  * extra life (shield for 10 seconds)
  * Tires: jump on to get a boost
* Score 
  * Coins collection + duration alive 

### JSON 
* Player score 
* Coin count 
* Level 

### Pages 
* Start menu
* Settings menu (maybe)
* Game play 
* Pause menu 
* Game over page 
