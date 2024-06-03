// use case:
// The user sets the language to one of the supported languages by the IDE, such as Spanish
// using the UI so the commands and the interface are in that language

public class SetLanguageUseCase {

  private SLogoApi api; // The SLogo API to interact with the model.

  public SetLanguageUseCase(SLogoApi api) {
    this.api = api;
  }

  //Sets the language
  public void setLanguage(String language) {
    // Set the language through the API
    api.switchlanguage(language);

    // Assuming for now there's a method to update the UI with the new language
    api.setLanguageInUI(language);
  }
}
