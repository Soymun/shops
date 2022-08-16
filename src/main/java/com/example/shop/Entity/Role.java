package com.example.shop.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role{
    Buyer(Set.of(Permission.BUY)),
    Salesman(Set.of(Permission.BUY, Permission.SELL)),
    Admin(Set.of(Permission.BUY, Permission.SELL, Permission.ADMIN));

    final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> simpleGrantedAuthorities() {
        return permissions.stream().map(n -> new SimpleGrantedAuthority(n.getPermission())).collect(Collectors.toSet());
    }
}
