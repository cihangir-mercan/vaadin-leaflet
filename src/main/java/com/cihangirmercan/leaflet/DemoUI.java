package com.cihangirmercan.leaflet;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("mytheme")
public class DemoUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {   	
    	// vaadin buttons
    	Button flyToSanFransisco = new Button("Fly to San Fransisco");
    	Button flyToLondon = new Button("Fly to London");
    	
    	// horizontal alignment between buttons
    	final HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(flyToSanFransisco, flyToLondon);
    	
        // our component
        Leaflet leaflet = new Leaflet();
        leaflet.setWidth("900px");
        leaflet.setHeight("500px");
        
        // my own functions    
        leaflet.setView(51.505, -0.090, 13); // centered to london with zoom level 13
        
        // button listeners
        flyToSanFransisco.addClickListener(e -> {
        	leaflet.flyTo(37.773, -122.431);
        });
        
        flyToLondon.addClickListener(e -> {
        	leaflet.flyTo(51.505, -0.090);
        });
       
        // our component listener - add marker to the map at the clicked point
        leaflet.addClickListener(e -> {
			leaflet.addMarker(new Marker(e.getLat(), e.getLng()));
        });
        
        // page layout 
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponents(buttons, leaflet);
        
        setContent(layout);   
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = DemoUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
