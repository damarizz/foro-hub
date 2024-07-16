package com.alura.forohub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forohub.dominio.usuarios.DatosAuthUsuario;
import com.alura.forohub.dominio.usuarios.Usuario;
import com.alura.forohub.infra.security.DatosJWTToken;
import com.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid DatosAuthUsuario datosAuth) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAuth.login(), datosAuth.clave());
        var authResult = authManager.authenticate(authToken);
        var jwt = tokenService.generarToken((Usuario) authResult.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwt));
    }
}
