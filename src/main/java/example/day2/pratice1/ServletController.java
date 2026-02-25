package example.day2.pratice1;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/day02/pratice1")
public class ServletController extends HttpServlet {
    @Override public void  init() throws ServletException{
        System.out.println("init 함수 실행");
        super.init();
    }
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service 함수 실행");
        super.service(req, res);
    }
    // 3-1 GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doGet");
        // *HTTP 요청시 포함된 매개변수 확인 , http://localhost:8080/day02/servlet?value= 아무값
        int value = Integer.getInteger(req.getParameter("value"));
        System.out.println("value = " + value);
        // *HTTP 요청시 클라이턴트에게 응답
        resp.getWriter().println("Client Response : "+value+2);
    }

    // 3-2 POST , 브라우저의 주소 입력창에 요청은 무조건 GET 방식 이므로 POST,PUT,DELETE는 포스트맨vs탈란드API 사용해서 확인해야함
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPost");
        int value = Integer.getInteger(req.getParameter("value"));
        System.out.println("value = " + value);
        resp.getWriter().println("Client Response : "+ value*2);
    }
    // 3-3 PUT
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPut");
        int value = Integer.getInteger(req.getParameter("value"));
        System.out.println("value = " + value);
        resp.getWriter().println("Client Response : "+value/2);

    }
    // 3-4 DELETE
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doDelete");
        int value = Integer.getInteger(req.getParameter("value"));
        System.out.println("value = " + value);
        resp.getWriter().println("Client Response : "+value%2);
    }
}
