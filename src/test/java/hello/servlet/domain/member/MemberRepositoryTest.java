package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    //테스트 끝날 때 마다 실행
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId());

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findall() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        Member member3 = new Member("member3", 40);
        Member member4 = new Member("member4", 50);

        //when
        Member saveMember1 = memberRepository.save(member1);
        Member saveMember2 = memberRepository.save(member2);
        Member saveMember3 = memberRepository.save(member3);
        Member saveMember4 = memberRepository.save(member4);

        //then
        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(4);
        assertThat(result).contains(member1, member2);
    }

}