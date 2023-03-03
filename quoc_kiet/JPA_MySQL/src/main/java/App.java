import com.Entity.User;
import com.Entity.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);

        userRepository.findAll()
                .forEach(System.out::println);

        User user = userRepository.save(new User());
        System.out.println("User vừa lưu có ID: " + user.getId());
        Long userId = user.getId();

        user.setAgi(100);
        userRepository.save(user);


        User user2 = userRepository.findById(userId).get();
        System.out.println("User: " + user);
        System.out.println("User2: " + user2);


        userRepository.delete(user);

        User user3 = userRepository.findById(userId).orElse(null);
        System.out.println("User3: " + user2);

    }

}