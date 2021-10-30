package org.example.clinicalapp;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.example.clinicalapp.model.Patient;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/ClinicalQueue") })
public class EligibilityListener implements MessageListener {

    private static final Logger LOG = Logger.getLogger(EligibilityListener.class.getName());

    @Override
    public void onMessage(Message message) {

        try {
            boolean isEligibile = message.getBooleanProperty("isEligibile");
            Patient patient = message.getBody(Patient.class);
            LOG.info(String.format("==========>> patient:\n%s\nis eligibile = %s", patient, isEligibile));
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
