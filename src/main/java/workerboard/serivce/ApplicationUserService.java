package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.model.dto.UserPasswordDto;
import workerboard.repository.ApplicationUserRepository;
import workerboard.serivce.mapper.ApplicationUserMapper;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository userRepository;
    private ApplicationUserMapper mapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //private Optional<ApplicationUser> applicationUser;



@Autowired
    public ApplicationUserService(ApplicationUserRepository userRepository, ApplicationUserMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<ApplicationUser> findUserByEmail(String userEmail) {

        return userRepository.findByEmail(userEmail);
    }

    public Optional<ApplicationUser>findUserById(Long id)
    {

        return userRepository.findById(id);

    }

    public ApplicationUser update(Long id)
    {
        return (this.findUserById(id).isPresent()) ?
                this.userRepository.saveAndFlush(this.findUserById(id).get()) : null;
    }

    public List<ApplicationUser> findAllUsers() {

        return userRepository.findAll();

    }

    public boolean deleteUserById(Long id )
    {
        userRepository.deleteById(id);

       return (this.findUserById(id).isPresent()) ? false : true;
    }

    public UserPasswordDto updatePassword(Long id) {


    //toDO


      return null;
    }


//method for fluent api
//    public ApplicationUserService getUser(String userEmail) {
//
//        this.findUser(userEmail);
//
//        return this;
//    }

//    public ApplicationUserDto mapToDao()
//    {
//        if(this.applicationUser.isPresent()) {
//            return mapper
//                    .userApplicationToDto(this.applicationUser.get());
//        }
//
//        return null;
//    }


}
