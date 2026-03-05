package example.day04.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers(){
        return memberRepository.findAll(); // select
    }

    public boolean saveMember(){
        Member member = new Member( 0L, "유재석" );
        memberRepository.save(member); // insert
        return true;
    }
}
