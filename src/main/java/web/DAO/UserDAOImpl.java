package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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
        em.createQuery("delete from User user where user.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public List<User> getAllUsers() {
        //join fetch, чтобы выводить роли
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        em.merge(userToBeUpdated);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<User> findByUsername(String username){
        return (Optional<User>)em.createQuery("from User user inner join fetch user.roles as roles where user.username = :username").setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

}
