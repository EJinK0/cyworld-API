package ask.anything.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //private final JwtTokenProvider jwtTokenProvider;
    //private final CorsFilter       corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) //csrf disable
            .formLogin(AbstractHttpConfigurer::disable) //From 로그인 방식 disable
            .httpBasic(AbstractHttpConfigurer::disable) //http basic 인증 방식 disable
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/v3/api-docs/**",
                        "/configuration/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/auth/signUp"
                ).permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
            ) // 경로별 인가 작업
            .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 설정

        return http.build();
    }

    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        JwtFilter jwtFilter = new JwtFilter(jwtTokenProvider);
//
//        http.csrf()
//            .disable()
//            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//            .exceptionHandling()
//
//            // 세션을 사용하지 않기 때문에 STATELESS로 설정
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//            .and()
//            .authorizeRequests()
//            //            .antMatchers("/login", "/auth/**")
//            .antMatchers("/**")
//            .permitAll()
//            .antMatchers("/swagger-ui/**")
//            .permitAll()
//
//            .anyRequest()
//            .authenticated()
//
//            .and()
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }

}