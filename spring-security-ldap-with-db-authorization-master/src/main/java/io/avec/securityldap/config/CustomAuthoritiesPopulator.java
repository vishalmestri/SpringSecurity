package io.avec.securityldap.config;



import io.avec.securityldap.data.authorities.Authorities;
import io.avec.securityldap.data.authorities.AuthoritiesRepository;
import io.avec.securityldap.data.user.MyUser;
import io.avec.securityldap.data.user.MyUserRepository;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import java.util.*;
import java.util.stream.Collectors;

public class CustomAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    private final AuthoritiesRepository authoritiesRepository;
    private final MyUserRepository myUserRepository;

    public CustomAuthoritiesPopulator(AuthoritiesRepository authoritiesRepository, MyUserRepository myUserRepository) {
    	System.out.println("CustomAuthoritiesPopulator-constructor called");
        this.authoritiesRepository = authoritiesRepository;
        this.myUserRepository = myUserRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations, String username) {
    	System.out.println("CustomAuthoritiesPopulator-getGrantedAuthorities called-start");

        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        final Optional<MyUser> user = myUserRepository.findByUsername(username);
        if(user.isPresent()) {
            final List<Authorities> authorities = authoritiesRepository.findAllByUser(user.get());
            System.out.println("CustomAuthoritiesPopulator-getGrantedAuthorities called-start1");
            grantedAuthorities = authorities.stream()
                    .map(authorities1 -> new SimpleGrantedAuthority(authorities1.getAuthority()))
            .collect(Collectors.toList());
        }
        System.out.println("CustomAuthoritiesPopulator-getGrantedAuthorities called-end");
        return grantedAuthorities;
    }
}
