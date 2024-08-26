<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.security.*, java.io.*, java.net.*" %>
    <%@ page import="java.util.*" %>
    <%@ page import="java.net.*" %>
    <%@ page import="java.io.*" %>
    <%@ page trimDirectiveWhitespaces="true" %>
<%
try {
    String apiUrl = "https://sslsms.cafe24.com/smsSenderPhone.php";
    String userAgent = "Mozilla/5.0";
    String postParams = "userId=no1fc20000&passwd=14569e82136048ea492a1e37d2be82a0";
    URL obj = new URL(apiUrl);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    con.setRequestMethod("POST");
    con.setRequestProperty("User-Agent", userAgent);

    // POST 요청을 보내기 위한 설정
    con.setDoOutput(true);

    // try-with-resources를 사용해 자동으로 스트림을 닫도록 함
    try (OutputStream os = con.getOutputStream()) {
        os.write(postParams.getBytes("UTF-8"));  // UTF-8 인코딩 사용
        os.flush();
    }

    int responseCode = con.getResponseCode();
    System.out.println("POST Response Code :: " + responseCode);

    // 응답 코드가 200일 경우, 즉 성공했을 때
    if (responseCode == HttpURLConnection.HTTP_OK) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
            String inputLine;
            StringBuilder buf = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                buf.append(inputLine);
            }
            // 응답 결과 출력
            System.out.println(buf.toString());
        }
    } else {
        System.out.println("POST 요청 실패");
    }

} catch (IOException ex) {
    ex.printStackTrace();  // 예외가 발생하면 스택 트레이스 출력
}


%>