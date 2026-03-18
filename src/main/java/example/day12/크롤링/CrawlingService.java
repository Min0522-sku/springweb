package example.day12.크롤링;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CrawlingService {

    // 1] Jsoup 이용한 특정 url html 정보 수집
    public List<String> test1(){
        List<String> list = new ArrayList<>();
        // 1. 크롤링 url 웹피이지 주소
        // 페이지가 있는 경우 url에서 페이지 부분을 변수 처리해서 for 반복문을 통해 진행
        String url = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";
        // 2. 크롤링 할 URL 요청하여 HTML 전체를 가져온다. Jsoup.connect(주소).get();
        // Document import org.jsoup.nodes.Document;
        try {
            Document document = Jsoup.connect(url).get(); // 외부 통신은 일반예외가 주로 발생
            System.out.println("document = " + document);
            // 3. 특정한 마크업/요소 식별자 document.select( "식별자" );
            Elements elements = document.select(".titles > a"); // 클래스가 titles 인 마크업 아래에 <a> 가져온다.
            System.out.println("elements = " + elements);
            // 4. 여러개 가져왔다면 반복문 이용한 요소/마크업(Element) 1개씩 순회
            for (Element element : elements){
                String title = element.text(); //  innerHTML 비슷하게 마크업 사이 텍스트를 반환 <a>여기!</a>
                if (title.isBlank()){continue;}else{list.add(title);}
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    // 2] Jsoup 이용한 HTML 정보 수집, 페이지 이동
    public List<Map<String, Object>> test2(){
        List<Map<String, Object>> list = new ArrayList<>();

        try{
            for(int page = 1; page <= 3; page ++){ // page는 1~ 3 까지
                // 1) 크롤링 URL 주소 (예스24 베스트셀러 일별) ++ 반복문 이용하여 페이지 번호 여러개 요청
                String url = "https://www.yes24.com/product/category/daybestseller";
                url += "?categoryNumber=001"; // 베스트 셀러 카테고리 번호
                url += "&pageNumber="+page; // 크롤링할 페이지 번호 <page 변수 활용>
                url += "&pageSize=24"; //페이지당 제품 수
                // URL 연결
                Document document = Jsoup.connect(url).get();
                // 3) 식별자, 가져올 텍스트가 위치한 식별자와 상위 식별자 1~2 같이 선택한다. <중복 배제>
                Elements nameList = document.select(".info_name .gd_name"); // 책이름
                Elements priceList = document.select(".info_price .txt_num .yes_b"); // 책가격
                Elements imageList = document.select(".img_bdr .lazy"); // 책이미지
                // 4) 반복문 이용하여 여러개 요소/마크업들을 도서별 MAP 구성하여 list 저장
                for (int i = 0; i < nameList.size(); i++) {
                    String name = nameList.get(i).text();
                    String price = priceList.get(i).text();
                    // text() : 마크업 사이 텍스트 반환, attr(속성명) : 해당 속성명의 속성값 반환
                    String image = imageList.get(i).attr("data-original");

                    // 5) DTO/MAP 구성
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name); map.put("price", price); map.put("image", image);
                    list.add(map);

                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return list;
    }


}
/*
         -웹 크롤링 : 웹(페이지의) HtML 정보/자료 수집 과정
         -웹 페이지 마다 크로링 허용 여부 url?robots.txt
            https://www.jobkorea.co.kr/robots.txt
         - 정적페이지 : HTML, 동적페이지 : JS(AXIOS/REACT)
            -정적페이지 : 자바 Jsoup 라이브러리
            -동적페이지 : Selenium 라이브러리 (파이썬 동일)

         - Jsoup 라이브러리 : // Source: https://mvnrepository.com/artifact/org.jsoup/jsoup
                               implementation 'org.jsoup:jsoup:1.22.1'

     */
