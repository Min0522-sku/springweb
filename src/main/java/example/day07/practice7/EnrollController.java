package example.day07.practice7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class EnrollController {
    @Autowired
    private EnrollService enrollService;

    @PostMapping("/enroll")
    public boolean 등록(@RequestBody EnrollDto enrollDto ){
        boolean result = enrollService.등록(enrollDto);
        return result;
    }

    @GetMapping("/enroll")
    public EnrollDto 개별조회(@RequestParam Integer enrollId){
        EnrollDto result = enrollService.개별조회(enrollId);
        return result;
    }
}
