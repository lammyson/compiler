package scanner;

import scanner.Token;
import scanner.Token.TokenType;
import scanner.Scanner;
import scanner.LexError;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.Reader;

%%

%public
%class CMinusLexer
%implements Scanner
%type Token
%scanerror LexError

%{
private Token nextToken;

public CMinusLexer(BufferedReader file) {
    this((Reader)file);
		try {
        nextToken = yylex();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public Token getNextToken() {
	try {
		Token returnToken = nextToken;
    if (nextToken.getTokenType() != Token.TokenType.EOF_TOKEN) {
      nextToken = yylex();
    }
    return returnToken;
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return null;
}

public Token viewNextToken() {
	return nextToken;
}
%}

letter = [a-zA-Z]
digit = [0-9]
identifier = {letter}+
iderror = {letter}+{digit}
number = {digit}+
numerror = {digit}+{letter}
whitespace = " "|\t|\r|\n|\r\n

/* Taken from the java.flex example file */
comment = "/*" [^*] ~"*/" | "/*" "*"+ "/"

%%

/* comments */
{comment}			{/* nothing */}
{whitespace}+	{/* nothing */}

/* keywords */
"else"		{return new Token(Token.TokenType.ELSE_TOKEN);}
"if"			{return new Token(Token.TokenType.IF_TOKEN);}
"int"			{return new Token(Token.TokenType.INT_TOKEN);}
"return"	{return new Token(Token.TokenType.RET_TOKEN);}
"void"		{return new Token(Token.TokenType.VOID_TOKEN);}
"while"		{return new Token(Token.TokenType.WHILE_TOKEN);}

/* symbols */
"<="			{return new Token(Token.TokenType.LESS_EQ_TOKEN);}
">="			{return new Token(Token.TokenType.GREAT_EQ_TOKEN);}
"=="			{return new Token(Token.TokenType.EQUAL_TOKEN);}
"!="			{return new Token(Token.TokenType.NOT_EQ_TOKEN);}
"<"				{return new Token(Token.TokenType.LESS_TOKEN);}
">"				{return new Token(Token.TokenType.GREAT_TOKEN);}
"="				{return new Token(Token.TokenType.ASSIGN_TOKEN);}
"+"				{return new Token(Token.TokenType.ADD_TOKEN);}
"-"				{return new Token(Token.TokenType.SUB_TOKEN);}
"*"				{return new Token(Token.TokenType.MUL_TOKEN);}
"/"				{return new Token(Token.TokenType.DIV_TOKEN);}
"("				{return new Token(Token.TokenType.LPAREN_TOKEN);}
")"				{return new Token(Token.TokenType.RPAREN_TOKEN);}
"{"				{return new Token(Token.TokenType.LCBRACK_TOKEN);}
"}"				{return new Token(Token.TokenType.RCBRACK_TOKEN);}
"["				{return new Token(Token.TokenType.LSBRACK_TOKEN);}
"]"				{return new Token(Token.TokenType.RSBRACK_TOKEN);}
";"				{return new Token(Token.TokenType.SEMI_TOKEN);}
","				{return new Token(Token.TokenType.COMMA_TOKEN);}

/* nums and identifiers */
{iderror}			{throw new LexError("Illegal id: num found in id");}
{numerror}		{throw new LexError("Illegal num: letter found in num");}
{identifier}	{return new Token(Token.TokenType.ID_TOKEN, yytext());}
{number}			{return new Token(Token.TokenType.NUM_TOKEN, yytext());}

<<EOF>>		{return new Token(Token.TokenType.EOF_TOKEN);}



