package com.pdnguyen.cnnwebapp.Utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Utility methods
 */
public class WebUtils {

    /**
     * Retrieving the Authorities of a User
     * @param user
     * @return
     */
    public static String toString(User user) {
        StringBuilder data = new StringBuilder();

        data.append("UserName: ").append(user.getUsername());
        Collection<GrantedAuthority> authorityCollection = user.getAuthorities();

        if (!authorityCollection.isEmpty()) {
            data.append(" (");
            for (GrantedAuthority authority : authorityCollection) {
                data.append(authority.getAuthority()).append(", ");
            }
            data.delete(data.length() - 2, data.length());
            data.append(")");
        }
        return data.toString();
    }
}
