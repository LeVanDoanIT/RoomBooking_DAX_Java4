package com.fpoly.Utils;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;

public class MaHoa {

	    public static String hashPassword(String password) {
	        String salt = BCrypt.gensalt(12);

	        // Hash the password
	        String hashedPassword = BCrypt.hashpw(password, salt);

	        return hashedPassword;
	    }

	    public static boolean verifyPassword(String password, String hashedPassword) {
	        // Check if the provided password matches the hashed password
	        return BCrypt.checkpw(password, hashedPassword);
	    }

	    public static void main(String[] args) {
	        String password = "myPassword123";

	        // Hash the password
	        String hashedPassword = hashPassword(password);
	        System.out.println("Hashed Password: " + hashedPassword);

	        // Verify a password
	        boolean passwordMatches = verifyPassword("myPassword123", hashedPassword);
	        System.out.println("Password Matches: " + passwordMatches);
    	
//	    	String salt = "hjgashjgs;bdhghdjfgbhdjatweiovjyj";
//	    	String result = null;
//	    	
//	    	password = password + salt;
//	    	
//	    	try {
//				byte[] dataBytes = password.getBytes("UTF-8");
//				MessageDigest md = MessageDigest.getInstance("SHA-1");
//				result = Base64.encodeBase64String(md.digest(dataBytes));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	    	return result;
	    }
	    
	}


