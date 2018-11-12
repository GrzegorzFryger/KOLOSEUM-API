package workerboard.serivce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.UserNotFound;
import workerboard.exception.UserWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.LoginDto;
import workerboard.repository.ApplicationUserRepository;

@Service
public class SignInService {

    private ApplicationUserRepository repository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SignInService(ApplicationUserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ApplicationUser checkUser(LoginDto login) throws UserWrongPassword, UserNotFound {



       ApplicationUser tempUser = null;

        tempUser = this.repository.findByEmail(login.getEmail())
                .orElseThrow(() -> new UserNotFound("User not found"));

        if(!this.bCryptPasswordEncoder
                .matches(login.getPassword(),tempUser.getPassword())) {

            throw new UserWrongPassword("Password not match");
        }

        return tempUser;

    }


}
