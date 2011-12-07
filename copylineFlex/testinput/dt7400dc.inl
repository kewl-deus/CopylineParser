      *****************************************************************
      * DT7400DC                                                      *
      *                                                               *
      * ((( DEFINITION                                                *
      * MINR-KFTAB                                                    *
      *                                                           ))) *
      *                                                               *
      * ((( BESCHREIBUNG                                              *
      * INTERNE SCHNITTSTELLE MINR-KFTAB                              *
      *                      VERSION 1.0  -  12.05.2004               *
      *                                                           ))) *
      *                                                               *
      * ((( AUTOR                                                     *
      * AUTHOR:              ROSSI                                    *
      * DATE WRITTEN:        12.05.2004                               *
      *                                                           ))) *
      *****************************************************************
      * ((( [NDERUNG                                                  *
      * AENDERUNGEN:                                                  *
      * TT.MM.JHJJ  [NDERUNG                                      NKZ *
      * 12.05.04 Ab{nderung der Copy-Strecke DT7100DC             ROI * 
      *                                                           ))) *
      *****************************************************************
      *    ((( MINR-KFTAB-SATZ
       05  MITAB-COPY.                                               
        10 MITAB-KEY.                                                
            15 SELNR                       PIC X(007).                 
            15 LFNR                        PIC X(003).                 
            15 KZ                          PIC X(001).                 
                 88 SEL-ORGA                   VALUE "O".         
                 88 SEL-MINR                   VALUE "M".         
        10 MITAB-REST.                                               
            15 MINR                        PIC X(006).                 
            15 GSV                         PIC X(003).                 
            15 GSB                         PIC X(003).                 
            15 ZWGV                        PIC X(004).                 
            15 ZWGB                        PIC X(004).                 
            15 TLN                         PIC X(004).                 
            15 FORMAT                      PIC X(003).                 
            15  FILLER REDEFINES FORMAT.
               20 ZUSATZ-SEL               PIC X(001).
                   88 ALLE-SAETZE              VALUE "A".
                   88 NUR-LEBENDE              VALUE "L".
                   88 NUR-RUHENDE              VALUE "R".
                   88 NUR-STORNIERTE           VALUE "S".
                   88 LEBENDE-U-RUHENDE        VALUE "B".
                   88 RUHENDE-U-STORNIERTE     VALUE "C".
                   88 LEBENDE-U-STORNIERTE     VALUE "D".
               20 ZUSATZ-SEL-REST          PIC X(002).
      *
      *    ))) 
