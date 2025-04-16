package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test void saveTest() {
        // Given: dado ususario y token de sesión
        AppUser user = new AppUser();
        user.setName("name");
        user.setEmail("email@email.com");
        user.setRole(Role.USER);
        user.setPassword("password23");

        // Guardar el usuario
        AppUser savedUser = appUserRepository.save(user);
        // crear el token
        Token token = new Token();
        token.setId(UUID.randomUUID().toString());
        token.setAppUser(savedUser);
        // Guardar el token
        Token savedToken = tokenRepository.save(token);

        // When ...

        // Consultar el token por el usuario
        // y el usuario por el email
        AppUser userByEmail = appUserRepository.findByEmail(savedUser.getEmail());
        Token tokenByUser = tokenRepository.findByAppUser(savedUser);

        // Then:Comprobar que ambos están correctamente guardados
        Assertions.assertNotNull(userByEmail);
        Assertions.assertEquals(savedUser.getEmail(), userByEmail.getEmail());

        Assertions.assertNotNull(tokenByUser);
        Assertions.assertEquals(savedToken.getId(), tokenByUser.getId());


    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test void deleteCascadeTest() {
        // Given ...
        AppUser user = new AppUser();
        user.setName("name");
        user.setEmail("email@email.com");
        user.setRole(Role.USER);
        user.setPassword("password23");

        // Guardar el usuario
        AppUser savedUser = appUserRepository.save(user);

        // crear el token
        Token token = new Token();
        token.setId(UUID.randomUUID().toString());
        token.setAppUser(savedUser);

        // Guardar el token
        Token savedToken = tokenRepository.save(token);

        // When ...
        // Borrar el usuario
        appUserRepository.delete(savedUser);


        // Then ...
        Assertions.assertEquals(0, appUserRepository.count());
        Assertions.assertEquals(0, tokenRepository.count());

    }
}