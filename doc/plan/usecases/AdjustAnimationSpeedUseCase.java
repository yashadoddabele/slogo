// use case
// user is adjusting animation speed

public class AdjustAnimationSpeedUseCase {

  private AnimationSettings animationSettings; // Manages settings for animations

  public AdjustAnimationSpeedUseCase(AnimationSettings animationSettings) {
    this.animationSettings = animationSettings;
  }

  public void adjustAnimationSpeed(double speedFactor) {
    // User adjusts the speed of the animation through a GUI component
    animationSettings.setSpeedFactor(speedFactor);
    // The animation behavior changes accordingly in real-time
  }
}
