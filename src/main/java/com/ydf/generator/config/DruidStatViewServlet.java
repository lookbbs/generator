package com.ydf.generator.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author yuandongfei
 * @date 2018/12/4
 */

@WebServlet(
        urlPatterns = {"/druid/*"},
        initParams = {
                @WebInitParam(name = "allow", value = "127.0.0.1"),
                @WebInitParam(name = "loginUsername", value = "root"),
                @WebInitParam(name = "loginPassword", value = "123"),
                @WebInitParam(name = "resetEnable", value = "true")// 允许HTML页面上的“Reset All”功能
        })
public class DruidStatViewServlet extends StatViewServlet implements Servlet {
}
