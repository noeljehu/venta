package com.example.venta.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/ventas/*")  // Aplica el filtro solo a los endpoints /api/ventas/*
public class ApiKeyFilter implements Filter {

    private static final String API_KEY_HEADER = "user";  // Nombre del encabezado esperado
    private static final String VALID_API_KEY = "123";    // Clave API válida

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Leer el encabezado "user"
        String apiKey = httpRequest.getHeader(API_KEY_HEADER);

        if (apiKey != null && apiKey.equals(VALID_API_KEY)) {
            // Si la clave es válida, continúa con la solicitud
            chain.doFilter(request, response);
        } else {
            // Si la clave no es válida, devuelve un error 401
            httpResponse.getWriter().write("Unauthorized: Invalid API Key");
            httpResponse.setContentType("text/plain");
            httpResponse.setStatus(401);
        }
    }
}
