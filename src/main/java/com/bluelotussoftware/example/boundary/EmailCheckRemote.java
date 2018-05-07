package com.bluelotussoftware.example.boundary;

import javax.ejb.Remote;

public interface EmailCheckRemote {

    public Boolean checkMailInSecurity(String mail);
}
