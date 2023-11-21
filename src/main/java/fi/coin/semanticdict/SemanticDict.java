package fi.coin.semanticdict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import fi.coin.semanticdict.repository.RoleRepository;
import fi.coin.semanticdict.entity.Role;
import fi.coin.semanticdict.entity.User;
import fi.coin.semanticdict.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class SemanticDict {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SemanticDict.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleRepository roleRepo) {
        return (args) -> {
            Role role=new Role();
            role.setName("ROLE_ADMIN");
            roleRepo.save(role);

            Role role2=new Role();
            role2.setName("USER");
            roleRepo.save(role2);
        };
    }
    @Bean
    public CommandLineRunner initUser(UserRepository userRepo) {
        return (args) -> {

            User user = new User();
            user.setUserName("yansh");
            user.setEmail("yansh@student.jyu.fi");
            user.setPassword(passwordEncoder.encode("yansh"));
            Role roles = roleRepository.findByName("ROLE_ADMIN").get();
            user.setRoles(Collections.singleton(roles));
            user.setName("Yan");
            userRepo.save(user);

        };
    }
}
