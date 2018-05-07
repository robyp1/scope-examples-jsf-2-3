package com.bluelotussoftware.example.boundary;

import com.bluelotussoftware.example.cdi.LogLevel;
import com.bluelotussoftware.example.cdi.LoggingMethod;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.regex.Pattern;

@Stateless
@Remote(EmailCheckRemote.class)
public class EmailCheckSecurity implements EmailCheckRemote{
//public class EmailCheckSecurity {

    public EmailCheckSecurity() {
    }

    @LoggingMethod(LogLevel.INFO)
    public Boolean checkMailInSecurity(String mail){
        Pattern compile = Pattern.compile("(\\w)+(@){1}(\\w)+(\\.)(\\w)+");
        return mail.matches(compile.pattern());
    }
}

