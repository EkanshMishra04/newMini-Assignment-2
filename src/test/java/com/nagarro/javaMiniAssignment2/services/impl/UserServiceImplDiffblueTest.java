package com.nagarro.javaMiniAssignment2.services.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nagarro.javaMiniAssignment2.exceptions.CustomException;
import org.junit.jupiter.api.Test;

class UserServiceImplDiffblueTest {
    /**
     * Method under test: {@link UserServiceImpl#createUserWithExecutor(Integer)}
     */
    @Test
    void testCreateUserWithExecutor() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       org.springframework.web.reactive.function.client.WebClient$Builder
        //   See https://diff.blue/R027 to resolve this issue.

        assertTrue((new UserServiceImpl()).createUserWithExecutor(0).isEmpty());
        assertThrows(CustomException.class, () -> (new UserServiceImpl()).createUserWithExecutor(400));
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUserAfterValidations(String, String, String, String)}
     */
    @Test
    void testGetAllUserAfterValidations() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       org.springframework.web.reactive.function.client.WebClient$Builder
        //   See https://diff.blue/R027 to resolve this issue.

        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserAfterValidations("9", "asc", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserAfterValidations("42", "asc", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserAfterValidations("name", "9", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserAfterValidations("name", "even", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserAfterValidations("name", "odd", "Limit", "Offset"));
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUserListWithPaginationInfo(String, String, String, String)}
     */
    @Test
    void testGetAllUserListWithPaginationInfo() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       org.springframework.web.reactive.function.client.WebClient$Builder
        //   See https://diff.blue/R027 to resolve this issue.

        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserListWithPaginationInfo("9", "asc", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserListWithPaginationInfo("42", "asc", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserListWithPaginationInfo("name", "9", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserListWithPaginationInfo("name", "even", "Limit", "Offset"));
        assertThrows(CustomException.class,
                () -> (new UserServiceImpl()).getAllUserListWithPaginationInfo("name", "odd", "Limit", "Offset"));
    }
}

