package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.NotFound;
import workerboard.exception.UserWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.UserPasswordDto;
import workerboard.repository.ApplicationUserCustomRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService extends BasicAbstractService<ApplicationUser> {

    private ApplicationUserCustomRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(ApplicationUserCustomRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        super.setBasicRepository(userRepository);
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    protected ApplicationUser userGetOneLazy(Long id) throws NotFound {

        ApplicationUser user = null;

        try {
            user = this.userRepository.getOne(id);
        } catch ( EntityNotFoundException e ) {
            throw new NotFound("Not Found user wit id:" + id);
        }

        return user;
    }

    public ApplicationUser findUserByEmail(String userEmail) throws NotFound {

        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFound("not Found"));
    }

    public ApplicationUser findUserById(Long id) throws NotFound {

        return userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User not found by id " + id));
    }

    public ApplicationUser updateUser(Long id, ApplicationUser newUser) throws NotFound {

        ApplicationUser oldUser = this.userGetOneLazy(id);

        newUser.setPassword(oldUser.getPassword());
        oldUser = newUser;

        return userRepository.save(oldUser);
    }

    public List<ApplicationUser> findAllUsers() throws NotFound {

        List<ApplicationUser> list = userRepository.findAll();

        if (list.isEmpty()) {
            throw new NotFound("Not found any users");
        }

        return list;
    }

    public boolean deleteUserById(Long id) throws NotFound {

        userRepository.deleteById(id);

        return (this.findUserById(id) != null) ? false : true;
    }

    public ApplicationUser updatePassword(Long id, UserPasswordDto newPassword) throws UserWrongPassword, NotFound {

        ApplicationUser user = this.userGetOneLazy(id);

        if (bCryptPasswordEncoder.matches(newPassword.getOldPassword(), user.getPassword())) {

            user.setPassword(bCryptPasswordEncoder
                    .encode(newPassword.getNewPassword()));
        } else {

            throw new UserWrongPassword("Password not Match");
        }

        return userRepository.save(user);
    }


//    @Deprecated
//    public List getUserByParameters(Map<String, String> parameters) throws WrongTypeArguments, NotFound {
//
//        if (parameters.size() > 1) {
//            throw new WrongTypeArguments("To much arguments in parameters");
//        }
//
//        SinglePredicate func = (field, value) -> {
//            return (root, query, cb) -> {
//                return cb.and(cb.equal(root.get(field), value));
//
//            };
//        };
//
//        return parameters.entrySet().stream().map(e -> {
//            return userRepository.findAll(func.createPredicate(e.getKey(), e.getValue()));
//        }).collect(Collectors.toList());
//    }


}
