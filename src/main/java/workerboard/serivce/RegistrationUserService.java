package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUser;
import workerboard.repository.ApplicationUserRepository;
import workerboard.serivce.mapper.UserRegistrationMapper;

import java.util.Optional;

@Service
public class RegistrationUserService {

    private UserRegistrationMapper registrationMapper;
    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder  passwordEncoder;

    @Autowired
    public RegistrationUserService(UserRegistrationMapper registrationMapper,
                                   ApplicationUserRepository applicationUserRepository,
                                   BCryptPasswordEncoder passwordEncoder) {
        this.registrationMapper = registrationMapper;
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
    }






    public Boolean registrationNewUser(RegistrationUser registrationUser)
    {

        Optional<ApplicationUser> optUser =
                Optional.ofNullable(registrationMapper.registrationUserToApplicationUser(registrationUser));

        if(optUser.isPresent())
        {
            optUser.get().setPassword(passwordEncoder.encode(optUser.get().getPassword()));
            applicationUserRepository.save(optUser.get());
            return true;
        }

        return false;

    }
}
