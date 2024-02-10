package dto.api.response;

import dto.Suite;
import lombok.Data;

@Data
public class SuiteApiResponse {

    Suite result;
    boolean status;
    String errorMessage;
    boolean filtered;
}
