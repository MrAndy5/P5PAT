package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private Hashing hashing;

    @Override
    public Token login(String email, String password) {
        //busco Usuario correcto
        /*AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null || !appUser.getPassword().equals(password)) {
            return null;
        }
        //Busco token
        Token token = null;
        if (token != null) return token;
        //Si no existe, creo uno nuevo
        token = new Token();
        token.setId(UUID.randomUUID().toString());
        token.setAppUser(appUser);
        return tokenRepository.save(token);*/
        //TODO#6
        //TODO#14
        //busco Usuario correcto
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null || !hashing.compare(appUser.getPassword(), password)) {
            return null;
        }

        //Busco token
        Token token = tokenRepository.findByAppUser(appUser);
        if (token != null) return token;

        //Si no existe, creo uno nuevo
        token = new Token();
        token.setId(UUID.randomUUID().toString());
        token.setAppUser(appUser);
        //Guardo el token en la base de datos
        return tokenRepository.save(token);
    }
    @Override
    public AppUser authentication(String tokenId) {
        //busco el token para autenticarlo
        Token token = tokenRepository.findById(tokenId).orElse(null);
        return token != null ? token.getAppUser() : null;
    }
    @Override
    public ProfileResponse profile(AppUser appUser) {
        //busco el usuario
        ProfileResponse profileResponse = new ProfileResponse(appUser.getEmail(),
                appUser.getName(), appUser.getRole());
        return profileResponse;
    }
    @Override
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile) {
        //Actualizar usuario
        appUser.setName(profile.name());
        appUser.setRole(profile.role());
        appUserRepository.save(appUser);
        return profile(appUser);
    }
    @Override
    public ProfileResponse profile(RegisterRequest register) {
        /*//Crear usuario
        AppUser appUser = new AppUser();
        appUser.setEmail(register.email());
        appUser.setName(register.name());
        appUser.setPassword(register.password());
        appUser.setRole(register.role());
        appUserRepository.save(appUser);
        return profile(appUser);
        TODO#6*/
        //TODO#14
        //Crear usuario
        AppUser appUser = new AppUser();
        appUser.setEmail(register.email());
        appUser.setName(register.name());
        //Hasheo el password
        appUser.setPassword(hashing.hash(register.password()));
        appUser.setRole(register.role());
        appUserRepository.save(appUser);
        return profile(appUser);
    }
    @Override
    public void logout(String tokenId) {
        //Cerrar sesión y borrar token de sesión
        tokenRepository.deleteById(tokenId);
    }
    @Override
    public void delete(AppUser appUser) {
        //Borrar usuario
        appUserRepository.delete(appUser);
    }

}
