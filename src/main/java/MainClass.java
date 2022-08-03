import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        // 회원가입 -> 저장

        // 로그인 -> 아이디 패스워드 찾기

        // 구슬의 정보를 수정 -> 업데이트

        // 유저를 삭제 -> 삭제


          Game game = new Game();
          GameDaoImpl dao = new GameDaoImpl();

          // 회원가입 또는 로그인 메소드
          GameDto dto = game.intro();
          System.out.println(dto.getId());

          OddEven oe = new OddEven(dto.getGusl(),10, dto.getName(), dto.getId());

          //게임 시작 메소드
          oe.gameStart();

    }
}
