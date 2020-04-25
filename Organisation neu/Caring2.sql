DROP TABLE fahrzeug cascade constraints;
DROP TABLE zone cascade constraints;
DROP TABLE schaden cascade constraints;
DROP TABLE leiht_aus cascade constraints;
DROP TABLE users cascade constraints;
DROP TABLE meldetSchaden cascade constraints;


CREATE TABLE fahrzeug(
	fid int primary key,
	bezeichnung VARCHAR2(100),
	marke VARCHAR2(100),
    laufleistung INT,
    geo_point SDO_GEOMETRY,
    ausgeliehen VARCHAR2(15) 
);

CREATE TABLE users(
	u_id VARCHAR2(30),
  PRIMARY KEY(u_id)
);

CREATE TABLE zone(
  zid int primary key , 
  bezeichnung VARCHAR2(100),
  kostensatz int,
  radius SDO_GEOMETRY
);

CREATE TABLE schaden(
  sid int primary key,

  bezeichnung VARCHAR2(100),
  beschreibung VARCHAR2(200),
  datum Date
  
);

CREATE TABLE leiht_aus (
  lid int PRIMARY KEY,
  la_fid int,
  la_uid VARCHAR2(30),
  la_zid int,
  von TIMESTAMP,
  bis TIMESTAMP,
  FOREIGN KEY(la_fid) REFERENCES fahrzeug(fid) ON DELETE CASCADE,
  FOREIGN KEY(la_uid) REFERENCES users(u_id)  ON DELETE CASCADE,
  FOREIGN KEY(la_zid) REFERENCES zone(zid) ON DELETE CASCADE 

);

Create Table meldetSchaden (
   ms_uid VARCHAR2(30),
   ms_sid int,
   ms_fid int,
   PRIMARY KEY(ms_uid,ms_sid,ms_fid),
   
    FOREIGN KEY(ms_uid) REFERENCES users(u_id) ON DELETE CASCADE,
    FOREIGN KEY(ms_sid) REFERENCES schaden(sid) ON DELETE CASCADE,
    FOREIGN KEY(ms_fid) REFERENCES fahrzeug(fid) ON DELETE CASCADE 

);

insert into users values('DkTycfHAhYfYAgzOkUXiopMqcfn1');
insert into users values('EcAUWSlaZChBpK7DuzoKKE3Pq8f1');
insert into users values('JM8MyOjSTZVL6xI3U5Xj4IrXRj82');
insert into users values('j6kUD6XYc5WO9p92YcvtDGnh9o43');
insert into users values('vfq4pAEVRPQQSk7JfxVG4WcudUJ3');

insert into fahrzeug values(1,'A5 TFSI','AUDI', 106243, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL),'ja');
insert into fahrzeug values(2,'A6 TDI','AUDI', 56712, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL),'ja');
insert into fahrzeug values(3,'A4 TDI','AUDI', 123, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL),'ja');
insert into fahrzeug values(4,'A3 TDI','AUDI', 8832, SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(12, 14, NULL),NULL,NULL),'ja');

insert into zone values(1,'Villach LIND',100, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(2,'Untere Fellach',10, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(3,'Velden',10, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(5,'Kötschach',1, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));
insert into zone values(4,'Mauthen',1, SDO_GEOMETRY(2003, NULL, NULL, SDO_ELEM_INFO_ARRAY(1,1003,4), SDO_ORDINATE_ARRAY(8,7, 10,9, 8,11)));

insert into schaden values(1,'Bordstein','test',TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into schaden values(2,'Fussgeher','test',TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into schaden values(3,'Motorschaden','test',TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into schaden values(4,'Steinschlag','test',TO_DATE('17/12/2015', 'DD/MM/YYYY'));



commit;