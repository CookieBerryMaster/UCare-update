package org.example.UCare.filter;

import org.example.UCare.util.Global;
import org.openxava.filters.BaseContextFilter;
import org.openxava.filters.FilterException;

public class FilterEstudiante extends BaseContextFilter {
    @Override
    public Object filter(Object o) throws FilterException {
       // String cif = (String)get("naviox.user");
        String cif = Global.userName;
        //String cif = "21011790";
        return new Object [] { cif };
    }
}
