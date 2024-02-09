package dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suite {

    @SerializedName("internal_id")
    int id;
    String title;
    @SerializedName("run_cases")
    ArrayList<Suite> testRuns;
    ArrayList<Suite> cases;
    ArrayList<Suite> suites;
    ArrayList<Suite> crumbs;
    int position;
    @SerializedName("parent_id")
    String parentId;
    String preconditions;
    String description;
}
