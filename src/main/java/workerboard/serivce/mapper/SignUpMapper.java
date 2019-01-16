package workerboard.serivce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUserDto;


@Mapper(componentModel = "spring")
public interface SignUpMapper {

    SignUpMapper INSTANCE = Mappers.getMapper(SignUpMapper.class);

    @Mappings(
            {
                    @Mapping(ignore = true, target = "id"),
                    @Mapping(source = "registrationName", target = "firstName"),
                    @Mapping(source = "registrationSurname", target = "lastName"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "password", target = "password"),
                    @Mapping(source = "userRole", target = "roles")
            }
    )
    ApplicationUser registrationUserToApplicationUser(RegistrationUserDto registrationUserDto);

}

