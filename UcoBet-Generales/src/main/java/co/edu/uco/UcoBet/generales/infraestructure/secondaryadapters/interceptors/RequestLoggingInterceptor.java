package co.edu.uco.UcoBet.generales.infraestructure.secondaryadapters.interceptors;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = UUID.randomUUID().toString();
        long startTime = System.currentTimeMillis();

        System.out.println("Request ID: " + requestId);
        System.out.println("URL: " + request.getRequestURI());
        System.out.println(" Start Time: " + startTime);
        
        RequestContext.setRequestId(requestId);
        
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Limpiar el request ID al finalizar la solicitud
        RequestContext.clear(); 
    }
    
    // Optionally override afterCompletion to log response time
}