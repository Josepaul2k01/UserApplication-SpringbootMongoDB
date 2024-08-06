package com.project.one.projOne.Service;
import com.project.one.projOne.model.Task;
import com.project.one.projOne.model.User;
import java.util.*;

public interface UserService {

    User createUser(User user);
    Optional<User> getUserById(String id);
    List <User> getAllUsers();
    User updateUser(String id,User user);
    void deleteUser(String id);
    User addTaskToUser (String userId, Task task);
    List<Task> getAllTasksForUser(String userId);
    User updateTask(String id,int taskIndex,Task task);
    double getAverageScore(String userId);
    User deleteTask(String userId, int taskIndex);






}
