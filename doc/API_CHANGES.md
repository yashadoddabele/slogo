## SLogo API Changes
### Team: 06
### Names


### Changes Made

#### External Model API

* Method changed: public double getTurtlePositions()

    * Why was the change made?
      * We ended up updated turtle positions through the Observer design pattern,
      so this method was unnecessary now.
    * Major or Minor (how much they affected your team mate's code)
      * This pretty substantially changed our teammate's code, since it was a change
      that needed to be reflected in the View as well/
    * Better or Worse (and why)
      * It's definitely much better now that this method is gone, since now all updates are
      done through the observer. Though this method didn't necessarily violate the View Model principle,
      it wasn't the best practice and we were able to get rid of it through using an Observer.
    * A similar method was also removed from the Controller file, public void updateTurtleView(). Once
  again, it was replaced with the observer pattern.


* Method changed: public void sendErrorToGui(String message)

    * Why was the change made?
      * In the beginning, we thought error handling would be something passed through the Controller.
      Eventually, we decided that error handling was more effective if had our methods throw specific,
      custom-made errors that are displayed in a user-friendly way. This was also important so that
      we could use the resource files to translate the messages into spanish/other language.

    * Major or Minor (how much they affected your team mate's code)
      * This didn't impact the front end too much, since the view code was taught to catch and display
      any exception/error in the same way.
    * Better or Worse (and why)
      * This is definitely better because it uses the resource files and abstracts errors/exceptions
      to the user.


#### Internal Model API

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


#### Internal View API

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)

