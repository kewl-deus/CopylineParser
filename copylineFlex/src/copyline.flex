
/*
 * This class is for scanning adabas meta-structure
 */


import java_cup.runtime.*;

%%

%class CopylineScanner
%implements sym


%line
%column
%cup
%cupdebug


%{
  StringBuffer string = new StringBuffer();
  
  private Symbol symbol(int type) {
    return new SymbolWithPosition(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new SymbolWithPosition(type, yyline+1, yycolumn+1, value);
  }

%}

/* main character classes */
LineTerminator = \r | \n | \r\n

InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f ] | " "


/* identifiers */
Identifier = [:jletter:] ([:jletter:] | [:jletterdigit:] | - )*


/* typedefinition */

AlphaTypeSymbol = "X" | "A" | "B";
AlphaTypeStringDefinition = {AlphaTypeSymbol} ({AlphaTypeSymbol} | "9" | "0" | "/")*
AlphaTypeBracketDefinition = {AlphaTypeSymbol} "(" {FieldSize}+ ")"
AlphaTypeDefinition = {AlphaTypeStringDefinition} | {AlphaTypeBracketDefinition}

NumericTypeSymbol = "9" | "Z"
NumericTypeLeadingSignSymbol = "S" | "+" | "-"
NumericTypeTailingSignSymbol = "+" | "-"
NumericTypeCommaSymbol = "V" | ","
NumericTypeStringDefinition = {NumericTypeSymbol} ({NumericTypeSymbol} | {NumericTypeCommaSymbol})*
NumericTypeBracketDefinition = {NumericTypeSymbol} "(" {FieldSize}+ ")"

/* S9(13)V99 oder S9(13)V9(2) */
NumericTypeDecimalDefinition = {NumericTypeBracketDefinition} "V" ({NumericTypeStringDefinition} | {NumericTypeBracketDefinition})

NumericTypeDefinition = {NumericTypeLeadingSignSymbol}? {NumericTypeDecimalDefinition}
						| {NumericTypeLeadingSignSymbol}? ({NumericTypeStringDefinition} | {NumericTypeBracketDefinition}) {NumericTypeTailingSignSymbol}?


TypeDefinition = {AlphaTypeDefinition} | {NumericTypeDefinition}


/* comments */
Comment = "*" {InputCharacter}* {LineTerminator}?


/* numbers */
Number = [0-9]*
FieldSize = 0* [1-9]+ 0*

/* string and character literals */
StringCharacter = [^\r\n\"\\]

%state STRING


%%

<YYINITIAL> {

  /* keywords */
  "OCCURS"                       { return symbol(OCCURS); }
  "TIMES"                        { return symbol(TIMES); }
  "VALUE"                        { return symbol(VALUE); }
  "VALUES"                       { return symbol(VALUE); }
  "THRU"                         { return symbol(THROUGH); }
  "THROUGH"                      { return symbol(THROUGH); }
  "REDEFINES"		   	 		 { return symbol(REDEFINES); }
  "PIC"                          { return symbol(PICTURE); }
  "PICTURE"                      { return symbol(PICTURE); }
  
  "BINARY"                       { /* ignore */ }
  "COMP"        	        	 { return symbol(PACKED_DECIMAL); }
  "COMP-3"        	        	 { return symbol(PACKED_DECIMAL); }  
  "PACKED-DECIMAL"               { return symbol(PACKED_DECIMAL); }
  
  "TRUE"                         { return symbol(BOOLEAN_LITERAL, new Boolean(true)); }
  "FALSE"                        { return symbol(BOOLEAN_LITERAL, new Boolean(false)); }
  "SPACE"                        { return symbol(STRING_LITERAL, " "); }
  "SPACES"                       { return symbol(STRING_LITERAL, " "); }
  "ZERO"                         { return symbol(NUMBER, new Integer(0)); }
  "ZEROES"                       { return symbol(NUMBER, new Integer(0)); }
  
  /* separators */
//  "("                            { return symbol(OPEN_BRACKET); }
//  ")"                            { return symbol(CLOSE_BRACKET); }
  ","                            { return symbol(COMMA); }
  "."                            { return symbol(DOT); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }
  
  /* numeric literals */
  {Number}            		 { return symbol(NUMBER, new Integer(yytext())); }
  
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
  
  /* comments */
  {Comment}                      { /* ignore */ }

 /* typedefinitions */
  {TypeDefinition}		 { return symbol(TYPEDEFINITION, yytext()); }
  
  /* identifiers */ 
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); } 
  

}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

/* error fallback */
.|\n                             { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(EOF); }