package example.종합.종합8.model.dao;




import example.종합.종합8.model.dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;

public class BoardDao {
    private BoardDao(){connect();}
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){return instance;}

    // 데이터 베이스 연동
    private String url = "jdbc:mysql://localhost:3306/boardservice7";
    private String user = "root"; private String pw = "0950";
    // 연동 인터페이스 변수 선언
    private Connection conn;
    // 연동 함수 정의, dao에 생성자에서 함수실행{dao 실글톤이 생성되면서 db연동 실행}
    private void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 메모리할당
            conn = DriverManager.getConnection(url,user,pw); // mysql 구현체로 db연동후 연동 인터페이스에 반환
            System.out.println("데이터베이스 연동 성공");
        } catch (Exception e) {
            System.out.println("연동실패"+e);
        }
    }

    public boolean writer(String bcontent, String bwriter) {
        try {
            // SQL 작성한다 저장 -> C -> INSERT
            // ? : 와일드카드 기호 로 변수 값이 들어갈 자리 뜻한다.
            String sql = "insert into board(bcontent, bwriter) values(?,?)";
            //연동된 인터페이스에 sql 기재한다
            PreparedStatement ps = conn.prepareStatement(sql); // 반환 PreparedStatment *일반예외 필요
            //매개변수 대입
            ps.setString(1, bcontent); //ps.set타입명(?순서번호, 값);
            ps.setString(2, bwriter);
            // sql 실행 반환값은 반영된 레코드 수
            int count = ps.executeUpdate();
            if (count == 1){return true;}
            else return false;
        } catch (SQLException e){
            System.out.println("[시스템 오류] SQL 문법 문제 발생: "+e);
        }
        return false;
    }
    public ArrayList<BoardDto> findAll(){
        ArrayList<BoardDto> boards = new ArrayList<>();
        try{
            String sql = "select * from board";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Resultset : select 결과물을 제어하는 인터페이스
            // rs.next() : 조회 결과에서 다음 레코드 1번 이동 , 만약 레코드가 10개이면 next 10번
            while (rs.next()){
                //rs.get타입명(속성명) : 현재 레코드의 속성 값 호출
                int bno = rs.getInt("bno");
                String bcontent = rs.getString("bcontent");
                String bwriter = rs.getString("bwriter");
                String bdate = rs.getString("bdate");
                BoardDto boardDto = new BoardDto(bno, bcontent,bwriter,bdate);
                boards.add(boardDto);
            }

        }catch (SQLException e){System.out.println("[시스템 오류] SQL 문법 문제 발생: "+e);}
        return boards;

    }

    public boolean update(int bno, String bcontent){
        try{
            String sql = "update board set bcontent = ? where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bcontent);
            ps.setInt(2, bno);
            int count = ps.executeUpdate();
            if (count == 1){return true;}
            else return false;
        }catch (SQLException e){
            System.out.println("[시스템 오류] SQL 문법 문제 발생: "+e);
        }
        return false;
    }

    public boolean delete(int bno) {
        try {
            String sql = "delete from board where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            int count = ps.executeUpdate();
            if (count == 1){return true;}
            else return false;
        } catch (SQLException e) {
            System.out.println("[시스템 오류] SQL 문법 문제 발생: "+e);
        }
        return false;
    }
}
