package tnotif;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.Login;
import notif.Notif;
import app.Home;

/*
 *
 * @author Takylo
 *
 *
*/
public class App {


	public App(){

		// flemme de tout commencer ses la jframe principale
		final JPanel HOME_PANEL = new JPanel();
		final JFrame _app = new JFrame();
		JLabel COPYRIGH = new JLabel("Developpe par Takylo");
		final JPanel LOGIN_PANEL = new JPanel(); 
		final JButton LOGIN_BTN = new JButton(Res.ICON_USER); 
		Tool.UNDECORATED_BTN(LOGIN_BTN);
		LOGIN_PANEL.setBackground(new Color(0,0,0));
		LOGIN_PANEL.setLayout(new BorderLayout());
		LOGIN_PANEL.add(LOGIN_BTN,BorderLayout.CENTER);
        LOGIN_PANEL.add(COPYRIGH,BorderLayout.NORTH);
		_app.add(LOGIN_PANEL);
		_app.setSize(800,500);
		_app.setLocationRelativeTo(null);
		_app.setResizable(false);
		_app.setVisible(true);
		_app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LOGIN_BTN.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if(Login.login() == true){
					_app.remove(LOGIN_PANEL);  
					_app.add(Home.HOME_PANEL());
					_app.validate();

					Notif notif_thread = new Notif(Home.getServId(),Home.getServName());
					notif_thread.start();

				}else{
					System.exit(0);
				}

			}
		});
	}
}