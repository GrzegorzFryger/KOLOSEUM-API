package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.exception.ApplicationNotFound;
import workerboard.exception.ApplicationWrongPassword;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.model.dto.UserPasswordDto;
import workerboard.repository.ApplicationUserRepository;
import workerboard.serivce.mapper.ApplicationUserMapper;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository userRepository;
    private ApplicationUserMapper mapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public ApplicationUserService(ApplicationUserRepository userRepository, ApplicationUserMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<ApplicationUser> findUserByEmail(String userEmail) {

        return userRepository.findByEmail( userEmail );
    }

    public ApplicationUser findUserById(Long id) throws ApplicationNotFound {

        return userRepository.findById( id )
                .orElseThrow(() -> new ApplicationNotFound("User not found by id " + id));
    }

    public ApplicationUser update(Long id, ApplicationUser newUser) throws ApplicationNotFound {

        ApplicationUser oldUser = this.userGetOne(id);

        newUser.setPassword( oldUser.getPassword() );
        oldUser = newUser;

        return userRepository.save( oldUser );
    }

    public List<ApplicationUser> findAllUsers() throws ApplicationNotFound {

        List <ApplicationUser> list = userRepository.findAll();

        if(list.isEmpty()){
            throw new ApplicationNotFound("Not found any users");
        }

        return list;
    }

    public boolean deleteUserById(Long id) throws ApplicationNotFound {
        userRepository.deleteById( id );

        return (this.findUserById(id) != null) ? false : true;
    }

    public ApplicationUser updatePassword(Long id, UserPasswordDto newPassword) throws ApplicationWrongPassword, ApplicationNotFound {


       ApplicationUser user = this.userGetOne( id );

        if (user.getPassword().equals( bCryptPasswordEncoder
                .encode( newPassword.getOldPassword() ))){

            user.setPassword( bCryptPasswordEncoder
                    .encode( newPassword.getNewPassword()  ));
        }
        else{

            throw new ApplicationWrongPassword("Password not Match");
        }

        return userRepository.save( user );
    }

    protected ApplicationUser userGetOne(Long id ) throws ApplicationNotFound {

        ApplicationUser user = null;

        try {
            user = this.userRepository.getOne( id );

        } catch ( EntityNotFoundException e ) {
            throw new ApplicationNotFound( "Not Found user wit id:" + id );
        }

        return user;

    }




}
