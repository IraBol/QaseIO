package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCounts {

    int cases;
    int suites;
    int milestones;
    ProjectRuns runs;
    ProjectDefects defects;
}
