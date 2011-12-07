      *****************************************************************
      * DT0007C                                                       *
      *                                                               *
      * ((( DEFINITION                                                *
      * INT - DTA-STAM                                                *
      *                                                           ))) *
      *                                                               *
      * ((( BESCHREIBUNG                                              *
      * DATEIBESCHREIBUNG DTASTAM = STAMMDATEI                        * 
      * FUER DTA-TEILNEHMER                                           * 
      *                      VERSION 1.0  -  01.07.1995               *
      *                                                           ))) *
      *                                                               *
      * ((( AUTOR                                                     *
      * AUTHOR:              HR. STUMPENHUSEN                         *
      * DATE WRITTEN:        01.07.1995                               *
      *                                                           ))) *
      *****************************************************************
      * ((( [NDERUNG                                                  *
      * AENDERUNGEN:                                                  *
      * TT.MM.JHJJ  [NDERUNG                                      NKZ *
      * 06.05.1996  NEUE BEDINGUNGSNAMEN -GDV-SA8XX-              STH *
      * 22.07.1996  Erg{nzung f}r Provisions-DTA                  BUS *
      * 23.10.1996  Ausgabeformat 304 Bedingungsname aktualisiert FAK *
      * 22.11.1996  Erweitert mit LKZ (L{nderkennzeichen)             *
      *             und BENUTZER-NAME                             ROI *
      * 13.02.1997  Bedingungsname 12-3 -> Sort 5 entfernt        Sth *
      * 25.03.1997  Sa-Ausg9 um Gradmann erweitert                Sth
      *             (Achtung nicht  aktivieren!!!)
      * 17.04.1997  Sa-Ausg9 um Gradmann entfernt                 Sth
      * 13.05.1997  SA-Ausg1 erweitert f}r KRSS-Satz (7)          BUS
      * 07.08.1997  Bedingungsnamen f}r PVD                       BUS
      * 17.03.1998  Sa-Ausg8 Bedingungsnamen f}r TARIFDTA         ROI
      * 18.09.1998  Sa-Ausg1 erweitert mit sa100 KFZ-UMBUCHER     ROI
      * 18.09.1998  GDV Satzart-Ausgabe 710-714 zugefuegt         WAR
      * 20.05.1999  ST-TN-PVD auch 0263 (DF])                     SEE
      * 18.09.1998  ST-GDV-HRV f}r HRV-NEU DT4180 zugefuegt       WAR
      * 25.10.1999  Prov-Formate geteilt in GDV oder Excel        ROI
      * 13.06.2000  Sa-Ausg12 Bedingungsname f}r NK-Sammler-AV    ROI
      * 11.10.2000  ST-DEVICE erweitert mit 6=Diskette / ANS-KONV ROI
      * 19.09.2001  NK-UMBUCHER AUFGENOMMEN                       ROI
      * 14.08.2001  ST-TEILN erweitert mit TN-KAR f}r KARSTADT    NIC
      * 06.06.2002  Sa-Ausg1 erweitert mit SA460 SCHADEN-GDV      ROI
      * 04.03.2003  SA-AUSG9-2 erweitert mit SA0807 DCS-sonder    ROI
      * 18.03.2003  ST-DEVICE erweitert mit 7=E-MAIL              ROI
      * 03.07.2003  ST-DEVICE erweitert mit 8=EBCDIC-MAIL         ROI
      *                                                           ))) *
      *****************************************************************
      *
      *
      *
      *                                                                 
       05  DTASTAM-SATZ.                                          
           10  STAM-KEY.                                          
      *
      * Teilnehmer-Nummer DTA
      *
                 15 TEILN                   PIC 9(4).          
                    88 TN-PVD               VALUE 0153, 0222,
                                                  0263, 0502, 0728.
                    88 TN-KAR               VALUE 0536.
      *
      * G}ltig bis JJMMTT
      *
                 15 GBIS                    PIC 9(6).                  
                   88 GUELTIG               VALUE 998888.              
      *
      * G}ltig von JJMMTT
      *
               10  GVON                     PIC 9(6)     
                                                  PACKED-DECIMAL.           
      *
      * Letzte DGF-Verarbeitung JJMMTT
      *
               10  LVDGF                    PIC 9(6)      
                                                  PACKED-DECIMAL.           
      *
      * Letzte DTA-Verarbeitung JJMMTT
      *
               10  LVDTA                    PIC 9(6)   
                                                  PACKED-DECIMAL.           
      *
      * Perioden Kennzeichen  
      *
               10  PERIO                    PIC 9.             
                 88 WOCHE                   VALUE 0.           
                 88 MONAT                   VALUE 1.           
                 88 QUART                   VALUE 2.           
                 88 HALBJ                   VALUE 3.          
                 88 JAHR                    VALUE 4.           
                 88 JAHR-HFAEL              VALUE 5.           
                 88 HFAEL                   VALUE 6.           
                 88 ABRUF                   VALUE 9.           
      *
      * Kennzeichen Buchen    
      *
               10  BUCH                     PIC X.            
                 88 MIT-BUCHUNG             VALUE "J".        
                 88 OHNE-BUCHUNG            VALUE "N".        
      *
      * Beleg-Nr. f}r Buchungen
      *
               10  BBEL                     PIC 9(5)   
                                                  PACKED-DECIMAL.     
      *
      * Versandadresse Datentr{ger
      *
               10  VERS-ADR-BAND.                                     
                 15 VB-ANREDE              PIC X(30).            
                 15 VB-NAME1               PIC X(30).            
                 15 VB-NAME2               PIC X(30).            
                 15 VB-NAME3               PIC X(30).            
                 15 VB-STRASSE             PIC X(30).            
                 15 VB-PLZ                 PIC X(5).             
                 15 VB-ORT                 PIC X(24).            
      *
      * Dateiname / Datentr{gername
      *
               10  DATEINAME.
                 15 TEILN-KURZBEZ          PIC X(04).
                 15 LFD-BAND-NR            PIC 9(03).
      *
      *!Achtung im DT3FILE wird DTA-DEVICE auf 3 gesetzt wenn
      *!T-PLATTE und DTA-DEVICE = 5,6,7 enth{lt    
      *
      * Art des Datentr{gers
               10  DTA-DEVICE              PIC X.
                 88 NUR-UMBUCHER           VALUE "0".
                 88 MIT-DTAVERS            VALUE
                                   "1" "2" "3" "4" "5" "6" "7" "8" "9".
                 88 BAND                   VALUE "1" "2" "4".    
                 88 BAND-6250BPI           VALUE "1".
                 88 BAND-1600BPI           VALUE "2".
                 88 BAND-T9G               VALUE "1".
                 88 BAND-T9P               VALUE "2".
                 88 DISKETTE               VALUE "3" "6".
                 88 ASC-KONVERTER          VALUE "3".
                 88 KASSETTE               VALUE "4".            
                 88 DATEN-FERN-UEBERTR     VALUE "5".
                 88 ANS-KONVERTER          VALUE "6".
                 88 E-MAIL                 VALUE "7".
                 88 EBCDIC-MAIL            VALUE "8".
                 88 NUR-SAMMLER            VALUE "9".
      *
      * Blockung-Gr|sse     
      *
               10  BLOCKSIZE               PIC 9(05).
      *
      * Datensatzart
      *
               10  RECFORM                 PIC X.
                 88 DATENSATZ-VARIABEL     VALUE "V".
                 88 DATENSATZ-FEST         VALUE "F".         
      *
      * Datensatzl{nge
      *
               10  RECSIZE                 PIC 9(04).
      *
      * Versandadresse Liste
      *
               10  VERS-ADR-LIST.                                    
                 15 VL-ANREDE              PIC X(30).        
                 15 VL-NAME1               PIC X(30).        
                 15 VL-NAME2               PIC X(30).        
                 15 VL-NAME3               PIC X(30).        
                 15 VL-STRASSE             PIC X(30).        
                 15 VL-PLZ                 PIC X(5).         
                 15 VL-ORT                 PIC X(24).        
      *
      * Satzart Ausgabe
      *
               10  SATZART-AUSGABEN        PIC X(45).
               10  FILLER REDEFINES SATZART-AUSGABEN.
                 15  SATZART-AUSGABE OCCURS 15.
                   20 SA-AUSG              PIC 9(03).        
                     88 SA-GUELT           VALUE ZEROES.       
                     88 SA-STAND           VALUE ZEROES.       
               10  FILLER REDEFINES SATZART-AUSGABEN.
      *
      * Ausgabe-Format  
      *
                 15  SA-AUSG1              PIC 9(03).
                   88  HDI-FORMAT
                                                 VALUE 000 THRU 001.
                   88  KFZ-UMBUCHER   
                                                 VALUE 100.
                   88  NK-UMBUCHER    
                                                 VALUE 200.
                   88  KARSTADT-FORMAT
                                                 VALUE 002.
                   88  PROV-GDV-FORMAT   
                                                 VALUE 400.
                   88  PROV-EXCEL-FORMAT 
                                                 VALUE 401.
                   88  SCHADEN-GDV-FORMAT   
                                                 VALUE 460.
                   88  ALT-FORMAT-K-OHNE-ADR
                                                 VALUE 501.
                   88  ALT-FORMAT-K-MIT-ADR
                                                 VALUE 502.
                   88  VWV-SAMMELINK-UNFALL
                                                 VALUE 600.
                   88  GDV-FORMATE        
                                                 VALUE 300 THRU 304
                                                       700 THRU 704
                                                       710 THRU 714.
                 15  FILLER REDEFINES SA-AUSG1.
                   20 SA-AUSG1-1           PIC 9.        
                     88  HDI-ODER-KARSTADT-FORMAT
                                                 VALUE 0.
                     88  GDV-FORMAT         
                                                 VALUE 3 7 8.
                     88  GDV-DGFK           
                                                 VALUE 3 8.
                     88  PROVDTA-FORMAT     
                                                 VALUE 4.
                     88  SCHADEN-FORMAT     
                                                 VALUE 4.
                     88  ALT-FORMAT
                                                 VALUE 5.
                     88  SAMMELINKASSO-UNFALL
                                                 VALUE 6.
                     88  GDV-KRSS            
                                                 VALUE 7.
                     88  GDV-HRV             
                                                 VALUE 8.
                     88  SAMMLER-NK
                                                 VALUE 9.
                   20 SA-AUSG1-2           PIC 9.        
                     88  ALT-FORMAT-K-1200
                                                 VALUE 0.
                     88  ALT-FORMAT-NK-155
                                                 VALUE 1.
                     88  ALT-FORMAT-NK-662
                                                 VALUE 2.
                     88  MANUELLE-BUCHUNG
                                                 VALUE 5.
                     88  SCHADEN-DTA
                                                 VALUE 6.
                   20 SA-AUSG1-3           PIC 9.        
                     88  ALT-OHNE-ADRESSE
                                                 VALUE 1.
                     88  ALT-MIT-ADRESSE
                                                 VALUE 2.
                     88  GDV-VERSION-1-0
                                                 VALUE 0 1.
                     88  GDV-VERSION-1-0-MIT-SA800
                                                 VALUE 0.
                     88  GDV-VERSION-1-0-OHNE-SA800
                                                 VALUE 1.
                     88  GDV-VERSION-1-2
                                                 VALUE 2 3.
                     88  GDV-NEUESTE-VERSION
                                                 VALUE 4.
                     88  GDV-VERSION-1-2-MIT-SA800
                                                 VALUE 2.
                     88  GDV-VERSION-1-2-OHNE-SA800
                                                 VALUE 3.
      *
      * Begleitzettel-Ausgabe und
      * Steuerungskriterium f}r Vertr{ge beim Bestandsabzug
      * (war fr}her erste Stelle TNR)
      *
               15  SA-AUSG2                PIC 9(03).
               15  FILLER REDEFINES SA-AUSG2.
                   20 SA-AUSG2-1           PIC 9.      
                     88  SA-AUSG2-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG2-2           PIC 9.        
                     88  NUR-LEBENDE
                                                 VALUE 0.
                     88  RUHENDE
                                                 VALUE 1.
                     88  LEBENDE-RUHENDE
                                                 VALUE 2.
                     88  LEBENDE-RUHENDE-STORNIERTE
                                                 VALUE 3.
                     88  STORNIERTE-ORGAWECHSEL
                                                 VALUE 4.
                     88  ALLE-STORNIERTEN
                                                 VALUE 5.
                     88  MANUELLE-BUCHUNG-NK
                                                 VALUE 9.
                   20 SA-AUSG2-3           PIC 9.      
                     88  LEERE-BEGLEITZETTEL-AUSGABE
                                                 VALUE 0.
                     88  LEERE-BEGLEITZETTEL-UNTERDR
                                                 VALUE 1.
      *
      * Provisions-Buchungen 
      *
                 15  SA-AUSG3              PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG3.
                   20 SA-AUSG3-1           PIC 9.         
                     88  SA-AUSG3-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG3-2           PIC 9.         
                     88  SA-AUSG3-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG3-3           PIC 9.         
                     88  SA-AUSG3-3-UNBELEGT
                                                 VALUE 0.
      *
      * Schreibweise Adresse (GDV)
      *
                 15  SA-AUSG4              PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG4.
                   20 SA-AUSG4-1           PIC 9.         
                     88  SA-AUSG4-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG4-2           PIC 9.         
                     88  SA-AUSG4-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG4-3           PIC 9.         
                     88  GDV-ADRESSE-NUR-GROSS
                                                 VALUE 0.
                     88  GDV-ADRESSE-GROSS-U-KLEIN
                                                 VALUE 1.
      *
      * Umlaute (nur KFZ-KZ?)
      *
                 15  SA-AUSG5              PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG5.
                   20 SA-AUSG5-1           PIC 9.         
                     88  SA-AUSG5-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG5-2           PIC 9.         
                     88  SA-AUSG5-2-UNBELEGT 
                                                 VALUE 0.
                   20 SA-AUSG5-3           PIC 9.         
                     88  UMLAUTE-SIEMENS
                                                 VALUE 0.
                     88  UMLAUTE-IBM
                                                 VALUE 1.
                     88  UMLAUTE-NIXDORF
                                                 VALUE 2.
      *
      * Getrennter DTA f}r [nderungsdienst/Rechnungen
      *
                 15  SA-AUSG6              PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG6.
                   20 SA-AUSG6-1           PIC 9.         
                     88  SA-AUSG6-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG6-2           PIC 9.         
                     88  SA-AUSG6-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG6-3           PIC 9.         
                     88  DATENTRAEGER-ZUSAMMEN
                                                 VALUE 0.
                     88  DATENTRAEGER-GETRENNT
                                                 VALUE 1.
      *
      * Format der Tarif-Felder
      *
                 15  SA-AUSG7              PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG7.
                   20 SA-AUSG7-1           PIC 9.         
                     88  SA-AUSG7-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG7-2           PIC 9.         
                     88  SA-AUSG7-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG7-3           PIC 9.         
                     88  TARIF-FELDER-GDV-MIT-NK
                                                 VALUE 0.
                     88  TARIF-FELDER-SONST-OHNE-NK
                                                 VALUE 0.
                     88  TARIF-FELDER-ALT-MIT-NK
                                                 VALUE 1.
      *
      * TARIFDTA Steuerung wechseln nach TLN oder TARIF   
      *
                 15  SA-AUSG8              PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG8.                    
                   20 SA-AUSG8-1           PIC 9.         
                     88  SA-AUSG8-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG8-2           PIC 9.         
                     88  SA-AUSG8-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG8-3           PIC 9.         
                     88 WECHSELN-BEI-TARIF VALUE 0. 
                     88 WECHSELN-BEI-TEILN VALUE 1. 
      *
      * SA801-899
      *
*                 15  SA-AUSG9              PIC 9(03).
 *                    88  0800-TER-SATZART   
  *                                               VALUE 001 THRU 999.
                 15  FILLER REDEFINES SA-AUSG9.
                   20 SA-AUSG9-1           PIC 9.         
                     88  SA-AUSG9-1-UNBELEGT
                                                 VALUE 0.
                     88  SA0800-NO
                                                 VALUE 1.
                   20 SA-AUSG9-2           PIC 9.         
                     88  SA0807            
                                                 VALUE 1.
                   20 SA-AUSG9-3           PIC 9.         
                     88  SA0801-BANKINFO
                                                 VALUE 1.
                     88  SA0802-ABRECHZEILEN
                                                 VALUE 2.
                     88  SA0801-BANK-0802-ABRECH
                                                 VALUE 3.
                     88  SA0804-GBU
                                                 VALUE 4.
                     88  SA0805-HALTER-ABW
                                                 VALUE 5.
                     88  SA0806-VORVERSICHERER
                                                 VALUE 6.
                     88  SA0801-SA0804-SA0805-SA0806
                                                 VALUE 7.
      *
      * Reserviert, verwendung unklar
      *
                 15  SA-AUSG10             PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG10.
                   20 SA-AUSG10-1          PIC 9.         
                     88  SA-AUSG10-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG10-2          PIC 9.         
                     88  SA-AUSG10-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG10-3          PIC 9.           
                     88  SA-AUSG10-3-UNBELEGT
                                                 VALUE 0.
      *
      * Nicht-K-Steuerung            
      *
                 15  SA-AUSG11             PIC 9(03).
                     88  FREMDSUMME-BEI-FUEHR
                                                 VALUE 101.
      * Sicher ?
                 15  FILLER REDEFINES SA-AUSG11.
                   20 SA-AUSG11-1          PIC 9.         
                     88  SA-AUSG11-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG11-2          PIC 9.         
                     88  SA-AUSG11-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG11-3          PIC 9.         
                     88  SA-AUSG11-3-UNBELEGT
                                                 VALUE 0.
      *
      * Nicht-K-Sammler              
      *
                 15  SA-AUSG12             PIC 9(3).
                     88  NK-SAMMLER         
                                                 VALUE 001 THRU 999.
                     88  KEIN-NK-SAMMLER  
                                                 VALUE 000.
                 15  FILLER REDEFINES SA-AUSG12.
                   20 SA-AUSG12-1          PIC 9.            
                     88  SAMMLER-OHNE-PROV
                                                 VALUE 1.
                     88  SAMMLER-MIT-PROV
                                                 VALUE 0.
                   20 SA-AUSG12-2          PIC 9.            
                     88  SAMMLER-DRB       
                                                 VALUE 0.
                     88  SAMMLER-AV     
                                                 VALUE 1.
                   20 SA-AUSG12-3          PIC 9.            
                     88  SAMMLER-SORT-1   
                                                 VALUE 1.
                     88  SAMMLER-SORT-2   
                                                 VALUE 2.
                     88  SAMMLER-SORT-3   
                                                 VALUE 3.
                     88  SAMMLER-SORT-4   
                                                 VALUE 4.
      *
      * K: Sonder-BRE   und  ZE: Fremd-OB  
      *
      *                      K: Sonder-BRE                         
                 15  SA-AUSG13             PIC 9(03).
                 15  FILLER REDEFINES SA-AUSG13.
                   20 SA-AUSG13-1          PIC 9.            
                     88  SA-AUSG13-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG13-2  PIC 9.                             
                     88  SA-AUSG13-2-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG13-3          PIC 9.                     
                     88  ENDSUMME-BRE-VERRECHNEN
                                                 VALUE 0.
                     88  ENDSUMME-BRE-NOT-VERRECHNEN
                                                 VALUE 1.
      *                      ZE: Fremd-OB  
                 15  FILLER REDEFINES SA-AUSG13.
                   20 SA-AUSG13-1-3        PIC 9(03).    
                     88  HDI-OB
                                                 VALUE 000.
                     88  PARTNER-OB9
                                                 VALUE 001 THRU 99.
      *          KFZ lfd. und Bestand: OB-FORMAT
                 15  FILLER REDEFINES SA-AUSG13.
                   20 SA-AUSG13-2-3        PIC 9(03).    
                     88  HDIOB13
                                                 VALUE 013.
                     88  HDIOB14
                                                 VALUE 014.
      *
      * ZE-Stamm:                                                   
      *
                 15  SA-AUSG14             PIC 9(03).    
                 15  FILLER REDEFINES SA-AUSG14.
                   20 SA-AUSG14-1          PIC 9.        
                     88  PROV-BEITR-BUCHEN
                                                 VALUE 0.
                     88  PROV-BUCHEN
                                                 VALUE 1.
                     88  BEITR-BUCHEN
                                                 VALUE 2.
                   20 SA-AUSG14-2          PIC 9.      
                     88  ZE-NEU           
                                                 VALUE 0.
                     88  ZE-ALT           
                                                 VALUE 1.
                   20 SA-AUSG14-3          PIC 9.      
                     88  SALDO-GESAMT     
                                                 VALUE 0.
                     88  PROV-GETRENNT            
                                                 VALUE 1.
      *
      * Verwendungszweck variabel
      *
                 15  SA-AUSG15             PIC 9(03).  
                 15  FILLER REDEFINES SA-AUSG15.
                   20 SA-AUSG15-1          PIC 9.      
                     88  SA-AUSG15-1-UNBELEGT
                                                 VALUE 0.
                   20 SA-AUSG15-2          PIC 9.      
                     88  GDV-NEUE-VERS    
                                                 VALUE 1.
                     88  GDV-ALTE-VERS    
                                                 VALUE 0.
                   20 SA-AUSG15-3          PIC 9.      
                     88  DT3K002-DOPPLER   
                                                 VALUE 1.
      *                                    KZ-ADRAEN = KENNZEICHEN      
               10  FILLER                        PIC X.                   
               10  GEGENBUCH               PIC 9(5)
                                                 PACKED-DECIMAL.
               10  OBNR.
                 15  GSNR                  PIC X(2).
                 15  MINR                  PIC X(6).
                 15  VSNR.
                   20 FILLER               PIC X(2).
                   20 VSNR-KURZ            PIC X(5).
               10  FILLER                        PIC X(7).
               10  VERGL.
                 15  TEIL                  PIC 9(4).
                 15  VERARB                PIC 9.
               10  LKZ                     PIC XXX.  
               10  BENUTZER-NAME           PIC X(8). 
               10  FILLER                        PIC X(6).
      *    ))) 
