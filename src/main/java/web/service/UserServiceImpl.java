package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDAO;
import web.model.User;

import java.util.List;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDAO service;

    public UserServiceImpl(UserDAO service){
        this.service = service;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return service.getUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        service.saveUser(user);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        service.removeUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(Long id, User updatedUser) {
        service.updateUser(id, updatedUser);
    }
}
