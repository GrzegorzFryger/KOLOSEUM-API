package workerboard.serivce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUser;

@Repository
@Mapper
public interface UserRegistrationMapper {

    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    @Mappings(
            {
                    @Mapping(source = "registrationName", target = "firstName"),
                    @Mapping(source = "registrationSurname", target = "lastName"),
                    @Mapping(source = "registrationEmail", target = "email"),
                    @Mapping(source = "registrationPassword", target = "password"),
                    @Mapping(source = "userRole", target = "userRole")
            }
    )

    ApplicationUser reogistrationUserToApplicationUser(RegistrationUser registrationUser);

}
