package org.tempuri;

import java.rmi.RemoteException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WeatherServlet extends HttpServlet {
	
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		
	}

		public void doGet(HttpServletRequest req, HttpServletResponse res) {
			IService client = new IServiceProxy();
			try {
				System.out.println(client.getWeather("Avon", "Indiana"));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}


