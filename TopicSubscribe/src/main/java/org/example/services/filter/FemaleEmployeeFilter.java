package org.example.services.filter;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.example.model.Employee;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/hrTopic"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "gender = false")
})
public class FemaleEmployeeFilter implements MessageListener {

    Logger LOG = Logger.getLogger(MaleEmployeeFilter.class.getName());

    @Override
    public void onMessage(Message message) {

        try {
            Employee employee = message.getBody(Employee.class);
            LOG.info("Female filter");
            LOG.info(employee.toString());
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
