package cordero.melchor.forohub.controller;

import cordero.melchor.forohub.domain.usuario.DatosAutenticacion;
import cordero.melchor.forohub.domain.usuario.Usuario;
import cordero.melchor.forohub.infra.security.DatosTockenJWT;
import cordero.melchor.forohub.infra.security.TockenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TockenService tockenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);
        var tokenJWT = tockenService.generarTocken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatosTockenJWT(tokenJWT));
    }
}
