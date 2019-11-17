create table tb_ques_tag
(
	qesno int,
	tagno int,
	constraint tb_ques_tag_pk
		primary key (qesno, tagno),
	constraint tb_ques_tag_TB_QUES_QNO_fk
		foreign key (qesno) references TB_QUES
			on delete cascade,
	constraint tb_ques_tag_TB_TAG_TNO_fk
		foreign key (tagno) references TB_TAG
			on delete cascade
);

