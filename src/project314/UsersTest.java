package project314;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {
    @Test
    void correctStaffIdAndCorrectStaffPassword(){
        Users u = new Users();
        assertTrue(u.login("staff01","staff01"));
    }

    @Test
    void correctOwnerIdAndCorrectOwnerPassword(){
        Users u = new Users();
        assertTrue(u.login("owner01","owner01"));
    }

    @Test
    void correctManagerIdAndCorrectManagerPassword(){
        Users u = new Users();
        assertTrue(u.login("manager01","manager01"));
    }

    @Test
    void correctAdminIdAndCorrectAdminPassword(){
        Users u = new Users();
        assertTrue(u.login("useradmin01","useradmin01"));
    }

    @Test
    void correctIdAndWrongPassword(){
        Users u = new Users();
        assertFalse(u.login("staff01","wrongpassword01"));
    }

    @Test
    void wrongIdAndCorrectPassword(){
        Users u = new Users();
        assertFalse(u.login("wrongid01","staff01"));
    }

    @Test
    void wrongIdAndWrongPassword(){
        Users u = new Users();
        assertFalse(u.login("wrongid01","wrongpassword01"));
    }

    @Test
    void checkRoleStaff(){
        Users u = new Users();
        assertEquals("Staff", u.checkRole("staff05"));
    }

    @Test
    void checkRoleOwner(){
        Users u = new Users();
        assertEquals("Owner", u.checkRole("owner01"));
    }

    @Test
    void checkRoleManager(){
        Users u = new Users();
        assertEquals("Manager", u.checkRole("manager01"));
    }

    @Test
    void checkRoleAdmin(){
        Users u = new Users();
        assertEquals("Manager", u.checkRole("manager01"));
    }

    @Test
    void wrongRole(){
        Users u = new Users();
        assertNotEquals("Manager", u.checkRole("staff01"));
    }
}