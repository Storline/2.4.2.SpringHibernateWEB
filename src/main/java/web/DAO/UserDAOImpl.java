package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User getUserById(Long id) {
        User user = em.find(User.class, id);
        em.detach(user);
        return user;
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void removeUserById(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = em.createQuery("select u from User u", User.class).getResultList();
        return listUsers;
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        em.merge(userToBeUpdated);
    }
}
