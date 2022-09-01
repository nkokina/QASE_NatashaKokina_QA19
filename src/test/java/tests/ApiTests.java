package tests;

import adapters.DefectAdapter;
import adapters.ProjectAdapter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import models.ErrorField;
import models.NegativeResponse;
import models.PositiveResponse;
import models.defect.Defect;
import models.project.Counts;
import models.project.Project;
import models.project.Runs;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class ApiTests {

    private final static Gson gson = new Gson();

    ProjectAdapter projectAdapter = new ProjectAdapter();
    DefectAdapter defectAdapter = new DefectAdapter();
    String testProjectCode = "CODE3";
    String idDefect = "1";

    private ErrorField.ErrorFieldBuilder builder;

    @Test
    public void getAllProjectsTest() {

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .build())
                .build();
        PositiveResponse<Project> response = gson.fromJson(
                projectAdapter.getAllProjects(200),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getProjectsTest() {

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .title("ADFDSFDSF")
                        .code(testProjectCode)
                        .counts(Counts.builder().
                                runs(Runs.builder()
                                        .build())
                                .build())
                        .build())
                .build();
        PositiveResponse<Project> response = gson.fromJson(
                projectAdapter.getProjectByCode(200, testProjectCode),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createProjectPositiveTest() {

        Project project = Project.builder()
                .title("ADFDSFDSF")
                .code(testProjectCode)
                .description("efsfsdfsdfsdf")
                .build();

        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Project.builder()
                        .code(testProjectCode)
                        .build())
                .build();

        PositiveResponse<Project> response = gson.fromJson(
                projectAdapter.createProject(200, gson.toJson(project)),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createProjectNegativeTest() {
        Project project = Project.builder()
                .title("")
                .description("")
                .build();
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Data is invalid.")
                .errorFields(Arrays.asList(
                        ErrorField.builder()
                                .field("title")
                                .error("Title is required.")
                                .build(),
                        ErrorField.builder()
                                .field("code")
                                .error("Project code is required.")
                                .build()
                ))
                .build();
        NegativeResponse response = gson.fromJson(projectAdapter.createProject(400, gson.toJson(project)), NegativeResponse.class);
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteProjectsTest() {
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder().build();

        PositiveResponse<Project> response = gson.fromJson(
                projectAdapter.deleteProjectByCode(200, testProjectCode),
                new TypeToken<PositiveResponse<Project>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getAllDefectTest() {
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .build())
                .build();
        PositiveResponse<Defect> response = gson.fromJson(
                defectAdapter.getAllDefects(200, testProjectCode),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void getSpecificDefectTest() {
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .code(testProjectCode)
                        .id("1")
                        .build())
                .build();
        PositiveResponse<Defect> response = gson.fromJson(
                defectAdapter.getSpecificDefect(200, testProjectCode, idDefect),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createDefectTest() {
        Defect defect = Defect.builder()
                .title("DEFECT")
                .actual_result("RESULT")
                .severity(1)
                .build();
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id("1")
                        .build())
                .build();

        PositiveResponse<Defect> response = gson.fromJson(
                defectAdapter.createDefect(200, testProjectCode, gson.toJson(defect)),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void createDefectNegativeTest() {
        Defect defect = Defect.builder()
                .title("")
                .actual_result("")
                .severity(8)
                .build();
        NegativeResponse expectedResponse = NegativeResponse.builder()
                .errorMessage("Data is invalid.")
                .errorFields(Arrays.asList(
                        ErrorField.builder()
                                .field("title")
                                .error("The title field is required.")
                                .build(),
                        ErrorField.builder()
                                .field("actual_result")
                                .error("The actual result field is required.")
                                .build(),
                        ErrorField.builder()
                                .field("severity")
                                .error("The selected severity is invalid.")
                                .build()
                ))
                .build();
        NegativeResponse response = gson.fromJson(defectAdapter.createDefect(400, testProjectCode, gson.toJson(defect)), NegativeResponse.class);
        Assert.assertEquals(response, expectedResponse);
    }

    @Test
    public void deleteDefectTest() {
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id("1")
                        .build())
                .build();
        PositiveResponse<Defect> response = gson.fromJson(
                defectAdapter.deleteDefectByCode(200, testProjectCode, idDefect),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);

    }

    @Test
    public void patchDefectTest() {

        Defect defect = Defect.builder()
                .title("DEF")
                .actual_result("RES")
                .severity(2)
                .build();
        PositiveResponse<Object> expectedResponse = PositiveResponse.builder()
                .result(Defect.builder()
                        .id("1")
                        .build())
                .build();
        PositiveResponse<Defect> response = gson.fromJson(
                defectAdapter.patchDefectByCode(200, testProjectCode, idDefect, gson.toJson(defect)),
                new TypeToken<PositiveResponse<Defect>>() {
                }.getType());
        Assert.assertEquals(response, expectedResponse);

    }
}