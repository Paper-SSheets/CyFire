package com.cyfire.websockets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Configuration class for Websockets.
 * 
 * @author Steven Marshall Sheets
 *
 */
@Configuration
public class WebSocketConfiguration
{
	@Bean
	public ServerEndpointExporter serverEndpointExporter()
	{
		return new ServerEndpointExporter();
	}
	
}
