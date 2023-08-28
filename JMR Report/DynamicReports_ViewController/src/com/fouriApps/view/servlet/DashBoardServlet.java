package com.fouriApps.view.servlet;

import com.octetstring.vde.util.Base64;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Map;

import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import oracle.adf.share.ADFContext;

import org.json.JSONException;
import org.json.JSONObject;

public class DashBoardServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
//    private static final String TASK_FLOW_ID ="faces/fragments/Reports.jspx";
    private static final String TASK_FLOW_ID ="faces/FilmStrip";
//    private static final String TASK_FLOW_ID ="faces/adf.task-flow?adf.tfId=Reports_BTF&adf.tfDoc=/taskFlow/Reports_BTF.xml&";
    private static final String InValidTASK_FLOW_ID ="faces/pages/InvalidPage.jspx";

    //continue anna

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    Map<Object, Object> sessionMap = ADFContext.getCurrent().getSessionScope();
    
    /**Process the HTTP doGet request.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = null;
        String reDirect="Error";
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>DashBoard Servlet</title></head>");
        out.println("<body>");
        //---
        String url = request.getRequestURL().toString();
        String subUrl =url.substring(0, url.lastIndexOf("/"));
//        String jwtToken = request.getParameter("jwt");
        String jwtToken="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6ImZCckR3OFA3bFVjMmN1ZDlnMjZ1ZGRsQk5ERSIsImtpZCI6InRydXN0c2VydmljZSJ9.eyJleHAiOjE1ODcxMzEzOTUsInN1YiI6IlNlb3llbml5aSIsImlzcyI6Ind3dy5vcmFjbGUuY29tIiwicHJuIjoiU2VveWVuaXlpIiwiaWF0IjoxNTg3MTMxMzM1fQ.nYWL00qkXe9nsZeln3P6pospnnWVO3LdBlYDDcB1DCgamnm-FaFzQ1qsN4iCAN3tkse6VoPo8doW_key7_CLuLTNAaRCyLDX9lHKSZLA0b-n1haYGGZyg61Cjv-RvQc6oEiD16rY0LIL4qsLDJGH0g2l96ZLaHwm9O4oAP3l_P_vLiSifeQzc-JOotVIO2IAR5CgLC3GwTUTXDH5K8Cv6lFrXFDNgmFq5AckvcMqkV907Gp1N4RBf7H4l9I1PuTMECbgFekwlln2bcdyUiCM4BfXDtWJWT8A8C_23NdmBF6Arm2U6ek9RnnEu72Oj2Exvc-LzghAfnZRuN7oIZQNnA";
        HttpSession session = request.getSession();
        if(jwtToken!=null){
            try{
                reDirect = checkLoginUserRole(jwtToken,session);
//                System.err.println("reDirect -->"+reDirect);
                if(reDirect.equalsIgnoreCase("invalidUser")){
                    path = subUrl + "/" + InValidTASK_FLOW_ID +"?errorType="+reDirect;
                }else{
                    path = subUrl + "/" + TASK_FLOW_ID;

                }
//               System.err.println("path"+path);
            }catch(Exception e){
              
              
            }
        }else{
            try {
            reDirect = 

             checkLoginUserRole("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6ImZCckR3OFA3bFVjMmN1ZDlnMjZ1ZGRsQk5ERSIsImtpZCI6InRydXN0c2VydmljZSJ9.eyJleHAiOjE1ODcxMzEzOTUsInN1YiI6IlNlb3llbml5aSIsImlzcyI6Ind3dy5vcmFjbGUuY29tIiwicHJuIjoiU2VveWVuaXlpIiwiaWF0IjoxNTg3MTMxMzM1fQ.nYWL00qkXe9nsZeln3P6pospnnWVO3LdBlYDDcB1DCgamnm-FaFzQ1qsN4iCAN3tkse6VoPo8doW_key7_CLuLTNAaRCyLDX9lHKSZLA0b-n1haYGGZyg61Cjv-RvQc6oEiD16rY0LIL4qsLDJGH0g2l96ZLaHwm9O4oAP3l_P_vLiSifeQzc-JOotVIO2IAR5CgLC3GwTUTXDH5K8Cv6lFrXFDNgmFq5AckvcMqkV907Gp1N4RBf7H4l9I1PuTMECbgFekwlln2bcdyUiCM4BfXDtWJWT8A8C_23NdmBF6Arm2U6ek9RnnEu72Oj2Exvc-LzghAfnZRuN7oIZQNnA", session);   
         
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if(reDirect.equalsIgnoreCase("invalidUser")){
                path = subUrl + "/" + InValidTASK_FLOW_ID +"?errorType="+reDirect;
            }else{
                path = subUrl + "/" + TASK_FLOW_ID;    
            }
            
        }
        //---   
        System.err.println("=="+path);
        response.sendRedirect(path);
        out.println("<p>Redirecting to Dashboard taskflow</p>");
        out.println("</body></html>");
        out.close();
    }
    
    
    public String checkLoginUserRole(String jwt,HttpSession session) throws SQLException, JSONException, NamingException {
            
            //        ADFContext.getCurrent().getPageFlowScope().put("jwt",jwt);
            String pageRedirect = "invalid";
        
            String userRole = null;
            if (jwt != null) {
                //        System.err.println("==>in");
                String inputEncodedText = jwt;
                //ADFContext.getCurrent().getPageFlowScope().get("tokens").toString();
                String[] xIn = inputEncodedText.split("\\.");
                byte b[] = Base64.decode(xIn[1]);
                System.err.println("b===="+b);
                String tempPass = new String(b);
                
                int chkOccurance = tempPass.lastIndexOf("}");
                if (chkOccurance < 1) {
                    tempPass += "}";
                }
                JSONObject jo;

                jo = new JSONObject(tempPass);
//                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//                Timestamp expFromToken = new Timestamp(jo.getLong("exp"));

                //comment the below if statement for local
                //                if(timestamp.compareTo(expFromToken)>0){
                //                    return "tokenExpired";
                //                }
                //till this you have to comment

                String userName = jo.getString("prn");
                ADFContext.getCurrent().getSessionScope().put("userName", jo.getString("prn"));
            //                String userName ="Seoyeniyi";

                session.setAttribute("userName", userName);
                  System.err.println("PRN==>"+jo.getString("prn"));
                //                userRole=getDBConnection(userName);
                //                if(userRole.equalsIgnoreCase("NO_ROLE")){
                //                    pageRedirect = "invalidUser";
                //                }else{
                pageRedirect = "validUser";
                //                }
            } else {
                pageRedirect = "invalidUser";
            }
            return pageRedirect;
        }
    

    public String getDBConnection(String USER_NAME, HttpSession session) throws NamingException,
                                                           SQLException {
            String retStr = "validUser";
            return retStr;              
            
        }
    

}
