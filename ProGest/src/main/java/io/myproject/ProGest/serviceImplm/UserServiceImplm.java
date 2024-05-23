package io.myproject.ProGest.serviceImplm;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.myproject.ProGest.model.User;
import io.myproject.ProGest.repository.UserRepository;
import io.myproject.ProGest.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserServiceImplm implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImplm.class);

    @Autowired
    private UserRepository repo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(String email, String password) {
        System.out.println("Sono nel servizio di login" + email);
        log.info("Tentativo di login per l'email: {}", email);
        User user = repo.findUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User register(User user) {
        System.out.println("Entering register method in UserServiceImpl");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("Ricerca utente per email: {}", email);
        return repo.findUserByEmail(email);
    }

    @Override
    public User findUserById(Integer id) {
        log.info("Ricerca utente per ID: {}", id);
        return repo.findUserById(id);
    }

    @Override
    public User updateProfileImage(Integer userId, byte[] imageData) {
        log.info("Aggiornamento immagine profilo per l'utente ID: {}", userId);
        User user = repo.findUserById(userId);
        if (user != null) {
            try {
                Blob imageBlob = new SerialBlob(imageData);
                user.setImage(imageBlob);
                return repo.save(user);
            } catch (SQLException e) {
                log.error("Errore durante la creazione del Blob: {}", e.getMessage());
                return null;
            }
        }
        return null;
    }

    @Override
    public User updateUser(User u, Integer id) {
        log.info("Aggiornamento utente ID: {}", id);
        User userFinded = this.findUserById(id);
        if (userFinded != null) {
            userFinded.setName(u.getName());
            userFinded.setEmail(u.getEmail());
            userFinded.setPassword(u.getPassword());
            userFinded.setImage(u.getImage());
            userFinded.setIsAdmin(u.getIsAdmin());
            return repo.save(userFinded);
        }
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        log.info("Eliminazione utente ID: {}", id);
        repo.deleteById(id);
    }
}



