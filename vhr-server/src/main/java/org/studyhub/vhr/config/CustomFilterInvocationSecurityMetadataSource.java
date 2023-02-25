package org.studyhub.vhr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.studyhub.vhr.model.Menu;
import org.studyhub.vhr.model.Role;
import org.studyhub.vhr.service.MenuService;

import java.util.Collection;
import java.util.List;

/**
 * @author haoren
 * @create 2023-02-24 9:35
 * 根据url分析接口所需要的用户角色
 */
@Configuration
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    
    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher =  new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        String url = ((FilterInvocation) o).getRequestUrl();
        List<Menu> menus = menuService.getAllMenusWithRole();

        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), url)) {
                List<Role> roles = menu.getRoles();
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
