package com.paterake.elexon.ingest;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;


public class PushDataLoader extends BaseDataLoader {

    private static final String URL = "ssl://api.bmreports.com:61616"; // This is the connection string to the ELEXON servers
    private static final String APIKEY = ELEXON_PORTAL_KEY; // This is your API key from the portal
    private static final String CLIENTID = "name@mail.com"; // This is a client name that needs to be unique (this you create)
    private static final String TOPICNAME = "bmrsTopic"; // This is the topic name
    private static final String SUBSCRIPTIONID = "subrak1"; // Each durable subscription needs an ID that is unique (this you create)

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Connection connection;
    private Session session;
    private MessageConsumer messageConsumer;
    private static PushDataLoader subscriberPublishSubscribe;

    public static void main(String[] args) throws Exception {
        try {
            // Setup and connect to the queue
            subscriberPublishSubscribe = new PushDataLoader();
            subscriberPublishSubscribe.create(URL, APIKEY, CLIENTID, TOPICNAME, SUBSCRIPTIONID);
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage());
            if (subscriberPublishSubscribe != null) {
                subscriberPublishSubscribe.closeConnection();
            }
        }
    }

    public void create(String url, String apikey, String clientId, String topicName, String subId) throws JMSException {
        // create a Connection Factory
        ConnectionFactory factory = new ActiveMQConnectionFactory(apikey, apikey, url);

        try {
            // create a Connection
            LOGGER.debug("Creating a connection");
            connection = factory.createConnection();
            connection.setClientID(clientId);

            // Create a Session
            LOGGER.debug("Creating a session");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create the Topic from which messages will be received
            LOGGER.debug("Creating the topic connection: " + topicName);
            Topic topic = session.createTopic(topicName);

            // Set up the message consumer
            LOGGER.debug("Creating the consumer for: " + topicName);
            //messageConsumer = session.createConsumer(topic);
            messageConsumer = session.createDurableSubscriber(topic, subId);
            // Create the listener.
            LOGGER.debug("Setting up the listener");
            JMSMessageListener listener = new JMSMessageListener();
            messageConsumer.setMessageListener(listener);
            // start the connection in order to receive messages
            LOGGER.debug("Starting the connection");
            connection.start();
        } catch (JMSException exp) {
            throw exp;
        }
    }

    public void closeConnection() throws JMSException {
        LOGGER.debug("Closing the connection");
        connection.close();
    }

    /**
     * This class implements a message listener for the ActiveMQ
     */
    class JMSMessageListener implements MessageListener {
        @Override
        public void onMessage(Message msg) {

            try {
                LOGGER.info(msg.toString());
                ActiveMQTextMessage txtMessage = (ActiveMQTextMessage) msg;
                LOGGER.info(txtMessage.getText());
                try (PrintWriter out = new PrintWriter(new FileWriter(txtMessage.getJMSMessageID()))) {
                    out.print(txtMessage.getText());
                }
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage());
            }
        }
    }
}