package com.youxiue.other.webservice.publish;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;

public class CheckUserInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    /**
     * 确定什么时候拦截
     */
    public CheckUserInterceptor() {
        super(Phase.PRE_PROTOCOL); // 准备协议化时拦截
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        Header header = soapMessage.getHeader(new QName("youxiue"));
        if(header!=null){
            Element element = (Element) header.getObject();
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            String password = element.getElementsByTagName("password").item(0).getTextContent();
            if("xfb".equals(name)&& "123456".equals(password)){
                System.out.println("通过拦截器");
            }else{
                System.out.printf("用户名:%s, 密码:%s",name,password);
                throw new RuntimeException("用户名或密码错误");
            }
        }
    }
}
