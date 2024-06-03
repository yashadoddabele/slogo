// use case:
// The user pauses the animation

public class PauseAnimationUseCase {

  private Animation animation; // This is the animation class to show movement in the GUI. Full implementation pending.

  public PauseAnimationUseCase(Animation animation) {
    this.animation = animation;
  }

  // pausing the animation after receiving the pause request from the user through
  // pause button
  public void pauseCommand() {
    animation.pause();
  }
}
