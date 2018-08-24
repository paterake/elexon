package com.paterake.elexon.transform.transformer;

import com.paterake.elexon.model.DataHeader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;

public class TransformHeader {

  public DataHeader getHeader(String systemId, InputStream inputStream)
      throws XMLStreamException, JAXBException, IOException {

    XMLInputFactory factory = XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLStreamReader(systemId, inputStream);

    while (reader.hasNext() && (!reader.isStartElement() || !reader.getLocalName()
        .equals("responseHeader"))) {
      reader.next();
    }
    if (reader.getEventType() == 1) {
      JAXBContext ucontext = JAXBContext.newInstance(DataHeader.class);
      Unmarshaller unmarshaller = ucontext.createUnmarshaller();
      JAXBElement<DataHeader> data = unmarshaller.unmarshal(reader, DataHeader.class);

      reader.close();
      inputStream.close();

      return data.getValue();
    } else {
      reader.close();
      inputStream.close();
      return null;
    }
  }
}
