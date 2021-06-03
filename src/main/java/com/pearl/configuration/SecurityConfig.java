package com.pearl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pearl.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceImpl member;
	
	@Autowired
	private LoginFailureHandler loginFailureHandler;

//	private Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/scss/**");
    }
    
    private AjaxAwareAuthenticationEntryPoint ajaxAwareAuthenticationEntryPoint(String url) {
    	return new AjaxAwareAuthenticationEntryPoint(url);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
//            .and()
//                .csrf()
//                .ignoringAntMatchers("/loginProcessing")
//                .ignoringAntMatchers("/login")
            .and() // 로그인 설정
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProcessing")
                .defaultSuccessUrl("/mypage")
                //.failureForwardUrl("/login")
                .usernameParameter("memEmail")
                .passwordParameter("memPass")
                .permitAll()
                //.failureHandler(loginFailureHandler)
            .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
            .and()
                // 403 예외처리 핸들링
                .exceptionHandling()
                .authenticationEntryPoint(ajaxAwareAuthenticationEntryPoint("/login"))
                .accessDeniedPage("/denied");
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(member).passwordEncoder(passwordEncoder());
    }
	
}
