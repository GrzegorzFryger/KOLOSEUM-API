package workerboard.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workerboard.repository.ApplicationUserRepository;

@Service
public class ApplicationUserService {

    private ApplicationUserRepository userRepository;

    @Autowired
    public ApplicationUserService(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
