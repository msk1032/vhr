package org.studyhub.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.studyhub.vhr.mapper.HrMapper;
import org.studyhub.vhr.model.Hr;

import java.util.Objects;

/**
 * @author haoren
 * @create 2023-02-22 18:57
 */
@Service
public class HrService implements UserDetailsService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    HrMapper hrMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (Objects.isNull(hr)) {
            throw new UsernameNotFoundException("Username Not Found");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    public void login(Hr hr) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(hr.getUsername(), hr.getPassword(),null));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }
}
