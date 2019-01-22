## Web-to-Server Access Token

### Spec

 1. Access token must be a JWT ([https://jwt.io](https://jwt.io)), RFC-7519 standard.
 2. Using RS256 - RSA PKCS#1 signature with SHA-256 as the hashing algorithm.
 3. Longer RSA keys offer stronger protection against cracking. RSA recommends a key size of at least 2048 bits.
 4. Detailed meta in JWT
    
    - **algo**
      - `RS256`
    - **iss**
      - `urn:your_company_name`
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

 7. Please provide the RSA public key in advance, for FsTK to verify the JWT if it’s signed by the autority of your company.
 8. Please call to FsTK API with the access token follows the rules above.

## How to generate RSA 2048 bits key pair

    # rsa 2048 bits
    name=your_company_name
    openssl genrsa -des3 -out ${name}_rsa2048_private.pem 2048
    openssl rsa -in ${name}_rsa2048_private.pem -outform PEM -pubout -out ${name}_rsa2048_public.pem

## Example

    const fs = require('fs');
    const jwt = require('jsonwebtoken');
        
    const privateKey = fs.readFileSync(
    `${__dirname}/your_company_name_rsa2048_private.pem`,
    );
        
    // this is from import user api result
    const base64JWTId = 'C8OTMX3Cj08Rw6jCjlpvbMKAw4fCnMOj';
    const JWTId = Buffer.from(base64JWTId, 'base64').toString('utf-8');
    const web_to_server_access_token = jwt.sign({ uid: JWTId }, privateKey, {
      algorithm: 'RS256',
      keyid: '52344262-36b4-47d5-b654-d893ee6a2ed1',
      expiresIn: '7d',
      issuer: 'urn:your_company_name',
      audience: 'urn:fstk:engine',
      subject: 'urn:fstk:engine:access_token',
    });
        
    console.info(web_to_server_access_token);