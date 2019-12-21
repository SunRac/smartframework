package cn.eastlegend.framework;

import cn.eastlegend.bean.*;
import cn.eastlegend.helper.*;
import cn.eastlegend.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author java_shj
 * @desc mvc框架最核心的DispatcherServlet类
 * @createTime 2019/12/6 14:00
 **/
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化Helper类,完成了创建实例、依赖注入、以及对请求和处理器的封装
        HelperLoader.init();
        //获取ServletContext对象（用于注册Servlet）
        ServletContext servletContext = config.getServletContext();
        //获取处理jsp的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        //设置JspServlet处理的请求路径
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        //设置处理静态资源servlet处理的请求路径
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
//        初始化上传文件助手类
        UploadHelper.init(servletContext);
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方法与请求路径
        //TODO 查看从req中得到的请求方式和路径
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
//        跳过favicon.ico请求
        if(requestPath.equals("/favicon.ico")) {
            return ;
        }
        Request request = new Request(requestMethod,requestPath);
        //获取Action处理器
        Handler handler = ControllerHelper.getHandler(request);
        if(handler != null) {
            //获取controller类及其Bean
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //创建请求参数对象
    /*        Map<String, Object> paramMap = new HashMap<>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }

            String body = CodeUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if(StringUtils.isNotEmpty(body)) {
                String[] params = body.split("&");
                if(ArrayUtils.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] paramKeyValue = param.split("=");
                        if(ArrayUtils.isNotEmpty(paramKeyValue) && paramKeyValue.length ==2){
                            String paramName = paramKeyValue[0];
                            String paramValue = paramKeyValue[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);*/
//            文件上传，对创建Param对象进行了封装
            Param param;
            if(UploadHelper.isMutipart(req)) {
                param = UploadHelper.createParam(req);
            } else {
                param = RequestHelper.createParam(req);
            }
            //调用Action方法
            Method actionMethod = handler.getActionMethod();
            //TODO 此处传入Param嘛？不是应该传入paramMap?
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            //处理Action方法返回值
            if(result instanceof View) {
                View view = (View) result;
                String path = view.getPath();
                if(StringUtils.isNotEmpty(path)) {
                    if(path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() +path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (String key : model.keySet()) {
                            req.setAttribute(key, model.get(key));
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                    }
                }
            } else if(result instanceof Data) {
                //返回Json数据
                Data data = (Data) result;
                Object model = data.getModel();
                if(model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JacksonUtil.obj2json(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }

        }
    }

    /**
     * 对View进行处理：视图View，跳转到页面
     */

    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) {
        String path = view.getPath();
        try {
        if(StringUtils.isNotEmpty(path)) {
            if(path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
        } catch (Exception e) {
            LOGGER.error("处理视图view异常", e);
        }
    }

    /**
     * 对View进行处理：数据View，返回json数据
     */
    private void handleDataResult(View view, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = view.getModel();
        if(CollectionUtil.isMapNotEmpty(model)) {
            response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
            response.setCharacterEncoding("UTF-8");
            try {
            PrintWriter writer = response.getWriter();
            String json = JacksonUtil.obj2json(model);
            writer.write(json);
            writer.flush();
            writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

