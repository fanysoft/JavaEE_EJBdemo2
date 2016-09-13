package servlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CalculatorBean;
import bean.CalculatorRemote;

/**
 * Servlet implementation class controler
 */
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static PrintWriter out;
	static String log = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		   
		
	    PrintWriter out = response.getWriter();
	    out.println( "Portlet : inside doGet method <br>");
	    
	    try {
	    	
		    String int1str = request.getParameter("int1");
			String int2str = request.getParameter("int2");
		    out.println( "Portlet : The parametr is =" + int1str + "," + int2str + "<br>");
			
			int int1int = Integer.parseInt(int1str);
			int int2int = Integer.parseInt(int2str);
			
			int result = invokeStatefulBean(int1int, int2int);
			
			log = log + "Portlet : bean returned RESULT - sum of " + int1int + " + " + int2int + " = " + result + "<br>";
			out.println(log);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	  private static int invokeStatefulBean(int int1int, int int2int) throws NamingException {

	        final CalculatorRemote statefulRemoteCounter = lookupRemoteStatefulCounter();
	        System.out.println("Portlet : volam metodu .add z Bean ...");
	        log = log + "Portlet : calling method .add from CalculatorBean .. <br>";
	        int result = statefulRemoteCounter.add(int1int, int2int);
	        return result;
	  }


	  private static CalculatorRemote lookupRemoteStatefulCounter() throws NamingException {
	  
		  	final Hashtable jndiProperties = new Hashtable();
	        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        final Context context = new InitialContext(jndiProperties);
	        final String appName = "";
	        // nazev projektu ktery vysila na serveru, ne aktualniho projektu klienta
	        final String moduleName = "DemoEJBtwo";
	        final String distinctName = "";
	        final String beanName = CalculatorBean.class.getSimpleName();
	        final String viewClassName = CalculatorRemote.class.getName();

	        //java:jboss/exported/DemoEJBtwo/CalculatorBean!bean.CalculatorRemote
	        
	        String BeanPath= ("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	        
	        System.out.println("Portlet : looking for bean " +BeanPath + "<br>");
	        log = log + "Portlet : looking for bean " +BeanPath + "<br>";
	        
	        return (CalculatorRemote) context.lookup(BeanPath);
	        
	    }
	    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		PrintWriter out = response.getWriter();   
		String int1 = request.getParameter("int1");
		String int2 = request.getParameter("int2");
	    out.println( "doPost - The parametr is =" + int1 + " " + int2);		
		
	}
	
	

}
