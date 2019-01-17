package com.ydf.generator.interceptor;

import com.ydf.generator.cache.DatabaseMemoryCache;
import com.ydf.generator.datasource.DatabaseDialect;
import com.ydf.generator.dto.DatabaseConfig;
import com.ydf.generator.thread.DatabaseContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检验是否有主配置存在
 *
 * @author yuandongfei
 * @date 2019/1/9
 */
@Slf4j
@Component
public class ConfigInterceptor implements HandlerInterceptor {

    @Autowired
    private DatabaseContextHolder databaseContextHolder;
    @Autowired
    private DatabaseMemoryCache databaseCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        DatabaseDialect dialect = databaseContextHolder.getDatabaseDialect();
        if (null == dialect) {
            log.info(">>> 系统中不存在主配置文件，系统自动跳转到配置文件页面，引导用户配置");
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        DatabaseConfig databaseConfig = databaseCache.get();
        if (null == databaseConfig) {
            log.info(">>> 系统中不存在DB配置文件，系统自动跳转到配置文件页面，引导用户配置");
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
