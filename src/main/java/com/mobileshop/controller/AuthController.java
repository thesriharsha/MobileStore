package com.mobileshop.controller;

import com.mobileshop.custom.exception.IdNotFoundException;
import com.mobileshop.dto.LoginDto;
import com.mobileshop.dto.RegisterDto;
import com.mobileshop.entities.Role;
import com.mobileshop.entities.UserEntity;
import com.mobileshop.repos.RoleRepo;
import com.mobileshop.repos.UserRepo;
import com.mobileshop.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder encoder;

    @Autowired
    private JWTService jwtService;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo,
                          PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;


    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) throws IdNotFoundException
    {
        if(userRepo.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        Role role = roleRepo.findByName(registerDto.getRole()).orElseThrow(
                ()-> new IdNotFoundException("101","No Role present with this name !!")
        );
        user.addRole(role);

//        Role roles = roleRepo.findByName("USER").get();
//        user.setRoles(Collections.singleton(roles));

        userRepo.save(user);

        return new ResponseEntity<>("User Registered success!", HttpStatus.OK);


    }



    /// To generate token
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("assignRole")
    public String assignRole(@RequestParam int userId,@RequestParam String name)throws IdNotFoundException{
        UserEntity user = userRepo.findById(userId).orElseThrow(
                ()-> new IdNotFoundException("505","No User present with the given ID")
        );
        Role role = roleRepo.findByName(name).orElseThrow(
                ()-> new IdNotFoundException("101","No Role present with this name !!")
        );
        user.addRole(role);
        userRepo.save(user);
        return "Role assigned to User successfully!";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("addRole")
    public ResponseEntity<String> addRole(@RequestParam String name){
        Optional<Role> role = roleRepo.findByName(name);
        if(role.isPresent())
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"The role is already present!!");

        Role newRole = new Role(name);
        roleRepo.save(newRole);
        return new ResponseEntity<>( "Role added successfully",HttpStatus.OK);
    }


//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getUsername(), loginDto.getPassword())
//                );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
//
//    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody LoginDto loginDto){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(loginDto.getUsername());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid User details");
    }

}
