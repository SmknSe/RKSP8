package com.example.composer.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Builder
public class AuthUser implements OAuth2User, UserDetails {

    @Getter
    private UUID id;
    private String name;
    private  Map<String, Object> attributes;
    private Set<GrantedAuthority> authorities;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AuthUser that = (AuthUser) obj;

        if (!getId().equals(that.getId())) {
            return false;
        }
        if (!getName().equals(that.getName())) {
            return false;
        }
        if (!getAuthorities().equals(that.getAuthorities())) {
            return false;
        }
        return this.getAttributes().equals(that.getAttributes());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAuthorities().hashCode();
        result = 31 * result + getAttributes().hashCode();
        return result;
    }

    @Override
    @SuppressWarnings("StringBufferReplaceableByString")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: [");
        sb.append(getId());
        sb.append("], Name: [");
        sb.append(getName());
        sb.append("], Granted Authorities: [");
        sb.append(getAuthorities());
        sb.append("], User Attributes: [");
        sb.append(getAttributes());
        sb.append("]");
        return sb.toString();
    }
}
