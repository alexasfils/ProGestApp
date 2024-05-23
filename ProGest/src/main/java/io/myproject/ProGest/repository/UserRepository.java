package io.myproject.ProGest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.myproject.ProGest.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findUserByEmail(String email);
    public User findUserByEmailAndPassword(String email, String password);
    public User findUserById(Integer id);

//    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
//    boolean existsByEmail(@Param("email") String email);
//
//    @Modifying
//    @Transactional
//    @Query("INSERT INTO User (name, email, password) VALUES (:name, :email, :password)")
//    void createUser(@Param("name") String name, @Param("email") String email, @Param("password") String password);
}
