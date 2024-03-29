package io.avec.securityldap.data.authorities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import io.avec.securityldap.data.user.MyUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.apache.commons.lang3.builder.ToStringBuilder;



@Getter
@Setter
@NoArgsConstructor
@Entity
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private MyUser user;

    private String authority;

    public Authorities(MyUser user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("user", user)
                .append("authority", authority)
                .toString();
    }
}
