# Rahul Niraula-(614604)
## Token Refresh Mechanism.
I Went with the following approach::
1. User Sends both Access Token and Refresh Token in every request.
2. The filter in the backend performs the following check.
    * if both the tokens are present in the header
        * if access token is expired and refresh token is still valid
            * extract username from refresh token and obtain **userdetails** using the username.
            * Generate new access token and refresh token
            * Set the user to the authentication context.
            * Attach both of the tokens to **response header**
            * In the client side, set up an interceptor to extract **accessToken** and **refreshToken** and send the updated tokens thereafter.
          

### This way, the client just needs to send both of the tokens in the header and intercept the response to check if there is any updated token sent by the server.

# Implementation Logic.
```java
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            token=authorizationHeader.substring(7);
            try{
                email= jwtUtil.getUsernameFromToken(token);
                System.out.println("Auth User email "+email+"--"+token);
            }catch (ExpiredJwtException e){
                System.out.println("JWT is expired now try refresh token");
                String isRefreshToken=request.getHeader("isRefreshToken");
                if(isRefreshToken!=null){
                    try{
                        email= jwtUtil.getUsernameFromToken(isRefreshToken);
                        System.out.println("In Refresh part");
                        var userDetails=userDetailsService.loadUserByUsername(email);
                        token= jwtUtil.generateToken(userDetails);
                        var refreshToken=jwtUtil.generateRefreshToken(userDetails.getUsername());
                        response.setHeader("isRefreshHeader",refreshToken);
                        response.setHeader("accessToken",token);
                    }catch (ExpiredJwtException ex){
                        System.out.println("Refresh token is also expired"+ex.getMessage());
                    }
                }
            }
        }
```
