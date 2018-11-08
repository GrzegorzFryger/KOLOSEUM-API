package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.ApplicationToMuchArguments;
import workerboard.exception.ApplicationWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.UserPasswordDto;
import workerboard.repository.ApplicationUserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    protected ApplicationUser userGetOneLazy(Long id) throws ApplicationNotFound {

        ApplicationUser user = null;

        try {
            user = this.userRepository.getOne(id);

        } catch ( EntityNotFoundException e ) {
            throw new ApplicationNotFound("Not Found user wit id:" + id);
        }

        return user;
    }

    public ApplicationUser findUserByEmail(String userEmail) throws ApplicationNotFound {

        return userRepository.findByEmail(userEmail).orElseThrow(() -> new ApplicationNotFound("not Found"));
    }

    public ApplicationUser findUserById(Long id) throws ApplicationNotFound {

        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFound("User not found by id " + id));
    }

    public ApplicationUser updateUser(Long id, ApplicationUser newUser) throws ApplicationNotFound {

        ApplicationUser oldUser = this.userGetOneLazy(id);

        newUser.setPassword(oldUser.getPassword());
        oldUser = newUser;

        return userRepository.save(oldUser);
    }

    public List<ApplicationUser> findAllUsers() throws ApplicationNotFound {

        List<ApplicationUser> list = userRepository.findAll();

        if (list.isEmpty()) {
            throw new ApplicationNotFound("Not found any users");
        }

        return list;
    }

    public boolean deleteUserById(Long id) throws ApplicationNotFound {
        userRepository.deleteById(id);

        return (this.findUserById(id) != null) ? false : true;
    }

    public ApplicationUser updatePassword(Long id, UserPasswordDto newPassword) throws ApplicationWrongPassword, ApplicationNotFound {

        ApplicationUser user = this.userGetOneLazy(id);

        if (bCryptPasswordEncoder.matches(newPassword.getOldPassword(), user.getPassword())) {

            user.setPassword(bCryptPasswordEncoder
                    .encode(newPassword.getNewPassword()));
        } else {

            throw new ApplicationWrongPassword("Password not Match");
        }

        return userRepository.save(user);
    }

    public List getUserByParameters(Map<String, String> parameters) throws ApplicationToMuchArguments, ApplicationNotFound {

        if (parameters.size() > 1) {
            throw new ApplicationToMuchArguments("To much arguments in parameters");
        }

        SinglePredicate func = (field, value) -> {
            return (root, query, cb) -> {
                return cb.and(cb.equal(root.get(field), value));

            };
        };

        return parameters.entrySet().stream().map(e -> {
            return userRepository.findAll(func.createPredicate(e.getKey(), e.getValue()));
        }).collect(Collectors.toList());
    }


}
