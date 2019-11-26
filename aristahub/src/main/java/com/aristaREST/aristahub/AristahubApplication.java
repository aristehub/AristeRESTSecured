package com.aristaREST.aristahub;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AristahubApplication {

	//main method
	public static void main(String[] args) 
	{
		SpringApplication.run(AristahubApplication.class, args);
	}
	//HTTPS adding
	@Bean
	public ServletWebServerFactory servletCOntainer()
	{
		//Enable SSL traffic
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory()
		{
			@Override
			protected void postProcessContext(Context context)
			{
				SecurityConstraint sc = new SecurityConstraint();
				sc.setUserConstraint("CONFIDENTIAL");
				SecurityCollection coll = new SecurityCollection();
				coll.addPattern("/*");
				sc.addCollection(coll);
				context.addConstraint(sc);
			}
		};
		//add HTTP to HTTPS redirect
		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
		
		return tomcat;
	}
	//Redirection from HTTP to HTTPS, without SSL this application used port 8082. With SSL
	//With SSL it will use port 8443 now so any request needs to be redirected to HTTPS on 8443
	private Connector httpToHttpsRedirectConnector()
	{
		//get connector and redirect ports...
		Connector conn = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		conn.setScheme("http");
		conn.setPort(8080);
		conn.setSecure(false);
		conn.setRedirectPort(8443);
		return conn;
	}
}
