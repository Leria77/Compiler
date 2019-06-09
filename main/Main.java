package main;
import lexer.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Lexer lex = new Lexer();
        lex.detectToken();
    }
}
