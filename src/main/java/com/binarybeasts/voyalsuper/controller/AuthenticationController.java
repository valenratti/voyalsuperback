package com.binarybeasts.voyalsuper.controller;

import com.binarybeasts.voyalsuper.exception.UserAlreadyExistException;
import com.binarybeasts.voyalsuper.model.dto.RegisterUserDto;
import com.binarybeasts.voyalsuper.security.jwt.JwtUtil;
import com.binarybeasts.voyalsuper.security.message.AuthenticationResponse;
import com.binarybeasts.voyalsuper.security.message.JwtResponse;
import com.binarybeasts.voyalsuper.security.message.LoginForm;
import com.binarybeasts.voyalsuper.service.JwtUserDetailsService;
import com.binarybeasts.voyalsuper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("authenticate")
public class AuthenticationController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailsService myUserDetailsService;

    private final JwtUtil jwtTokenUtil;


    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, JwtUserDetailsService myUserDetailsService, JwtUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@RequestBody @Valid RegisterUserDto user, final BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("The information provided is not correct.");
        }
        try {
            return ResponseEntity.ok(userService.save(user));
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
        }
        return ResponseEntity.badRequest().body("Not registered");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginForm authenticationRequest) throws Exception{
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(token);
        }
        catch (Exception e){
            return new ResponseEntity<>("Usuario y/o contraseña invalidos", HttpStatus.FORBIDDEN); //403
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);


        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
