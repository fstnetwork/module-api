# Generate Access tokens

## Server-to-Server access token

### Spec

 1. Access token must be a JWT ([https://jwt.io](https://jwt.io)), RFC-7519 standard.
 2. Using RS256 - RSA PKCS#1 signature with SHA-256 as the hashing algorithm.
 3. Longer RSA keys offer stronger protection against cracking. RSA recommends a key size of at least 2048 bits.
 4. Detailed meta in JWT
    
    - **algo**
      - `RS256`
    - **iss**
      - `urn:qonect`
    - **aud**
      - `urn:fstk:engine`
    - **sub**
      - `urn:fstk:engine:s2s_token`
    - **kid** 
      - Public key id provided by FsTK, after public key is registered in FsTK
    - **exp**
      - Must not exceed 1 minute (re-generate token every api request for safety)

 5. Please provide the RSA public key in advance, for FsTK to verify the JWT if it’s from you.
 6. Please call to FsTK API with the access token follows the rules above.

## Web-to-Server access token

### Spec

 1. Access token must be a JWT ([https://jwt.io](https://jwt.io)), RFC-7519 standard.
 2. Using RS256 - RSA PKCS#1 signature with SHA-256 as the hashing algorithm.
 3. Longer RSA keys offer stronger protection against cracking. RSA recommends a key size of at least 2048 bits.
 4. Detailed meta in JWT
    
    - **algo**
      - `RS256`
    - **iss**
      - `urn:qonect`
    - **aud**
      - `urn:fstk:engine`
    - **sub**
      - `urn:fstk:engine:access_token`
    - **kid** 
      - Public key id provided by FsTK, after public key is registered in FsTK
    - **exp**
      - Must not exceed 7 days

 5. Required payload in JWT
    
    - **uid**

      -  FsTK provided user's id (FsTK defined db id, after the importing end user)

 7. Please provide the RSA public key of Qonect in advance, for FsTK to verify the JWT if it’s from Qonect.
 8. Please call to FsTK API with the access token follows the rules above.

## How to generate RSA 2048 bits key pair

    # rsa 2048 bits
    name=qonect
    openssl genrsa -des3 -out ${name}_rsa2048_private.pem 2048
    openssl rsa -in ${name}_rsa2048_private.pem -outform PEM -pubout -out ${name}_rsa2048_public.pem
