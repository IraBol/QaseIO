package dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    String title;
    String code;
    String description;
    @SerializedName("is_private")
    boolean isProjectPrivate;
    String access;
    String group;

    ProjectCounts counts;
    ProjectRuns runs;
    ProjectDefects defects;

    int total;
    int filtered;
    int count;
    ArrayList<Project> entities;
}
