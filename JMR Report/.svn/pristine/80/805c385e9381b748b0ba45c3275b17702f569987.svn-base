package com.fouriApps.view.backing;

import com.fouriApps.view.utils.ADFUtils;

import com.fouriApps.view.utils.JSFUtils;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.nav.RichGoLink;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class ReportsPfb {
    
    Map<String,ArrayList<SelectItem>> paramsMap;
    Map<String,String> paramsType;
    Map<String,String> paramsDisplay;
    Map<String,String> paramsMandatory;
    List<String> params;
    private RichPanelGroupLayout dynamicParamsPGL;
    Map<String,String> paramsSelectedValue;
    private RichSelectOneChoice reportLOVBinding;
    private String reportPath;
    private RichGoLink goLink;

    public ReportsPfb() {
        super();
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();  
        AttributeBinding attr = (AttributeBinding)bindings.getControlBinding("Report1"); 
        if(attr.getInputValue()!=null){
            getParams(attr.getInputValue().toString(),false);
        }
    }
    
    public void getParams(String reportId,boolean partialTrigger){
        paramsMap = new HashMap<String,ArrayList<SelectItem>>();
        paramsType = new HashMap<String,String>();
        paramsDisplay = new HashMap<String,String>();
        paramsMandatory = new HashMap<String,String>();
        params = new ArrayList<String>();
        paramsSelectedValue = new HashMap<String,String>();
        String userName = (String)ADFContext.getCurrent().getSessionScope().get("userName");
        if(reportId!=null){
        ViewObject vo = ADFUtils.findIterator("Functions_VO1Iterator").getViewObject();
        vo.applyViewCriteria(vo.getViewCriteriaManager().getViewCriteria("FindByFuncId"));
        vo.setNamedWhereClauseParam("BV_ID", reportId);
        vo.setRangeSize(-1);
        vo.executeQuery();
        Row row;
        if(vo.getEstimatedRowCount()>0){
            row = vo.getAllRowsInRange()[0];
            reportPath = row.getAttribute("PagePath")!=null ? row.getAttribute("PagePath").toString() : "";
        }
        vo = ADFUtils.findIterator("XxfndReportParamsVoIterator").getViewObject();
        vo.applyViewCriteria(vo.getViewCriteriaManager().getViewCriteria("FindByFuncId"));
        vo.setNamedWhereClauseParam("b_FuncId", reportId);
        vo.setRangeSize(-1);
        vo.executeQuery();
        Row[] rows = vo.getAllRowsInRange();
        OperationBinding operationBinding = ADFUtils.findOperation("getLOVValues");
        for(int i=0;i<rows.length;i++){
            row=rows[i];
            Map mapObj = operationBinding.getParamsMap();
            if(row.getAttribute("ParamTable")!=null && row.getAttribute("ParamColumn")!=null && row.getAttribute("ParamDispColumn")!=null
                    && row.getAttribute("ParamLookupType")==null && row.getAttribute("LovBasedUserBindVariable")==null && row.getAttribute("LovBasedUserYn")==null
                    && row.getAttribute("LovLookupType")==null){
                mapObj.put("tableName", row.getAttribute("ParamTable"));
                mapObj.put("value", row.getAttribute("ParamColumn"));
                mapObj.put("displayValue", row.getAttribute("ParamDispColumn"));
                mapObj.put("lookupType", null);
                mapObj.put("LovBasedUserBindVariable", null);
                mapObj.put("LovBasedUserYn", null);
                mapObj.put("dynamicLovlookupType", null);
                mapObj.put("userName", userName);
                paramsMap.put((String)row.getAttribute("Token"),(ArrayList<SelectItem>)operationBinding.execute());
                params.add(row.getAttribute("Token").toString());
                paramsType.put((String)row.getAttribute("Token"), "LOV");
                paramsMandatory.put((String)row.getAttribute("Token"), row.getAttribute("Mandatory")!=null ? row.getAttribute("Mandatory").toString() : "N");
                paramsDisplay.put((String)row.getAttribute("Token"), (String)row.getAttribute("ParamName"));
            }
            else if(row.getAttribute("ParamTable")==null && row.getAttribute("ParamColumn")==null && row.getAttribute("ParamDispColumn")==null
                        && row.getAttribute("ParamLookupType")!=null && row.getAttribute("LovBasedUserBindVariable")==null && row.getAttribute("LovBasedUserYn")==null
                    && row.getAttribute("LovLookupType")==null){
                mapObj.put("tableName", null);
                mapObj.put("value", null);
                mapObj.put("displayValue", null);
                mapObj.put("lookupType", row.getAttribute("ParamLookupType"));
                mapObj.put("LovBasedUserBindVariable", null);
                mapObj.put("LovBasedUserYn", null);
                mapObj.put("dynamicLovlookupType", null);
                mapObj.put("userName", userName);
                paramsMap.put((String)row.getAttribute("Token"),(ArrayList<SelectItem>)operationBinding.execute());
                params.add(row.getAttribute("Token").toString());
                paramsType.put((String)row.getAttribute("Token"), "LOV");
                paramsDisplay.put((String)row.getAttribute("Token"), (String)row.getAttribute("ParamName"));
            }
            else if(row.getAttribute("ParamTable")==null && row.getAttribute("ParamColumn")==null && row.getAttribute("ParamDispColumn")==null
                        && row.getAttribute("ParamLookupType")==null && row.getAttribute("LovBasedUserYn")!=null
                    && row.getAttribute("LovLookupType")!=null){
                mapObj.put("tableName", null);
                mapObj.put("value", null);
                mapObj.put("displayValue", null);
                mapObj.put("lookupType", null);
                mapObj.put("LovBasedUserBindVariable", row.getAttribute("LovBasedUserBindVariable"));
                mapObj.put("LovBasedUserYn", row.getAttribute("LovBasedUserYn"));
                mapObj.put("dynamicLovlookupType", row.getAttribute("LovLookupType"));
                mapObj.put("userName", userName);
                paramsMap.put((String)row.getAttribute("Token"),(ArrayList<SelectItem>)operationBinding.execute());
                params.add(row.getAttribute("Token").toString());
                paramsType.put((String)row.getAttribute("Token"), "LOV");
                paramsDisplay.put((String)row.getAttribute("Token"), (String)row.getAttribute("ParamName"));
            } 
            
            else if(row.getAttribute("ParamTable")==null && row.getAttribute("ParamColumn")==null && row.getAttribute("ParamDispColumn")==null
                        && row.getAttribute("ParamLookupType")==null){
                params.add(row.getAttribute("Token").toString());
                paramsDisplay.put((String)row.getAttribute("Token"), (String)row.getAttribute("ParamName"));
                if(row.getAttribute("DataType")!=null){
                    if(row.getAttribute("DataType").toString().equalsIgnoreCase("C")){
                        paramsType.put((String)row.getAttribute("Token"), "CHAR");
                    }
                    else if(row.getAttribute("DataType").toString().equalsIgnoreCase("N")){
                        paramsType.put((String)row.getAttribute("Token"), "NUMBER");
                    }
                    else if(row.getAttribute("DataType").toString().equalsIgnoreCase("D")){
                        paramsType.put((String)row.getAttribute("Token"), "DATE");
                    }
                }
                else{
                    paramsType.put((String)row.getAttribute("Token"), "CHAR");
                }
            }
            System.err.println("--->"+paramsSelectedValue.get(row.getAttribute("Token")));
            System.err.println("-->"+paramsType.get(row.getAttribute("Token")));
            
            if(paramsSelectedValue.get(row.getAttribute("Token"))==null && paramsType.get(row.getAttribute("Token")).equalsIgnoreCase("LOV")){
                
                paramsSelectedValue.put(row.getAttribute("Token").toString(),paramsMap.get(row.getAttribute("Token"))!=null ?  paramsMap.get(row.getAttribute("Token")).isEmpty() ? null : paramsMap.get(row.getAttribute("Token")).get(0)!=null ? paramsMap.get(row.getAttribute("Token")).get(0).getValue().toString() : null : null);
            }
        }
        }
        if(partialTrigger){
        AdfFacesContext.getCurrentInstance().addPartialTarget(dynamicParamsPGL);
        }
        
    }

    public void reportsValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("value change listener called");
        FacesContext.getCurrentInstance().renderResponse();
        String reportId = valueChangeEvent.getNewValue()!=null ? valueChangeEvent.getNewValue().toString() : null;
        getParams(reportId,true);
        System.out.println("report id:"+reportId);
    }

    public void setDynamicParamsPGL(RichPanelGroupLayout dynamicParamsPGL) {
        this.dynamicParamsPGL = dynamicParamsPGL;
    }

    public RichPanelGroupLayout getDynamicParamsPGL() {
        return dynamicParamsPGL;
    }

    public void setParamsMap(Map<String, ArrayList<SelectItem>> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public Map<String, ArrayList<SelectItem>> getParamsMap() {
        return paramsMap;
    }

    public void setParamsType(Map<String, String> paramsType) {
        this.paramsType = paramsType;
    }

    public Map<String, String> getParamsType() {
        return paramsType;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParamsSelectedValue(Map<String, String> paramsSelectedValue) {
        this.paramsSelectedValue = paramsSelectedValue;
    }

    public Map<String, String> getParamsSelectedValue() {
        return paramsSelectedValue;
    }

    public void paramValueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String paramName = (String)valueChangeEvent.getComponent().getAttributes().get("paramName");
        if(valueChangeEvent.getNewValue()!=null){
            String value = valueChangeEvent.getNewValue().toString();
            paramsSelectedValue.remove(paramName);
            paramsSelectedValue.put(paramName,value);
        }
        else{
            paramsSelectedValue.remove(paramName);
        }
    }
   
    public void getReport(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings =
                            BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding reportsFormatBinding =
                            (AttributeBinding)bindings.getControlBinding("ReportsFormat1");
        Object[] paramNameObj = paramsSelectedValue.keySet().toArray();
        String reportPath = this.reportPath;
        reportPath = reportPath.concat("?P_File_Type="+reportsFormatBinding.getInputValue());
        System.err.println("?P_File_Type="+reportsFormatBinding.getInputValue());
        for(int i=0;i<paramNameObj.length;i++){
            String paramName = (String)paramNameObj[i];
            System.out.println("Param name:"+paramName);
            Object selectedValue = paramsSelectedValue.get(paramName);
            if(selectedValue!=null){
                if(selectedValue.getClass().getName().equalsIgnoreCase("java.util.Date")){
    //                will be using this when taking the format from the profile preferences
    //                Map sessionApplicationSetup = (Map)ADFContext.getCurrent().getSessionScope().get("applicationSetup");
                    String dFormat = "dd-MM-yyyy";
                    DateFormat dateFormat = new SimpleDateFormat(dFormat);  
                    String strDate = dateFormat.format(paramsSelectedValue.get(paramName));  
                        reportPath = reportPath.concat("&"+paramName+"="+strDate);
                }
                else{
                        reportPath = reportPath.concat("&"+paramName+"="+paramsSelectedValue.get(paramName).toString());
                }
            }
        }
        FacesContext fctx = FacesContext.getCurrentInstance();
        String taskflowURL = reportPath;
        ExtendedRenderKitService erks = Service.getRenderKitService(fctx, ExtendedRenderKitService.class);
        StringBuilder script = new StringBuilder();
        script.append("window.open(\"" + taskflowURL + "\");");
        erks.addScript(fctx, script.toString());
        AdfFacesContext.getCurrentInstance().addPartialTarget(reportLOVBinding);
        AdfFacesContext.getCurrentInstance().addPartialTarget(dynamicParamsPGL);
    }

    public void setReportLOVBinding(RichSelectOneChoice reportLOVBinding) {
        this.reportLOVBinding = reportLOVBinding;
    }

    public RichSelectOneChoice getReportLOVBinding() {
        return reportLOVBinding;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setGoLink(RichGoLink goLink) {
        this.goLink = goLink;
    }

    public RichGoLink getGoLink() {
        return goLink;
    }

    public void setParamsDisplay(Map<String, String> paramsDisplay) {
        this.paramsDisplay = paramsDisplay;
    }

    public Map<String, String> getParamsDisplay() {
        return paramsDisplay;
    }
    public void setParamsMandatory(Map<String, String> paramsMandatory) {
            this.paramsMandatory = paramsMandatory;
        }

        public Map<String, String> getParamsMandatory() {
            return paramsMandatory;
}
}
