package example.day16;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    // 업로드 경로 1] 로컬 환경
    private String baseDir = System.getProperty("user.dir"); // C:\Users\PC\Desktop\12-23~6-19\springweb
    private String uploadDir = baseDir+ "/build/resources/main/static/upload/"; // 상세 경로 추가
    // 업로드 경로 2] 클라우드 환경 * 추후 진행 *


    // 업로드
    public String upload(MultipartFile uploadFile){

        // 파일이 존재 하지 않으면
        if (uploadFile.isEmpty() == true){return null;} // 업로드 실패

        // 업로드할 파일의 경로 *서버 경로*,    개발자(src파일) --배포/실행--> 서버(build파일) <-- 클라이언트(사용자)
        File uploadPath = new File(uploadDir); // 업로드할  uploadDir 경로 File 객체내 대입
        // ** 만약에 해당 경로의 폴더가 존재하지 않으면 폴더 생성
        if (!uploadPath.exists()){ // ! 부정문, file객체.exists() : 경로가 존재하면 true
            uploadPath.mkdir(); // file객체.mkdir() : 경로/폴더 생성
        }
        //업로드
        // 파일명과 경로 연결해서 최종적인 경로 파일객체 생성
        String fileName = uploadFile.getOriginalFilename();
        File uploadRealPath = new File(uploadDir +fileName);
        try {
            uploadFile.transferTo(uploadRealPath); // 엄로드파일을 특정한 경로에 이송/복사 한다 *예외처리 발생*
            return fileName;
        } catch (Exception e) {System.out.println(e);}
        return null;
    }

    // 다운로드

    // 삭제

}
/*
    System.out.println(uploadFile.isEmpty()); // 첨부파일 존재여부 확인 존재하면 false 비어있으면 true
        System.out.println(uploadFile.getOriginalFilename()); // 첨부파일의 파일명
        System.out.println(uploadFile.getContentType()); // 첨부파일의 확장자
        System.out.println(uploadFile.getSize()); // 첨부파일의 용량(바이트)
*/
