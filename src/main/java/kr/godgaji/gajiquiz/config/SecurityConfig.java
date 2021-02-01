package kr.godgaji.gajiquiz.config;

import kr.godgaji.gajiquiz.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * WebSecurityConfigurerAdapter를 상속받은 클래스에 @EnableWebSecurity를 명시하는것만으로도
 * springSecurityFilterChain이 자동으로 포함된다.
 */
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/templates/**", "/templates/**");
    }


    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/myinfo/**").hasAnyRole("USER","ADMIN")
//                .antMatchers("/", "/login/**").permitAll()
                .antMatchers("/h2-console/*").permitAll()
                .anyRequest().permitAll() //배포시 아래와 .authenticated()로 교체
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .logout()
                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //user/**은 authenticated인 사용자로 구분할 수 있으니까
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/loginSuccess")
                .userInfoEndpoint()  /**OAuth2 로그인 성공 후 "사용자 정보를 가져올 때의 설정"들을 담당**/
                .userService(customOAuth2UserService);
        /**소셜 로그인 성공 시 후속조치를 시행할 UserService 인터페이스의 구현체를 등록합니다.
         소셜 서비스에서 사용자 정보를 가져온 후 진행할 새로운 기능에 대해 기재할 수 있다고합니다.**/
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties properties,
                                                                     @Value("${spring.security.oauth2.client.registration.kakao.client-id}") String kakaoClientId,
                                                                     @Value("${spring.security.oauth2.client.registration.kakao.client-secret}") String kakaoClientSecret) {
        List<ClientRegistration> registrations = new ArrayList<>();

        // 카카오 OAuth2 정보 추가(vendor마다 변경될 수 있는 정보들을 따로 입력해준다)
        registrations.add(
                CustomOAuth2Provider.KAKAO.getBuilder("kakao")
                        .clientId(kakaoClientId)
                        .clientSecret(kakaoClientSecret)
                        .jwkSetUri("tmp")
                        .build()
        );

        return new InMemoryClientRegistrationRepository(registrations);
    }

}
