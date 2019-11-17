alter table TB_QUES
	add qauthor int;

alter table TB_QUES
	add constraint TB_QUES___fk_qauthor
		foreign key (qauthor) references TB_USER
			on delete set null;