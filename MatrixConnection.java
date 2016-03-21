/*
 * Copyright (C) 2004 Datalex. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Datalex
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * licence agreement you entered into with Datalex.
 *
 * $Log: MatrixConnection.java,v $
 * Revision 1.4  2004/06/02 10:00:51  aharan
 * Deleted commented out old send/recieve methods as their 
 * javadoc were interferring with new send/recieve's javadoc 
 * with the result that warning was occuring during build that 
 * the new send/recieve methods were depreciated.
 *
 * Revision 1.3  2004/05/04 10:57:04  acraig
 * Removed references to obsolete send/receive methods
 *
 * Revision 1.2  2004/04/30 13:41:12  acraig
 * Added support for attributes with send/receive
 *
 * Revision 1.1  2004/01/29 10:29:26  aharan
 * Moved MatrixConnection from com.datalex.matrix.connection.MatrixConnection 
 * to com.datalex.matrix.adapters.connections.MatrixConnection.
 * Did this to save need changing handlers to point to MatrixConnection's new location.
 *
 * Revision 1.1  2004/01/19 17:02:08  acraig
 * Initial Revision
 *
 * Revision 1.1  2004/01/19 16:06:24  acraig
 * Initial Revision
 *
 */
package com.datalex.matrix.adapters.connections;

import java.io.IOException;

/**
 * This interface is the basic interface all connections should expose.
 * @author andrewc
 */
public interface MatrixConnection
{
	/**
	 * Send data through the connection to the host
	 * @param data the data to send
	 * @param attributes any specific attributes for this send call. May be null.
	 * @throws IOException if an error occurs sending the data to the host.
	 */
	void send(byte[] data, TransactionAttributes attributes) throws IOException;
		
        
	/**
	 * Receive data from the host.
	 * @param attributes any specific attributes for this receive call.
	 * @return the data to receive
	 * @throws IOException if an error occurs
	 */
	byte[] receive(TransactionAttributes attributes) throws IOException;
	
	/**
	 * send and receive data from the host asynchronously.
	 * @param data data to send to the host
	 * @param attributes any specific attributes for this send call. May be null.
	 * @return the data received from the host
	 * @throws IOException if an error occurs sending the data to the host.
	 */
	byte[] sendAndReceive(byte[] data, TransactionAttributes attributes) throws IOException;
    
	/**
	 * Release this connection.
	 * This effectively returns the connection back to the pool.
	 */
	void close();
    

    /**
     * Returns just enough information to uniquely identify this connection.
     *
     * @return A String with the unique identification.
     */
    String getConnectionInfo();
}
