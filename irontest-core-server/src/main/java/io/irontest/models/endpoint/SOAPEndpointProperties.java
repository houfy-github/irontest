package io.irontest.models.endpoint;

import com.fasterxml.jackson.annotation.JsonView;
import io.irontest.models.Properties;
import io.irontest.resources.ResourceJsonViews;

@JsonView(ResourceJsonViews.TestcaseExport.class)
public class SOAPEndpointProperties extends Properties {
    private String wsdlURL = "?wsdl";
    private boolean wsdlURLByConvention = true;

    public String getWsdlURL() {
        return wsdlURL;
    }

    public void setWsdlURL(String wsdlURL) {
        this.wsdlURL = wsdlURL;
    }

    public boolean isWsdlURLByConvention() {
        return wsdlURLByConvention;
    }

    public void setWsdlURLByConvention(boolean wsdlURLByConvention) {
        this.wsdlURLByConvention = wsdlURLByConvention;
    }
}
