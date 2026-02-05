package com.swp.ckms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    private static final Logger log = LoggerFactory.getLogger(StartupListener.class);

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${spring.application.name:CKMS Application}")
    private String applicationName;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String banner = """
                
                ╔══════════════════════════════════════════════════════════════╗
                ║                                                              ║
                ║   ██████╗██╗  ██╗███╗   ███╗███████╗                         ║
                ║  ██╔════╝██║ ██╔╝████╗ ████║██╔════╝                         ║
                ║  ██║     █████╔╝ ██╔████╔██║███████╗                         ║
                ║  ██║     ██╔═██╗ ██║╚██╔╝██║╚════██║                         ║
                ║  ╚██████╗██║  ██╗██║ ╚═╝ ██║███████║                         ║
                ║   ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝                         ║
                ║                                                              ║
                ║  Central Kitchen Management System                           ║
                ║                                                              ║
                ╠══════════════════════════════════════════════════════════════╣
                                                                              
                  Application started successfully!                          
                                                                             
                  Local:    http://localhost:%s                              
                  Swagger:  http://localhost:%s/swagger-ui.html             
                  API Docs: http://localhost:%s/v3/api-docs                 
                                                                            
                  Ready to accept requests!                                  
              
                """.formatted(serverPort, serverPort, serverPort);
        
        log.info(banner);
    }
}
