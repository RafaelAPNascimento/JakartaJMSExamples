package org.example.services;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.example.model.Employee;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/hrTopic") })
public class SecurityService implements MessageListener {

    Logger LOG = Logger.getLogger(SecurityService.class.getName());

    @Override
    public void onMessage(Message message) {

        try {
            Employee employee = message.getBody(Employee.class);
            LOG.info("new employee on the Topic\n" + employee);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
