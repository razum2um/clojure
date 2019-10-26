package clojure.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class PatternFn extends AFn {
    private final Pattern regex;

    static public PatternFn compile(String regex) { return new PatternFn(regex); }
    static public PatternFn compile(String regex, int flags) { return new PatternFn(regex, flags); }
    static public boolean matches(String regex, CharSequence input) { return Pattern.matches(regex, input); }
    static public String quote(String s) { return Pattern.quote(s); }

    public PatternFn(Pattern p) { regex = p; }
    public PatternFn(String regex) { this.regex = Pattern.compile(regex); }
    public PatternFn(String regex, int flags) { this.regex = Pattern.compile(regex, flags); }

    public int flags() { return regex.flags(); }
    public String pattern() { return regex.pattern(); }
    public Matcher matcher(CharSequence input) { return regex.matcher(input); }
    public String[] split(CharSequence input) { return regex.split(input); }
    public String[] split(CharSequence input, int limit) { return regex.split(input, limit); }
    public String toString() { return regex.toString(); }

    public Pattern regex() { return regex; }
    public Object invoke(Object input) {
        if (!(input instanceof CharSequence)) {
            throw new IllegalArgumentException("#\"" + regex.pattern() +"\" invoked with " + input.getClass().getName() + ", not a CharSequence");
        }
        CharSequence cinput = (CharSequence) input;
        return matcher(cinput).find();
    }
}