package dto;

import lombok.Data;

@Data
public class SuiteApiResponse {

    Suite result;
    boolean status;
    String errorMessage;
    boolean filtered;
}
