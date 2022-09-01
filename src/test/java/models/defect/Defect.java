package models.defect;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Defect {
    String title;
    String id;
    String code;
    String description;
    Integer severity;
    String actual_result;
}
