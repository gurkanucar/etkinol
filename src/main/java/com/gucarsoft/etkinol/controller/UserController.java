package com.gucarsoft.etkinol.controller;

import com.gucarsoft.etkinol.config.jwt.JwtUtil;
import com.gucarsoft.etkinol.model.AuthRequest;
import com.gucarsoft.etkinol.model.user.CreateUser;
import com.gucarsoft.etkinol.model.user.User;
import com.gucarsoft.etkinol.service.UserService;
import com.gucarsoft.etkinol.service.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    // ***************************
    // ADMIN OPERATIONS
    // ***************************

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        return service.create(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> list() {
        return service.list();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return service.deleteById(id);
    }


    // ***************************
    // USER OPERATIONS
    // ***************************

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/self")
    public ResponseEntity<User> getSelf() {
        return service.getByUsername(getAuthUserName());
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/updateProfile")
    public ResponseEntity updateProfile(@RequestBody User user) {
        return service.updateProfile(getAuthUserName(), user);
    }

    // PUBLIC
    @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUser user) {
        return service.register(user);
    }

}
