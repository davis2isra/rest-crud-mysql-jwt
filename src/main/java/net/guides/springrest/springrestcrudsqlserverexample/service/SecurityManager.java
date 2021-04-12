package net.guides.springrest.springrestcrudsqlserverexample.service;

import net.guides.springrest.springrestcrudsqlserverexample.model.AppUser;
import net.guides.springrest.springrestcrudsqlserverexample.model.AppUserAuth;
import net.guides.springrest.springrestcrudsqlserverexample.model.AppUserClaims;
import net.guides.springrest.springrestcrudsqlserverexample.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityManager {

    @Autowired
    private AppUserRepository repository;

    public AppUserAuth validateUser(AppUser user) {

        AppUserAuth ret = new AppUserAuth();
        AppUser authUser = null;

        authUser = repository.findByUserName(user.getUserName());


        if(authUser != null) {
            ret = this.buildUserAuthObject(authUser);
        }

        return ret;
    }

    public List<AppUserClaims> getUserClaims(AppUser authUser) {
        List<AppUserClaims> list = new ArrayList<>();
        AppUser usuario = null;

        Optional<AppUser> appUserOptional = repository.findById(authUser.getUserId());

        if(appUserOptional.isPresent()) {
            usuario = appUserOptional.get();
            list = usuario.getClaims();
        }
        return list;

    }

    public AppUserAuth buildUserAuthObject(AppUser authUser) {

        AppUserAuth ret = new AppUserAuth();
        List<AppUserClaims> claims = new ArrayList<>();

        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        // Set las propiedades del usuario
        ret.setUserName(authUser.getUserName());
        ret.setIsAuthenticated( true );
        ret.setBearerToken(randomUUIDString);

        // Get all claims
        claims = getUserClaims(authUser);

        // Loop all claims and set properties to
        // user object

        for ( AppUserClaims claim: claims) {
            switch (claim.claimType) {
                case "canListar":
                    ret.setCanListar(claim.getClaimValue());
                    break;
                case "canAgregar"    :
                    ret.setCanAgregar(claim.getClaimValue());
                    break;
                case "canEditar"    :
                    ret.setCanEditar(claim.getClaimValue());
                    break;
                case "canBorrar"    :
                    ret.setCanBorrar(claim.getClaimValue());
                    break;
                case "canVerDetalle"   :
                    ret.setCanVerDetalle(claim.getClaimValue());
                    break;
            }

        }

        return ret;
    }

}
