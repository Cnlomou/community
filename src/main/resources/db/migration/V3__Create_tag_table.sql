create table tb_tag
(
	tno int auto_increment,
	ttag varchar(10),
	tquesno int,
	constraint tb_tag_pk
		primary key (tno),
	constraint tb_tag_TB_QUES_QNO_fk
		foreign key (tquesno) references TB_QUES
			on delete set null
);

