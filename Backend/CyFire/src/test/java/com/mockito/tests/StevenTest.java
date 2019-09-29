package com.mockito.tests;

import com.cyfire.users.User;
import com.cyfire.users.UserController;
import com.cyfire.users.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StevenTest {
    @InjectMocks
    UserController userController;

    @Autowired
    @Mock
    UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserByNetIDTest() {
        User steven = new User("Steven", "Sheets", "641-895-0122", "Male", "Software Engineering", "CS309isfun",
                "Sophomore", "smsheets", 19, 902138, 902138, true);

        userController.saveUser(steven);

        when(userController.usersRepository.findUserByNetID("smsheets")).thenReturn(steven);

        assertEquals(steven, userController.usersRepository.findUserByNetID("smsheets"));
    }

    @Test
    public void getAllUsersTest() {
        List<User> userList = new ArrayList<User>();

        User steven = new User("Steven", "Sheets", "641-895-0122", "Male", "Software Engineering", "CS309isfun",
                "Junior", "smsheets", 19, 9021348, 9021348, true);

        User andrew = new User("Andrew", "Marek", "666-420-8008", "Male", "Software Engineering", "CS309isfun",
                "Junior", "andmarek", 20, 1234567, 1234567, true);

        User jared = new User("Jared", "Weiland", "642-222-3333", "Male", "Software Engineering", "Esketit", "Junior",
                "jweiland", 19, 7654321, 7654321, true);

        User vamsi = new User("Vamsi", "Calpakkam", "757-816-5246", "Male", "Computer Science", "qwerty123", "Graduate",
                "vamsi", 19, 1234569, 1234568, false);

        addToUserList(userList, steven, andrew, jared, vamsi);

        saveAllTheUsers(steven, andrew, jared, vamsi);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> theUsers = userRepository.findAll();

        assertEquals(4, theUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    private void saveAllTheUsers(User steven, User andrew, User jared, User vamsi) { // Do not need userController as a parameter because it is a global variable.
        userController.saveUser(steven);
        userController.saveUser(andrew);
        userController.saveUser(jared);
        userController.saveUser(vamsi);
    }

    private void addToUserList(List<User> userList, User steven, User andrew, User jared, User vamsi) {
        userList.add(steven);
        userList.add(andrew);
        userList.add(jared);
        userList.add(vamsi);
    }

    @Test
    public void saveEnteredCodeTest() {
        User vamsi = new User("Vamsi", "Calpakkam", "757-816-5246", "Male", "Computer Science", "qwerty123", "Graduate",
                "vamsi", 19, 1234569, 1234568, false);

        when(userController.saveUser(vamsi)).thenReturn(vamsi.getNetID());

        userController.setUserEnteredCode(vamsi.getCode(), "vamsi");

        //	when(userController.ifCodesMatch("vamsi")).thenReturn(true);
    }

}
