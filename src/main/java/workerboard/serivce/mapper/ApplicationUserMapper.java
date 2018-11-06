package workerboard.serivce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.model.dto.RegistrationUser;




@Repository
@Mapper(componentModel = "spring")
public interface ApplicationUserMapper {

    workerboard.serivce.mapper.ApplicationUserMapper INSTANCE = Mappers.getMapper(workerboard.serivce.mapper.ApplicationUserMapper.class);

    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "name", target = "firstName"),
                    @Mapping(source = "surname", target = "lastName"),
                    @Mapping(source = "email", target = "email"),
                    @Mapping(source = "password", target = "password"),
                    @Mapping(source = "role", target = "userRole")
            }
    )
    ApplicationUser dtoUserToApplicationUser(ApplicationUserDto registrationUser);

    ApplicationUserDto userApplicationToDto(ApplicationUser registrationUser);
}
