package br.com.renanrramossi.auth;

import br.com.renanrramossi.auth.core.domain.Permission;
import br.com.renanrramossi.auth.core.domain.User;
import br.com.renanrramossi.auth.interfaceadapter.repository.PermissionRepository;
import br.com.renanrramossi.auth.interfaceadapter.repository.UserRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@Slf4j
public class Application {

	public static final String USERNAME = "renanrramossi";
	public static final String RAW_PASSWORD = "123456";
	public static final String ADMIN = "ADMIN";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner init(final UserRepository userRepository, final PermissionRepository permissionRepository,
			final BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args -> initUsers(userRepository, permissionRepository, bCryptPasswordEncoder);
	}

	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		Permission permission = null;

		final Permission permissionByDescription = permissionRepository.findByDescription(ADMIN);

		if (permissionByDescription == null) {
			permission = new Permission();
			permission.setDescription(ADMIN);
			permission = permissionRepository.save(permission);
		} else {
			permission = permissionByDescription;
		}

		User admin = new User();
		admin.setUsername(USERNAME);
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnabled(true);
		admin.setPassword(bCryptPasswordEncoder.encode(RAW_PASSWORD));
		admin.setPermissions(List.of(permission));

		User find = userRepository.findByUserName(USERNAME);

		if (find == null) {
			userRepository.save(admin);
		}

		var user = userRepository.findByUserName(USERNAME);

		log.info("user found {}", user.toString());

	}

}
