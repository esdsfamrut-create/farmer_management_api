package com.famrut.farmer_management_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import com.famrut.farmer_management_api.service.JwtService;
import com.famrut.farmer_management_api.dto.FarmerResponse;
import com.famrut.farmer_management_api.entity.Farmer;
import com.famrut.farmer_management_api.entity.FarmerStatus;
import com.famrut.farmer_management_api.repository.FarmerRepository;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY =
            "farmer-management-api-secret-key-2026-change-this";

    private final SecretKey secretKey;
      private final JwtService jwtService;
      private final FarmerRepository farmerRepository;

    public JwtAuthenticationFilter(JwtService jwtService, FarmerRepository farmerRepository) {
        this.secretKey = Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
        this.jwtService = jwtService;
        this.farmerRepository = farmerRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader =
                request.getHeader("Authorization");

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);

        try {

            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String role = claims.get("role", String.class);

           String farmerId =
        jwtService.extractFarmerId(token);

                Farmer farmer = farmerRepository
                        .findById(Long.valueOf(farmerId))
                        .orElse(null);

                if (farmer == null ||
                        farmer.getStatus() != FarmerStatus.ACTIVE) {

                SecurityContextHolder.clearContext();

                filterChain.doFilter(request, response);
                return;
                }

                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority(
                                "ROLE_" + role
                        );

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                farmerId,
                                null,
                                List.of(authority)
                        );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        } catch (Exception ex) {

            SecurityContextHolder.clearContext();

        }

        filterChain.doFilter(request, response);
    }
}