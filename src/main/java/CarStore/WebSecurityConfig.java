package CarStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import CarStore.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;




@Configuration
@ComponentScan("fi.haagahelia.course")
public class WebSecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
    		.authorizeRequests()
    			.antMatchers("/css/**", "/signup", "/saveuser", "/**").permitAll() // Enable css when logged out
    			.antMatchers("/h2-console/**").permitAll()
    			.anyRequest().authenticated()
    			.and()
    		.csrf().ignoringAntMatchers("/h2-console/**")
    		.and()
    		.headers().frameOptions().sameOrigin()
    		.and()
    		.formLogin()
	        	.loginPage("/login")
	        	.defaultSuccessUrl("/cars", true)
	        	.permitAll()
	        	.and()
	        .logout()
	        .logoutSuccessUrl("/cars")
	        	.permitAll()
	        	.and()
	        .httpBasic();
	        return http.build();
    }
     
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}