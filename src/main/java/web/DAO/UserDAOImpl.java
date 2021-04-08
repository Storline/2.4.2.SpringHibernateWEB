package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.management.relation.Role;
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
        return em.find(User.class, id);
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
        return em.createQuery("select u from User u left join fetch u.roles as roles", User.class).getResultList();
    }

    @Override
    public void updateUser(Long id, User updatedUser) {
        em.merge(updatedUser);
    }


    @Override
    //Вдальнейшем, может потребоваться обработка в try-catch, но это не точно. Пока что - полет нормальный
    public Optional<User> findByUsername(String username){
        User user =  (User) em.createQuery("select user from User user where user.username = :username")
                .setParameter("username", username)
                .getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public List<Role> getRoleList(){
        return null;
    }

    @Override
    public Role getRole(String role){
        return null;
    }

}
