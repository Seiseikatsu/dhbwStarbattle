package com.starbattle.client.views.error;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.main.error.ConnectionErrorListener;
import com.starbattle.client.resource.ClientConfiguration;
import com.starbattle.client.resource.ResourceLoader;
import com.starbattle.client.window.ContentView;

public class ConnectionErrorView extends ContentView {

	public final static int VIEW_ID = -2;

	private Dimension windowSize = new Dimension(400, 300);
	private ConnectionErrorListener connectionErrorListener;

	private JButton close = new DesignButton("Close");
	private JButton reconnect = new DesignButton("Reconnect");
	private JButton changeIP = new DesignButton("Change IP");
	private static JTextArea errorInfo=new JTextArea();
	
	public ConnectionErrorView(ConnectionErrorListener connectionErrorListener) {
		this.connectionErrorListener = connectionErrorListener;
		initLayout();
	}

	public static void setErrorInfo(Exception e)
	{
		String error=e.getMessage()+"\n";
		StackTraceElement[] stackTraceElements=e.getStackTrace();
		int depth=5;
		for(int i=0; i<depth; i++)
		{
			if(i<stackTraceElements.length)
			{
			StackTraceElement element=stackTraceElements[i];
			error+="  > "+element.getClassName()+"."+element.getMethodName()+"()"+"\n";
			}
		}
		if(stackTraceElements.length>=depth)
		{
			error+="  > ...";
		}
		errorInfo.setText(error);
	}
	
	private void initLayout() {
		view.setLayout(new BorderLayout());
		JLabel title=new DesignLabel("Connection Error",ResourceLoader.loadIcon("disconnect_icon.png",96,96),new Color(50,50,50),26);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		view.add(title,BorderLayout.NORTH);
		view.add(new JScrollPane(errorInfo),BorderLayout.CENTER);
		errorInfo.setForeground(new Color(200,0,0));
		errorInfo.setEditable(false);
		errorInfo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		JPanel bot = new JPanel();
		bot.add(reconnect);
		bot.add(changeIP);
		bot.add(close);
		view.add(bot, BorderLayout.SOUTH);

		changeIP.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String ip=JOptionPane.showInputDialog("IP Address:",ClientConfiguration.get().get("server"));
				if(ip!=null)
				{
				ClientConfiguration.get().setProperty("server", ip);
				ClientConfiguration.saveProperties();
				connectionErrorListener.tryReconnect();
				}			
			}
		});
		
		reconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionErrorListener.tryReconnect();
			}
		});

		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connectionErrorListener.exit();
			}
		});

	}

	@Override
	protected void initView() {
		resizeWindow(windowSize);
	}

	@Override
	protected void onClosing() {

	}

	@Override
	public int getViewID() {
		return VIEW_ID;
	}

}
