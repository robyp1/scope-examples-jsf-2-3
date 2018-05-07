package com.bluelotussoftware.example.jsf;


import com.bluelotussoftware.example.boundary.Email;
import com.bluelotussoftware.example.boundary.EmailCheckRemote;
import com.bluelotussoftware.example.boundary.EmailCheckSecurity;
import com.bluelotussoftware.example.cdi.Configuration;
import com.bluelotussoftware.example.cdi.LogLevel;
import com.bluelotussoftware.example.cdi.LoggingMethod;
import com.bluelotussoftware.example.cdi.ServiceLocator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

@FacesValidator(value="emailValidator",managed = true)
public class EmailValidator implements Validator<Email> {


//    @EJB
    EmailCheckRemote checker;

    @Inject
    Configuration conf;

    @LoggingMethod(LogLevel.INFO)
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Email email) throws ValidatorException {
        String applicationName = conf.getConf().getProperty("app.name");
        checker = ServiceLocator.locateService(EmailCheckSecurity.class, applicationName);
        if (!checker.checkMailInSecurity(email.getEmail())){
            throw new ValidatorException(new FacesMessage(null, "this is not a valid mail!"));
        }
    }
}
