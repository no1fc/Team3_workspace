<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.security.*, java.io.*, java.net.*" %>
    <%!
     /**==============================================================
          Description        :  사용 함수 선언
     ==============================================================**/
     /**
     * nullcheck
     * @param str, Defaultvalue
     * @return
     */

     public static String nullcheck(String str,  String Defaultvalue ) throws Exception
     {
          String ReturnDefault = "" ;
          if (str == null)
          {
              ReturnDefault =  Defaultvalue ;
          }
          else if (str == "" )
         {
              ReturnDefault =  Defaultvalue ;
          }
          else
          {
                      ReturnDefault = str ;
          }
           return ReturnDefault ;
     }
     /**
     * BASE64 Encoder
     * @param str
     * @return
     */
     public static String base64Encode(String str)  throws java.io.IOException {
         return java.util.Base64.getEncoder().encodeToString(str.getBytes());
     }

     /**
      * BASE64 Decoder
      * @param str
      * @return
      */
     public static String base64Decode(String str)  throws java.io.IOException {
         byte[] decodedBytes = java.util.Base64.getDecoder().decode(str);
         return new String(decodedBytes);
     }
    %>
    <%
    /**==============================================================
          Description        :  사용자 샘플코드
    ==============================================================**/

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String sms_url = "";
    sms_url = "https://sslsms.cafe24.com/sms_remain.php"; // SMS 잔여건수 요청 URL
    String user_id = base64Encode("no1fc20000"); // SMS아이디
    String secure = base64Encode("14569e82136048ea492a1e37d2be82a0");//인증키
    String mode = base64Encode("1");
    String testflag = base64Encode(nullcheck(request.getParameter("testflag"), ""));
    String[] host_info = sms_url.split("/");
    String host = host_info[2];
    String path = "/" + host_info[3];
    int port = 80;

    // 데이터 맵핑 변수 정의
    String arrKey[]
        = new String[] {"user_id" ,"secure","mode"};
    String valKey[]= new String[arrKey.length] ;
    valKey[0] = user_id;
    valKey[1] = secure;
    valKey[2] = mode;

    String boundary = "";
    Random rnd = new Random();
    String rndKey = Integer.toString(rnd.nextInt(32000));
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] bytData = rndKey.getBytes();
    md.update(bytData);
    byte[] digest = md.digest();
    for(int i =0;i<digest.length;i++)
    {
        boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
    }
    boundary = "---------------------"+boundary.substring(0,10);

    // 본문 생성
    String data = "";
    String index = "";
    String value = "";
    for (int i=0;i<arrKey.length; i++)
    {
        index =  arrKey[i];
        value = valKey[i];
        data +="--"+boundary+"\r\n";
        data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
        data += "\r\n"+value+"\r\n";
        data +="--"+boundary+"\r\n";
    }

    //out.println(data);

    InetAddress addr = InetAddress.getByName(host);
    Socket socket = new Socket(host, port);
    // 헤더 전송
    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
    wr.write("POST "+path+" HTTP/1.0\r\n");
    wr.write("Content-Length: "+data.length()+"\r\n");
    wr.write("Content-type: multipart/form-data, charset=utf-8, boundary="+boundary+"\r\n");
    wr.write("\r\n");

    // 데이터 전송
    wr.write(data);
    wr.flush();

    // 결과값 얻기
    BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
    String line;
    String alert = "";
    ArrayList tmpArr = new ArrayList();
    while ((line = rd.readLine()) != null) {
        tmpArr.add(line);
    }
    wr.close();
    rd.close();

    String tmpMsg = (String)tmpArr.get(tmpArr.size()-1);
    out.println(tmpMsg);
    %>

                