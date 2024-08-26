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
     { //throws 예외처리 미루기
    	//입력된(들어온)값이 즉 개발자 / 사용자 외부 요인으로 오류가 발생할 수 있으니
    	//Error 처리해서 사용해라 라는 뜻
    	//Defaultvalue input Tag의 처음 값
          String ReturnDefault = "" ;  // 전달할 기본값을 초기변수로 설정해준다.
          if (str == null) // 만약 받아온 파라메타 값이 null 이면
          {
              ReturnDefault =  Defaultvalue ; //기본값을 전달해주고
          }
          else if (str == "" ) // 만약 받아온 파라메타 값이 공백 이면
         {
              ReturnDefault =  Defaultvalue ; //기본값으로 전달해주고
          }
          else // null이거나 공백이 아니라면
          {
                      ReturnDefault = str ; //받아온 파라메타 값으로 변경한다.
          }
           return ReturnDefault ; //최종적으로 변경된 값을 반환해준다.
     }
     /**
     * BASE64 Encoder
     * @param str
     * @return
     */
    public static String base64Encode(String str)  throws java.io.IOException {
    	//throws 예외처리 미루기
    	//입출력되는 값이 오류가 발생할 수 는 있다.
        return java.util.Base64.getEncoder().encodeToString(str.getBytes());
    	//인코더를 Base64로 맞춰서 넘겨준다.
    }

    /**
     * BASE64 Decoder
     * @param str
     * @return
     */
    public static String base64Decode(String str)  throws java.io.IOException {
    	//throws 예외처리 미루기
    	//입출력되는 값이 오류가 발생할 수 는 있다.
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(str);
        return new String(decodedBytes);
        //인코더를 base64로 변경 후 bytes수(보통 배열로 많이 불러온다)를
        //받아서 문자열로 형변환 후 반환해준다.
    }
    %>
    <%
    /**==============================================================
      Description        : 캐릭터셋 정의
      EUC-KR: @ page contentType="text/html;charset=EUC-KR
      UTF-8: @ page contentType="text/html;charset=UTF-8
    ==============================================================**/
    %>
    <%
    /**==============================================================
      Description        :  사용자 샘플코드
    ==============================================================**/
    String charsetType = "UTF-8"; //EUC-KR 또는 UTF-8

    request.setCharacterEncoding(charsetType);
    response.setCharacterEncoding(charsetType);
    //action 파라미터가 null인지 확인 후 action 변수에 추가
    String action = nullcheck(request.getParameter("action"), "");
    if(action.equals("go")) { //action이 go라면 if문 실행

    	//Cafe24 SMS 전송요청 URL
        String sms_url = "";
    
        sms_url = "https://sslsms.cafe24.com/sms_sender.php"; 
     	
     	//Cafe24 SMS아이디
        String user_id = base64Encode("no1fc20000"); 
      	//Cafe24 인증키
        String secure = base64Encode("14569e82136048ea492a1e37d2be82a0");
      	
     	//메시지 전달할 내용
        String msg = base64Encode(nullcheck(request.getParameter("msg"), ""));
     	
      	//문자를 받을 전화번호 - 추가해서 입력 010-0000-0000
        String rphone = base64Encode(nullcheck(request.getParameter("rphone"), "")); 
      	
     	//보낼 전화번호 시작번호 
        String sphone1 = base64Encode(nullcheck(request.getParameter("sphone1"), ""));
     	
      	//보낼 전화번호 중간번호 기업전화번호는 중간(두번째)번호까지만 입력해도 문자가 간다.
        String sphone2 = base64Encode(nullcheck(request.getParameter("sphone2"), ""));
      	
      	//보낼 전화번호 끝번호
        String sphone3 = base64Encode(nullcheck(request.getParameter("sphone3"), ""));
      	
      	//예약 문자를 보낼 날짜 - 없이 작성 20090909
        String rdate = base64Encode(nullcheck(request.getParameter("rdate"), ""));
      	
      	//예약 문자를 보낼 시간 - 없이 작성 173000 : 17시 30분 00초
      	//예약 시간은 최소 10분이상
        String rtime = base64Encode(nullcheck(request.getParameter("rtime"), ""));
      	
        String mode = base64Encode("1");
        String subject = "";
        if(nullcheck(request.getParameter("smsType"), "").equals("L")) {
            subject = base64Encode(nullcheck(request.getParameter("subject"), ""));
        }
        //실제로 문자를 보내면 금액이 지불되기 때문에
        //문자 테스트를 할지 여부를 입력
        String testflag = base64Encode(nullcheck(request.getParameter("testflag"), ""));
        
        //이름을 입력 010-000-0000|홍길동
        String destination = base64Encode(nullcheck(request.getParameter("destination"), ""));
        
        //반복 설정 여부
        String repeatFlag = base64Encode(nullcheck(request.getParameter("repeatFlag"), ""));
        
        //반복 횟수 설정
        String repeatNum = base64Encode(nullcheck(request.getParameter("repeatNum"), ""));
        
        //반복할 시간 설정 15분 이상부터 가능
        String repeatTime = base64Encode(nullcheck(request.getParameter("repeatTime"), ""));
        
        //문자를 전송 후 넘어갈 주소를 입력
        String returnurl = nullcheck(request.getParameter("returnurl"), "smsTest.jsp");
        
        //발송 오류 및 성공 실패 여부를 alert창으로 보여줄지 체크
        //사용할 경우 : 1, 성공시 대화상자(alert)를 생략.
        String nointeractive = nullcheck(request.getParameter("nointeractive"), "");
        
        //문자 형식을 지정
        //S단문, L장문
        String smsType = base64Encode(nullcheck(request.getParameter("smsType"), ""));

        String[] host_info = sms_url.split("/");
        String host = host_info[2];
        String path = "/" + host_info[3];
        int port = 80;

        // 데이터 맵핑 변수 정의
        String arrKey[]
            = new String[] {"user_id","secure","msg", "rphone","sphone1","sphone2","sphone3","rdate","rtime"
                        ,"mode","testflag","destination","repeatFlag","repeatNum", "repeatTime", "smsType", "subject"};
        String valKey[]= new String[arrKey.length] ;
        valKey[0] = user_id;
        valKey[1] = secure;
        valKey[2] = msg;
        valKey[3] = rphone;
        valKey[4] = sphone1;
        valKey[5] = sphone2;
        valKey[6] = sphone3;
        valKey[7] = rdate;
        valKey[8] = rtime;
        valKey[9] = mode;
        valKey[10] = testflag;
        valKey[11] = destination;
        valKey[12] = repeatFlag;
        valKey[13] = repeatNum;
        valKey[14] = repeatTime;
        valKey[15] = smsType;
        valKey[16] = subject;

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
        boundary = "---------------------"+boundary.substring(0,11);

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
		
        //본문 내용 확인
        out.println(data);
        
        InetAddress addr = InetAddress.getByName(host);
        Socket socket = new Socket(host, port);
        // 헤더 전송
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
        wr.write("POST "+path+" HTTP/1.0\r\n");
        wr.write("Content-Length: "+data.length()+"\r\n");
        wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
        wr.write("\r\n");

        // 데이터 전송
        wr.write(data);
        wr.flush();
		out.println("SMSPush BufferedWriter 로그 : "+path + " " + data.length() + " " +boundary);

        // 결과값 얻기
        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),charsetType));
        String line;
        String alert = "";
        ArrayList tmpArr = new ArrayList();
        while ((line = rd.readLine()) != null) {
            tmpArr.add(line);
            out.print("line 로그 : "+line);
        }
        wr.close();
        rd.close();

        String tmpMsg = (String)tmpArr.get(tmpArr.size()-1);
        String[] rMsg = tmpMsg.split(",");
        String Result= rMsg[0]; //발송결과
        String Count= ""; //잔여건수
        
        //결과값 확인
        out.print(tmpMsg);
        if(rMsg.length>1) {Count= rMsg[1]; }

                        //발송결과 알림
        if(Result.equals("success")) {
            alert = "성공적으로 발송하였습니다.";
            alert += " 잔여건수는 "+ Count+"건 입니다.";
        }
        else if(Result.equals("reserved")) {
            alert = "성공적으로 예약되었습니다";
            alert += " 잔여건수는 "+ Count+"건 입니다.";
        }
        else if(Result.equals("3205")) {
            alert = "잘못된 번호형식입니다.";
        }
        else {
            alert = "[Error]"+Result;
        }

        out.println(nointeractive);

        if(nointeractive.equals("1") && !(Result.equals("Test Success!")) && !(Result.equals("success")) && !(Result.equals("reserved")) ) {
            out.println("<script>alert('" + alert + "')</script>");
        }
        else if(!(nointeractive.equals("1"))) {
            out.println("<script>alert('" + alert + "')</script>");
        }


        //out.println("<script>location.href='"+returnurl+"';</script>");
    }
    %>
                