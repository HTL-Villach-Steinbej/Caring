DROP TABLE fahrzeug cascade constraints;
DROP TABLE benutzer cascade constraints;
DROP TABLE zone cascade constraints;
DROP TABLE schaden cascade constraints;
DROP TABLE leiht_aus cascade constraints;
DROP TABLE verursacht_schaden cascade constraints;

CREATE TABLE fahrzeug(
	fid VARCHAR2(2) primary key,
	bezeichnung VARCHAR2(15),
	marke VARCHAR2(15)
  );
  
CREATE TABLE benutzer(
  bid VARCHAR2(2) primary key,
  name VARCHAR2(15)
);
CREATE TABLE zone(
  zid VARCHAR(2) primary key, 
  bezeichnung VARCHAR2(15),
  kostensatz NUMBER
);

CREATE TABLE schaden(
  sid VARCHAR2(2) primary key,
  bezeichnung VARCHAR2(15)
);


CREATE TABLE leiht_aus (
  la_fid VARCHAR(2),
  la_bid VARCHAR(2),
  la_zid VARCHAR(2),
  start_zone VARCHAR2(2),
  von DATE,
  bis DATE,
  PRIMARY KEY(la_fid,la_bid),
  FOREIGN KEY(la_fid) REFERENCES fahrzeug(fid),
  FOREIGN KEY(la_bid) REFERENCES benutzer(bid),
  FOREIGN KEY(la_zid) REFERENCES zone(zid) 
);

CREATE TABLE verursacht_schaden (
  vs_fid VARCHAR(2),
  vs_bid VARCHAR(2),
  vs_sid VARCHAR(2),
  kosten Number,
  PRIMARY KEY(vs_fid,vs_bid,vs_sid),
  FOREIGN KEY(vs_fid) REFERENCES fahrzeug(fid),
  FOREIGN KEY(vs_bid) REFERENCES benutzer(bid),
  FOREIGN KEY(vs_sid) REFERENCES schaden(sid)
);

insert into fahrzeug values('f1','A5 TFSI','AUDI');
insert into fahrzeug values('f2','A6 TDI','AUDI');

SELECT * FROM FAHRZEUG;

insert into benutzer values('b1','Peter');
insert into benutzer values('b2','HANS');

SELECT * FROM BENUTZER;

insert into zone values('z1','Villach LIND',100);
insert into zone values('z2','Untere Fellach',1);

SELECT * FROM zone;

insert into schaden values('s1','Bordstein');
insert into schaden values('s2','Fussgeher');

SELECT * FROM schaden;

insert into leiht_aus values('f1','b1','z1','z1',TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into leiht_aus values('f2','b2','z2','z2',TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));

SELECT * FROM LEIHT_AUS;

SELECT * FROM LEIHT_AUS INNER JOIN BENUTZER on LEIHT_AUS.LA_BID = BENUTZER.bid
INNER JOIN ZONE on LEIHT_AUS.LA_ZID = ZONE.ZID
INNER JOIN FAHRZEUG on FAHRZEUG.FID = LEIHT_AUS.LA_FID;


insert into verursacht_schaden values('f1','b1','s1',100);
insert into verursacht_schaden values('f2','b2','s2',10000);

SELECT * FROM verursacht_schaden;

SELECT * FROM verursacht_schaden INNER JOIN FAHRZEUG on verursacht_schaden.VS_FID = FAHRZEUG.FID
INNER JOIN BENUTZER on verursacht_schaden.VS_BID = BENUTZER.BID
INNER JOIN SCHADEN on verursacht_schaden.VS_SID = SCHADEN.SID;







