package login;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import tnotif.Crypt;
import tnotif.Tool;
import database.SQLManager;

/*
 *
 * @author Takylo
 *
 *
*/

/* LOGIN SYSTEME */

public class Login {
	public static boolean CHECK_IS_FIRT_UTILISATION(){ //  a supprimer [A VOIR | PEUT SERVIR]
		File config = new File("data/config.tnotif"); // sert juste a verifier que data/config.tnotif existe
 		if(config.exists()){  // mais un fichier de config sera peut etre pas utilisé
 			return true;
 		}else{
 			return false;
 		}
 	}
 	static int ID_MEMBER;
 	static String EMAIL_MEMBER ; 
 	static String ACCOUNT_MEMBER;
 	public static boolean login() {

 		JOptionPane instal = new JOptionPane();
		// formulaire de login
 		String account  = instal.showInputDialog(null, "Account King RPG", "Account", JOptionPane.QUESTION_MESSAGE);
 		String password = instal.showInputDialog(null, "Password King RPG" , "Password", JOptionPane.QUESTION_MESSAGE);
 		String hashpass = Crypt.encode(password); // on hash le mdp en md5
 		if(	password.toString().isEmpty()){
 			Tool.Alert("Error","Invalid information :( retry again !",2); // si il a pas tout remplie
 				System.exit(0);
 			}else{
 				SQLManager.setUpConnexion();
 				String pass = "";
 				ResultSet login_procedure = null;

 				try {
						// la query
 					login_procedure = SQLManager.executeQuery("SELECT * FROM membre WHERE login = '"+account+"' ");

						while( login_procedure.next() ){ // on recuperer les donnée de l'account
						pass = login_procedure.getString("pass_md5");
						setIdMember(login_procedure.getInt("id"));
						setEMailMember(login_procedure.getString("mail")); 
						setAccountMember(login_procedure.getString("login"));
					}
					} catch (SQLException e) { // why not ?
						Tool.Alert("Error","Error please contact admin :( !",2);
					}

				if(hashpass.equals(pass)){ // Si les password sont egale
					// System.out.print("Mdp saisi : " + hashpass + " Mdp dans la bdd " + pass); TEST DEBUG
					return true;
				}else{ // si il a pas tout remplie
					Tool.Alert("Error","Invalid information :( retry again !",2);
						return false;
					}
				}
				return true;
			}

			// SETTER AND GETTER
			public static void setIdMember(int id){ // set id
				ID_MEMBER = id; 
			}
			public static int getIdMember(){ // get id
				return ID_MEMBER;
			}
			public static void setEMailMember(String email){ // set email
				EMAIL_MEMBER = email;
			}
			public static String getEmailMember(){ // get email
				return EMAIL_MEMBER;
			}
			public static void setAccountMember(String account){ // set account
				ACCOUNT_MEMBER = account;
			}
			public static String getAccountMember(){ // get account
				return ACCOUNT_MEMBER;
			}
		}