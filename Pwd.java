import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;
import java.io.*;

public class Pwd {
	public static String Crypt(String pwd, String salt) throws Exception { // throws NoSuchAlgorithmException, NoSuchProviderException {
		try{
			// the encryption algorithm is MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// encrypt the password using the salt, and print it to the command line
			md.update(salt.getBytes());
			md.update(pwd.getBytes(), 0, pwd.length());
			String encrypt = new BigInteger(1, md.digest()).toString(16);
			return encrypt;
		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	} // end Encrypt
	
	public static String DictCrypt(String hash, String salt) throws FileNotFoundException, Exception {
		// dictionary
		File rockyou = new File("rockyou.txt");
		Scanner in = new Scanner(rockyou);

		// dictionary attack
		String pwd;
		String crypted;
		while (in.hasNext()) {
			pwd = in.next();
			crypted = Pwd.Crypt(pwd, salt);
			System.out.println(pwd + ": " + crypted);
			if (crypted.equals(hash)) return pwd;
		}
		return "could not decrypt";
	}
}