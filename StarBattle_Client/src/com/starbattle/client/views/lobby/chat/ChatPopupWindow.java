package com.starbattle.client.views.lobby.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.starbattle.client.window.BorderMouseListener;
import com.starbattle.client.window.ContentPanel;
import com.starbattle.client.window.DesignWindowBorder;
import com.starbattle.client.window.WindowBorderListener;

public class ChatPopupWindow {

	private Dimension size;
	private JDialog window;

	public ChatPopupWindow(Dimension size, String title) {
		this.size = size;
		window = new JDialog();
		window.setUndecorated(true);
		window.setTitle(title);
		window.setSize(size);
		window.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		window.setModal(false);
		window.setResizable(false);
		window.setFocusable(true);
		window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void setContent(ChatContentPanel content)
	{
		//Add design border view and control
		DesignWindowBorder border=new DesignWindowBorder(window.getTitle());
		content.setBorder(border);
		BorderMouseListener borderMouseListener=new BorderMouseListener(border,new WindowListener());
		content.addMouseListener(borderMouseListener);
		content.addMouseMotionListener(borderMouseListener);
		window.setContentPane(content);
	}
	
	public void open()
	{
		if(!window.isVisible())
		{
		window.setVisible(true);
		}
		window.requestFocus();
		window.requestFocusInWindow();
	}

	public void init(ContentPanel parent) {
		Point ppos = parent.getLocationOnScreen();
		Dimension psize = parent.getSize();
		System.out.println(ppos);
		int x = ppos.x + psize.width / 2 - size.width / 2;
		int y = ppos.y + psize.height / 2 - size.height / 2;
		window.setLocation(x, y);
	}


	private class WindowListener implements WindowBorderListener{

		@Override
		public void closeWindow() {
			window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
		}

		@Override
		public void setLocation(int x, int y) {
			window.setLocation(x, y);
		}
		
	}


	public void close() {
		window.dispose();
	}
}
