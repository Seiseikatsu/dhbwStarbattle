package com.starbattle.client.views.reset;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.starbattle.client.connection.NetworkConnection;
import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.window.ContentView;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.objects.NP_ResetEmail;

public class ResetPasswordView extends ContentView {

	public final static int VIEW_ID = 2;

	
	private ResetPasswordModel model = new ResetPasswordModel();
	private JButton backButton = new DesignButton("Back");
	private JButton resetButton = new DesignButton("Send Email");
	private SendServerConnection sendConnection;

	public ResetPasswordView(NetworkConnection connection) {

		windowSize=new Dimension(400,400);
		
		sendConnection = connection.getSendConnection();
		view.setLayout(new BorderLayout());
		view.setBackground(new Color(170,110,40));
		
		JLabel title = new DesignLabel("Reset Password",25);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(title.getFont().deriveFont(20f));
		view.add(title, BorderLayout.NORTH);
		view.add(model.getView(), BorderLayout.CENTER);

		JPanel footer = new JPanel();
		footer.setOpaque(false);
		footer.add(backButton);
		footer.add(resetButton);
		view.add(footer, BorderLayout.SOUTH);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openView(LoginView.VIEW_ID);
			}
		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetEmail();
			}
		});
	}

	private void resetEmail() {
		NP_ResetEmail reset = new NP_ResetEmail();
		reset.name = model.getUserName();
		reset.email = model.getEmail();
		if(!reset.name.isEmpty()&&!reset.email.isEmpty())
		{
		sendConnection.sendTCP(reset);
		}
		//return 
		openView(LoginView.VIEW_ID);
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
