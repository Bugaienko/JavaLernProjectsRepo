package ua.bugaienko.FirstSecurityApp.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Sergii Bugaienko
 */

@Service
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void duAminStuff() {
        System.out.println("Can do only admin");
    }
}
