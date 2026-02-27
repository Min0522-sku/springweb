package example.종합.종합9.model.dao;

import example.종합.종합9.model.dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component // 빈(객체)등록
public class BoardDao {
    public BoardDao(){connect();}
    // 데이터 베이스 연동
    private String url = "jdbc:mysql://localhost:3306/boardservice9";
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

    public List<BoardDto> findAll(){
        List<BoardDto> list = new ArrayList<>(); // 조회 결과 레코드들을 dto로 저장하기 위한 리스트
        try{
            String sql = "select * from board"; //sql 작성
            PreparedStatement ps = conn.prepareStatement(sql); //sql 등록
            ResultSet rs = ps.executeQuery(); // sql 실행하고 결과 받기
            while (rs.next()){ // 첫번째 레코드부터 마지막 레코드 까지
                // 조회중인 레코드의 속성정보들을 dto 구성
                BoardDto boardDto = new BoardDto(rs.getInt("bno"), rs.getString("bcontent"), rs.getString("bwriter"), rs.getString("bdate"));
                list.add(boardDto); // 구성한 dto 리스트에 추가
            }

        } catch (Exception e) {System.out.println(e);}
        return list; // 리스트 반환
    }

    public boolean write(BoardDto boardDto){
        try{
            String sql = "insert into board( bcontent, bwriter) values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, boardDto.getBcontent()); // sql 첫번째 매개변수 ? 에 값 대입
            ps.setString(2, boardDto.getBwriter());
            int count = ps.executeUpdate(); // sql 실행하고 반영한 레코드 수 저장
            if(count == 1) return true; // 한개면 성공
        } catch (Exception e) {
            System.out.println(e);
        }
        return false; // 실패
    }

    public boolean update(BoardDto boardDto){

        try{
            String sql = "update board set bcontent = ? where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, boardDto.getBcontent());
            ps.setInt(2, boardDto.getBno());
            int count = ps.executeUpdate();
            if (count == 1) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean delete(int bno){

        try{
            String sql = "delete from board where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            int count = ps.executeUpdate();
            if (count ==1 ) return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }



}
