package com.project.one.projOne.Controller;

import com.project.one.projOne.Service.UserService;
import com.project.one.projOne.model.Task;
import com.project.one.projOne.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity <List <User>> getAllUser(){
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        Optional <User> user = userService.getUserById(id);

        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok(user.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);

        if (Objects.nonNull(updatedUser)) {
            return ResponseEntity.ok(updatedUser);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<User> addTaskToUser(@PathVariable String id,@RequestBody Task task){
        try{
            User updatedUser = userService.addTaskToUser(id,task);
            return ResponseEntity.ok(updatedUser);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getAllTasksForUser(@PathVariable String userId) {
        List<Task> tasks = userService.getAllTasksForUser(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{userId}/tasks/{taskIndex}")
    public ResponseEntity<User> updateTask(@PathVariable String userId, @PathVariable int taskIndex, @RequestBody Task task) {
        try {
            User updatedUser = userService.updateTask(userId, taskIndex, task);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{userId}/average-score")
    public ResponseEntity<Double> getAverageScore(@PathVariable String userId) {
        double averageScore = userService.getAverageScore(userId);
        return ResponseEntity.ok(averageScore);
    }

    @DeleteMapping("/{userId}/tasks/{taskIndex}")
    public ResponseEntity<User> deleteTask(@PathVariable String userId, @PathVariable int taskIndex) {
        try {
            User updatedUser = userService.deleteTask(userId, taskIndex);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}




