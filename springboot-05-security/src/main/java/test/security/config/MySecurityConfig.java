package test.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()   // 根路径都能访问
        .antMatchers("/level1/**").hasRole("VIP1")
        .antMatchers("/level2/**").hasRole("VIP2")
        .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能,如果没有登录则没有权限就会来到登录页面
        //http.formLogin();
        // 设定自己的登录页
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/login");
        // 1.login请求来到登录页
        // 2.重定向到/login/error表示登录失败
        // 3.更多详细规则

        // 开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");    //注销成功以后来到首页
        // 1.访问/logout表示用户注销并清空session
        // 2.注销成功会返回/login?loginout

        // 开启记住我功能
        // 登录成功以后将cookie发送给浏览器,以后都带上这个cookie,只要通过检查就可以免登录
        http.rememberMe().rememberMeParameter("remember");
    }


    @Override
    // 定义认证规则
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * nMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())",这相当于登陆时用BCrypt加密方式对用户密码进行处理.
         * 以前的".password("123")" 变成了 ".password(new BCryptPasswordEncoder().encode("123"))",这相当于对内存中的密码进行Bcrypt编码加密.如果比对时一致,说明密码正确,才允许登陆.
         *
         */
      auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
              .withUser("aaa").password(new BCryptPasswordEncoder().encode("123")).roles("VIP1","VIP2")
              .and().withUser("bbb").password(new BCryptPasswordEncoder().encode("123")).roles("VIP3","VIP2")
              .and().withUser("ccc").password(new BCryptPasswordEncoder().encode("123")).roles("VIP3","VIP1");
    }
}
