package workerboard.serivce.mapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.StatusAssertions;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.ApplicationUserDto;
import workerboard.model.dto.RegistrationUser;
import workerboard.model.enums.UserRole;

import static org.junit.Assert.*;

public class ApplicationUserMapperTest
{

    ApplicationUserMapper userMapper;

    @Before
    public void setUp() throws Exception
    {
        userMapper = ApplicationUserMapper.INSTANCE;
    }

    @Test
    public void dtoUserToApplicationUser() {

        //given
        ApplicationUserDto dto = new ApplicationUserDto();
        dto.setId(Long.valueOf(1));
        dto.setName("name");
        dto.setSurname("surname");
        dto.setEmail("email@wp.pl");
        dto.setPassword("Xxx");
        dto.setRole(UserRole.ADMIN);


        ApplicationUser returnedUser = userMapper.dtoUserToApplicationUser(dto);


        //then

        assertEquals(returnedUser.getId(),Long.valueOf(1));
        //and more


    }



    @Test
    public void userApplicationToDto() {


        ApplicationUser user = new ApplicationUser();
        user.setFirstName("Greg");
        user.setLastName("NON");
        user.setEmail("test@wp.pl");
        user.setUserRole(UserRole.ADMIN);
        user.setPassword("Xxxxx");


        ApplicationUserDto returnedUser = userMapper.userApplicationToDto(user);


        assertEquals(user.getFirstName(),"greg");
    }

    @Test
    public void testWitNull() {


        ApplicationUser user = new ApplicationUser();



        ApplicationUserDto returnedUser = userMapper.userApplicationToDto(user);


        assertEquals(user.getFirstName(),null);
    }
}