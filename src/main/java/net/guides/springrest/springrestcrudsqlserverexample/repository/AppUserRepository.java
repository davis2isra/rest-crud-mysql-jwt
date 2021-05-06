package net.guides.springrest.springrestcrudsqlserverexample.repository;

import net.guides.springrest.springrestcrudsqlserverexample.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    public AppUser findByUsername(String userName);
}
