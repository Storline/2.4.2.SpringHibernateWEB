package web.service;

import web.model.User;

import java.util.List;

public interface UserService  {

    User getUserById(Long id);

    void saveUser(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    void updateUser(Long id, User updatedUser);
}
