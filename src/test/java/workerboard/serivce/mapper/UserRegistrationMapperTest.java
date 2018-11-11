package workerboard.serivce.mapper;

import org.junit.Before;
import org.junit.Test;
import workerboard.model.ApplicationUser;
import workerboard.model.dto.RegistrationUserDto;
import workerboard.model.enums.UserRole;

import static org.junit.Assert.assertEquals;

public class UserRegistrationMapperTest {

    UserRegistrationMapper registrationMapper;

    @Before
    public void setUp() throws Exception
    {
        registrationMapper = UserRegistrationMapper.INSTANCE;
    }

    @Test
    public void reogistrationUserToApplicationUser() {

        RegistrationUserDto regUser = new RegistrationUserDto();
        regUser.setRegistrationName("Greg");
        regUser.setRegistrationSurname("testSurname");
        regUser.setRegistrationEmail("testEmail");

        ApplicationUser user = new ApplicationUser();

        user.setFirstName(regUser.getRegistrationName());
        user.setLastName(regUser.getRegistrationSurname());
        user.setPassword(regUser.getRegistrationPassword());
        user.setEmail(regUser.getRegistrationEmail());
        user.setUserRole(UserRole.UNSPECIFED);


       ApplicationUser returnedUser = registrationMapper.registrationUserToApplicationUser(regUser);


        //then

        assertEquals(user,returnedUser);



    }
}