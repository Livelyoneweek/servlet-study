package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //[status-line]
        //200을 직접 써도되지만 정해진 상수를 쓰셈
        response.setStatus(HttpServletResponse.SC_OK);

        //[response-header]
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        //헤더 편의 메소드
        content(response);

        //캐시를 무효화하겠다
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        // 쿠키 편의 메소드
        cookie(response);
        
        //헤더 내가원하는거 넣기
        response.setHeader("my-header", "hello");

        // 리다이렉트 편의 메소드
        redirect(response);

        //메시지 응답 바디
        PrintWriter writer = response.getWriter();
        writer.println("ok");

    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //302이면서 로케이션 있으니 리다이렉트 됌
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        
        //위에가 불편해서 이거 한줄로 리다렉트 하면 됌
        response.sendRedirect("/basic/hello-form.html");
    }
}
