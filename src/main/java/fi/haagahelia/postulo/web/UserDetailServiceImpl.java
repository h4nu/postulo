package fi.haagahelia.postulo.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import fi.haagahelia.postulo.domain.User;
import fi.haagahelia.postulo.domain.UserRepository;
import fi.haagahelia.postulo.domain.Role;

/**
 * This class was used by spring security to authenticate and authorize user
 **/
@Service
// public class UserDetailServiceImpl implements UserDetailsService {
public class UserDetailServiceImpl {
	private UserRepository userRepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	


	/*
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User curruser = urepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
	}
	*/

}
