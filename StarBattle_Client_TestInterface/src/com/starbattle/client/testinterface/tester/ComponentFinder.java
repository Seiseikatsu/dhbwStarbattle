package com.starbattle.client.testinterface.tester;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;

public class ComponentFinder {

	
	private static JComponent foundComponent;

	public static void findElement(String name, Container c) {
		Component[] components = c.getComponents();

		for (Component com : components) {
			String cn = com.getName();

			if (cn != null) {
				if (cn.equals(name)) {
					foundComponent = (JComponent) com;
				}
			}
			if (com instanceof Container) {
				findElement(name, (Container) com);
			}
		}
	}
	
	public static JComponent getFoundComponent() {
		return foundComponent;
	}
	
}
