# ZAPL

ZAPL is a program based on the guarded command language based on Edsger
Dijkstra's designs. It is written in Scala 2 using the Scala Parser Combinator
library.

## EBNF

The language takes some designs from languages such as Ruby, Elixir and
smalltalk for a concise yet expressive syntax.

```text
================================================================================
CONSTANT
================================================================================


TRUE              = "true";

FALSE             = "false";

DEF               = "def";

DO                = "do";

END               = "end";

IF                = "if";

WHILE             = "WHILE";

MOD               = "mod";

UPPER_ALPHA       = ? any char between A to Z ?;

LOWER_ALPHA       = ? any char between a to z ?; 

NUMBER            = ? any char between 1 to 9 ?;

ZERO              = "0";

LEFT_PARENTHESIS  = "(";

RIGHT_PARENTHESIS = ")";

LEFT_BRACKET      = "[";

RIGHT_BRACKET     = "]";

DQUOTE            = "\"";

UNDERSCORE        = "_";

FAT_ARROW         = "=>";

COMMA             = ",";

PLUS              = "+";

MULTIPLICATION    = "*";

MINUS             = "-";

DIVISION          = "/";

AND               = "&&";

OR                = "||";

EQEQ              = "==";

NEQ               = "!=";

LT                = "<";

GT                = ">";

LTEQ              = "<=";

GTEQ              = ">=";

NOT               = "!";

BOX               = "::";

EQ                = "=";

================================================================================
TYPE
================================================================================

string  = DQUOTE, ? any char including escaped and whitespace ?, DQUOTE;

boolean = TRUE | FALSE;

list    = LEFT_BRACKET, ( type, { COMMA, type } ) RIGHT_BRACKET;

integer = ( NUMBER | ZERO ), { NUMBER | ZERO  };

================================================================================
EXPRESSION
================================================================================

arith_expression    = arith_factor, {
                        PLUS, arith_expression 
                      | MINUS, arith_expression 
                      | MULTIPLICATION, arith_expression 
                      | DIVISION, arith_expression };

arith_factor        = arith_constant 
                    | LEFT_PARENTHESIS, arith_expression, RIGHT_PARENTHESIS;

arith_constant      = integer | identifier;

logic_expression    = logic_factor, { 
                      AND, logic_expression
                    | OR, logic_expression
                    | EQEQ, logic_expression
                    | NEQ, logic_expression
                    | LT, logic_expression
                    | GT, logic_expression
                    | LTEQ, logic_expression
                    | GTEQ, logic_expression };

logic_factor        = NOT, logic_factor
                    | logic_constant 
                    | LEFT_PARENTHESIS, logic_expression, RIGHT_PARENTHESIS;

logic_constant      = arith_expression | boolean | integer | identifier;

================================================================================
CALL
================================================================================

fn_call_params = { type
               | identifier 
               | fn_call 
               | module_fn_call }

fn_call        = identifier 
               , LEFT_PARENTHESIS
               , fn_call_params
               , RIGHT_PARENTHESIS;

module_fn_call_identifier = module_identifier, { BOX, module_identifier};

module_fn_call            = module_fn_call_identifier, BOX, fn_call;

================================================================================
CONTROL
================================================================================

guard             = logic_expression 

command           = identifier | type | expression | block;

condition         = guard, FAT_ARROW, command;

default_condition = UNDERSCORE, FAT_ARROW, command;

if                = IF, DO, { condition }, default_condition, END;

while             = WHILE, DO, { condition }, default_condition, END;

================================================================================
BLOCK
================================================================================

block             = DO, ( expression | function | control | call ), END;

================================================================================
FUNCTION
================================================================================

function = DEF, identifier, LEFT_PARENTHESIS, { identifier }, RIGHT_PARENTHESIS
         , EQ, ( identifier | type | call | control | expression | block );

================================================================================
MODULE
================================================================================

module_identifier = UPPER_ALPHA, identifier;

module            = MOD, module_identifier, DO, { function }, END;

================================================================================
AUXILIARY
================================================================================

identifier = ( UPPER_ALPHA | LOWER_ALPHA ) 
          , { UPPER_ALPHA | UNDERSCORE | NUMBER | ZERO };

================================================================================
Variable
================================================================================

variable = identifier, EQ, ( type | expression | control | call | block );

================================================================================
TOP LEVEL COMBINATORS
================================================================================

control    = if | while;

expression = logic_expression | arith_expression;

statement  = function | module;

call       = module_fn_call | fn_call;

program    = { statement | expression | control | call };

type       = string | boolean | list | integer;

```
