package net.guides.springrest.springrestcrudsqlserverexample.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UserClaim")
public class AppUserClaims {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @Column(name = "claim_type")
    @NotEmpty
    public String claimType;

    @Column(name = "claim_value")
    @NotNull
    public Boolean claimValue;

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Boolean getClaimValue() {
        return claimValue;
    }

    public void setClaimValue(Boolean claimValue) {
        this.claimValue = claimValue;
    }
}
