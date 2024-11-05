package co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.interceptors;

public class RequestContext {
    private static final ThreadLocal<String> requestId = new ThreadLocal<>();

    public static void setRequestId(String id) {
        requestId.set(id);
    }

    public static String getRequestId() {
        return requestId.get();
    }

    public static void clear() {
        requestId.remove();
    }
}
