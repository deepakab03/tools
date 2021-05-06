package com.deepak.antlr.test;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class AntlrWhiteBoard {

    public static void main(String[] args) throws IOException {
        CharStream inputStream = CharStreams.fromStream(AntlrWhiteBoard.class.getResourceAsStream("file_filter_sample.erf"));
//        FlexFilterSampleLexer lexer = new FlexFilterSampleLexer(inputStream);
        // create a buffer of tokens pulled from the lexer
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer

//        FlexFilterSampleParser parser = new FlexFilterSampleParser(tokens);
//        ParseTree tree = parser.input(); // begin parsing at init rule

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks
//        walker.walk(new FlexFilterParserListener(), tree);
        System.out.println();

//        SimpleP
    }
}
