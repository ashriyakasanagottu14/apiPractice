package com.example.demo;



import org.springframework.web.bind.annotation.*;
import java.util.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")

public class example {

    private Map<Integer,User> users=new HashMap<>();
    // private int idcount=1;

    @GetMapping
    public Collection<User> getUsers(){
        return users.values();
    }

    @GetMapping("getByID/{id}")
    public Collection<User> getMethodName(@PathVariable int id) {
        return Arrays.asList(users.get(id));
    }
    

    @PostMapping("/addUser")
    public Object addUser(@Valid @RequestBody User user){
        if (users.containsKey(user.getId())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "A user with the same id already exists");
            return error;
        }
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping("/replace/{id}")
    public User updateUser(@PathVariable int id, @Valid @RequestBody User user){
        if(users.containsKey(id)){
            users.remove(id);
            user.setId(user.getId());
            users.put(user.getId(),user);
            return user;
        }
        return null;
    }

    @PatchMapping("/updateDetails/{id}")
    public String updateUserName(@PathVariable int id, @Valid @RequestBody UpdateUserNameRequest request) {
    if (!users.containsKey(id)) {
        return "User not found!";
    }
    User existing = users.get(id);
    existing.setName(request.getName());
    return "User's name updated with ID: " + id;
    }
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){ 
        if(users.containsKey(id)){
            users.remove(id);
            return "User deleted with ID: "+id;
        }
        return "User not found!";
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllUsers() {
        users.clear();
        return "All users deleted!";
    }
    
}
