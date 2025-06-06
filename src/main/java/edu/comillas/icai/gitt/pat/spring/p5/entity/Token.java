package edu.comillas.icai.gitt.pat.spring.p5.entity;

import jakarta.persistence.*;
import jakarta.websocket.OnError;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * TODO#3
 * Completa la entidad Token (cuya tabla en BD se llamará TOKEN)
 * para que, además de la clave primaria ya indicada (cadena autogenerada aleatoria representando la sesión),
 * tenga un campo appUser, que represente la asociación uno a uno con la entidad AppUser (el usuario asociado a la sesión).
 * Este campo deberá configurarse para que en caso de que se borre el usuario, también se borre su sesión asociada.
 */

@Entity
@Table(name = "TOKEN")
public class Token {
    @Id @GeneratedValue(strategy = GenerationType.UUID) public String id;

    @OneToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public AppUser appUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }



}
