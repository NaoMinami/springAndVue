DROP TABLE IF EXISTS members;
create sequence itemseq;
CREATE TABLE `members` (
	`id` NUMBER default itemseq.nextval,
	`name` VARCHAR2(80),
	primary key (`id`)
);