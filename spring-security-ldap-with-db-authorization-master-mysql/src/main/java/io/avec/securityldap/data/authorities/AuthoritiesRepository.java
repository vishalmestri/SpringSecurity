package io.avec.securityldap.data.authorities;

import io.avec.securityldap.data.user.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    List<Authorities> findAllByUser(MyUser user);
}
