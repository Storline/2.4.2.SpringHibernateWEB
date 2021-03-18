package web.DAO;

import web.model.User;

import java.util.List;


public interface UserDAO {

    User getUserById(Long id);

    void saveUser(User user);

    void removeUserById(Long id);

    List<User> getAllUsers();

    void updateUser(Long id, User updatedUser);
}
