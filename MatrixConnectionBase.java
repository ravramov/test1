/*
 * Copyright (C) 2007 Datalex. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Datalex
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Datalex.
 *
 */
package com.datalex.matrix.adapters.connections;

import java.io.IOException;
import java.util.Properties;

import com.datalex.mw.bean.BeanBase;

/**
 * <B>Copyright (c) 2007 Datalex PLC. All Rights Reserved.</B><BR><BR>
 *
 * A abstract base class for MatrixConnection implementations.
 * 
 * @dlex:bean policy="MatrixConnectionBase" type="MatrixConnectionBase" singleton="false"
 * @dlex:bean-property name="ConnectTimeout"
 *                     value="com.sun.xml.internal.ws.connect.timeout" 
 *                     description="different JAX WS Impl has different values"
 * @dlex:bean-property name="RequestTimeout"
 *                     value="com.sun.xml.internal.ws.request.timeout" 
 *                     description="different JAX WS Impl has different values"
 *
 */
public abstract class MatrixConnectionBase extends BeanBase implements MatrixConnection
{
    /** Bean-property tag for Request_Timeout */
    private static final String REQUEST_TIMEOUT_PROPERTY_TAG = "RequestTimeout";
    /** Default Value for for Request_Timeout */
    private static final String REQUEST_TIMEOUT_DEFAULT_VALUE = "com.sun.xml.internal.ws.request.timeout";
    /** member variable for Request Timeout */
    private String m_reqTimeout;
    /** Bean-property tag for Connect_Timeout */
    private static final String CONNECT_TIMEOUT_PROPERTY_TAG = "ConnectTimeout";
    /** Default Value for for Connect_Timeout */
    private static final String CONNECT_TIMEOUT_DEFAULT_VALUE = "com.sun.xml.internal.ws.connect.timeout";
    /** member variable for Connect Timeout */
    private String m_conTimeout;
    /**
     * Initialize the connection.
     */
    @Override
    public void init()
    {
        super.init();
        m_conTimeout = getProperty(CONNECT_TIMEOUT_PROPERTY_TAG, CONNECT_TIMEOUT_DEFAULT_VALUE);
        m_reqTimeout = getProperty(REQUEST_TIMEOUT_PROPERTY_TAG, REQUEST_TIMEOUT_DEFAULT_VALUE);
    }
    /**
     * Provide JAX WS request timeout property name. 
     * The value is different depends on on different JAX WS impl.
     * Config the value in bean policy 
     * 
     * @return JAXWS request timeout property name
     */
    public final String getJAXWSRequestTimeoutPropertyName()
    {
        return m_reqTimeout;
    }
    /**
     * Provide JAX WS connection timeout property name. 
     * The value is different depends on on different JAX WS impl.
     * Config the value in bean policy 
     * 
     * @return JAXWS connection timeout property name
     */
    public final String getJAXWSConnectionTimeoutPropertyName()
    {
        return m_conTimeout;
    }
    /** The extended properties */
    private Properties m_extendedProps = new Properties();
    
    /**
     * Add an extended property.
     * 
     * @param name The name of the extended property.
     * @param value The value of the extended property.
     */
    public final void addExtendedProperty(String name, String value)
    {
        m_extendedProps.put(name, value);
    }
    
    /**
     * Get an extended property value.
     * 
     * 1) We first try to get the property from the extended propertes 
     * 2) If it is null then we try to get it from bean policies 
     * 3) If it is still null then we parse the property name to determine the new property name by removing all character up until 
     * and including the last '.' ie test.tester.Username will parse to Username. We try again to get it from bean policies. 
     * 
     * @param name The name of the extended property.
     * @return The value of the extended property or null if the property is not found.
     */
    public final String getExtendedProperty(String name)
    {
        String value = (String) m_extendedProps.get(name);
        
        // If the value is null then try to get the property from beanpolicies
        if (value == null)
        {
            value = getProperty(name);
        }

        // If the value is still null then parse the name ie if the name is test.tester.Username then the
        // propertyName will be Username. Then try to get the property from beanpolicies again with the new propertyName
        int index;

        if (value == null && name != null && (index = name.lastIndexOf(".")) != -1)
        {
            String propertyName = name.substring(index + 1, name.length());
            value = getProperty(propertyName);
        }
        
        return value;
    }
    
    /**
     * Get an array of all the property names.
     * 
     * @return The array of property names.
     */
    public final String[] getExtendedPropertyNames()
    {
        String[] retVal = new String[m_extendedProps.size()];
        return (String[]) m_extendedProps.keySet().toArray(retVal);
    }

    /**
     * Remove an extended property.
     * 
     * @param name The name of the extended property.
     */
    public final void removeExtendedProperty(String name)
    {
        m_extendedProps.remove(name);
    }

    /**
     * Clear all the extended properties.
     */
    public final void clearExtendedProperties()
    {
        m_extendedProps.clear();
    }
    
    /**
     * Get the source control version for this file.
     * 
     * @return <code>$Revision: 1.2 $, $Date: 2007/12/05 12:18:19 $";</code>
     */
    public static String getVersion()
    {
        return "$Revision: 1.2 $, $Date: 2007/12/05 12:18:19 $";
    }
    
    /**
   	 * unsupported operation for this implementation
   	 * @param data data to be sent to the host
   	 * @param attributes transaction attributes
   	 * @throws IOException if some error occurs
   	 * @return raw response from host
   	 * */
   	public byte[] sendAndReceive(byte[] data, TransactionAttributes attributes)
   			throws IOException 
   	{
   		throw new UnsupportedOperationException();
   	}
}
