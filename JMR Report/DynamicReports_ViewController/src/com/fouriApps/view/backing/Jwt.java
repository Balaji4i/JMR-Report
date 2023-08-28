package com.fouriApps.view.backing;


import com.fouriApps.view.utils.JSFUtils;

import com.octetstring.vde.util.Base64;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.json.JSONException;
import org.json.JSONObject;


public class Jwt {
    public Jwt() {
        super();
    }
    
    public String checkUsers(){
//        String jwt="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6IjhzQkZrd3J4Mk00cXRnNHo1V19lV3E2c256QSIsImtpZCI6InRydXN0c2VydmljZSJ9.eyJleHAiOjE1ODUxMTkxMTAsInN1YiI6IkxFa3BvIiwiaXNzIjoid3d3Lm9yYWNsZS5jb20iLCJwcm4iOiJMRWtwbyIsImlhdCI6MTU4NTExOTA1MH0.f4v61c1YJLT_aArTKzlaG_ebOMF1Slx_L0JVjSboh33xVWUFNe3Hf2288o5m1s5wcib1y_vBi-jAUoTJa6UEHXHZlCfKvBAOw1-zMPsBBfENxjO7HyiSXGIdD-iRAOOm2sCmfFGpZOwGJvyamrquadRRYDbrQ_JavSn9Y4T0hGdNI_dVeTv7BMqbUfnq1g0z30xWFQgfX8xMlBYxd--PgVLs4Rc9deooQgwu2evfP6EGaczVk_EgSf8eA9EqJi6yX0b1MegyRnsfS8Wi9BiBZRfJ8uuNxBC8HcmkJJ3vzapsSwJ4XnlQXDBpT5YSqkm1tgOzzJGtwHOBovSazKK2nA";
               //   System.out.println("phase listener constructor called1213131");  
//    String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6ImZCckR3OFA3bFVjMmN1ZDlnMjZ1ZGRsQk5ERSIsImtpZCI6InRydXN0c2VydmljZSJ9.eyJleHAiOjE1Njk4NTM4OTUsInN1YiI6Im9zb2JhbmRlQE9hbmRvZW5lcmd5cmVzb3VyY2VzLmNvbW0iLCJpc3MiOiJ3d3cub3JhY2xlLmNvbSIsInBybiI6Im9zb2JhbmRlQE9hbmRvZW5lcmd5cmVzb3VyY2VzLmNvbW0iLCJpYXQiOjE1Njk4Mzk0OTV9.JQd6ECLjGLOo1psJZjKcrQ3t6iUvjDzBGmR7T-LiNiJMJUjlWnpp_mCuqTWwUhj_8T6JijjdYZd1MuouSzsp1MVr8mPA3FbASBgBI2z7KHAKHAgSEtJOczxDFpzZsQdFHtubkpcDcIsnfCK0XK0hwbpFUt2VUMtZudAYtg34FO0vdD5dDvEZ5Hfd4e5oSEEREBYsiFi3Y5_0SbOmXTdZjMH-Yc8Ub2zauqKojKgiPdeGB4zdRCgaN9GfNAzIJMaCn3J1veXIgPxghoz686_1vZHmq7wbMCDqQxHB39Ezy-b2CBwyEm_8x1q8RPUoeL2metwjMzKTAvcioxDOzYq-iQ&_afrLoop=2944370349104917&_afrWindowMode=2&Adf-Window-Id=tikoc9kbt&_afrFS=16&_afrMT=screen&_afrMFW=1600&_afrMFH=789&_afrMFDW=1600&_afrMFDH=900&_afrMFC=8&_afrMFCI=0&_afrMFM=0&_afrMFR=96&_afrMFG=0&_afrMFS=0&_afrMFO=0";
//        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6ImZCckR3OFA3bFVjMmN1ZDlnMjZ1ZGRsQk5ERSIsImtpZCI6InRydXN0c2VydmljZSJ9.eyJleHAiOjE1Njk4NTk0MzcsInN1YiI6Im9zb2JhbmRlQE9hbmRvZW5lcmd5cmVzb3VyY2VzLmNvbW0iLCJpc3MiOiJ3d3cub3JhY2xlLmNvbSIsInBybiI6Im9zb2JhbmRlQE9hbmRvZW5lcmd5cmVzb3VyY2VzLmNvbW0iLCJpYXQiOjE1Njk4NDUwMzd9.lIMBJdKMBhofvK6vsrGYw3NJtH6PsF5qA1SVr2O5Y9oIfvSU8OmgVqBtcCMD67k3yw-fBuRENifNZLRm9k0jDU1kIAa53MZhOQWKSxIBU7zogIBNWxMU7hl4U88EG562pQGUKNLOimpNujgAk2XCPpsGHeGnEo4D9AzTWrJxUSqeIpntceWfFyiiKCBSRr3mm-g2AakuOiTCC1-DVvHOjbe2HNyPF0pyqAXDu7s2VJg5PtlzO1LyvJIbKYjP06Dn9FtBrxjQAngFW2NVWpY5dHkVB4p55jCirU2NnOTm5mhEIfW3WCaos3SYZyqhmttXqUeb6jYgIOu7fhadLXmVXQ&adf.tfId=Reports_BTF&_afrRedirect=2949912366294462";
          String jwt = (String)ADFContext.getCurrent().getPageFlowScope().get("tokens");
        System.out.println("jwt==>"+jwt);
//        JSFUtils.addFacesErrorMessage(jwt);
        String  outputString = "validUser";
              if(jwt!=null){
                  String inputEncodedText = jwt;
                  try{                
                      String[] xIn = inputEncodedText.split("\\.");
                      byte b[] = Base64.decode(xIn[1]);
                      String tempPass = new String(b);
                      int chkOccurance = tempPass.lastIndexOf("}");
                      if (chkOccurance < 1) {
                          tempPass += "}";
                      }
                      JSONObject jo;

                      jo = new JSONObject(tempPass);
//                      String userName= "LEkpo";
                      String userName = jo.getString("prn");
                      ADFContext.getCurrent().getSessionScope().put("userName", jo.getString("prn"));
                      outputString="validUser";
                      System.out.println("userName:"+jo.getString("prn"));
                      
                  }
                  catch(JSONException e){
                     e.printStackTrace(); 
                      outputString="InvalidUser";
                  }
              }
              else{
                  System.out.println("inside else user TESTREP");
                  ADFContext.getCurrent().getSessionScope().put("userName", "TESTREP");
                  outputString="InvalidUser";
              }
        return outputString;
    }
    
    
}
