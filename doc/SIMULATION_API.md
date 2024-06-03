# Cell Society API Lab Discussion

### NAMES: Yasha, Yinuo, Saad, Divyansh

### TEAM: 6

## Current Simulation API

### External

* Identified Classes/Methods
    * All classes are public: Config/View modules
    * FileManagement: retrieving/saving file methods are public
    * Exceptions class (displaying/creating exceptions): all methods are public
    * LanguageManager (gets translations): all methods are public
    * Parser: methods that directly manipulate internal data (populateCellsToFile, createAndAppend,
      etc) are private. Methods that return data from the config file are public.
    * RandomizeXml: all methods are private except for the public randomizeCellStates which will be
      used externally.
    * /assets in the View module: most classes follow a pattern where getters and constructors are
      public, and unless they are strictly visual setters/modifiers that won’t necessarily directly
      change internal data, the rest are private/protected.
    * GamePlay: they are all public, which makes sense, since this is the wrapper class for the
      entire simulation.
    * Gui: constructor, getter/setters, and updateGrid (which is used in GamePlay) are public, the
      rest are private.
    * Main: start is public.

### Internal

* Identified Classes/Methods
    * Gametypes: all methods are public except for directly creating cells/cell grids/updating
      internal data
    * Cells: all methods are public

## Wish Simulation API

### External

* Goals
    * Easy to use
    * Extendable
    * Easy to make components
    * Protects against changing internal, foundational data/code


* Abstractions
    * Cell Type
    * Game model type abstract class. methods:
        * Update : can be overridden based on game simulation
        * Setting of the grid
        * Changing cell states
        * Getters
        * Rules and parsers


* Services and their Contract
    * Make components of the Gui.
    * Make an entire, running Gui/GamePlay.
    * Update visual components of the Gui easily, as well as information they supplied themselves.
    * Interact with the backend, but the user cannot see this interaction nor can they change it
      anymore than simply providing/changing input data (which will be conveyed in a configuration
      file). They might be able to see that a GameType object has been made, but they don’t
      necessarily know how it works.
    * Declarative vs imperative approach (user does not tell us how to do something, just what to
      do).

### Internal

* Goals
    * Stability
    * Extensibility
    * Efficiency
    * Maintainability


* Abstractions
    * Use existing methods and classes to create new simulation
    * The existing API should be broad enough to give the programmer flexibility in designing their
      simulation
    * Proper error handling should already be implemented, so the new designer could freely design
      their simulation without having to tip-toe around and the exceptions not handled previously in
      the implementation.


* Services and their Contract
    * Service: Backend Logic Management
    * Contract:
        * The internal API manages the backend logic of the game simulation, including game state
          management, rules enforcement, and simulation execution.
        * It provides modular components for different aspects of the game simulation (e.g., game
          rules, state transitions, scoring).
        * Abstraction layers ensure that the GUI interacts with the backend through well-defined
          interfaces, shielding the GUI from implementation details.
        * The API interprets user-configured settings and actions declaratively to drive the backend
          simulation, abstracting away the imperative execution details.
        * Backend components can be easily extended or modified without impacting the functionality
          of the GUI, maintaining flexibility and modularity.

## API Task Description

### External

* English:
    * The backend could provide an object that updates its state after it’s called by the frontend.
    * One possible way of doing this would be passing the Grid object (a custom object) from the
      model that stores the state and functionality of updating its states to a method call from
      display. This way, once the display has the object, the only thing it has to do is display its
      changing state.
* Java:
    * ```// Frontend display calls loadGrid to load the grid object, so it can display its state
  public void loadGrid (Grid gridObj) {
  //Loads the grid object and assigns it to an instance variable while calling the method initiating
  Grid functionality.
  }

  ```// Frontend display calls the updateGridView on the grid object to capture the new state of the grid and update its display accordingly
    private void updateGridView(Grid gridObj) {
    //It reloads the new state of the grid after a tick, updating the view based on the changed state of the grid object} 

    ```@Override
    // Backend model uses this method to update its grid state after each tick
    Public void updateGridState() {
    Updates the state of the grid depending on the parameters and previous
    state of the grid }

### Internal

* English:
    * The programmer should be able to create the new simulation under an overarching simulation
      interface, using/overriding the interface’s methods depending on its desired behavior.
        * The programmer could also have access to a certain abstract object cell and create a new
          type of cell if they wish to.
* Java:
    * ```// Developer implements the SimulationLoader interface to create a new simulation
  public class NewSimulation implements SimulationLoader {
  // Implement methods to load, initialize, and run the new simulation
  @Override
  public void loadSimulation(String simulationData) {
  // Load simulation data for the new simulation }

    	@Override
  	  public void initializeSimulation() {
      	  // Initialize the new simulation
  }

  ```@Override
  public void runSimulation() {
  // Run the new simulation
  }

```// Example of extending the Cell class for a custom cell type
public class CustomCell extends Cell {
// Implement methods specific to the custom cell type
@Override
public void updateState() {
// Update the state of the custom cell
}
// Additional methods specific to the custom cell type
}

// BackendManager loads and integrates the new simulation
BackendManager.loadSimulation("new_simulation_data");
BackendManager.initializeSimulation();







