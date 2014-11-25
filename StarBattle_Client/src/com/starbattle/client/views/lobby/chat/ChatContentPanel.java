package com.starbattle.client.views.lobby.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.starbattle.client.layout.DesignButton;
import com.starbattle.client.layout.DesignTextField;
import com.starbattle.client.layout.VerticalLayout;

public class ChatContentPanel extends JPanel{

	private WriteMessageListener writeMessageListener;
	private String friend;
	private JTextField input;
	private JPanel content=new JPanel();
	private DesignButton send=new DesignButton("Send");
	
	public ChatContentPanel(String to, WriteMessageListener writeMessageListener)
	{
		send.setButtonStyle(1);
		this.friend=to;
		this.writeMessageListener=writeMessageListener;
		content.setLayout(new VerticalLayout());
		initLayout();
	}
	
	private void initLayout()
	{
		ActionListener write=new Write();
		input=new DesignTextField(write);
		input.setName("Chat_Text");
		send.addActionListener(write);
		send.setName("Chat_Send");
		content.setBackground(new Color(180,180,180));
		this.setLayout(new BorderLayout());
		JScrollPane scroll=new JScrollPane(content, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scroll,BorderLayout.CENTER);
		JPanel bottom=new JPanel();
		bottom.setBackground(new Color(50,50,50));
		bottom.setLayout(new BorderLayout());
		bottom.add(input,BorderLayout.CENTER);
		bottom.add(send,BorderLayout.EAST);
		this.add(bottom,BorderLayout.SOUTH);
		
	}
	
	public void receive( String text)
	{
		addMessage(text, true);
	}
	
	private void addMessage(String text, boolean fromFriend)
	{
		ChatMessageDesign message=new ChatMessageDesign(text, fromFriend);
		content.add(message);
		content.revalidate();
		this.repaint();
		
	}
	
	private void write(String text)
	{
		addMessage(text, false);
		writeMessageListener.writeMessage(friend,text);
	}
	
	private class Write implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String text=input.getText();
			if(!text.isEmpty())
			{
				input.setText("");
				write(text);
			}
		}
		
	}
	
	public JPanel getChatContent() {
		return content;
	}
}
