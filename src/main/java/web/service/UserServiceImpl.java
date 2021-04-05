package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDAO;
import web.model.User;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service("userServiceImpl")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO service;

    public UserServiceImpl(UserDAO service){
        this.service = service;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    @Override
    public User getUserById(Long id) {
        return service.getUserById(id);
    }

    @Override
    public Optional<User> findByUsername(String username){return service.findByUsername(username);}

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
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @Override
    @Transactional
    public void updateUser(Long id, User updatedUser) {
        service.updateUser(id, updatedUser);
    }

    @Override
    public List<Role> getRoleList() {
        return null;
    }

    @Override
    public Role getRole(String role) {
        return null;
    }

}
