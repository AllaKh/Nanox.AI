package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import service.FormSubmissionService;

public class FormSubmissionSteps {

    private final FormSubmissionService formSubmissionService;
    public FormSubmissionSteps(FormSubmissionService formSubmissionService) {
        this.formSubmissionService = formSubmissionService;
    }

    @Given("user is on the Demoblaze contact form page")
    public void userOnTheContactFormPage() {
        formSubmissionService.userOnTheContactFormPage();
    }

    @When("user enters valid data in the form fields")
    public void userEntersValidDataInTheFormFields() {
        formSubmissionService.userEntersValidDataInTheFormFields();
    }

    @When("user submits the form")
    public void userSubmitsTheForm() {
        formSubmissionService.userSubmitsTheForm();
    }

    @Then("user should see a confirmation message")
    public void userShouldSeeAConfirmationMessage() {
        formSubmissionService.userShouldSeeAConfirmationMessage();
    }

    @When("user enters invalid data in the form fields")
    public void userEntersInvalidDataInTheFormFields() {
        formSubmissionService.userEntersInvalidDataInTheFormFields();
    }

    @Then("user should see an error message")
    public void userShouldSeeAnErrorMessage() {
        formSubmissionService.userShouldSeeAnErrorMessage();
    }
}
