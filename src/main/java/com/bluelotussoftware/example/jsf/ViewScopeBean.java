/*
 *  Copyright 2012 Blue Lotus Software, LLC..
 *  Copyright 2012 John Yeary <jyeary@bluelotussoftware.com>.
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id: ViewScopeBean.java,v 5bbb4560fac5 2012/04/20 16:16:54 jyeary $
 */
package com.bluelotussoftware.example.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import javax.inject.Named;

/**
 *
 * @author John Yeary <john.yeary@bluelotussoftware.com>
 * @version 1.0
 */
@Named //@Named al posto di ManagedBean da provare solo con jsf 2.3
@ViewScoped
public class ViewScopeBean implements Serializable {

    private static final long serialVersionUID = 3152248670694285690L;
    private List<String> names;

    public ViewScopeBean() {
        names = new ArrayList<String>();
    }

    @PostConstruct
    private void initialize() {
        names.add("John");
        names.add("Ethan");
        names.add("Sean");
        names.add("Patty");
        names.add("Java");
        names.add("Duke");
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void delete(String name) {
        names.remove(name);
    }
}
