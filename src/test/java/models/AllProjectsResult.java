package models;

import lombok.Builder;
import lombok.Data;
import models.project.Project;

import java.util.List;

@Data
@Builder
public class AllProjectsResult {
    private List<Project> entities;
    private int total, filtered, count;
}
