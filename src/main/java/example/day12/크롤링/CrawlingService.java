package example.day12.크롤링;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
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

    // [3]
    public Map< String, Object > test3(){
        // 1] 크롬 드라이버  설치
        WebDriverManager.chromedriver().setup();
        // 2] 크롤링 할 웹 주소
        String url = "https://weather.daum.net/";
        // 3] 크롬 드라이버 객체 생성
        // * 드라이버 옵션
        ChromeOptions options = new ChromeOptions();
        // 크롬 백그라운드 실행
        // options.addArguments( "--headless=new" , "--disable-gpu");
        WebDriver webDriver = new ChromeDriver( options );
        // 4] 크롬 드라이버 객체 에 크롤링할 주소 넣기
        webDriver.get( url );
        // 5] 해당 페이지는 동적( 데이터를 표현하는데 부분적 시간필요) 페이지
        // new WebDriverWait ( 현재크롬객체 , Duratuon.ofXXX( 시간단위 ) )
        WebDriverWait wait = new WebDriverWait( webDriver  , Duration.ofSeconds( 1 ) );
        // 6] 크롤링할 선택자 , element/요소/마크업/<마크업>
        // WebElement 변수명 = wait.until( ExpectedConditions.presenceOfElementLocated(By.cssSelector("선택자"));
        // 6-1) 온도 : info_weather -> num_deg
        WebElement temp = wait.until(ExpectedConditions.presenceOfElementLocated(   By.cssSelector(".info_weather .num_deg")) );
        System.out.println( temp.getText() ); // 크롤링된 요소/마크업의 텍스트 확인
        WebElement temp2 = wait.until( ExpectedConditions.presenceOfElementLocated( By.cssSelector(".tooltip_icon .ico_airstat1" ) ) );
        System.out.println( temp2.getText() );
        // 7] 가져온 정보들을 dto/map 구성
        Map< String, Object > map = new HashMap<>();
        map.put( "온도" , temp2.getText() );
        map.put( "초미세먼지" , temp.getText() );
        // 8] 안전하게 드라이버 객체 직접 종료
        webDriver.quit();
        // 9] map 반환
        return map ; // 임의
    }



}
/*
        - 웹크롤링 : 웹(페이지의) HTML 정보/자료 수집 과정
        - 웹페이지 마다 크롤링 허용 여부 확인 : URL/robots.txt
            - https://www.jobkorea.co.kr/robots.txt
        - 정적페이지 : HTML  , 동적페이지 : JS( AXIOS/REACT )
            - 정적페이지 : Jsoup 라이브러리
            - 동적페이지 : Selenium 라이브러리 ( * 파이썬 동일 * )
        - Jsoup 라이브러리 : implementation 'org.jsoup:jsoup:1.22.1'
        - Selenium 라이브러리 :
            implementation 'org.seleniumhq.selenium:selenium-java:4.41.0'
            implementation 'io.github.bonigarcia:webdrivermanager:6.3.3'

            - 스프링 지원하는 공식 라이브러리 : https://start.spring.io/
            - 그외 라이브러리 : http://mvnrepository.com/
    */
