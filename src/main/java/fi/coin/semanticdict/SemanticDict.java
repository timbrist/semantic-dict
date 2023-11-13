package fi.coin.semanticdict;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import fi.coin.semanticdict.repository.RoleRepository;
import fi.coin.semanticdict.entity.Role;
@SpringBootApplication
public class SemanticDict {
    public static void main(String[] args) {
        SpringApplication.run(SemanticDict.class, args);
    }

    @Bean
    public CommandLineRunner demo(RoleRepository roleRepo) {
        return (args) -> {
            Role role=new Role();
            role.setName("ROLE_ADMIN");
            roleRepo.save(role);
        };
    }
}
