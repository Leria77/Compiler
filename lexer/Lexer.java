package lexer;
import java.io.*;
import java.util.*;

public class Lexer {


    char[] buff  =  new char[120];
    int i = 0, a;
    String buf, itg;
    char[] chArray;


    public String tokens(){
        buf =  new String(buff);
        if (buf.trim().equals("var")) return "var";
        if (buf.trim().equals("alert")) return "alert";
        if (buf.trim().equals("function")) return "function";
        if (buf.trim().equals("for")) return "for";
        if (buf.trim().equals("case")) return "case";
        if (buf.trim().equals("catch")) return "catch";
        if (buf.trim().equals("continue")) return "continue";
        if (buf.trim().equals("debugger")) return "debugger";
        if (buf.trim().equals("default")) return "default";
        if (buf.trim().equals("delete")) return "delete";
        if (buf.trim().equals("do")) return "do";
        if (buf.trim().equals("else")) return "else";
        if (buf.trim().equals("finally")) return "finally";
        if (buf.trim().equals("if")) return "if";
        if (buf.trim().equals("in")) return "in";
        if (buf.trim().equals("instanceof")) return "instanceof";
        if (buf.trim().equals("new")) return "new";
        if (buf.trim().equals("null")) return "null";
        if (buf.trim().equals("return")) return "return";
        if (buf.trim().equals("switch")) return "switch";
        if (buf.trim().equals("this")) return "this";
        if (buf.trim().equals("throw")) return "throw";
        if (buf.trim().equals("true")) return "true";
        if (buf.trim().equals("false")) return "false";
        if (buf.trim().equals("try")) return "try";
        if (buf.trim().equals("typeof")) return "typeof";
        if (buf.trim().equals("void")) return "void";
        if (buf.trim().equals("while")) return "while";
        if (buf.trim().equals("with")) return "with";
        if (buf.trim().equals("class")) return "class";
        if (buf.trim().equals("const")) return "const";
        if (buf.trim().equals("enum")) return "enum";
        if (buf.trim().equals("export")) return "export";
        if (buf.trim().equals("extends")) return "extends";
        if (buf.trim().equals("import")) return "import";
        if (buf.trim().equals("super")) return "super";
        if (buf.trim().equals("+")) return "plus";
        if (buf.trim().equals("-")) return "minus";
        if (buf.trim().equals("*")) return "multiply";
        if (buf.trim().equals("/")) return "divide";
        if (buf.trim().equals("\\")) return "div";
        if (buf.trim().equals("^")) return "pow";
        if (buf.trim().equals("%")) return "mod";
        if (buf.trim().equals("=")) return "equal";
        if (buf.trim().equals("!=")) return "not_equal";
        if (buf.trim().equals(">")) return "more";
        if (buf.trim().equals("<")) return "less";
        if (buf.trim().equals( ">=")) return "more_or_equal";
        if (buf.trim().equals("<="))return "less_or_equal";
        if (buf.trim().equals("(")) return "l_paren";
        if (buf.trim().equals(")")) return "r_paren";
        if (buf.trim().equals(",")) return "comma";
        if (buf.trim().equals(";")) return "semi";

        try{
            chArray  = buf.trim().toCharArray();

        if(chArray[0] == '"') {
            if (chArray[(chArray.length)-1] == '"')
                return "literal";
            else
                return "unknown";

        }else if ((chArray[0] >= 'A' && chArray[0] <= 'Z') || (chArray[0] >= 'a' && chArray[0] <= 'z') || (chArray[0] == '_')) {

            for (int i = 1; i < (chArray.length); i++) {
                if (!((chArray[i] >= 'A' && chArray[i] <= 'Z') || (chArray[i] >= 'a' && chArray[i] <= 'z') || (chArray[i] == '_') || (chArray[i] >= '0' && chArray[i] <= '9')))
                    return "unknown";
            }
            return "id";
        }}
        catch (ArrayIndexOutOfBoundsException e){

        }

        try{
            a  = Integer.parseInt(buf.trim());
                return "numeric";

        }
        catch (NumberFormatException e){

        }




        return "unknown";
    }


    public int detectToken() throws IOException {

        FileReader fr = new FileReader(new File("prog.txt"));
        int ch, row = 0, col = 0;

        String token;

        //для лексем
        while ((ch = fr.read()) != -1) {
           // System.out.print((char)ch);
            if((char)ch == '"'){
                buff[i] = (char)ch;
                i++;
                col++;
                while((char)(ch = fr.read()) != '"' && (char)ch != '\n'){
                    buff[i] = (char)ch;
                    i++;
                    col++;
                }
                if((char)ch == '"'){
                    buff[i] = (char)ch;
                    i++;
                    col++;
                }
                buff[i] = '\0';
                token = tokens();
                itg = new String(buff).trim();
                System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                Arrays.fill(buff, '\0');
                i = 0;
                continue;
            }
            //для пропуска пробелов
            else if((char)ch == ' '){
                if(i == 0){
                    col++;
                    continue;
                }

                buff[i] = '\0';
            /*   //для пропуска комментариев
                if(buff.equals("//") || buff.equals("/*")){
                    while ((char)(ch = fr.read()) != '/')
                        continue;
                    i = 0;
                    row++;
                    col = 1;
                    continue;
                } */
                token = tokens();

                itg = new String(buff).trim();
                System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                Arrays.fill(buff, '\0');
                i = 0;
                col++;
                continue;
            }//если новая строка
            else if((char)ch =='\r'){

                if (i == 0)
                    continue;

                buff[i-1] = '\0';
                 /*    //для пропуска комментариев
                if(buff.equals("//") || buff.equals("/*")){
                    while ((char)(ch = fr.read()) != '/)
                        continue;
                    i = 0;
                    row++;
                    col = 1;
                    continue;
                } */

                token = tokens();
                itg = new String(buff).trim();
                System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                Arrays.fill(buff, '\0');
                col = 1;
                row++;
                i = 0;
                continue;

            }
            //знаки в один символ
            else if ((char)ch  == ',' || (char)ch  == ';' || (char)ch  == '+' || (char)ch  == '-' ||
                    (char)ch  == '=' || (char)ch  == '*' || (char)ch  == '/' || (char)ch == '\\' || (char)ch  == '^' ||
                    (char)ch  == '(' || (char)ch  == ')') {

                if (i != 0) {
                    buff[i] = '\0';

                    token = tokens();
                    itg = new String(buff).trim();
                    System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                    Arrays.fill(buff, '\0');
                    i = 0;
                }

                buff[i] = (char)ch;
                i++;
                col++;
                buff[i] = '\0';

                token = tokens();
                itg = new String(buff).trim();
                System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                Arrays.fill(buff, '\0');
                i = 0;
                continue;
            }

            else if ((char)ch == '>') {
                if (i != 0) {
                    buff[i] = '\0';

                    token = tokens();
                    itg = new String(buff).trim();
                    System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                    Arrays.fill(buff, '\0');
                    i = 0;
                }

                buff[i] = (char)ch;
                i++;
                col++;

                if ((char)(ch = fr.read()) == '=') {
                    buff[i] = (char)ch;
                    i++;
                    col++;
                    buff[i] = '\0';

                    token = tokens();
                    itg = new String(buff).trim();
                    System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                    Arrays.fill(buff, '\0');
                    i = 0;
                    continue;
                } else {
                    buff[i] = '\0';

                    token = tokens();
                    itg = new String(buff).trim();
                    System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                    Arrays.fill(buff, '\0');
                    i = 0;

                    if ((char)ch == ' ') {
                        col++;
                        continue;
                    }
                }
            }

            else if ((char)ch == '<') {
                if (i != 0) {
                    buff[i] = '\0';

                    token = tokens();
                    System.out.println(token + " " +  buff + " " + row +  " " + (col-i));
                    Arrays.fill(buff, '\0');
                    i = 0;
                }
                buff[i] = (char)ch;
                i++;
                col++;

                ch = fr.read();

                if (((char)ch == '=') || ((char)ch == '>')) {
                    buff[i] = (char)ch;
                    i++;
                    col++;
                    buff[i] = '\0';

                    token = tokens();
                    System.out.println(token + " " +  buff + " " + row +  " " + (col-i));
                    Arrays.fill(buff, '\0');
                    i = 0;
                    continue;
                } else {
                    buff[i] = '\0';

                    token = tokens();
                    itg = new String(buff).trim();
                    System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
                    Arrays.fill(buff, '\0');
                    i = 0;

                    if ((char)ch == ' ') {
                        col++;
                        continue;
                    }
                }
            }

            buff[i] = (char)ch;
            i++;
            col++;
        }
        if (i != 0) {
            buff[i] = '\0';

            token = tokens();
            itg = new String(buff).trim();
            System.out.println(token + " " +  itg + " " + row +  " " + (col-i));
            Arrays.fill(buff, '\0');
            i = 0;
        }
        System.out.println("EOF" + "" + row +  (col-i));
        return 0;
        }

    }
