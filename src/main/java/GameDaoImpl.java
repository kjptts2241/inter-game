import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {
    private PreparedStatement pstmt = null;
    private Connection conn = dbConn();

    public Connection dbConn() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "dbdb";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, "root", "1234");
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return conn;
    }

    public void DBclose() {
        try {
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(GameDto dto) {

        try {
            String sql = "INSERT INTO `game` (`userid`, `userpw`, `name`) VALUES (?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserId());
            pstmt.setString(2, dto.getUserPw());
            pstmt.setString(3, dto.getName());


            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("데이터 넣기 실패");
            }else {
                System.out.println("데이터 넣기 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            DBclose();
        }
    }

    @Override
    public GameDto findIdPw(String userId, String userPw) {
        ResultSet rs = null;

        GameDto dto = new GameDto();

        try {
            String sql = "SELECT * FROM `game` WHERE userid = ? AND userpw = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dto.setId(rs.getInt("id"));
                dto.setGusl(rs.getInt("gusl"));
                dto.setName(rs.getString("name"));
                dto.setUserId(rs.getString("userid"));
                dto.setUserPw(rs.getString("userpw"));
                System.out.println("=======================================");
                System.out.println(dto.getName() + " 님 환영합니다.");
                System.out.println("게임을 시작하겠습니다.");
            } else {
                System.out.println("아이디가 존재하지 않습니다.");
                System.out.println("다시 입력해주세요.");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            DBclose();
        }
        return dto;
    }

    @Override
    public void update(int id, int gusl) {

        try {
            String sql = "UPDATE `game` SET `gusl` = ?, update_at = NOW() WHERE  `id` = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gusl);
            pstmt.setInt(2, id);

            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("데이터 넣기 실패");
            }else {
                System.out.println("데이터 넣기 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            DBclose();
        }

    }

    @Override
    public void delete(int id) {

        try {
            String sql = "DELETE FROM `game` WHERE  `id` = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int result = pstmt.executeUpdate();
            if(result == 0){
                System.out.println("데이터 넣기 실패");
            }else {
                System.out.println("데이터 넣기 성공");
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            DBclose();
        }

    }

    @Override
    public List<GameDto> findAll() {
        List<GameDto> list = new ArrayList<>(); // 외부로 전달 시킬 리스트
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM `game`";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                GameDto dto = new GameDto();
                dto.setId(rs.getInt("id"));
                dto.setGusl(rs.getInt("gusl"));
                dto.setName(rs.getString("name"));
                dto.setUserId(rs.getString("userid"));
                dto.setUserPw(rs.getString("userpw"));
                list.add(dto); // list에 dto를 담는다
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            DBclose();
        }
        return list;
    }
}
