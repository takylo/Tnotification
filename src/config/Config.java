package config;

import java.io.BufferedReader;
import java.io.FileReader;

import tnotif.Tool;
/*
 *
 * @author Takylo
 *
 *
*/
public class Config {

	private static String ACCOUNT_PASS;
	private static String ACCOUNT_USER;
	private static final String CONFIG_FILE = "data/config.tnotif";
	static BufferedReader config;

	public static boolean LOAD_CONFIG(){
		
		try {
			config = new BufferedReader(new FileReader(CONFIG_FILE));
			String line = "";
			while ((line=config.readLine())!=null)
			{
				if(line.split("=").length == 1) continue ;
				String param = line.split("=")[0].trim();
				String value = line.split("=")[1].trim();
				if(param.equalsIgnoreCase("USER"))
				{		
					setUser(value);
				}else if(param.equalsIgnoreCase("PASS")){
					setPass(value);
				}
			}
			return true;
		}catch(Exception e){
			Tool.Alert("Erreur","Impossible de charger le fichier de configuration :(",2);
		}
		return false;
	}
	public static void setPass(String pass){
		ACCOUNT_PASS = pass;
	}
	public static void setUser(String user){
		ACCOUNT_USER = user;
	}
	public static String getPass(){
		return ACCOUNT_USER;
	}

	public static String getUser(){
		return ACCOUNT_PASS;
	}



}