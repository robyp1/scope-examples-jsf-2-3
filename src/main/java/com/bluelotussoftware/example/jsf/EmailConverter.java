package com.bluelotussoftware.example.jsf;

import com.bluelotussoftware.example.boundary.Email;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "emailConverter", managed = true)
public class EmailConverter implements Converter<Email>{


    @Override
    public Email getAsObject(FacesContext context, UIComponent component, String value) {
        return new Email(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Email value) {
        return value.getEmail();
    }
}
