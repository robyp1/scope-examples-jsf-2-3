package com.bluelotussoftware.example.jsf;

import com.bluelotussoftware.example.cdi.LoggingMethod;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class FeedbackBean implements Serializable{

    private static final Logger logger = Logger.getLogger(FeedbackBean.class.getName());
    private String lastSave;


    public FeedbackBean() {
    }

    public String getLastSave() {
        return lastSave;
    }

    public void setLastSave(String lastSave) {
        this.lastSave = lastSave;
    }

    @LoggingMethod
    public void send(){

        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String user = params.get("user");
        String feedback = params.get("feedback");

        lastSave = new Date(new GregorianCalendar().getTimeInMillis()).toString();
        logger.log(Level.INFO, "Feedback saved at {0} !", lastSave);
        logger.log(Level.INFO, "Saved by: {0} \n Content: {1}", new String[]{
                user, feedback
        });

    }
}
