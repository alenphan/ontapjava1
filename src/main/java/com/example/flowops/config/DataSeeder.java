package com.example.flowops.config;

import com.example.flowops.model.userModel;
import com.example.flowops.repository.userRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(userRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                String[][] data = {
                        {"Leanne Graham", "Bret", "Sincere@april.biz"},
                        {"Ervin Howell", "Antonette", "Shanna@melissa.tv"},
                        {"Clementine Bauch", "Samantha", "Nathan@yesenia.net"},
                        {"Patricia Lebsack", "Karianne", "Julianne.OConner@kory.org"},
                        {"Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca"},
                        {"Mrs. Dennis Schulist", "Leopoldo_Corkery", "Karley_Dach@jasper.info"},
                        {"Kurtis Weissnat", "Elwyn.Skiles", "Telly.Hoeger@billy.biz"},
                        {"Nicholas Runolfsdottir V", "Maxime_Nienow", "Sherwood@rosamond.me"},
                        {"Glenna Reichert", "Delphine", "Chaim_McDermott@dana.io"},
                        {"Clementina DuBuque", "Moriah.Stanton", "Rey.Padberg@karina.biz"}
                };
                for (String[] u : data) {
                    userModel user = new userModel();
                    user.setName(u[0]);
                    user.setUsername(u[1]);
                    user.setEmail(u[2]);
                    repo.save(user);
                }
            }
        };
    }
}
