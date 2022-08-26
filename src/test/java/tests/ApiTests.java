package tests;

import adapters.DefectAdapter;
import adapters.ProjectAdapter;
import com.google.gson.Gson;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {

    private final String expectedCreateProjectJson = "{\"status\":true,\"result\":{\"total\":3,\"filtered\":3,\"count\":3,\"entities\":[{\"title\":\"Demo Project\",\"code\":\"DEMO\",\"counts\":{\"cases\":10,\"suites\":3,\"milestones\":2,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":1,\"open\":1}}},{\"title\":\"QA19_Natalia_Kokina\",\"code\":\"QNK\",\"counts\":{\"cases\":16,\"suites\":1,\"milestones\":0,\"runs\":{\"total\":2,\"active\":0},\"defects\":{\"total\":4,\"open\":4}}},{\"title\":\"Selenide\",\"code\":\"CODE\",\"counts\":{\"cases\":1,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}}]}}";
    private final String expectedProjectJson = "{\"status\":true,\"result\":{\"title\":\"CODE3\",\"code\":\"CODE3\",\"counts\":{\"cases\":0,\"suites\":0,\"milestones\":0,\"runs\":{\"total\":0,\"active\":0},\"defects\":{\"total\":0,\"open\":0}}}}";
    private final String expectedCreateDefectJson = "{\"status\":true,\"result\":{\"total\":1,\"filtered\":1,\"count\":1,\"entities\":[{\"id\":1,\"title\":\"DEFECT\",\"actual_result\":\"RESULT\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":236298,\"severity\":\"blocker\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-26 07:51:26\",\"updated\":\"2022-08-26 07:51:26\",\"created_at\":\"2022-08-26T07:51:26+00:00\",\"updated_at\":\"2022-08-26T07:51:26+00:00\",\"tags\":[]}]}}";
    private final String getExpectedGetSpecificDefectJson = "{\"status\":true,\"result\":{\"id\":1,\"title\":\"DEFECT\",\"actual_result\":\"RESULT\",\"status\":\"open\",\"milestone_id\":null,\"project_id\":236298,\"severity\":\"blocker\",\"member_id\":1,\"attachments\":[],\"custom_fields\":[],\"external_data\":\"{}\",\"resolved_at\":null,\"created\":\"2022-08-26 07:51:26\",\"updated\":\"2022-08-26 07:51:26\",\"created_at\":\"2022-08-26T07:51:26+00:00\",\"updated_at\":\"2022-08-26T07:51:26+00:00\",\"tags\":[]}}";
    private final String expectedProjectDefectJson = "{\"status\":true}";
    private final String expectedDeleteDefectJson = "{\"status\":true,\"result\":{\"id\":1}}";
    private final static Gson gson = new Gson();
    ProjectAdapter projectAdapter = new ProjectAdapter();
    DefectAdapter defectAdapter = new DefectAdapter();
    String testProjectCode = "CODE3";
    String idDefect = "1";

    @Test
    public void getAllProjectsTest() {
        String responseBody = projectAdapter.getAllProjects(200);
        Assert.assertEquals(responseBody, expectedCreateProjectJson);
    }

    @Test
    public void getProjectsTest() {
        String responseBody = projectAdapter.getProjectByCode(200, testProjectCode);
        Assert.assertEquals(responseBody, expectedProjectJson);
    }

    @Test
    public void createProjectTest() {
        Project project = Project.builder()
                .title("CODE3")
                .code(testProjectCode)
                .description("efsfsdfsdfsdf")
                .build();
        ProjectResponse expectedProjectResponseBody = ProjectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testProjectCode)
                        .build())
                .build();
        String actualResponseBody = projectAdapter.createProject(200, gson.toJson(project));
        Assert.assertEquals(gson.fromJson(actualResponseBody, ProjectResponse.class),
                expectedProjectResponseBody);
    }

    @Test
    public void deleteProjectsTest() {
        String responseBody = projectAdapter.deleteProjectByCode(200, testProjectCode);
        Assert.assertEquals(responseBody, expectedProjectDefectJson);
    }

    @Test
    public void getAllDefectTest() {
        String responseBody = defectAdapter.getAllDefects(200, testProjectCode);
        Assert.assertEquals(responseBody, expectedCreateDefectJson);
    }

    @Test
    public void getSpecificDefectTest() {
        String responseBody = defectAdapter.getSpecificDefect(200, testProjectCode, idDefect);
        Assert.assertEquals(responseBody, getExpectedGetSpecificDefectJson);
    }

    @Test
    public void createDefectTest() {
        Defect defect = Defect.builder()
                .title("DEFECT")
                .actual_result("RESULT")
                .severity(1)
                .build();
        DefectResponse expectedDefectResponseBody = DefectResponse
                .builder()
                .result(Result
                        .builder()
                        .code(testProjectCode)
                        .id(idDefect)
                        .build())
                .build();
        String actualResponseBody = defectAdapter.createDefect(200, testProjectCode, gson.toJson(defect));
        Assert.assertEquals(gson.fromJson(actualResponseBody, DefectResponse.class),
                expectedDefectResponseBody);
    }

    @Test
    public void deleteDefectTest() {
        String responseBody = defectAdapter.deleteDefectByCode(200, testProjectCode, idDefect);
        Assert.assertEquals(responseBody, expectedDeleteDefectJson);
    }

    @Test
    public void patchDefectTest() {
        Defect defect = Defect.builder()
                .title("DEF")
                .actual_result("RES")
                .severity(2)
                .build();
        String responseBody = defectAdapter.patchDefectByCode(200, testProjectCode, idDefect, gson.toJson(defect));
        Assert.assertEquals(responseBody, expectedDeleteDefectJson);
    }
}