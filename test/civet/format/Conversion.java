package format;

class Conversion {
    // Byte, Short, Integer, Long, BigInteger
    // (and associated primitives due to autoboxing)
    static final char DECIMAL_INTEGER     = 'd';
    static final char OCTAL_INTEGER       = 'o';
    static final char HEXADECIMAL_INTEGER = 'x';
    static final char HEXADECIMAL_INTEGER_UPPER = 'X';

    // Float, Double, BigDecimal
    // (and associated primitives due to autoboxing)
    static final char SCIENTIFIC          = 'e';
    static final char SCIENTIFIC_UPPER    = 'E';
    static final char GENERAL             = 'g';
    static final char GENERAL_UPPER       = 'G';
    static final char DECIMAL_FLOAT       = 'f';
    static final char HEXADECIMAL_FLOAT   = 'a';
    static final char HEXADECIMAL_FLOAT_UPPER = 'A';

    // Character, Byte, Short, Integer
    // (and associated primitives due to autoboxing)
    static final char CHARACTER           = 'c';
    static final char CHARACTER_UPPER     = 'C';

    // java.util.Date, java.util.Calendar, long
    static final char DATE_TIME           = 't';
    static final char DATE_TIME_UPPER     = 'T';

    // if (arg.TYPE != boolean) return boolean
    // if (arg != null) return true; else return false;
    static final char BOOLEAN             = 'b';
    static final char BOOLEAN_UPPER       = 'B';
    // if (arg instanceof Formattable) arg.formatTo()
    // else arg.toString();
    static final char STRING              = 's';
    static final char STRING_UPPER        = 'S';
    // arg.hashCode()
    static final char HASHCODE            = 'h';
    static final char HASHCODE_UPPER      = 'H';

    static final char LINE_SEPARATOR      = 'n';
    static final char PERCENT_SIGN        = '%';

    static boolean isValid(char c) {
        return (isGeneral(c) || isInteger(c) || isFloat(c) || isText(c)
                || c == 't' || isCharacter(c));
    }

    // Returns true iff the Conversion is applicable to all objects.
    static boolean isGeneral(char c) {
        switch (c) {
        case BOOLEAN:
        case BOOLEAN_UPPER:
        case STRING:
        case STRING_UPPER:
        case HASHCODE:
        case HASHCODE_UPPER:
            return true;
        default:
            return false;
        }
    }

    // Returns true iff the Conversion is applicable to character.
    static boolean isCharacter(char c) {
        switch (c) {
        case CHARACTER:
        case CHARACTER_UPPER:
            return true;
        default:
            return false;
        }
    }

    // Returns true iff the Conversion is an integer type.
    static boolean isInteger(char c) {
        switch (c) {
        case DECIMAL_INTEGER:
        case OCTAL_INTEGER:
        case HEXADECIMAL_INTEGER:
        case HEXADECIMAL_INTEGER_UPPER:
            return true;
        default:
            return false;
        }
    }

    // Returns true iff the Conversion is a floating-point type.
    static boolean isFloat(char c) {
        switch (c) {
        case SCIENTIFIC:
        case SCIENTIFIC_UPPER:
        case GENERAL:
        case GENERAL_UPPER:
        case DECIMAL_FLOAT:
        case HEXADECIMAL_FLOAT:
        case HEXADECIMAL_FLOAT_UPPER:
            return true;
        default:
            return false;
        }
    }

    // Returns true iff the Conversion does not require an argument
    static boolean isText(char c) {
        switch (c) {
        case LINE_SEPARATOR:
        case PERCENT_SIGN:
            return true;
        default:
            return false;
        }
    }
}