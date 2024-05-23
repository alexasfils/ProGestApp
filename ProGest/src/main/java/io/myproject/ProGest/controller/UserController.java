package io.myproject.ProGest.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import io.myproject.ProGest.model.User;
import io.myproject.ProGest.service.UserService;
import io.myproject.ProGest.token.JwtTokenUtil;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/progest")
@RequiredArgsConstructor
public class UserController {

   private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
    	System.out.println("Sono nel controller register");
//        log.info("Ricevuta richiesta di registrazione per l'utente: {}", user.getEmail());
        try {
            User createdUser = service.register(user);
            URI location = new URI("/progest/" + createdUser.getId());
//            log.info("Utente registrato con successo: {}", createdUser.getId());
            return ResponseEntity.created(location).body(createdUser);
        } catch (URISyntaxException e) {
//            log.error("Errore durante la creazione dell'URI: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        } catch(Exception e) {
//            log.error("Errore durante la registrazione dell'utente: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // Autentica l'utente
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        // Controlla se l'autenticazione è riuscita
        if (authentication.isAuthenticated()) {
            // Genera il token JWT
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(); // Istanzia un'istanza di JwtTokenUtil
            String token = jwtTokenUtil.generateToken(userDetails);

            // Restituisci il token JWT nella risposta
            return ResponseEntity.ok(token);
        } else {
            // L'autenticazione non è riuscita, restituisci un errore
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @GetMapping("/user/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) {
        // Ottieni l'oggetto Authentication dal SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Controlla se l'utente è autenticato
        if (authentication != null && authentication.isAuthenticated()) {
            // L'utente è autenticato, puoi procedere a recuperare l'utente dal servizio
            log.info("Fetching user with id: {}", id);
            User user = service.findUserById(id);

            if (user != null) {
                return ResponseEntity.ok().body(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            // L'utente non è autenticato, restituisci un errore di autorizzazione
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @GetMapping("/user/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") String email) {
        // Ottieni l'oggetto Authentication dal SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Controlla se l'utente è autenticato
        if (authentication != null && authentication.isAuthenticated()) {
            // L'utente è autenticato, puoi procedere a recuperare l'utente dal servizio
            log.info("Fetching user with email: {}", email);
            User user = service.findUserByEmail(email);

            if (user != null) {
                return ResponseEntity.ok().body(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            // L'utente non è autenticato, restituisci un errore di autorizzazione
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    
    
    @GetMapping("/logoutSuccess")
    public ResponseEntity<String> logoutSuccess() {
        return ResponseEntity.ok("Logged out successfully");
    }

    // Altri metodi del controller...
}

