package com.starbattle.client.testinterface.tester;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.starbattle.client.window.ContentView;
import com.starbattle.client.window.WindowContent;

public class ComponentFinder {

	private static JComponent foundComponent=null;

	
	public static JComponent findElement(String name, WindowContent content)
	{
		foundComponent=null;
		
	/*	searchComponent(name, (Container)content); // search in window
		ContentView view=content.getModalWindowViewer().getCurrentView();
		if(view!=null)
		{
		searchComponent(name, (Container)view.getView()); // search in modal windows	 
		}*/
		
		for(ContentView view: content.getViews().values())
		{
			searchComponent(name, (Container)view.getView()); 
		}
		
		return foundComponent;
	}
	
	public static void searchComponent(String name, Container c) {
		Component[] components = c.getComponents();

		for (Component com : components) {
			String cn = com.getName();

			if (cn != null) {
				if (cn.equals(name)) {
					foundComponent = (JComponent) com;
				}
			}
			if (com instanceof Container) {
				searchComponent(name, (Container) com);
			}
		}
	}
	
	

}
