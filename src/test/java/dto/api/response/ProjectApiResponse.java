package dto.api.response;

import dto.Project;
import lombok.Data;
import java.util.ArrayList;

@Data
public class ProjectApiResponse {
    Project result;
    boolean status;
    String errorMessage;
    ArrayList<Project> projects;
}
