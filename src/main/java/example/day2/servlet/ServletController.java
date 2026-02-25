package example.day2.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//서블릿이란? 자바회사에서 HTTP 웹 동적처리 하기 위한 라이브러리(미리만든)/클래스
// 사용법
// 1. 현재클래스의 "HttpServlet" 클래스로 부터 상속 받는다.
// 2. 현재클래스 위에 @WebServlet("/URL") 주소 설정. 중복없이 영문으로 정의
// 3. (스프링 환경에만 해당) AppStart 클래스 위에 @ServletComponentScan 주입하여 스프링이 서블릿을 찾을 수 있도록 함
@WebServlet("/day02/servlet") //-> localhost:8080/day02/servlet 요청시 통신 가능한 클래스 정의
public class ServletController extends HttpServlet {
    // 순수 자바의 메소드/함수
    boolean method(int parma){return true;}

    // 상속 받은
    // 1. 서블릿 클래스로 부터 HTTP 요청시 init 함수 최초 1번 실행된다
    @Override public void  init() throws ServletException{
        System.out.println("init 함수 실행");
        super.init();
    }

    // 2. 서블릿 클래스로 부터 HTTP 요청마다 service 함수 실행된다
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service 함수 실행");
        super.service(req, res);
    }

    // 3. HTTP 메소르란?  GET, POST, PUT, DELETE 으로 통신 방법
    // 서블릿 객체는 요청이 끝나도 사라지지 않는다. 다음 요청에 재사용
    // HttpServletRequest, HttpServletResponse 객체는 요청이 끝나면 사라진다.
    // 3-1 GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doGet");
        // *HTTP 요청시 포함된 매개변수 확인 , http://localhost:8080/day02/servlet?data= 아무값
        String data = req.getParameter("data");
        System.out.println("data = " + data);
        // *HTTP 요청시 클라이턴트에게 응답
        resp.getWriter().println("Client Response : Hi !!");
    }

    // 3-2 POST , 브라우저의 주소 입력창에 요청은 무조건 GET 방식 이므로 POST,PUT,DELETE는 포스트맨vs탈란드API 사용해서 확인해야함
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPost");
        String data = req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi !!");
    }
    // 3-3 PUT
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPut");
        String data = req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi !!");

    }
    // 3-4 DELETE
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doDelete");
        String data = req.getParameter("data");
        System.out.println("data = " + data);
        resp.getWriter().println("Client Response : Hi !!");
    }
    // HTTP란? 클라이언트와 서버간의 통신하는 규칙
    // 클라이언트가 1번 요청의 1번 응답, 즉] 요청이 없으면 응답 할 수 없다.
    // 서블릿 클래스내 동일한 시그니처(선언부)를 갖는 메소드는 존재할 수 없다.
    // 즉] 하나의 서블릿에 doGet() 메소드는 1개만 존재할 수 있다. --> 스프링 환경에서 보완 제공
}
