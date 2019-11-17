create table TB_QUES
(
	QNO int AUTO_INCREMENT,
	QTITLE varchar(25),
	QCONTENT text,
	QCREATE_AT timestamp,
	constraint TB_QUES_PK
		primary key (QNO)
);