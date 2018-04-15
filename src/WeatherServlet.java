package org.tempuri;

import java.rmi.RemoteException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class WeatherServlet extends HttpServlet {
	
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		
	}

		public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
			HttpSession session = req.getSession();
		StringBuilder sb = new StringBuilder("<html><head></head><body>");
		PrintWriter pw = res.getWriter();


			IService client = new IServiceProxy();
			String ret=" ";
			
			
			sb.append("<p>HERE I AM</p>"+ret);
			sb.append("</body></html>");

		pw.print(sb.toString());

		}
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
			//String text = "some text";
			res.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
    res.setCharacterEncoding("UTF-8"); // You want world domination, huh?
    //res.getWriter().write(text); 

			//StringBuilder sb = new StringBuilder("<html><head></head><body>");
		PrintWriter pw = res.getWriter();
		String ret=""; 


			IService client = new IServiceProxy();
			if(!req.getParameterMap().containsKey("hourly")&&!req.getParameterMap().containsKey("tenday")){
				ret = client.getWeather(req.getParameter("city"), req.getParameter("state"));
			}
			else if (req.getParameterMap().containsKey("hourly")) {
			ret = client.getWeather_hourly(req.getParameter("city"), req.getParameter("state"), true);
			}
			else {//(req.getParameterMap().containsKey("tenday")){
			ret = client.getWeather_tenDays(req.getParameter("city"), req.getParameter("state"), true);
			}
			
			//res.addProperty("success", true);
			
			//sb.append("<div  id='resBox'>");
			//sb.append("<p>HERE I AM</p>"+ret);
			//sb.append("</div>");
			//sb.append("</body></html>");

		pw.write(ret);
		res.getWriter().write(ret);
		//pw.println(ret.toString());

        pw.close();


		}



	}


