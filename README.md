# Santorini board game 
*Prova Finale per il corso di Ingegneria del Software I, Politecnico di Milano, Ingegneria Informatica, a.a. 2019-2020*

### Overview
This is the final course of Software Engineering I for Information Engineering at Politecnico di Milano, academic year 2019-20, in which students have to develop a software adaptation for a board game.

Each year students are required to design the software version of a different board game as a thesis of a bachelor's degree. In 2019-2020 the assignment was Santorini.

### Objectives of the course project
The goal of this project is to develop the fundamental skills of teamwork, software design and problem solving that are required to work under tight deadlines and standarts. This was achieved during the semester by having a couple of professors commision a task on a biweekly and weekly basis and review the results the following week

### Description and Rules
(from [boardgamegeek.com](https://boardgamegeek.com/boardgame/194655/santorini))
The board game is designed to be played by 2 to 3 players. 

Santorini is an accessible strategy game, simple enough for an elementary school classroom while aiming to provide gameplay depth and content for hardcore gamers to explore, The rules are simple. Each turn consists of 2 steps:

1. Move - move one of your builders into a neighboring space. You may move your Builder Pawn on the same level, step-up one level, or step down any number of levels.

2. Build - Then construct a building level adjacent to the builder you moved. When building on top of the third level, place a dome instead, removing that space from play.

Winning the game - If either of your builders reaches the third level, you win.

Variable player powers - Santorini features variable player powers layered over an otherwise abstract game, with 40 thematic god and hero powers that fundamentally change the way the game is played.

### Development
Requirements were categorized based on the target score:

**for 18 out of 30 Lode:**

Simplified Rules + CLI + Socket

**for 30 Lode:**

Complete Rules + CLI + GUI + Socket + 2 FA (FA = Advanced Feature)

• *Simplified Rules:* it must be possible to complete a game with exactly two players;
cards 1 to 5 of the game manual must be supported.

• *Complete Rules:* it must be possible to complete a game with two or three players; all "simple gods" cards in the game manual must be supported, except for Hermes.

-------------------------
The requirements that have met:
* ***Complete Rules***
* ***CLI***
* ***GUI***
* ***Socket***
* ***2 Advanced Features:***
    - **Multiple match**
    - **Advanced Gods:**
        1. *Aphrodite*
        2. *Ares*
        3. *Limus*
        4. *Triton*
        5. *Zeus*

-------------------------
To develop the project MVC (Model-View-Controller) pattern is applied.

### How to run it:

Game is loaded to the server Azure and released in single .jar file. While .jar file is clicked GUI is launched which connects the server Azure.
You can launch the Cli mode with this command:
```cmd
java -jar ./GC51.jar cli
```

The server can be set up that tries to operate in Server localhost  CLI or GUI  mode to make both work.
You can also specify an IP address different from the one of default: write IP as a second argument in command line, after you write cli or gui as first argument.
Without specifying an IP-address, it works with the IP of Azure.

-------------------------
### How to play the game: 
GUI tutorial:

>1. Enter your name
>2. Choose the number of players
>3. Choose the God/Gods
>4. click the `start the Game` button

![Name and number of players](src/main/resources/123.png "Name and number of player")

Once the one types his/her name and chooses the number of players, if there are enough players for a chosen game, depending who is the first accessed to the server gets to the God selection  stage.

![God Selection](src/main/resources/godsel.jpg "God selection for the first player and the second")

 For the first player appears list of all Gods (14 Gods) and depending on the number of players in the about to start match chooses appropriate number of Gods and for the other players displaying God list contains only the ones that are selected by the first player.   
  
In the following image the board match with 2 players is represented:
 
![board](src/main/resources/game.png "Board")
Red arrows point 
1. to numbers from 0 to 3 and blue circle without the number with the meanings:
    - **O - board**
    - **1 - Level 1 TypeBlock**
    - **2 - Level 2 TypeBlock**
    - **3 - Level 3 TypeBlock**
    - **blue circle - DOME TypeBlock**
2. to the pink,blue and gray circles linked to the Gods on the right side:
    - each color represents the God and God card has the same color
3. to the God power's explanation:
    - while mouse is on the God Card the power description appears
4. to the Dialog window:
    - appears when a player wins  

***************************************
#### Some other tips to play:

• *Pay attention to the message area, the one on the downside of the window*

• *After each step button with triangle should be clicked* 

• *Before using the power make sure that it's active*

### Dependencies
The project has been developed under Java 13 and JavaFX 11 using JetBrain's IntelliJ as the main Integrated Developement Environment. Any Java version > 13 should be able to run the game. A JavaDoc Library for almost every class is included.

### Developers
Marco Pasqualini: https://github.com/marcopasqua

Leone Pistori: https://github.com/LeonePistori

Zhanat Shengelbayeva: https://github.com/ZhanatShengelbay
