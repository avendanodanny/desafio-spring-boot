package com.tecnova.demo.config;

import com.tecnova.demo.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HEADER_STRING);

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            String token = header.replace(TOKEN_PREFIX, "");

            try {
                // Validar el token y obtener el nombre de usuario
                String username = JwtTokenUtil.getUsernameFromToken(token);

                // Si el token es válido, se crea un objeto de autenticación
                if (username != null && !JwtTokenUtil.isTokenExpired(token)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException | IllegalArgumentException e) {
                // En caso de error en la validación, puedes agregar más lógica de manejo de errores
                response.sendError(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
                return;
            }
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}

