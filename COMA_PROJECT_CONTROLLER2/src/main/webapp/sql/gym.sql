CREATE TABLE GYM(
	GYM_NUM INT PRIMARY KEY,						--암벽장 PK
	GYM_NAME VARCHAR(100) NOT NULL,					--암벽장 이름
	GYM_PROFILE VARCHAR(2048),						--암벽장 사진 URL
	GYM_DESCRIPTION CLOB,							--암벽장 설명
	GYM_LOCATION VARCHAR(100) NOT NULL,				--암벽장 위치
	GYM_RESERVATION_CNT INT DEFAULT 10 NOT NULL,		--암벽 예약 가능 개수
	GYM_PRICE INT DEFAULT 20000					--암벽장 이용가격
);
delete GYM;
select * from gym;
commit
SELECT
				G.GYM_NUM,
				G.GYM_NAME,
				G.GYM_PROFILE,
				G.GYM_DESCRIPTION,
				G.GYM_LOCATION,
				G.GYM_RESERVATION_CNT,
				G.GYM_PRICE,
				B.BATTLE_NUM,
				B.BATTLE_GAME_DATE
			FROM
				GYM G
			LEFT JOIN
				BATTLE B
			ON
				G.GYM_NUM = B.BATTLE_GYM_NUM
			WHERE
				G.GYM_NUM =2 AND B.BATTLE_GAME_DATE IS NULL;
				SELECT
						BATTLE_NUM
						BATTLE_GYM_NUM,
						BATTLE_GAME_DATE
						FROM
							BATTLE
						WHERE
						BATTLE_NUM = 1
--(페이지 네이션) 암벽장 전체 출력
SELECT 
   GYM_NUM,
   GYM_NAME,
   GYM_PROFILE,
   GYM_DESCRIPTION,
   GYM_LOCATION,
   GYM_RESERVATION_CNT,
   GYM_PRICE,
   SUB.BATTLE_NUM,
   SUB.BATTLE_GAME_DATE,
   RN
FROM
   (
   SELECT DISTINCT
      G.GYM_NUM,
      G.GYM_NAME,
      G.GYM_PROFILE,
      G.GYM_DESCRIPTION,
      G.GYM_LOCATION,
      G.GYM_RESERVATION_CNT,
      G.GYM_PRICE,
      B.BATTLE_NUM,
      B.BATTLE_GAME_DATE,
      ROW_NUMBER() OVER (ORDER BY G.GYM_NUM) AS RN
   FROM
      GYM G
  left JOIN
      BATTLE B
   ON
      G.GYM_NUM = B.BATTLE_GYM_NUM
      ) SUB
WHERE
   RN BETWEEN 1 AND 5
	
--(페이지 네이션) 암벽장 전체 출력
SELECT 
   GYM_NUM, 
   GYM_NAME, 
   GYM_PROFILE, 
   GYM_DESCRIPTION, 
   GYM_LOCATION, 
   GYM_RESERVATION_CNT, 
   GYM_PRICE, 
   BATTLE_NUM, 
   BATTLE_GAME_DATE
FROM 
   (
   SELECT 
	  	GYM_NUM, 
	  	GYM_NAME, 
	  	GYM_PROFILE, 
	  	GYM_DESCRIPTION, 
	  	GYM_LOCATION, 
	  	GYM_RESERVATION_CNT, 
	  	GYM_PRICE, 
	  	BATTLE_NUM, 
	  	BATTLE_GAME_DATE,
   		ROW_NUMBER() OVER (ORDER BY GYM_NUM) AS RN
	FROM 
   		(
   		SELECT
	      G.GYM_NUM,
	      G.GYM_NAME,
	      G.GYM_PROFILE,
	      G.GYM_DESCRIPTION,
	      G.GYM_LOCATION,
	      G.GYM_RESERVATION_CNT,
	      G.GYM_PRICE,
	      B.BATTLE_NUM,
	      B.BATTLE_GAME_DATE,
	      ROW_NUMBER() OVER (PARTITION BY G.GYM_NAME ORDER BY G.GYM_NUM) AS RN_G,  -- GYM_NAME별로 순번 부여
	      ROW_NUMBER() OVER (ORDER BY G.GYM_NUM) AS ROW_INDEX
   		FROM
      		GYM G
   		LEFT JOIN
      		BATTLE B
   		ON
      		G.GYM_NUM = B.BATTLE_GYM_NUM
   		ORDER BY ROW_INDEX
      )GYM_BATTLE_CTE
	WHERE RN_G = 1
  	 )GYM_BATTLE
WHERE 
   RN BETWEEN 1 AND 5 -- 페이지네이션을 위한 전체 순번 부여

   -- 페이지네이션: 1페이지 (1~5행)
   
   
--PK로 검색
SELECT
	G.GYM_NUM,
	G.GYM_NAME,
	G.GYM_PROFILE,
	G.GYM_DESCRIPTION,
	G.GYM_LOCATION,
	G.GYM_RESERVATION_CNT,
	G.GYM_PRICE,
	B.BATTLE_NUM,
	B.BATTLE_GAME_DATE
FROM
	GYM G
LEFT JOIN
	BATTLE B
ON
	G.GYM_NUM = B.BATTLE_GYM_NUM
WHERE
	G.GYM_NUM = 1

--암벽장의 총 개수
SELECT COUNT(*) AS GYM_TOTAL FROM GYM;

--예약가능 개수 업데이트 GYM_RESERVATION_CNT, GYM_NUM
UPDATE GYM SET GYM_RESERVATION_CNT = ? WHERE GYM_NUM = ?