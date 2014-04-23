package notif;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tnotif.Tool;
import app.Home;
import database.SQLManager;

/*
 *
 * @author Takylo 
 *
 *
*/


// le thread qui gere les notification
public class Notif extends Thread {

	private static int ID = Home.getServId();
	private static String NAME = Home.getServName();
	public static String SERV_NAME_VOTE = ""; // 

	public Notif(int servid,String servname){

		super(servname); // thread


	}
	public void run(){
		//while(true){
			Tool.ADD_PAUSE(10000);
			int idvote = 4; // ne pas toucher SVP
		int id = Home.getServId(); // guid du serv
		int valnotview = 0; // sa aussis pas touche
		final JFrame _NOTIF = new JFrame(); // frame
		_NOTIF.setResizable(false);
		_NOTIF.setUndecorated(true);
		_NOTIF.setSize(300,60);
		JPanel NOTIF_PANEL = new JPanel();
		NOTIF_PANEL.setBackground(new Color(0,0,0));
		SQLManager.setUpConnexion();
		try {
			// on selecte tout les vote
			ResultSet RECUP_NOTIF = SQLManager.executeQuery("SELECT * FROM vote WHERE guid = '"+id+"' ");
			System.out.print("Checking notif !");
			while(RECUP_NOTIF.next()){
				idvote = RECUP_NOTIF.getInt("id");
				SQLManager.executeUpdate("DELETE FROM vote WHERE id = '"+idvote+"' ");
			}
			System.out.print(idvote);
				     // la notif
			JLabel NEW_NOTIF = new JLabel("<html><p style='color:white;'>You have received a vote for the server \n <br> " + NAME + " ! \n  <br> Click for disappear this notification \n </p></html>");
		// la jframe/jpanel de la notif et tout le tralalal
			_NOTIF.add(NOTIF_PANEL);
			NOTIF_PANEL.add(NEW_NOTIF);
			_NOTIF.setVisible(true);

				NEW_NOTIF.addMouseListener(new MouseAdapter()  //si il clique sur la notif
				{  
					public void mouseReleased(MouseEvent e)  
					{   
 _NOTIF.setVisible(false); // on la fait disparaitre
}
});

			} catch (SQLException e) {
				e.getMessage();
			Tool.Alert("Error","Error with code BF964D",2); // error ?
		}
	}
//}
	public static void setServNameVote(String serv){ // set
		SERV_NAME_VOTE = serv;
	}
	public static String getServNameVote(){ // get
		return SERV_NAME_VOTE;
	}
}
