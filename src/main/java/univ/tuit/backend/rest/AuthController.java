package univ.tuit.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.tuit.backend.dto.LogOutResponse;
import univ.tuit.backend.dto.LoginRequest;
import univ.tuit.backend.dto.LoginResponse;
import univ.tuit.backend.helper.APIResponse;
import univ.tuit.backend.helper.ResponseBuilder;
import univ.tuit.backend.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/login")
    ResponseEntity<APIResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse login = userService.login(loginRequest);
        return ResponseBuilder.buildOK(login, null, HttpStatus.OK);
    }

    @GetMapping("/logout")
    LogOutResponse logout() {
        return userService.logOut();
    }
}
