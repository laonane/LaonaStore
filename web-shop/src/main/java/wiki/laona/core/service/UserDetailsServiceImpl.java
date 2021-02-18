package wiki.laona.core.service;

import com.google.common.base.Strings;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import wiki.laona.core.pojo.seller.Seller;
import wiki.laona.service.SellerService;

import java.util.ArrayList;

/**
 * @description: UserDetailService实现类
 * @author: laona
 * @create: 2021-02-09 16:31
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 激活状态
     */
    private final String STATUS_ACTIVE = "1";

    /**
     * 通过 bean 注入，需要提供 setter 方法
     */
    @Setter
    private SellerService sellerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 权限集合
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        // 认证
        if (Strings.isNullOrEmpty(username)) {
            return null;
        }
        // 查询商家是否存在
        Seller seller = sellerService.findUser(username);
        if (seller != null && STATUS_ACTIVE.equals(seller.getStatus())) {
            // 返回用户名、密码、所有权限的用户信息集合
            // {noop} 表示不需要验证（不加密）
            // return new User(username, "{noop}" + seller.getPassword(), authorities);
            return new User(username, seller.getPassword(), authorities);
        }
        return null;
    }
}
