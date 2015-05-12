package com.starbattle.server.modes;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ModesReader {

	public static final File path = new File("resource/modes.xml");

	public ModesReader() {

	}

	public ModeSettings readSettings() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ModeSettings.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (ModeSettings) jaxbUnmarshaller.unmarshal(path);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void writeSettings(ModeSettings settings) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ModeSettings.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(settings, path);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
