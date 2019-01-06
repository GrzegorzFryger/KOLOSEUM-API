package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.NotFound;
import workerboard.model.ApplicationUser;
import workerboard.model.Experience;
import workerboard.model.Role;
import workerboard.model.enums.UserRole;
import workerboard.repository.ApplicationUserCustomRepository;
import workerboard.repository.ExperienceRepository;
import workerboard.repository.RoleRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class SignUpService {


    private ApplicationUserCustomRepository applicationUserRepository;
    private ExperienceRepository repository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SignUpService(ApplicationUserCustomRepository applicationUserRepository,
                         BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    public ApplicationUser registrationNewUser(ApplicationUser newApplicationUser) throws NotFound {

        if (applicationUserRepository
                .findByEmail(newApplicationUser.getEmail())
                .isPresent()) {
            throw new NotFound("User with email: " + newApplicationUser.getEmail() + " exist");
        }

        newApplicationUser.setPassword(passwordEncoder.encode(newApplicationUser.getPassword()));

        Optional<Role> role = roleRepository.findByName(UserRole.ROLE_UNSPECIFIED);

        if(role.isPresent()) {
            newApplicationUser.setRoles(Arrays.asList(role.get()));
        }else {
            newApplicationUser.setRoles(Arrays.asList(new Role(UserRole.ROLE_UNSPECIFIED)));
        }


        Experience experience = new Experience();
        experience.setLevel(1);
        experience.setExpToNextLevel(1000);

        newApplicationUser.setExperience(experience);
        return applicationUserRepository.save(newApplicationUser);
    }
}
