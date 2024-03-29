package group.jsjxh.community.config;

import group.jsjxh.community.bean.User;
import group.jsjxh.community.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
public class WebMvcAdapterConfiguration implements WebMvcConfigurer {

    @Resource
    UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                //是资源请求时直接通过
                if(handler instanceof ResourceHttpRequestHandler)
                    return true;
                User user=null;
                try {
                    if(request.getSession().getAttribute("user")==null){
                        String cookieToken = getCookieToken(request);
                        if(cookieToken!=null)
                             user=userService.getUserByTokne(cookieToken);
                        if(user==null&&!request.getServletPath().equals("/"))   //避免在主页时多次重定向
                            throw  new RuntimeException();
                        request.getSession().setAttribute("user",user);
                    }
                }catch (RuntimeException e){
                    response.sendRedirect("/");
                }
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        }).excludePathPatterns(Arrays.asList("/callback","/ajax/**","/*.ico")).addPathPatterns("/**");      //主页请求的servletpath是'/'
    }
    private String getCookieToken(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>0) //没有cookies时进行遍历会报空指针异常
            for(Cookie cookie: cookies)
                    if(cookie.getName().equals("_token"))
                        return cookie.getValue();
         return null;
    }
}
