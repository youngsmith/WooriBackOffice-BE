package com.woori.wooribackoffice.security.constant;

public final class SecurityConstants {
    public static final String ROLE_CLAIMS = "rol";

    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60 * 60L;


    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    /**
     * The JWT signing key is hard-coded into the application code and should be stored in an environment variable or .properties file.
     */
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";


    private SecurityConstants() throws NoSuchMethodException {
        throw new NoSuchMethodException("Can not create SecurityConstants Object.");
    }
}
