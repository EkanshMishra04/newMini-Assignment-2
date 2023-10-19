package com.nagarro.javaMiniAssignment2.helper;

import com.nagarro.javaMiniAssignment2.dto.UserResponse;
import com.nagarro.javaMiniAssignment2.models.Dob;
import com.nagarro.javaMiniAssignment2.models.Name;
import com.nagarro.javaMiniAssignment2.models.Registered;
import com.nagarro.javaMiniAssignment2.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void jsonToEntityMapper() {
        UserResponse response=getUserReponse();
        User user =Helper.jsonToEntityMapper(response);
        assertEquals(response.getNat(),user.getNationality());
        assertEquals(response.getRegistered().getAge(),user.getAge());
        System.out.println(response);
        System.out.println(user);
    }

    private UserResponse getUserReponse() {
        UserResponse userResponse=new UserResponse();
        userResponse.setCell("cell");
        userResponse.setName(new Name());
        userResponse.setPhone("phone");
        userResponse.setNat("nat");
        Dob dob=new Dob();
        dob.setAge(25);
        dob.setDate("29/01/1996");
        dob.setAdditionalProperty("dummy","dummy");
        userResponse.setDob(dob);
        Registered registered=new Registered();
        registered.setAge(25);
        registered.setDate("29/01/1996");
        registered.setAdditionalProperty("dummy","dummy");
        userResponse.setRegistered(registered);
        return userResponse;
    }
}