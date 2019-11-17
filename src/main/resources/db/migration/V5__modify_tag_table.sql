
drop table TB_TAG;
create table tb_tag
(
	tno int auto_increment,
	tname varchar(20) not null,
	tcreate_at timestamp,
	constraint tb_tag_pk
		primary key (tno)
);


