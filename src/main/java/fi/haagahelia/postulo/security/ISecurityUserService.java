package fi.haagahelia.postulo.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
