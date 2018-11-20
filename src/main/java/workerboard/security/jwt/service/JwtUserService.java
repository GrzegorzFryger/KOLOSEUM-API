package workerboard.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import workerboard.repository.ApplicationUserRepository;
import workerboard.security.jwt.model.JwtUserPrincipal;

@Service
public class JwtUserService implements UserDetailsService {


    @Autowired
    ApplicationUserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return JwtUserPrincipal.buildPrinciple(

                repository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Not found user with: " + email))
        );
    }
}
