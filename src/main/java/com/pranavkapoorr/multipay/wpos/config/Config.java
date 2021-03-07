package com.pranavkapoorr.multipay.wpos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import com.pranavkapoorr.multipay.wpos.handler.MessageHandler;

@Configuration
@EnableWebSocket
public class Config implements WebSocketConfigurer {
	@Autowired
	private MessageHandler handler;
	
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/ips-epos");
	}
}