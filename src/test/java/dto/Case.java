package dto;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class Case {
    //Basic
    String title;
    String status;
    String description;
    String suite;
    String severity;
    String priority;
    String type;
    String layer;
    String isFlaky;
    String milestone;
    String behavior;
    String automationStatus;
    boolean isCheckBoxChecked;
    //Conditions
    String preConditions;
    String postConditions;
    //Tags
    String[] tags;
    //Attachments
    File addAttachment;
    String attachmentTitle;
    //Parameters
    String parameterTitle;
    String parameterValue;
    //Test Case Steps
    String testCaseStepsDropdownOption;
    String gherkinStepsDropdownOption;
    String gherkinStepsNumber;
    String gherkinStepsInput;
}
