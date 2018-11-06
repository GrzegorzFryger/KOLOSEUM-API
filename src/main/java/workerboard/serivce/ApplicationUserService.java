package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.repository.ApplicationUserRepository;
import workerboard.serivce.mapper.ApplicationUserMapper;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository userRepository;
    private ApplicationUserMapper mapper;

    private Optional<ApplicationUser> applicationUser;


    @Autowired
    public ApplicationUserService(ApplicationUserRepository userRepository, ApplicationUserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Optional<ApplicationUser> findUser(String userEmail) {

        this.applicationUser = userRepository.findByEmail(userEmail);

        return this.applicationUser;
    }

    public ApplicationUserService getUser(String userEmail) {

        this.findUser(userEmail);

        return this;
    }




    public ApplicationUserDto mapToDao()
    {
        if(this.applicationUser.isPresent()) {
            return mapper
                    .userApplicationToDto(this.applicationUser.get());
        }

        return null;
    }


}
