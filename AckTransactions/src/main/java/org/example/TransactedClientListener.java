package org.example;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/wordTopic")
})
public class TransactedClientListener implements MessageListener {

    Logger LOG = Logger.getLogger(TransactedClientListener.class.getName());

    @Override
    public void onMessage(Message message) {

        try {
            TextMessage text = (TextMessage) message;
            LOG.info("\n==========> received: " + text.getText());

        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
