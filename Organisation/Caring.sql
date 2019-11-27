DROP TABLE fahrzeug cascade constraints;
DROP TABLE zone cascade constraints;
DROP TABLE schaden cascade constraints;
DROP TABLE leiht_aus cascade constraints;
DROP TABLE verursacht_schaden cascade constraints;

CREATE TABLE fahrzeug(
	fid int primary key,
	bezeichnung VARCHAR2(15),
	marke VARCHAR2(15),
  laufleistung INT,
  geo_point SDO_GEOMETRY
);

CREATE TABLE zone(
  zid int primary key, 
  bezeichnung VARCHAR2(15),
  kostensatz int,
  radius SDO_GEOMETRY
);

CREATE TABLE schaden(
  sid int primary key,
  bezeichnung VARCHAR2(15)
);

CREATE TABLE leiht_aus (
  lid int PRIMARY KEY,
  la_fid int,
  la_zid int,
  von DATE,
  bis DATE,
  FOREIGN KEY(la_fid) REFERENCES fahrzeug(fid),
  FOREIGN KEY(la_zid) REFERENCES zone(zid) 
);

CREATE TABLE verursacht_schaden (
  lid int, 
  vs_sid int,
  kosten Number,
  PRIMARY KEY(lid, vs_sid),
  FOREIGN KEY(lid) REFERENCES leiht_aus(lid),
  FOREIGN KEY(vs_sid) REFERENCES schaden(sid)
);

insert into fahrzeug values(1,'A5 TFSI','AUDI', 106243, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL));
insert into fahrzeug values(2,'A6 TDI','AUDI', 56712, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL));
insert into fahrzeug values(3,'A4 TDI','AUDI', 123, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL));
insert into fahrzeug values(4,'A3 TDI','AUDI', 8832, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL));

insert into zone values(1,'Villach LIND',100, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(2,'Untere Fellach',10, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(3,'Velden',10, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(5,'Kötschach',1, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(4,'Mauthen',1, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));

insert into schaden values(1,'Bordstein');
insert into schaden values(2,'Fussgeher');
insert into schaden values(3,'Motorschaden');
insert into schaden values(4,'Steinschlag');

insert into leiht_aus values(1,1,1,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into leiht_aus values(2,2,2,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into leiht_aus values(3,3,2,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into leiht_aus values(4,1,1,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into leiht_aus values(5,2,2,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));

insert into verursacht_schaden values(1, 1, 100);
insert into verursacht_schaden values(2, 2, 10000);
insert into verursacht_schaden values(2, 3, 1000);
insert into verursacht_schaden values(5, 2, 10);

SELECT * FROM FAHRZEUG;

SELECT * FROM zone;

SELECT * FROM schaden;

SELECT * FROM LEIHT_AUS;

SELECT * FROM LEIHT_AUS INNER JOIN ZONE on LEIHT_AUS.LA_ZID = ZONE.ZID
                        INNER JOIN FAHRZEUG on FAHRZEUG.FID = LEIHT_AUS.LA_FID;

SELECT * FROM verursacht_schaden;

SELECT * FROM verursacht_schaden INNER JOIN LEIHT_AUS ON LEIHT_AUS.LID = verursacht_schaden.lid 
                                 INNER JOIN FAHRZEUG on LEIHT_AUS.la_fid = FAHRZEUG.FID
                                 INNER JOIN SCHADEN on verursacht_schaden.VS_SID = SCHADEN.SID;
                                 
commit;