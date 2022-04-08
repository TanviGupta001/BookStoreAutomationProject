package mainEngine;

import org.apache.commons.codec.binary.Base64;


public class EncryptDecryptEngine {
	
	public static void main(String[] args)
	{	
		//---PUT YOUR STRING HERE AND GET ENCYPTED TEXT---
		String str="tanvi";
		
		byte[] encodedString=Base64.encodeBase64(str.getBytes());
		System.out.println("Encoded String is :- "+new String (encodedString));
		
		byte[] decodeString=Base64.decodeBase64(encodedString);
		System.out.println("Decode String is :- "+new String(decodeString));
	
	}		
}
