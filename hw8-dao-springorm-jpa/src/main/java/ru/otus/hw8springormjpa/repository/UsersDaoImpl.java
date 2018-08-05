package ru.otus.hw8springormjpa.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw8springormjpa.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class UsersDaoImpl implements UsersDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<User> getUserByUsername(String userName) {
        Optional<User> userOpt = Optional.empty();
        try {
            TypedQuery<User> query = em.createQuery(
                    "select u from User u where u.username = :userName",
                    User.class);
            query.setParameter("userName", userName);
            userOpt = Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            userOpt = Optional.empty();
        }

        return userOpt;
    }

    @Override
    @Transactional
    public void insert(User user) {
        em.persist(user);
    }
}
