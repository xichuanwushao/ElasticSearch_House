package com.lcz.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功处理类
 *
 * @date 2022/04/04 15:04
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final RequestCache cache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = cache.getRequest(request, response);
        // 如果来源请求为空则跳转到用户博客首页
        String url;
        if (savedRequest == null) {
            url = request.getHeader("Referer").replace(request.getHeader("Origin"), "");
            if (url.startsWith("/admin")) {
                url = "/admin/center";
            } else {
                url = "/";
            }
        } else {
            url = savedRequest.getRedirectUrl();
        }

        System.out.println(url);

        response.sendRedirect(url);
    }
}
