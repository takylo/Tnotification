package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import login.Login;
import tnotif.Res;
import tnotif.Tool;
import database.SQLManager;

/*
 *
 * @author Takylo
 *
 *
*/

public class Home {

	static int SERV_ID;
	static String SERV_NAME;
	static String SERV_DESC;
	static String SERV_SITE;
	static int SERV_VOTE;
	static String SERV = null;	
	public static Component HOME_PANEL(){


		int id = Login.getIdMember();
		String mail = Login.getEmailMember();
		String account = Login.getAccountMember();

		JLabel SERV_MORE = null;
		ResultSet SERVEUR_MEMBER = null;
		try {
			SERVEUR_MEMBER = SQLManager.executeQuery("SELECT * FROM top WHERE guid = '"+id+"'  ");
		} catch (SQLException e) {
			// erreur
		}
		try {
			while(SERVEUR_MEMBER.next()){
				setServId(SERVEUR_MEMBER.getInt("id"));
				setServName(SERVEUR_MEMBER.getString("name"));
				setServDesc(SERVEUR_MEMBER.getString("text"));
				setServSite(SERVEUR_MEMBER.getString("web"));
              // setServVote(SERVEUR_MEMBER.getString(""));
				SERV = "Votre serveur " + getServName() + "\n Site : " + getServUrl() + " \n Votre description : " + getServDesc() + " \n ---------------- \n";

				SERV_MORE = new JLabel(Res.ICON_MORE_INFORMATION_SERV_USER);
			}
		} catch (SQLException e) {
			// erreur
		}
		System.out.print("ID : " + id + " Mail : " + mail + " account " + account);
		JLabel _AccountInfo = new JLabel("<html><p style='color:white;font-family:Arial;margin-top:10px;margin-left:10px;'>Account Information | Email : "+mail+" , Account : "+account+"</p></html>");

		JPanel HOME = new JPanel();
		HOME.setLayout(new BorderLayout());
		HOME.add(_AccountInfo,BorderLayout.NORTH);
		HOME.setBackground(new Color(0,0,0));
		HOME.add(SERV_MORE,BorderLayout.CENTER);

		SERV_MORE.addMouseListener(new MouseAdapter()  
		{  
			public void mouseReleased(MouseEvent e)  
			{   
				Tool.Alert("-- Your Serveur --",SERV,1);
			}
		});

		return HOME;
	}

	public static void setServId(int servid){
		SERV_ID = servid;
	}
	public static void setServName(String servname){
		SERV_NAME = servname;
	}	
	public static void setServDesc(String servdesc){
		SERV_DESC = servdesc;
	}
	public static void setServSite(String url){
		SERV_SITE = url;
	}
	public static void setServVote(int vote){
		SERV_VOTE = vote;
	}
	public static int getServId(){
		return SERV_ID;
	}
	public static String getServName(){
		return SERV_NAME;
	}
	public static String getServDesc(){
		return SERV_DESC;
	}
	public static String getServUrl(){
		return SERV_SITE;
	}
}
