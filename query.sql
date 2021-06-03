create table member (
    memNum number,
    memName varchar2(36),
    memPass varchar2(100),
    memEmail varchar2(30),
    memPhone varchar2(20),
    memLevel varchar2(10) default 'member',
    memBank varchar2(30),
    memAccount varchar2(20),
    constraint member_pk primary key(memnum)
);

create table board (
    boardNum number,
    memNum number,
    boardTitle varchar2(100),
    boardContent varchar2(3000),
    boardDate date,
    boardType char(1),
    CONSTRAINT board_pk primary key(boardnum),
);

create table fund (
	fundNum number,
	memNum number,
	fundstartdate date,
	fundenddate date,
	fundmoney number,
	funddday date,
	fundtitle varchar2(100),
  fundintro varchar2(3000),
  constraint fund_pk primary key(fundnum),
);

create table reward (
    rwrdNum number,
    rwrdName varchar2(60),
    fundNum number,
    rwrdPrice number,
    CONSTRAINT reward_pk primary key(rwrdnum),
);

create table Payment (
    payNum NUMBER,
    payTime date,
    fundNum NUMBER,
    memNUM number,
    payCardNum varchar2(16),
    paycvc char(3),
    paycardexpire varchar2(4),
    paydona number default 0,
    payTotal number,
    payAdress varchar2(300),
    constraint payment_pk PRIMARY KEY(paynum),
);

create table item (
    itemNum number,
    rwrdNum number,
    rwrdCount number default 1,
    payNum number,
    constraint item_pk primary key(itemnum),
);

create table reply(
    replyNum number,
    memNum number,
    replyContent varchar2(1000),
    replyDate date,
    boardNum number,
    constraint reply_pk primary key(replynum),
);

create table emotion(
	emonum number,
	boardnum number,
	memnum number,
	emoexpress char(1),
  constraint emo_pk primary key(emonum),
);

create table picture (
    picNum number,
    picUuid varchar2(300),
    picPath varchar2(1000),
    picName varchar2(500),
    picTail varchar2(10),
    picclass char(1),
    postNum number,
    constraint pic_pk primary key(picnum)
);

create table subscribe (
    artist number,
    audience number
);


create sequence member_seq;
create sequence board_seq;
create sequence fund_seq;
create sequence reply_seq;
create sequence emotion_seq;
create sequence reward_seq;
create sequence payment_seq;
create sequence item_seq;
create sequence picture_seq;

COMMENT ON TABLE MEMBER IS '회원';
COMMENT ON COLUMN MEMBER.memNum IS '회원 번호';
COMMENT ON COLUMN MEMBER.memname IS '회원 이름';
COMMENT ON COLUMN MEMBER.mempass IS '비밀번호';
COMMENT ON COLUMN MEMBER.mememail IS '이메일 주소';
COMMENT ON COLUMN MEMBER.memphone IS '연락처';
COMMENT ON COLUMN MEMBER.memlevel IS '회원 등급';
COMMENT ON COLUMN MEMBER.membank IS '은행 이름';
COMMENT ON COLUMN MEMBER.memaccount IS '계좌 번호';

COMMENT ON TABLE BOARD IS '게시글';
COMMENT ON COLUMN BOARD.BOARDNUM IS '게시글 번호';
COMMENT ON COLUMN BOARD.MEMNUM IS '작성자 번호';
COMMENT ON COLUMN BOARD.BOARDTITLE IS '게시글 제목';
COMMENT ON COLUMN BOARD.BOARDCONTENT IS '게시글 내용';
COMMENT ON COLUMN BOARD.BOARDDATE IS '등록 날짜';
COMMENT ON COLUMN BOARD.BOARDTYPE IS '게시글 분류';

COMMENT ON TABLE fund IS '펀딩';
COMMENT ON COLUMN fund.FUNDNUM IS '펀딩 번호';
COMMENT ON COLUMN fund.MEMNUM IS '작가 번호';
COMMENT ON COLUMN fund.FUNDSTARTDATE IS '펀딩 시작일';
COMMENT ON COLUMN fund.FUNDENDDATE IS '펀딩 종료일';
COMMENT ON COLUMN fund.FUNDMONEY IS '목표 금액';
COMMENT ON COLUMN fund.FUNDDDAY IS '전시일자';
COMMENT ON COLUMN fund.FUNDTITLE IS '펀딩 제목';
COMMENT ON COLUMN fund.FUNDINTRO IS '펀딩 소개글';

COMMENT ON TABLE REWARD IS '리워드';
COMMENT ON COLUMN REWARD.RWRDNUM IS '리워드 번호';
COMMENT ON COLUMN REWARD.RWRDNAME IS '리워드 이름';
COMMENT ON COLUMN REWARD.FUNDNUM IS '펀딩 번호';
COMMENT ON COLUMN REWARD.RWRDPRICE IS '리워드 가격';

COMMENT ON TABLE PAYMENT IS '결제';
COMMENT ON COLUMN PAYMENT.PAYNum IS '결제 번호';
COMMENT ON COLUMN PAYMENT.PAYTime IS '결제 시간';
COMMENT ON COLUMN PAYMENT.FUNDNum IS '펀딩 번호';
COMMENT ON COLUMN PAYMENT.MEMNUM IS '회원 번호';
COMMENT ON COLUMN PAYMENT.PAYCardNum IS '카드 번호';
COMMENT ON COLUMN PAYMENT.PAYCVC IS '카드 cvc';
COMMENT ON COLUMN PAYMENT.PAYCARDEXPIRE IS '카드 유효기간';
COMMENT ON COLUMN PAYMENT.PAYDONA IS '추가 후원금';
COMMENT ON COLUMN PAYMENT.PAYTotal IS '결재 총액';
COMMENT ON COLUMN PAYMENT.PAYADRESS IS '고객 주소';

COMMENT ON TABLE ITEM IS '결제품목';
COMMENT ON COLUMN ITEM.ITEMNUM IS '결제품목 번호';
COMMENT ON COLUMN ITEM.RWRDNUM IS '리워드 번호';
COMMENT ON COLUMN ITEM.RWRDCOUNT IS '수량';
COMMENT ON COLUMN ITEM.PAYNUM IS '결제 번호';

COMMENT ON TABLE REPLY IS '댓글';
COMMENT ON COLUMN REPLY.REPLYNUM IS '댓글 번호';
COMMENT ON COLUMN REPLY.MEMNUM IS '댓글 작성자 번호';
COMMENT ON COLUMN REPLY.REPLYCONTENT IS '댓글 내용';
COMMENT ON COLUMN REPLY.REPLYDATE IS '작성 일자';
COMMENT ON COLUMN REPLY.BOARDNUM IS '게시글 번호';

COMMENT ON TABLE EMOTION IS '감정표현';
COMMENT ON COLUMN EMOTION.EMONUM IS '감정표현 번호';
COMMENT ON COLUMN EMOTION.BOARDNUM IS '게시글 번호';
COMMENT ON COLUMN EMOTION.MEMNUM IS '회원 번호';
COMMENT ON COLUMN EMOTION.EMOEXPRESS IS '선택한 감정';

COMMENT ON TABLE picture IS '사진';
COMMENT ON COLUMN picture.picNum is '사진 번호';
COMMENT ON COLUMN picture.picUuid is 'UUID';
COMMENT ON COLUMN picture.picPath is '파일 경로';
COMMENT ON COLUMN picture.picName is '파일 명';
COMMENT ON COLUMN picture.picTail is '파일 확장자';
COMMENT ON COLUMN picture.picclass is '글 종류';
COMMENT ON COLUMN picture.postNum is '글 번호';

COMMENT ON TABLE subscribe IS '사진';
COMMENT ON COLUMN subscribe.artist is '작가 번호';
COMMENT ON COLUMN subscribe.audience is '팔로워 번호';