package net.guides.springrest.springrestcrudsqlserverexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name",length = 255)
    private String username;

    @Column(name = "password",length = 255)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<AppUserClaims> claims;

   public AppUser() {
       claims = new ArrayList<>();
   }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AppUserClaims> getClaims() {
        return claims;
    }

    public void setClaims(List<AppUserClaims> claims) {
        this.claims = claims;
    }
}
