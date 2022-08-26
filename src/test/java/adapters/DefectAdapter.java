package adapters;

public class DefectAdapter extends BaseAdapter {

    private static final String ENDPOINT_DEFECT = "defect";

    public String getAllDefects(int statusCode, String defectCode) {
        return get(ENDPOINT_DEFECT + "/" + defectCode, statusCode);
    }

    public String getSpecificDefect(int statusCode, String defectCode, String id) {
        return get(ENDPOINT_DEFECT + "/" + defectCode + "/" + id, statusCode);
    }

    public String createDefect(int statusCode, String defectCode, String requestBody) {
        return post(ENDPOINT_DEFECT + "/" + defectCode, statusCode, requestBody);
    }

    public String deleteDefectByCode(int statusCode, String defectCode, String id) {
        return delete(ENDPOINT_DEFECT + "/" + defectCode + "/" + id, statusCode);
    }

    public String patchDefectByCode(int statusCode, String defectCode, String id, String requestBody) {
        return patch(ENDPOINT_DEFECT + "/" + defectCode + "/" + id, statusCode, requestBody);
    }

}
