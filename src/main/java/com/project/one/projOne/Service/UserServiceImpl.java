package com.project.one.projOne.Service;

import com.project.one.projOne.model.Task;
import com.project.one.projOne.model.User;
import com.project.one.projOne.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(String id, User user) {
        Optional <User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()){
            user.setId(id);
            return userRepository.save(user);
        }else{
            throw new IllegalArgumentException("Invalid user id "+id);
        }
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);

    }

    @Override
    public User addTaskToUser(String userId, Task task){
        Optional <User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getTasks()==null){
                user.setTasks(new ArrayList<>());
            }
            user.getTasks().add(task);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Invalid user id " + userId);

    }


    @Override
    public List<Task> getAllTasksForUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::getTasks).orElse(new ArrayList<>());
    }

    @Override
    public User updateTask(String userId, int taskIndex, Task updatedTask) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getTasks() != null && taskIndex >= 0 && taskIndex < user.getTasks().size()) {
                user.getTasks().set(taskIndex, updatedTask);
                return userRepository.save(user);
            }
        }
        throw new IllegalArgumentException("Invalid user id " + userId + " or task index " + taskIndex);
    }

    @Override
    public double getAverageScore(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getTasks() != null && !user.getTasks().isEmpty()) {
                int totalScore = user.getTasks().stream().mapToInt(Task::getTaskScore).sum();
                return (double) totalScore / user.getTasks().size();
            }
        }
        return 0.0;
    }

    @Override
    public User deleteTask(String userId, int taskIndex) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getTasks() != null && taskIndex >= 0 && taskIndex < user.getTasks().size()) {
                user.getTasks().remove(taskIndex);
                return userRepository.save(user);
            }
        }
        throw new IllegalArgumentException("Invalid user id " + userId + " or task index " + taskIndex);
    }
}



