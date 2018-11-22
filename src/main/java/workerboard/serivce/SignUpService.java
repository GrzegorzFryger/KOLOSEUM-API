package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.UserNotFound;
import workerboard.model.ApplicationUser;
import workerboard.model.Role;
import workerboard.model.enums.UserRole;
import workerboard.repository.ApplicationUserRepository;

import java.util.Arrays;

@Service
public class SignUpService {


    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(ApplicationUserRepository applicationUserRepository,
                         BCryptPasswordEncoder passwordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ApplicationUser registrationNewUser(ApplicationUser newApplicationUser) throws UserNotFound {

        if (applicationUserRepository
                .findByEmail(newApplicationUser.getEmail())
                .isPresent()) {
            throw new UserNotFound("User with email: " + newApplicationUser.getEmail() + " exist");
        }

        newApplicationUser.setPassword(passwordEncoder.encode(newApplicationUser.getPassword()));

        newApplicationUser.setRoles(Arrays.asList(new Role(UserRole.ROLE_UNSPECIFIED)));

        return applicationUserRepository.save(newApplicationUser);
    }
}
