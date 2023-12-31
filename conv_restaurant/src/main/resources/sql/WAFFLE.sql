show user;

/* 회원 */
DROP TABLE MEMBER 
	CASCADE CONSTRAINTS;

/* 찜한 목록 */
DROP TABLE WISHLIST 
	CASCADE CONSTRAINTS;

/* 편의점 상품 */
DROP TABLE PRODUCT 
	CASCADE CONSTRAINTS;

/* 게시판 이미지 파일 */
DROP TABLE R_IMAGE_FILE 
	CASCADE CONSTRAINTS;

/* 인기 레시피 */
DROP TABLE HOT_RECIPE 
	CASCADE CONSTRAINTS;

/* 추천 레시피 */
DROP TABLE REC_RECIPE 
	CASCADE CONSTRAINTS;

/* 상품 카테고리 */
DROP TABLE CATEGORY 
	CASCADE CONSTRAINTS;

/* 편의점 */
DROP TABLE CONV_STORE 
	CASCADE CONSTRAINTS;

/* 맛있는 레시피 게시글 */
DROP TABLE BOARD 
	CASCADE CONSTRAINTS;

/* 제조사 */
DROP TABLE MF_COMPANY 
	CASCADE CONSTRAINTS;

/* 회원 이미지 파일 */
DROP TABLE M_IMAGE_FILE 
	CASCADE CONSTRAINTS;

/* 상품 이미지 파일 */
DROP TABLE P_IMAGE_FILE 
	CASCADE CONSTRAINTS;
    
DROP SEQUENCE HOT_RECIPE_SEQ;
DROP SEQUENCE M_IMAGE_FILE_SEQ;
DROP SEQUENCE P_IMAGE_FILE_SEQ;
DROP SEQUENCE R_IMAGE_FILE_SEQ;
DROP SEQUENCE REC_RECIPE_SEQ;
DROP SEQUENCE RECIPE_BOARD_SEQ;
DROP SEQUENCE WISHLIST_SEQ;

/* 회원 */
CREATE TABLE MEMBER (
	member_id VARCHAR2(100) NOT NULL, /* 회원 ID */
	member_name VARCHAR2(50) NOT NULL, /* 회원이름 */
	member_email VARCHAR2(150) NOT NULL, /* 이메일 */
	member_nickname VARCHAR2(20) NOT NULL, /* 닉네임 */
	member_phonenumber VARCHAR2(50) NOT NULL, /* 휴대폰번호 */
	member_password VARCHAR2(200) NOT NULL, /* 비밀번호 */
	member_gender VARCHAR2(30) NOT NULL, /* 성별 */
	member_age VARCHAR2(30) NOT NULL, /* 나이 */
	member_joindate DATE NOT NULL, /* 가입날짜 */
	member_state VARCHAR2(4) NOT NULL, /* 가입상태 */
	member_banstate DATE NOT NULL /* 신고여부 */
);

CREATE UNIQUE INDEX PK_MEMBER
	ON MEMBER (
		member_id ASC
	);

ALTER TABLE MEMBER
	ADD
		CONSTRAINT PK_MEMBER
		PRIMARY KEY (
			member_id
		);

/* 찜한 목록 */
CREATE TABLE WISHLIST (
	wishlist_id NUMBER NOT NULL, /* 찜한목록 ID */
	member_id VARCHAR2(100), /* 회원 ID */
	board_id NUMBER /* 편의점 게시판 ID */
);

CREATE UNIQUE INDEX PK_WISHLIST
	ON WISHLIST (
		wishlist_id ASC
	);

ALTER TABLE WISHLIST
	ADD
		CONSTRAINT PK_WISHLIST
		PRIMARY KEY (
			wishlist_id
		);

/* 편의점 상품 */
CREATE TABLE PRODUCT (
	product_id NUMBER NOT NULL, /* 편의점 상품 ID */
	product_name VARCHAR2(50) NOT NULL, /* 상품 이름 */
	product_price NUMBER NOT NULL, /* 가격 */
	conv_store NUMBER, /* 편의점 ID */
	category_id NUMBER, /* 상품 카테고리 ID */
	product_mnfctrng_cmpn_id NUMBER /* 제조사 ID */
);

CREATE UNIQUE INDEX PK_PRODUCT
	ON PRODUCT (
		product_id ASC
	);

ALTER TABLE PRODUCT
	ADD
		CONSTRAINT PK_PRODUCT
		PRIMARY KEY (
			product_id
		);

/* 게시판 이미지 파일 */
CREATE TABLE R_IMAGE_FILE (
	r_image_file_id NUMBER NOT NULL, /* 이미지 파일 ID */
	r_image_file_name VARCHAR2(50) NOT NULL, /* 이미지 파일명 */
	r_image_file_path VARCHAR2(100) NOT NULL, /* 이미지 파일 저장 경로 */
	r_image_file_type VARCHAR2(20) NOT NULL, /* 이미지 파일 확장자 타입 */
	board_id NUMBER /* 편의점 게시판 ID */
);

CREATE UNIQUE INDEX PK_R_IMAGE_FILE
	ON R_IMAGE_FILE (
		r_image_file_id ASC
	);

ALTER TABLE R_IMAGE_FILE
	ADD
		CONSTRAINT PK_R_IMAGE_FILE
		PRIMARY KEY (
			r_image_file_id
		);

/* 인기 레시피 */
CREATE TABLE HOT_RECIPE (
	hot_recipe_id NUMBER NOT NULL, /* 인기 레시피 ID */
	board_id NUMBER /* 편의점 게시판 ID */
);

CREATE UNIQUE INDEX PK_HOT_RECIPE
	ON HOT_RECIPE (
		hot_recipe_id ASC
	);

ALTER TABLE HOT_RECIPE
	ADD
		CONSTRAINT PK_HOT_RECIPE
		PRIMARY KEY (
			hot_recipe_id
		);

/* 추천 레시피 */
CREATE TABLE REC_RECIPE (
	rec_recipe_id NUMBER NOT NULL, /* 추천 레시피 ID */
	board_id NUMBER /* 편의점 게시판 ID */
);

CREATE UNIQUE INDEX PK_REC_RECIPE
	ON REC_RECIPE (
		rec_recipe_id ASC
	);

ALTER TABLE REC_RECIPE
	ADD
		CONSTRAINT PK_REC_RECIPE
		PRIMARY KEY (
			rec_recipe_id
		);

/* 상품 카테고리 */
CREATE TABLE CATEGORY (
	category_id NUMBER NOT NULL, /* 상품 카테고리 ID */
	category_name VARCHAR2(20) NOT NULL /* 상품 카테고리명 */
);

CREATE UNIQUE INDEX PK_CATEGORY
	ON CATEGORY (
		category_id ASC
	);

ALTER TABLE CATEGORY
	ADD
		CONSTRAINT PK_CATEGORY
		PRIMARY KEY (
			category_id
		);

/* 편의점 */
CREATE TABLE CONV_STORE (
	conv_store NUMBER NOT NULL, /* 편의점 ID */
	conv_store_name VARCHAR2(20) NOT NULL /* 편의점 상호명 */
);

CREATE UNIQUE INDEX PK_CONV_STORE
	ON CONV_STORE (
		conv_store ASC
	);

ALTER TABLE CONV_STORE
	ADD
		CONSTRAINT PK_CONV_STORE
		PRIMARY KEY (
			conv_store
		);

/* 맛있는 레시피 게시글 */
CREATE TABLE BOARD (
	board_id NUMBER NOT NULL, /* 편의점 게시판 ID */
	board_name VARCHAR2(150) NOT NULL, /* 게시글 제목 */
	board_cookingtime VARCHAR2(30) NOT NULL, /* 조리 시간 */
	board_cookinglevel VARCHAR2(4) NOT NULL, /* 조리 난이도 */
	board_totalprice NUMBER NOT NULL, /* 가격 */
	board_likecount NUMBER, /* 좋아요 개수 */
	member_id VARCHAR2(100), /* 회원 ID */
	product_id NUMBER /* 편의점 상품 ID */
);

CREATE UNIQUE INDEX PK_BOARD
	ON BOARD (
		board_id ASC
	);

ALTER TABLE BOARD
	ADD
		CONSTRAINT PK_BOARD
		PRIMARY KEY (
			board_id
		);

/* 제조사 */
CREATE TABLE MF_COMPANY (
	mf_company_id NUMBER NOT NULL, /* 제조사 ID */
	mf_company_name VARCHAR2(50) NOT NULL /* 제조사명 */
);

CREATE UNIQUE INDEX PK_MF_COMPANY
	ON MF_COMPANY (
		mf_company_id ASC
	);

ALTER TABLE MF_COMPANY
	ADD
		CONSTRAINT PK_MF_COMPANY
		PRIMARY KEY (
			mf_company_id
		);

/* 회원 이미지 파일 */
CREATE TABLE M_IMAGE_FILE (
	m_image_file_id NUMBER NOT NULL, /* 이미지 파일 ID */
	m_image_file_name VARCHAR2(50) NOT NULL, /* 이미지 파일명 */
	m_image_file_path VARCHAR2(100) NOT NULL, /* 이미지 파일 저장 경로 */
	m_image_file_type VARCHAR2(20) NOT NULL, /* 이미지 파일 확장자 타입 */
	member_id VARCHAR2(100) /* 회원 ID */
);

CREATE UNIQUE INDEX PK_M_IMAGE_FILE
	ON M_IMAGE_FILE (
		m_image_file_id ASC
	);

ALTER TABLE M_IMAGE_FILE
	ADD
		CONSTRAINT PK_M_IMAGE_FILE
		PRIMARY KEY (
			m_image_file_id
		);

/* 상품 이미지 파일 */
CREATE TABLE P_IMAGE_FILE (
	p_image_file_id NUMBER NOT NULL, /* 이미지 파일 ID */
	p_image_file_name VARCHAR2(50) NOT NULL, /* 이미지 파일명 */
	p_image_file_path VARCHAR2(100) NOT NULL, /* 이미지 파일 저장 경로 */
	p_image_file_type VARCHAR2(20) NOT NULL, /* 이미지 파일 확장자 타입 */
	product_id NUMBER /* 편의점 상품 ID */
);

CREATE UNIQUE INDEX PK_P_IMAGE_FILE
	ON P_IMAGE_FILE (
		p_image_file_id ASC
	);

ALTER TABLE P_IMAGE_FILE
	ADD
		CONSTRAINT PK_P_IMAGE_FILE
		PRIMARY KEY (
			p_image_file_id
		);

ALTER TABLE WISHLIST
	ADD
		CONSTRAINT FK_MEMBER_TO_WISHLIST
		FOREIGN KEY (
			member_id
		)
		REFERENCES MEMBER (
			member_id
		)ON DELETE CASCADE;

ALTER TABLE WISHLIST
	ADD
		CONSTRAINT FK_BOARD_TO_WISHLIST
		FOREIGN KEY (
			board_id
		)
		REFERENCES BOARD (
			board_id
		)ON DELETE CASCADE;

ALTER TABLE PRODUCT
	ADD
		CONSTRAINT FK_CONV_STORE_TO_PRODUCT
		FOREIGN KEY (
			conv_store
		)
		REFERENCES CONV_STORE (
			conv_store
		);

ALTER TABLE PRODUCT
	ADD
		CONSTRAINT FK_CATEGORY_TO_PRODUCT
		FOREIGN KEY (
			category_id
		)
		REFERENCES CATEGORY (
			category_id
		);

ALTER TABLE PRODUCT
	ADD
		CONSTRAINT FK_MF_COMPANY_TO_PRODUCT
		FOREIGN KEY (
			product_mnfctrng_cmpn_id
		)
		REFERENCES MF_COMPANY (
			mf_company_id
		);

ALTER TABLE R_IMAGE_FILE
	ADD
		CONSTRAINT FK_BOARD_TO_R_IMAGE_FILE
		FOREIGN KEY (
			board_id
		)
		REFERENCES BOARD (
			board_id
		)ON DELETE CASCADE;

ALTER TABLE HOT_RECIPE
	ADD
		CONSTRAINT FK_BOARD_TO_HOT_RECIPE
		FOREIGN KEY (
			board_id
		)
		REFERENCES BOARD (
			board_id
		)ON DELETE CASCADE;

ALTER TABLE REC_RECIPE
	ADD
		CONSTRAINT FK_BOARD_TO_REC_RECIPE
		FOREIGN KEY (
			board_id
		)
		REFERENCES BOARD (
			board_id
		)ON DELETE CASCADE;

ALTER TABLE BOARD
	ADD
		CONSTRAINT FK_MEMBER_TO_BOARD
		FOREIGN KEY (
			member_id
		)
		REFERENCES MEMBER (
			member_id
		);

ALTER TABLE BOARD
	ADD
		CONSTRAINT FK_PRODUCT_TO_BOARD
		FOREIGN KEY (
			product_id
		)
		REFERENCES PRODUCT (
			product_id
		);

ALTER TABLE M_IMAGE_FILE
	ADD
		CONSTRAINT FK_MEMBER_TO_M_IMAGE_FILE
		FOREIGN KEY (
			member_id
		)
		REFERENCES MEMBER (
			member_id
		)ON DELETE CASCADE;

ALTER TABLE P_IMAGE_FILE
	ADD
		CONSTRAINT FK_PRODUCT_TO_P_IMAGE_FILE
		FOREIGN KEY (
			product_id
		)
		REFERENCES PRODUCT (
			product_id
		)ON DELETE CASCADE;
        
CREATE SEQUENCE WISHLIST_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;
       
CREATE SEQUENCE RECIPE_BOARD_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;
    
CREATE SEQUENCE HOT_RECIPE_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;
       
CREATE SEQUENCE REC_RECIPE_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;
       
CREATE SEQUENCE M_IMAGE_FILE_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;
       
CREATE SEQUENCE P_IMAGE_FILE_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;
       
CREATE SEQUENCE R_IMAGE_FILE_SEQ
       INCREMENT BY 1
       START WITH 1
       MINVALUE 1
       MAXVALUE 9999
       NOCYCLE
       NOCACHE
       NOORDER;
    
COMMIT;