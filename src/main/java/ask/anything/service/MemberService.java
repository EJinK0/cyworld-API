package  ask.anything.service;

import ask.anything.entity.Member;
import ask.anything.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional //변경
    public String join(Member member) {
        validateMemberId(member.getMemberId()); //중복 회원 검증
        memberRepository.save(member);
        return String.valueOf(member.getMemberId());
    }

    public void validateMemberId(String memberId) {
        Optional<Member> findMembers = memberRepository.findByMemberId(memberId);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 조회
     */
    public Member findMember(String memberId, String memberPw) {
        // 1. ID로 먼저 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        // 2. 비밀번호 일치 여부 검증
        if (!passwordEncoder.matches(memberPw, member.getMemberPw())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return memberRepository.findById(member.getMemberNo()).orElse(null);
    }
}