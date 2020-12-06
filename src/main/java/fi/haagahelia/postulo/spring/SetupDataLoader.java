package fi.haagahelia.postulo.spring;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fi.haagahelia.postulo.domain.PrivilegeRepository;
import fi.haagahelia.postulo.domain.Requirement;
import fi.haagahelia.postulo.domain.RequirementRepository;
import fi.haagahelia.postulo.domain.RoleRepository;
import fi.haagahelia.postulo.domain.Type;
import fi.haagahelia.postulo.domain.TypeRepository;
import fi.haagahelia.postulo.domain.UserRepository;
import fi.haagahelia.postulo.domain.Privilege;
import fi.haagahelia.postulo.domain.Role;
import fi.haagahelia.postulo.domain.User;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger log = LoggerFactory.getLogger(SetupDataLoader.class);

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges
        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        final Privilege passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");

        // == create initial roles
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege, passwordPrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, passwordPrivilege));
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        // == create initial user
        createUserIfNotFound("test@test.com", "Test", "Test", "test", new ArrayList<>(Arrays.asList(adminRole)));

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String email, final String firstName, final String lastName, final String password, final Collection<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }
    
    @Bean
	public CommandLineRunner requirementDemo(RequirementRepository rrepository, TypeRepository trepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of categories");
			
			trepository.save(new Type("Functional"));
			trepository.save(new Type("Non-functional"));
			trepository.save(new Type("Feature"));
			trepository.save(new Type("Epic"));
			trepository.save(new Type("Theme"));
			trepository.save(new Type("User story"));
			
			// lists requirement types created
			for (Type type : trepository.findAll()) {
				if(type == null) { 
				log.info("empty db");
				
				} else {
				log.info(type.getName().toString());	
				}
			}
			
			log.info("save a couple of requirements");
			// these generates the requirements multiple times
			//rrepository.save(new Requirement("reqid", "type", "summary", "rationale", "priority", "source", "owner", "rdate"));
			//rrepository.save(new Requirement("foobar-0001", trepository.findByName("Functional").get(0), "A sample requirement summary", "a sample rationale for testing the requirement database", "Must have", "Sample source", "John Owner", "2020-10-30"));
			rrepository.save(new Requirement("foobar-0001", trepository.findByName("Functional").get(0), "A sample requirement summary", "a sample rationale for testing the requirement database", "Must have", "Sample source", "John Owner", LocalDate.of(2020, 10, 18)));
			rrepository.save(new Requirement("foobar-0002", trepository.findByName("Functional").get(0), "Another test requirement", "Yet another rationale for testing the requirement app", "Could Have", "Sample source", "John Owner",LocalDate.of(2020, 11, 28)));
		
			log.info("fetch all requirements");
			for (Requirement requirement : rrepository.findAll()) {
				log.info(requirement.toString());
			}
			// Create users: admin/admin user/user
			// System.out.println();
			
	
			// These are already generated once to heroku-postgresql so these needs to be commented out...
			// however, the test will fail if johndoe will not be created manually
			// urepository.save(new User("user", "$2a$10$KkBleYKXVS.U72vsMeAgmeIMyl7Z985YNETMrZicCoLUH4QnYfkNm", "user@foo.bar", "USER") );
            // urepository.save(new User("admin", "$2a$10$7TmD6APcS5O08dkAnf340e.H0JzlN3j8iffXhiSGsq6eKFx6S8BlS", "admin@foo.bar", "ADMIN"));
            // urepository.save(new User("johndoe", "$2a$10$mrFtIoSfeBQE9EPwgoRUYeXBp.CbkX4Xw6OinxKooc3rewtxDhz8C", "john@doe.bar", "USER"));
            log.info("Create users");
            for (User user : urepository.findAll()) {
				log.info(user.getLastName().toString());
			}

		};
	}

}
