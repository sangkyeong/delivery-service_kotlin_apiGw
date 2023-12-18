package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);

        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        var header  = req.getHeaderNames();
        var headerValues = new StringBuilder();

        header.asIterator().forEachRemaining(value ->{
            var headerVal = req.getHeader(value);

            headerValues
                    .append("[")
                    .append(value)
                    .append(" : ")
                    .append(headerVal)
                    .append(" , ");
        });

        var reqBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info(">>> uri : {}, method : {}, headerValues: {}, body : {}", uri, method, headerValues, reqBody);


        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(value ->{
            var headerVal = req.getHeader(value);

            responseHeaderValues
                    .append(value)
                    .append(" : ")
                    .append(headerVal)
                    .append(" , ");
        });

        var resBody = new String(res.getContentAsByteArray());

        log.info(">>> uri : {}, method : {}, responseHeaderValues : {}, body : {}", uri, method, responseHeaderValues, resBody);

        res.copyBodyToResponse();

    }
}
