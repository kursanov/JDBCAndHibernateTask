package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        System.out.println(userDaoJdbc.createUsersTable());
        userDaoJdbc.dropUsersTable();

            userDaoJdbc.saveUser("sdsdv", "dfvffdvvd", 12);
            userDaoJdbc.saveUser("Zarip", "kursan", 22);
            userDaoJdbc.saveUser("Zarip", "kurssan", 22);

        System.out.println(userDaoJdbc.getAllUsers());
        userDaoJdbc.removeUserById(11);
        userDaoJdbc.cleanUsersTable();


    }
}
