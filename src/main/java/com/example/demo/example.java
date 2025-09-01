package com.example.demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")

public class example {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("getByID/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id).orElse(null);
    }
    

    @PostMapping("/addUser")
    public Object addUser(@Valid @RequestBody User user){
        if (userRepository.existsById(user.getId())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "A user with the same id already exists");
            return error;
        }
        return userRepository.save(user);
    }

    @PutMapping("/replace/{id}")
    public User updateUser(@PathVariable int id, @Valid @RequestBody User user){
        if(userRepository.existsById(id)){
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    @PatchMapping("/updateDetails/{id}")
    public String updateUserName(@PathVariable int id, @Valid @RequestBody UpdateUserNameRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return "User not found!";
        }
        User existing = optionalUser.get();
        existing.setName(request.getName());
        userRepository.save(existing);
        return "User's name updated with ID: " + id;
    }
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){ 
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "User deleted with ID: "+id;
        }
        return "User not found!";
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted!";
    }
    
}
