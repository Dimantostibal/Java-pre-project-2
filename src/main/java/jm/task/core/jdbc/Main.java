package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Dmitriy", "Eskov", (byte) 20);
        userService.saveUser("Bob", "Marley", (byte) 55);
        userService.saveUser("Chester", "Bennington", (byte) 41);
        userService.saveUser("Cristopher", "Nolan", (byte) 60);

        userService.getAllUsers();

        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.getAllUsers();

        userService.dropUsersTable();
    }
}
