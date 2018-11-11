package workerboard.serivce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUserDto;


@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    UserRegistrationMapper INSTANCE = Mappers.getMapper(UserRegistrationMapper.class);

    @Mappings(
            {
                    @Mapping(ignore = true, target = "id"),
                    @Mapping(source = "registrationName", target = "firstName"),
                    @Mapping(source = "registrationSurname", target = "lastName"),
                    @Mapping(source = "registrationEmail", target = "email"),
                    @Mapping(source = "registrationPassword", target = "password"),
                    @Mapping(source = "userRole", target = "userRole")
            }
    )
    ApplicationUser registrationUserToApplicationUser(RegistrationUserDto registrationUserDto);

}
