package univ.tuit.backend.store.jpo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import univ.tuit.backend.domain.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class UserJpo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "surname")
    private String surname;

    @Column(unique = true, nullable = false, name = "phoneNumber")
    private String phoneNumber;

    @Column(nullable = false, name = "carNumber")
    private String carNumber;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<RoleJpo> roles = new HashSet<>();

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public UserJpo(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public UserJpo(User user, RoleJpo roles) {
        BeanUtils.copyProperties(user, this);
        this.roles.add(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public static List<User> toDomain(List<UserJpo> userJpoList) {
        return userJpoList.stream().map(UserJpo::toDomain).collect(Collectors.toList());
    }

    public User toDomain() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        Set<String> setOfRoles = new HashSet<>();
        for (RoleJpo roleJpo : this.roles) {
            setOfRoles.add(roleJpo.getName());
        }
        user.setRoles(setOfRoles);
        return user;
    }

  /*  public static UserJpo fromDomain(User user) {
        UserJpo jpo = new UserJpo();
        BeanUtils.copyProperties(user, jpo);
//        BeanUtils.copyProperties(user.getRoles(), jpo.roles);
       *//* for (Role role: user.getRoles()) {
            jpo.getRoles().add(new RoleJpo(role.getRoleName()));
        }*//*
        return jpo;
    }*/
}
