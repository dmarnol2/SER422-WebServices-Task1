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

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
			//JSONArray json = new JSONArray(ret);
			//json = JSONparse(ret);
			//json = JSON.parse(ret);
			//String json = new Gson().toJson(ret);
			//String x = ret.response.version;
			//String x = json.response;
    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    //res.getWriter().write(res.getParameter("current_observation"));
			//res.addProperty("success", true);
			
			//sb.append("<div  id='resBox'>");
			//sb.append("<p>HERE I AM</p>"+ret);
			//sb.append("</div>");
			//sb.append("</body></html>");
    JSONParser parser=new JSONParser();
    JSONObject jobj=null;
    //JSONArray array=null;
		String array="";
		try {
			jobj =   (JSONObject) parser.parse(client.getWeather("Indianapolis", "Indiana"));//(JSONObject) parser.parse(ret);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		array =  jobj.get("current_observation").toString();
	



    //JSONObject jsonObject = new JSONObject(ret);
	//	JSONArray age = jsonObject.getJSONArray("response");
    	//String[] sa = ret.toArray();
		//pw.write(age);
		res.getWriter().write(array.toString());
		//pw.println(ret.toString());

        pw.close();


		}



	}



