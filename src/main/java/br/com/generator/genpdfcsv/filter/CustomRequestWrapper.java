package br.com.generator.genpdfcsv.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomRequestWrapper extends HttpServletRequestWrapper {

    public CustomRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(final String name)
    {

        String[] strings = getParameterMap().get(name);
        if (strings != null)
        {
            String result = strings[0];
            return result;
        }

        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap()
    {
        //Return an unmodifiable collection because we need to uphold the interface contract.
        return super.getParameterMap();
    }

    @Override
    public Enumeration<String> getParameterNames()
    {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(final String name)
    {
        //TODO alterar o valor

        if (name.equals("param")) {
            String[] values = getParameterMap().get(name);
            values[0] = values[0];
            return values;
        }

        return getParameterMap().get(name);
    }
}
