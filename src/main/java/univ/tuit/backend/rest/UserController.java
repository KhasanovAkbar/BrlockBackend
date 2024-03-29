package univ.tuit.backend.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import univ.tuit.backend.config.exception.klass.AlreadyExistsException;
import univ.tuit.backend.domain.User;
import univ.tuit.backend.dto.EditUserRequest;
import univ.tuit.backend.helper.APIResponse;
import univ.tuit.backend.helper.ResponseBuilder;
import univ.tuit.backend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "This method for register new user")
    @PostMapping(value = "/create")
    ResponseEntity<APIResponse> create(@RequestBody User user) throws AlreadyExistsException {
        User createUser = userService.create(user);
        return ResponseBuilder.buildOK(createUser, null, HttpStatus.OK);

    }

    @ApiOperation(value = "This method register phone number who has car")
    @PutMapping(value = "/register")
    ResponseEntity<APIResponse> register(@RequestParam String phoneNumber) {
        User registerUser = userService.register(phoneNumber);
        return ResponseBuilder.buildOK(registerUser, null, HttpStatus.OK);
    }


    @ApiOperation(value = "This method is used to get the users.")
    @GetMapping(value = "/users")
    ResponseEntity<APIResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseBuilder.buildOK(users, null, HttpStatus.OK);
    }
    @ApiOperation(value = "This method edits user info")
    @PutMapping("/edit")
    ResponseEntity<APIResponse> edit(@RequestBody EditUserRequest editUserRequest) {
        User edit = userService.edit(editUserRequest);
        return ResponseBuilder.buildOK(edit, null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "This method searches car owner phone number with car plate number")
    @PostMapping(value = "/search")
    ResponseEntity<APIResponse> searchPhoneNumber(@RequestParam String carNumber) {
        User search = userService.retrieve(carNumber);
        return ResponseBuilder.buildOK(search, null, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "This method deletes user from db though id")
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable Integer id) {
        User retrieve = userService.retrieve(id);
        userService.delete(id, retrieve.getCarNumber());
    }


}