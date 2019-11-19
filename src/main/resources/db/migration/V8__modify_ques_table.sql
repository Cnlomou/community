alter table TB_QUES
	add qupdate_at timestamp;

alter table TB_QUES
	add qread int default 0;

alter table TB_QUES
	add qlike int default 0;

alter table TB_QUES
	add qshit int default 0;
