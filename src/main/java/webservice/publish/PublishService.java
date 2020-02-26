package webservice.publish;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.message.Message;

import javax.xml.ws.Endpoint;
import java.util.List;

/**
 * 服务端 提供服务
 */
public class PublishService {
    public static void main(String[] args) {

        /**
         * 1. 发布的地址
         * 2. 被发布的是哪一个
         *  发布这个webservice的时候，wsdl也一并发布出来了，用来描述该服务如何调用
         */
        Endpoint endpoint = Endpoint.publish("http://localhost:8989/userDao", new UserDao());

        EndpointImpl endpointImpl = (EndpointImpl)endpoint;
        // 服务端的日志入拦截器
        List<Interceptor<? extends Message>> inInterceptors = endpointImpl.getInInterceptors();
        inInterceptors.add(new LoggingInInterceptor());
        // 自定义校验用户 密码的拦截器
        inInterceptors.add(new CheckUserInterceptor());
        // 服务端的日志出拦截器
        List<Interceptor<? extends Message>> outInterceptors = endpointImpl.getOutInterceptors();
        outInterceptors.add(new LoggingOutInterceptor());
        System.out.println("web service发布成功");

    }
}
