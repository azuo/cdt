/*******************************************************************************
* Copyright (c) 2006, 2007 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     IBM Corporation - initial API and implementation
*******************************************************************************/

// This file was generated by LPG

package org.eclipse.cdt.internal.core.dom.parser.upc;

import lpg.lpgjavaruntime.*;

import org.eclipse.cdt.core.parser.CodeReader;
import org.eclipse.cdt.internal.core.dom.parser.c99.C99LexerKind;
import org.eclipse.cdt.internal.core.dom.parser.c99.preprocessor.TokenList;
import org.eclipse.cdt.internal.core.dom.parser.c99.preprocessor.Token;
import org.eclipse.cdt.core.dom.c99.ILexer;

import org.eclipse.cdt.internal.core.dom.parser.c99.preprocessor.Token;

public class UPCLexer extends LpgLexStream implements UPCParsersym, UPCLexersym, RuleAction , ILexer 
{
    private static ParseTable prs = new UPCLexerprs();
    private PrsStream prsStream;
    private LexParser lexParser = new LexParser(this, prs, this);

    public PrsStream getPrsStream() { return prsStream; }
    public int getToken(int i) { return lexParser.getToken(i); }
    public int getRhsFirstTokenIndex(int i) { return lexParser.getFirstToken(i); }
    public int getRhsLastTokenIndex(int i) { return lexParser.getLastToken(i); }

    public int getLeftSpan() { return lexParser.getFirstToken(); }
    public int getRightSpan() { return lexParser.getLastToken(); }

    public UPCLexer(String filename, int tab) throws java.io.IOException 
    {
        super(filename, tab);
    }

    public UPCLexer(char[] input_chars, String filename, int tab)
    {
        super(input_chars, filename, tab);
    }

    public UPCLexer(char[] input_chars, String filename)
    {
        this(input_chars, filename, 1);
    }

    public UPCLexer() {}

    public String[] orderedExportedSymbols() { return UPCParsersym.orderedTerminalSymbols; }
    public LexStream getLexStream() { return (LexStream) this; }

    public void lexer(PrsStream prsStream)
    {
        lexer(null, prsStream);
    }
    
    public void lexer(Monitor monitor, PrsStream prsStream)
    {
        if (getInputChars() == null)
            throw new NullPointerException("LexStream was not initialized");//$NON-NLS-1$

        this.prsStream = prsStream;

        prsStream.makeToken(0, 0, 0); // Token list must start with a bad token
            
        lexParser.parseCharacters(monitor);  // Lex the input characters
            
        int i = getStreamIndex();
        prsStream.makeToken(i, i, TK_EOF_TOKEN); // and end with the end of file token
        prsStream.setStreamLength(prsStream.getSize());
            
        return;
    }

private TokenList tokenList = null;
private boolean returnCommentTokens = false;
private char[] input = null; // the input character buffer
   
public UPCLexer(CodeReader reader) {
	super(reader.buffer, new String(reader.filename));
}

// defined in interface ILexer
public synchronized TokenList lex(int options) {
	if((OPTION_GENERATE_COMMENT_TOKENS & options) != 0)
		returnCommentTokens = true;
		
	tokenList = new TokenList();
	input = super.getInputChars();
        
    lexParser.parseCharacters(null);  // Lex the input characters
    
    TokenList result = tokenList;
    tokenList = null;
    input = null;
    return result;
}

protected void makeToken(int kind) {
	// ignore comments if desired
	if(!returnCommentTokens && (kind == TK_MultiLineComment || kind == TK_SingleLineComment))
		return;
		
	int startOffset = lexParser.getFirstToken();
	int endOffset   = lexParser.getLastToken();
	
	// an adjustment for trigraphs, commented out for optimization purposes
    //if(kind != C99Parsersym.TK_Question && startOffset == endOffset && input[startOffset] == '?') {
    //    // The token starts with a '?' but its not a question token, then it must be a trigraph.
    //    endOffset += 2; // make sure the toString() method of the token returns the entire trigraph sequence
    //}
	
	tokenList.add(new Token(startOffset, endOffset, kind, input));
}

public void reportError(int leftOffset, int rightOffset) {
	Token token = new Token(leftOffset, rightOffset, TK_Invalid, getInputChars());
	tokenList.add(token);
}

public int getKind(int i) {
	return C99LexerKind.getKind(this, i);
}


    public void ruleAction( int ruleNumber)
    {
        switch(ruleNumber)
        {
 
            //
            // Rule 1:  Token ::= identifier
            //
            case 1: {   makeToken(TK_identifier);           break;
            }
 
            //
            // Rule 2:  Token ::= integer-constant
            //
            case 2: {   makeToken(TK_integer);             break;
            }
 
            //
            // Rule 3:  Token ::= floating-constant
            //
            case 3: {   makeToken(TK_floating);            break;
            }
 
            //
            // Rule 4:  Token ::= character-constant
            //
            case 4: {   makeToken(TK_charconst);           break;
            }
 
            //
            // Rule 5:  Token ::= string-literal
            //
            case 5: {   makeToken(TK_stringlit);           break;
            }
 
            //
            // Rule 6:  Token ::= [
            //
            case 6: {   makeToken(TK_LeftBracket);                 break;
            }
 
            //
            // Rule 7:  Token ::= ]
            //
            case 7: {   makeToken(TK_RightBracket);                break;
            }
 
            //
            // Rule 8:  Token ::= (
            //
            case 8: {   makeToken(TK_LeftParen);                   break;
            }
 
            //
            // Rule 9:  Token ::= )
            //
            case 9: {   makeToken(TK_RightParen);                  break;
            }
 
            //
            // Rule 10:  Token ::= {
            //
            case 10: {   makeToken(TK_LeftBrace);                   break;
            }
 
            //
            // Rule 11:  Token ::= }
            //
            case 11: {   makeToken(TK_RightBrace);                  break;
            }
 
            //
            // Rule 12:  Token ::= .
            //
            case 12: {   makeToken(TK_Dot);                         break;
            }
 
            //
            // Rule 13:  Token ::= - >
            //
            case 13: {   makeToken(TK_Arrow);                       break;
            }
 
            //
            // Rule 14:  Token ::= + +
            //
            case 14: {   makeToken(TK_PlusPlus);                    break;
            }
 
            //
            // Rule 15:  Token ::= - -
            //
            case 15: {   makeToken(TK_MinusMinus);                  break;
            }
 
            //
            // Rule 16:  Token ::= &
            //
            case 16: {   makeToken(TK_And);                         break;
            }
 
            //
            // Rule 17:  Token ::= *
            //
            case 17: {   makeToken(TK_Star);                        break;
            }
 
            //
            // Rule 18:  Token ::= +
            //
            case 18: {   makeToken(TK_Plus);                        break;
            }
 
            //
            // Rule 19:  Token ::= -
            //
            case 19: {   makeToken(TK_Minus);                       break;
            }
 
            //
            // Rule 20:  Token ::= ~
            //
            case 20: {   makeToken(TK_Tilde);                       break;
            }
 
            //
            // Rule 21:  Token ::= !
            //
            case 21: {   makeToken(TK_Bang);                        break;
            }
 
            //
            // Rule 22:  Token ::= /
            //
            case 22: {   makeToken(TK_Slash);                       break;
            }
 
            //
            // Rule 23:  Token ::= %
            //
            case 23: {   makeToken(TK_Percent);                     break;
            }
 
            //
            // Rule 24:  Token ::= < <
            //
            case 24: {   makeToken(TK_LeftShift);                   break;
            }
 
            //
            // Rule 25:  Token ::= > >
            //
            case 25: {   makeToken(TK_RightShift);                  break;
            }
 
            //
            // Rule 26:  Token ::= <
            //
            case 26: {   makeToken(TK_LT);                          break;
            }
 
            //
            // Rule 27:  Token ::= >
            //
            case 27: {   makeToken(TK_GT);                          break;
            }
 
            //
            // Rule 28:  Token ::= < =
            //
            case 28: {   makeToken(TK_LE);                          break;
            }
 
            //
            // Rule 29:  Token ::= > =
            //
            case 29: {   makeToken(TK_GE);                          break;
            }
 
            //
            // Rule 30:  Token ::= = =
            //
            case 30: {   makeToken(TK_EQ);                          break;
            }
 
            //
            // Rule 31:  Token ::= ! =
            //
            case 31: {   makeToken(TK_NE);                          break;
            }
 
            //
            // Rule 32:  Token ::= ^
            //
            case 32: {   makeToken(TK_Caret);                       break;
            }
 
            //
            // Rule 33:  Token ::= |
            //
            case 33: {   makeToken(TK_Or);                          break;
            }
 
            //
            // Rule 34:  Token ::= & &
            //
            case 34: {   makeToken(TK_AndAnd);                      break;
            }
 
            //
            // Rule 35:  Token ::= | |
            //
            case 35: {   makeToken(TK_OrOr);                        break;
            }
 
            //
            // Rule 36:  Token ::= ?
            //
            case 36: {   makeToken(TK_Question);                    break;
            }
 
            //
            // Rule 37:  Token ::= :
            //
            case 37: {   makeToken(TK_Colon);                       break;
            }
 
            //
            // Rule 38:  Token ::= ;
            //
            case 38: {   makeToken(TK_SemiColon);                   break;
            }
 
            //
            // Rule 39:  Token ::= . . .
            //
            case 39: {   makeToken(TK_DotDotDot);                   break;
            }
 
            //
            // Rule 40:  Token ::= =
            //
            case 40: {   makeToken(TK_Assign);                      break;
            }
 
            //
            // Rule 41:  Token ::= * =
            //
            case 41: {   makeToken(TK_StarAssign);                  break;
            }
 
            //
            // Rule 42:  Token ::= / =
            //
            case 42: {   makeToken(TK_SlashAssign);                 break;
            }
 
            //
            // Rule 43:  Token ::= % =
            //
            case 43: {   makeToken(TK_PercentAssign);               break;
            }
 
            //
            // Rule 44:  Token ::= + =
            //
            case 44: {   makeToken(TK_PlusAssign);                  break;
            }
 
            //
            // Rule 45:  Token ::= - =
            //
            case 45: {   makeToken(TK_MinusAssign);                 break;
            }
 
            //
            // Rule 46:  Token ::= < < =
            //
            case 46: {   makeToken(TK_LeftShiftAssign);             break;
            }
 
            //
            // Rule 47:  Token ::= > > =
            //
            case 47: {   makeToken(TK_RightShiftAssign);            break;
            }
 
            //
            // Rule 48:  Token ::= & =
            //
            case 48: {   makeToken(TK_AndAssign);                   break;
            }
 
            //
            // Rule 49:  Token ::= ^ =
            //
            case 49: {   makeToken(TK_CaretAssign);                 break;
            }
 
            //
            // Rule 50:  Token ::= | =
            //
            case 50: {   makeToken(TK_OrAssign);                    break;
            }
 
            //
            // Rule 51:  Token ::= ,
            //
            case 51: {   makeToken(TK_Comma);                       break;
            }
 
            //
            // Rule 52:  Token ::= #
            //
            case 52: {   makeToken(TK_Hash);                        break;
            }
 
            //
            // Rule 53:  Token ::= # #
            //
            case 53: {   makeToken(TK_HashHash);                    break;
            }
 
            //
            // Rule 54:  Token ::= < :
            //
            case 54: {   makeToken(TK_LeftBracket);                 break;
            }
 
            //
            // Rule 55:  Token ::= : >
            //
            case 55: {   makeToken(TK_RightBracket);                break;
            }
 
            //
            // Rule 56:  Token ::= < %
            //
            case 56: {   makeToken(TK_LeftBrace);                   break;
            }
 
            //
            // Rule 57:  Token ::= % >
            //
            case 57: {   makeToken(TK_RightBrace);                  break;
            }
 
            //
            // Rule 58:  Token ::= % :
            //
            case 58: {   makeToken(TK_Hash);                        break;
            }
 
            //
            // Rule 59:  Token ::= % : % :
            //
            case 59: {   makeToken(TK_HashHash);                    break;
            }
 
            //
            // Rule 217:  Token ::= NewLine
            //
            case 217: {   makeToken(TK_NewLine);           break;
            }
 
            //
            // Rule 219:  Token ::= SLC
            //
            case 219: {   makeToken(TK_SingleLineComment);           break;
            }
 
            //
            // Rule 220:  Token ::= MLC
            //
            case 220: {   makeToken(TK_MultiLineComment);           break;
            }

    
            default:
                break;
        }
        return;
    }
}

