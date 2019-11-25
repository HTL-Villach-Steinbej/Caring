DROP TABLE fahrzeug cascade constraints;
DROP TABLE benutzer cascade constraints;
DROP TABLE zone cascade constraints;
DROP TABLE schaden cascade constraints;
DROP TABLE leiht_aus cascade constraints;
DROP TABLE verursacht_schaden cascade constraints;

CREATE TABLE fahrzeug(
	fid int primary key,
	bezeichnung VARCHAR2(15),
	marke VARCHAR2(15)
  );
  
CREATE TABLE benutzer(
  bid int primary key,
  name VARCHAR2(15)
);
CREATE TABLE zone(
  zid int primary key, 
  bezeichnung VARCHAR2(15),
  kostensatz NUMBER
);

CREATE TABLE schaden(
  sid int primary key,
  bezeichnung VARCHAR2(15)
);


CREATE TABLE leiht_aus (
  la_fid int,
  la_bid int,
  la_zid int,
  von DATE,
  bis DATE,
  PRIMARY KEY(la_fid,la_bid),
  FOREIGN KEY(la_fid) REFERENCES fahrzeug(fid),
  FOREIGN KEY(la_bid) REFERENCES benutzer(bid),
  FOREIGN KEY(la_zid) REFERENCES zone(zid) 
);

CREATE TABLE verursacht_schaden (
  vs_fid int,
  vs_bid int,
  vs_sid int,
  kosten Number,
  PRIMARY KEY(vs_fid,vs_bid,vs_sid),
  FOREIGN KEY(vs_fid) REFERENCES fahrzeug(fid),
  FOREIGN KEY(vs_bid) REFERENCES benutzer(bid),
  FOREIGN KEY(vs_sid) REFERENCES schaden(sid)
);

insert into fahrzeug values(1,'A5 TFSI','AUDI');
insert into fahrzeug values(2,'A6 TDI','AUDI');

insert into benutzer values(1,'Peter');
insert into benutzer values(2,'HANS');

insert into zone values(1,'Villach LIND',100);
insert into zone values(2,'Untere Fellach',1);

insert into schaden values(1,'Bordstein');
insert into schaden values(2,'Fussgeher');

insert into leiht_aus values(1,1,1,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));
insert into leiht_aus values(2,2,2,TO_DATE('17/12/2015', 'DD/MM/YYYY'),TO_DATE('17/12/2015', 'DD/MM/YYYY'));

insert into verursacht_schaden values(1,1,1,100);
insert into verursacht_schaden values(2,2,2,10000);

SELECT * FROM FAHRZEUG;

SELECT * FROM BENUTZER;

SELECT * FROM zone;

SELECT * FROM schaden;

SELECT * FROM LEIHT_AUS;

SELECT * FROM LEIHT_AUS INNER JOIN BENUTZER on LEIHT_AUS.LA_BID = BENUTZER.bid
INNER JOIN ZONE on LEIHT_AUS.LA_ZID = ZONE.ZID
INNER JOIN FAHRZEUG on FAHRZEUG.FID = LEIHT_AUS.LA_FID;

SELECT * FROM verursacht_schaden;

SELECT * FROM verursacht_schaden INNER JOIN FAHRZEUG on verursacht_schaden.VS_FID = FAHRZEUG.FID
INNER JOIN BENUTZER on verursacht_schaden.VS_BID = BENUTZER.BID
INNER JOIN SCHADEN on verursacht_schaden.VS_SID = SCHADEN.SID;