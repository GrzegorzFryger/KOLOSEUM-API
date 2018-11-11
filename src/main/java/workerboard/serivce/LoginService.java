package workerboard.serivce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.ApplicationWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.LoginDto;
import workerboard.repository.ApplicationUserRepository;

import java.util.Optional;

@Service
public class LoginService {

    private ApplicationUserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginService(ApplicationUserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ApplicationUser checkUser(LoginDto login) throws ApplicationWrongPassword, ApplicationNotFound {



       ApplicationUser tempUser = null;

        tempUser = this.repository.findByEmail(login.getEmail())
                .orElseThrow(() -> new ApplicationNotFound("User not found"));

        if(!this.bCryptPasswordEncoder
                .matches(login.getPassword(),tempUser.getPassword())) {

            throw new ApplicationWrongPassword("Password not match");
        }

        return tempUser;

    }


}
