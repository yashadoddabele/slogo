# DESIGN Document for PROJECT_NAME
### Team: 06
### Names: Yasha Doddabele, 


## Team Roles and Responsibilities

* Team Member #1: Yasha
  * In charge of Model classes 
    * API and Controller
    * Error handling
    * Helping with parsing/tokenization
    * Debugging
    * Helping with creation of Commands
    * Creating many testing classes

* Team Member #2

* Team Member #3

* Team Member #4


## Design Goals

* Goal #1
  * Encapsulate and abstract complex functionalities within the View and the Model. This is
  done by making sure the Model and the View are separate of each other and only communicate through
  the Controller and Observer paradigms.
  * 
* Goal #2

* Goal #3


#### How were Specific Features Made Easy to Add / Features Designed to be Easy to Add

* Feature #1
  * New commands are made easy to add. You simply create a line with its canonical and coding name in
  each resource file for all supported languages in the program, and then you create a Command subclass
  for that specific command. The parser and tokenizer will work as intended to process this command.
  This also supports the creation of commands over multiple languages.

* Feature #2

* Feature #3

* Feature #4


## High-level Design

#### Core Classes and Abstractions, their Responsibilities and Collaborators

* Class #1: Controller
  * The Controller facilitates information from the View to the Model through the external model API
  class. Its responsibility is to abstract the process of facilitating information to the backend without
  revealing any sensitive information. It is also intended to abstract the functionality of the program.
  For example, pushCommand() simply sends the user's input to the backend api where it is automatically 
  parsed and processed.

* Class #2

* Class #3

* Class #4



## Assumptions or Simplifications

* Decision #1: Brackets will only ever enclose calculated values or entire commands with their max # 
of parameters. This was done for simplicity within parsing, as we had a method to dynamically add back
in brackets if a user didn't input them themselves. However, this assumption backfired on us during the 
Change, as it made it difficult to implement the multiple turtle commands that used lists of IDs.

* Decision #2

* Decision #3

* Decision #4



## Changes from the Original Plan

* Change #1: the Controller does not shuttle information from the Model to the View. Instead, we made
an Observer paradigm to facilitate communication of updated turtle data to the frontend using the TurtleObserver
interface.

* Change #2

* Change #3

* Change #4


## How to Add New Features

#### Features Not Yet Done

* Feature #1: Multiple turtles
  * We were unable to connect the backend implementation of multiple turtles to the frontend. The backend
  implementation is a relatively simplified variation of the requirements, but it dynamically and correctly
  updates the data of each turtle and stores them correctly. We did not have enough time to adequately display
  each of these turtles in the frontend.

* Feature #2

* Feature #3

* Feature #4
 