package com.bluelotussoftware.example.jsf;

import com.bluelotussoftware.example.boundary.Email;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("emailBean")
@ViewScoped
public class EmailBean implements Serializable{


    private static final long serialVersionUID = 3247395658736113405L;

    private Email email;

    public EmailBean() {
        this.email = new Email("");
    }


    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void sendEmail(){
        System.out.println("Email sended");
    }
}
