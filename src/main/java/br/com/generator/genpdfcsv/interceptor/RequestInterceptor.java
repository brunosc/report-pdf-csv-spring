package br.com.generator.genpdfcsv.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        System.out.println("In preHandle we are Intercepting the Request");
//        System.out.println("____________________________________________");
//        String requestURI = request.getRequestURI();
//        String personId = ServletRequestUtils.getStringParameter(request, "param", "default");
//        System.out.println("RequestURI::" + requestURI + ", param = " + personId);
//        System.out.println("____________________________________________");

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

//        System.out.println("_________________________________________");
//        System.out.println("In postHandle request processing "
//                + "completed by @RestController");
//        System.out.println("_________________________________________");

        //https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-handlermapping-interceptor

        /*
        Note that postHandle is less useful with @ResponseBody and ResponseEntity methods for which a the response
        is written and committed within the HandlerAdapter and before postHandle.
        That means its too late to make any changes to the response such as adding an extra header.
        For such scenarios you can implement ResponseBodyAdvice and either declare it as an
        Controller Advice bean or configure it directly on RequestMappingHandlerAdapter.
        */

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

//        System.out.println("________________________________________");
//        System.out.println("In afterCompletion Request Completed");
//        System.out.println("________________________________________");

    }
}
