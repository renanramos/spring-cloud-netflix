package br.com.renanrramossi.auth;

import br.com.renanrramossi.auth.core.domain.Permission;
import br.com.renanrramossi.auth.core.domain.User;
import br.com.renanrramossi.auth.infra.delegate.UserDelegate;
import br.com.renanrramossi.auth.interfaceadapter.repository.PermissionRepository;
import br.com.renanrramossi.auth.interfaceadapter.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableWebSecurity
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	CommandLineRunner init(final UserRepository userRepository, final PermissionRepository permissionRepository,
			final BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args -> initUsers(userRepository, permissionRepository, bCryptPasswordEncoder);
	}

	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		Permission permission = null;

		final Permission permissionByDescription = permissionRepository.findByDescription("Admin");

		if (permissionByDescription == null) {
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		} else {
			permission = permissionByDescription;
		}

		User admin = new User();
		admin.setUsername("renanrramossi");
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnabled(true);
		admin.setPassword(bCryptPasswordEncoder.encode("123456"));
		admin.setPermissions(List.of(permission));

		User find = userRepository.findByUserName("renanrramossi");

		if (find == null) {
			userRepository.save(admin);
		}
	}

}
