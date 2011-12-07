         05 FB4500DC-SORT-DATEN.
           10 FB4500DC-SORT1-STAPEL        PIC  X(006).
           10 FB4500DC-SORT1-VERSAND.
             15 FB4500DC-SORT1-VDKZ        PIC  X(006).
               88 FB4500DC-VERS-D               VALUE SPACE.
               88 FB4500DC-VERS-G               VALUE "*G    ".
               88 FB4500DC-VERS-V               VALUE "*V    ".
               88 FB4500DC-VERS-Z               VALUE "*Z    ".
               88 FB4500DC-VERS-KO              VALUE "*0    ".
               88 FB4500DC-VERS-VU              VALUE "*VU   ".
             15 FB4500DC-SORT1-GRUP        PIC  X(007).
             15 FILLER REDEFINES FB4500DC-SORT1-GRUP.
               20 FB4500DC-SORT1-GSNR      PIC  X(002).
             15 FILLER REDEFINES FB4500DC-SORT1-GRUP.
               20 FB4500DC-SORT1-ORGNR     PIC  X(007).
           10 FB4500DC-SORT2.
             15 FB4500DC-SORT2-SONDER      PIC  X(015).
             15 FB4500DC-SORT2-GSNR        PIC  X(002).
             15 FB4500DC-SORT2-MINR        PIC  X(006).
             15 FB4500DC-SORT2-ABBU        PIC  X(001).
             15 FB4500DC-SORT2-VSNR        PIC  X(005).
             15 FILLER REDEFINES FB4500DC-SORT2-VSNR.
               20 FB4500DC-SORT2-BUNR      PIC  X(003).
               20 FB4500DC-F               PIC  X(002).
           10 FB4500DC-SORT2-INVERS REDEFINES FB4500DC-SORT2.
             15 FB4500DC-SORT2-INV         PIC  X(001)  OCCURS 29.
           10 FB4500DC-SORT3.
             15 FB4500DC-SORT3-KOP-KZ      PIC  9(002).
               88 FB4500DC-KOPIE                VALUE 01 THRU 99.
             15 FB4500DC-SORT3-KOP-TEXT    PIC  X(030).
           10 FB4500DC-SORT4               PIC  X(020).

         05 FB4500DC-ALLG-DATEN.
           10 FB4500DC-ALLG-SORT.
             15 FB4500DC-OB.
               20 FB4500DC-MINR-NEU.
                 25 FB4500DC-GS-NR-OB      PIC  9(002).
                 25 FB4500DC-MINR          PIC  9(006).
               20 FB4500DC-VSNR            PIC  X(005).
               20 FILLER REDEFINES FB4500DC-VSNR.
                 25 FB4500DC-BUNR          PIC  9(003).
                 25 FB4500DC-F             PIC  X(002).
               20 FB4500DC-F               PIC  X(006).
             15 FB4500DC-BEARB-DAT         PIC  9(008).
             15 FB4500DC-BEARB-ZEIT        PIC  9(006).
             15 FB4500DC-FORMULAR          PIC  9(001).
               88 FB4500DC-RECHNUNG             VALUE  1.
               88 FB4500DC-DEKLARATION          VALUE  2.
               88 FB4500DC-VERTEILPLAN          VALUE  3.
               88 FB4500DC-ABRECHNUNG-VU        VALUE  4.
             15 FB4500DC-FORMULAR-NR       PIC  9(002).
             15 FB4500DC-LFD-NR            PIC  9(004).

           10 FB4500DC-INK-ART             PIC  9(001).
           10 FB4500DC-ORG-NUMMER.
             15 FB4500DC-ORGNR.
               20 FB4500DC-ORG-NR          PIC  9(004).
               20 FB4500DC-ORG-UNR         PIC  9(002).
             15 FB4500DC-ORG-PZ            PIC  9(001).
           10 FB4500DC-ZWEIG.
             15 FB4500DC-F                 PIC  9(002).
             15 FB4500DC-ZWEG              PIC  9(003).
           10 FB4500DC-PRODUKT-GRUP        PIC  X(001).
           10 FB4500DC-BAST.
             15 FB4500DC-GS-NR             PIC  X(003).
             15 FB4500DC-TEAM              PIC  X(004).
           10 FB4500DC-BETREFF             PIC  X(030).
           10 FB4500DC-GEH-ABZUG-IDENT     PIC  X(008).
           10 FB4500DC-ZW-KZ               PIC  9(001).
           10 FB4500DC-HAUPT-FAEL          PIC  9(004).

           10 FB4500DC-VERSAND-KZ          PIC  X(001).
             88 FB4500DC-VD-MITGLIED            VALUE  "M".
             88 FB4500DC-VD-HNL                 VALUE  "H".
             88 FB4500DC-VD-VERMITTLER          VALUE  "V".
             88 FB4500DC-VD-ZENTRALE            VALUE  "Z".
             88 FB4500DC-VD-KEIN-ORIGINAL       VALUE  "0".
           10 FB4500DC-ANZ-KOP-HNL         PIC  9(002).
           10 FB4500DC-ANZ-KOP-VRM         PIC  9(002).
           10 FB4500DC-ANZ-KOP-VU          PIC  9(002).
           10 FB4500DC-ZEICHN-ART          PIC  X(001).
             88 FB4500DC-ALLEINZEICHNUNG        VALUE  "A".
             88 FB4500DC-FUEHRUNG               VALUE  "F".
           10 FB4500DC-RECH-MIT-WZ-INFO.
             15 FB4500DC-ANZ-VTR-MIT-WZ    PIC  9(002).
             15 FB4500DC-ANZ-DEKL-MIT-WZ   PIC  9(002).
             15 FB4500DC-ANZ-POS-MIT-WZ    PIC  9(002).
           10 FB4500DC-RECH-FORM           PIC  X(001).
             88 FB4500DC-EINZEL-VTR             VALUE  "V".
             88 FB4500DC-BUENDEL-VTR            VALUE  "B".
           10 FB4500DC-ABBUCHER-KZ         PIC  X(001).
             88 FB4500DC-ABBUCHER               VALUE  "1".
           10 FB4500DC-LAST-BV.
             15 FB4500DC-LAST-BV-BLZ       PIC  9(008).
             15 FB4500DC-LAST-BV-KTO-NR    PIC  X(010).
           10 FB4500DC-DOK-DAT.
             15 FB4500DC-DOK-DAT-JHJJ      PIC  9(004).
             15 FILLER REDEFINES FB4500DC-DOK-DAT-JHJJ.
               20 FB4500DC-DOK-DAT-JH      PIC  9(002).
               20 FB4500DC-DOK-DAT-JJ      PIC  9(002).
             15 FB4500DC-DOK-DAT-MM        PIC  9(002).
             15 FB4500DC-DOK-DAT-TT        PIC  9(002).
           10 FB4500DC-VON-DAT.
             15 FB4500DC-VON-DAT-JHJJ      PIC  9(004).
             15 FILLER REDEFINES FB4500DC-VON-DAT-JHJJ.
               20 FB4500DC-VON-DAT-JH      PIC  9(002).
               20 FB4500DC-VON-DAT-JJ      PIC  9(002).
             15 FB4500DC-VON-DAT-MM        PIC  9(002).
             15 FB4500DC-VON-DAT-TT        PIC  9(002).
           10 FB4500DC-END-BETR-VZ         PIC  X(001).
           10 FB4500DC-END-BETR            PIC S9(013)V99.
           10 FB4500DC-WAEH-KZ-ISO         PIC  X(003).
           10 FB4500DC-PROV-BETR           PIC S9(013)V99.

           10 FB4500DC-BEILAGEN.
             15 FB4500DC-BEILAGE-01        PIC  9(001).
               88 FB4500DC-BEILAGE-01-AN        VALUE  1.
               88 FB4500DC-BEILAGE-01-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-02        PIC  9(001).
               88 FB4500DC-BEILAGE-02-AN        VALUE  1.
               88 FB4500DC-BEILAGE-02-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-03        PIC  9(001).
               88 FB4500DC-BEILAGE-03-AN        VALUE  1.
               88 FB4500DC-BEILAGE-03-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-04        PIC  9(001).
               88 FB4500DC-BEILAGE-04-AN        VALUE  1.
               88 FB4500DC-BEILAGE-04-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-05        PIC  9(001).
               88 FB4500DC-BEILAGE-05-AN        VALUE  1.
               88 FB4500DC-BEILAGE-05-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-06        PIC  9(001).
               88 FB4500DC-BEILAGE-06-AN        VALUE  1.
               88 FB4500DC-BEILAGE-06-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-07        PIC  9(001).
               88 FB4500DC-BEILAGE-07-AN        VALUE  1.
               88 FB4500DC-BEILAGE-07-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-08        PIC  9(001).
               88 FB4500DC-BEILAGE-08-AN        VALUE  1.
               88 FB4500DC-BEILAGE-08-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-09        PIC  9(001).
               88 FB4500DC-BEILAGE-09-AN        VALUE  1.
               88 FB4500DC-BEILAGE-09-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-10        PIC  9(001).
               88 FB4500DC-BEILAGE-10-AN        VALUE  1.
               88 FB4500DC-BEILAGE-10-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-11        PIC  9(001).
               88 FB4500DC-BEILAGE-11-AN        VALUE  1.
               88 FB4500DC-BEILAGE-11-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-12        PIC  9(001).
               88 FB4500DC-BEILAGE-12-AN        VALUE  1.
               88 FB4500DC-BEILAGE-12-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-13        PIC  9(001).
               88 FB4500DC-BEILAGE-13-AN        VALUE  1.
               88 FB4500DC-BEILAGE-13-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-14        PIC  9(001).
               88 FB4500DC-BEILAGE-14-AN        VALUE  1.
               88 FB4500DC-BEILAGE-14-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-15        PIC  9(001).
               88 FB4500DC-BEILAGE-15-AN        VALUE  1.
               88 FB4500DC-BEILAGE-15-AUS       VALUE  0.
             15 FB4500DC-BEILAGE-16        PIC  9(001).
               88 FB4500DC-BEILAGE-16-AN        VALUE  1.
               88 FB4500DC-BEILAGE-16-AUS       VALUE  0.
           10 FILLER REDEFINES FB4500DC-BEILAGEN.
             15 FB4500DC-BEILAGE           PIC  9(001)  OCCURS 16.
               88 FB4500DC-BEILAGE-AN           VALUE  1.
               88 FB4500DC-BEILAGE-AUS          VALUE  0.
           10 FB4500DC-UMSATZ-GS           PIC  X(003).
           10 FB4500DC-GES-NR              PIC  9(003).
           10 FB4500DC-HDI-GB              PIC  X(002).
           10 FB4500DC-HNL-NL-NR           PIC  9(003).
           10 FB4500DC-VU-KENN-NR          PIC  9(008).
           10 FB4500DC-VERTRAG-MERK        PIC  9(002).
           10 FB4500DC-F                   PIC  X(015).

         05 FB4500DC-DRUCK-DATEN.
           10 FB4500DC-TXT-TAB-INDEX       PIC  9(004).
           10 FB4500DC-TXT-TAB                          OCCURS 100.
             15 FB4500DC-TXT-KLASSE-KZ     PIC  X(001).
               88 FB4500DC-NORM-TXT             VALUE  " ".
               88 FB4500DC-BLK-TXT              VALUE  "B".
               88 FB4500DC-ABR-TXT              VALUE  "A".
               88 FB4500DC-ENDE-TXT-KZ          VALUE  "E".
             15 FB4500DC-TXT-BLK-KZ        PIC  X(001).
               88 FB4500DC-KEIN-BLK             VALUE  " ".
               88 FB4500DC-BLK-ANF              VALUE  "A".
               88 FB4500DC-BLK-ENDE             VALUE  "E".
             15 FB4500DC-TXT-DRU-KZ        PIC  X(001).
               88 FB4500DC-F10-NORM             VALUE  "0".
               88 FB4500DC-F10-FETT             VALUE  "1".
               88 FB4500DC-F12-NORM             VALUE  " ".
               88 FB4500DC-F12-FETT             VALUE  "F".
               88 FB4500DC-F15-NORM             VALUE  "S".
               88 FB4500DC-NORM-DRU             VALUE  " ".
               88 FB4500DC-SCHM-DRU             VALUE  "S".
               88 FB4500DC-FETT-DRU             VALUE  "F".
             15 FB4500DC-TXT-STB           PIC  X(001).
             15 FB4500DC-TXT-ZEILE         PIC  X(110).
             15 FB4500DC-TXT-Z10TEL  REDEFINES  FB4500DC-TXT-ZEILE
                                           PIC  X(074).
             15 FB4500DC-TXT-Z12TEL  REDEFINES  FB4500DC-TXT-ZEILE
                                           PIC  X(092).
             15 FB4500DC-TXT-Z15TEL  REDEFINES  FB4500DC-TXT-ZEILE
                                           PIC  X(110).
