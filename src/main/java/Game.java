import java.util.Objects;
import java.util.Scanner;

public class Game {
    String userCheck; // 회원가입 여부 체크
    String idCheck; // 아이디 체크
    String pwCheck; // 패스워드 체크
    Scanner scanner = new Scanner(System.in);
    GameDaoImpl dao = new GameDaoImpl();
    GameDto dto;

    public GameDto intro(){
        System.out.println("=======================================");
        System.out.println("오징어 게임에 오신 것을 환영합니다.");
        System.out.print("아이디가 있습니까? (Y/N) >> ");
        userCheck = scanner.next();

        if (userCheck.equalsIgnoreCase("N")) {
            register();
            System.out.println("회원가입이 완료되었습니다.");
            System.out.println("=======================================");
            System.out.println("게임을 시작하기 위해 아이디 패스워드를 입력해주세요.");
            dto = start();
        } else if (userCheck.equalsIgnoreCase("Y")) {
            System.out.println("=======================================");
            System.out.println("게임을 시작하기 위해 아이디 패스워드를 입력해주세요.");
            dto = start();
        }
        else{
            System.out.println("대소문자를 구분하여 정확히 입력해주세요.");
            intro();
        }
        return dto;
    }

    // 회원가입 메소드
    public void register(){
        System.out.println("회원 가입을 진행하겠습니다.");
        System.out.print("아이디 입력 >> ");
        String userId = scanner.next();
        System.out.print("패스워드 입력 >> ");
        String userPw = scanner.next();
        System.out.print("이름 입력 >> ");
        String name = scanner.next();

        GameDto dto = new GameDto();
        dto.setUserId(userId);
        dto.setUserPw(userPw);
        dto.setName(name);

        dao.save(dto);
    }

    // 로그인 메소드
    public GameDto start() {
        System.out.println("=======================================");
        System.out.print("아이디를 입력해주세요. >> ");
        idCheck = scanner.next();
        System.out.println("=======================================");
        System.out.print("패스워드를 입력해주세요. >> ");
        pwCheck = scanner.next();

        GameDto dto = dao.findIdPw(idCheck, pwCheck);

        if (dto == null) {
            System.out.println("로그인에 실패하셨습니다.");
            start();
        } else {
            System.out.println("로그인 하셨습니다.");
        }
        return dto;
    }

}