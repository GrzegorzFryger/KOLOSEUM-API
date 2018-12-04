package workerboard.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import workerboard.security.jwt.CurrentUser;
import workerboard.security.jwt.model.JwtUserPrincipal;

import java.util.Optional;

//access to current user
class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(((JwtUserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername());


    }
}